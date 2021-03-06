package uns.ftn.mbrs.controller;

import java.util.List;
import java.util.Date;
import uns.ftn.mbrs.model.*;


import uns.ftn.mbrs.model.FriendshipRequest;
import uns.ftn.mbrs.service.FriendshipRequestService;
import uns.ftn.mbrs.converter.FriendshipRequestDTOToFriendshipRequest;
import uns.ftn.mbrs.converter.FriendshipRequestToFriendshipRequestDTO;
import uns.ftn.mbrs.dto.FriendshipRequestDTO;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/friendshipRequest")
public class FriendshipRequestController {  

	@Autowired
	private FriendshipRequestService friendshipRequestService;
	
	@Autowired
	private FriendshipRequestToFriendshipRequestDTO toDTO;
	
	@Autowired
	private FriendshipRequestDTOToFriendshipRequest toFriendshipRequest;
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<FriendshipRequestDTO>> getFriendshipRequestList () {

		List<FriendshipRequest> friendshipRequestList = friendshipRequestService.findAll();
	
		return new ResponseEntity<>(toDTO.convert(friendshipRequestList), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<FriendshipRequestDTO> getFriendshipRequest(@PathVariable Long id) {
		FriendshipRequest friendshipRequest = friendshipRequestService.findOne(id);
		if (friendshipRequest == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(friendshipRequest), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<FriendshipRequestDTO> add(@RequestBody @Valid FriendshipRequestDTO newFriendshipRequest) {

		FriendshipRequest savedFriendshipRequest = friendshipRequestService.save(toFriendshipRequest.convert(newFriendshipRequest));

		return new ResponseEntity<>(toDTO.convert(savedFriendshipRequest), HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<FriendshipRequestDTO> delete(@PathVariable Long id) {
		FriendshipRequest deleted = friendshipRequestService.remove(id);

		return new ResponseEntity<>(toDTO.convert(deleted), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/filterByFriendshipRequestorId/{id}", method = RequestMethod.GET)
	ResponseEntity<List<FriendshipRequestDTO>> getFriendshipRequestListByFriendshipRequestorId(@PathVariable Long id) {

		List<FriendshipRequest> friendshipRequestList = friendshipRequestService.findByFriendshipRequestor(id);
			
		return new ResponseEntity<>(toDTO.convert(friendshipRequestList), HttpStatus.OK);
	}

	@RequestMapping(value = "/filterByFriendshipRequesteeId/{id}", method = RequestMethod.GET)
	ResponseEntity<List<FriendshipRequestDTO>> getFriendshipRequestListByFriendshipRequesteeId(@PathVariable Long id) {

		List<FriendshipRequest> friendshipRequestList = friendshipRequestService.findByFriendshipRequestee(id);
			
		return new ResponseEntity<>(toDTO.convert(friendshipRequestList), HttpStatus.OK);
	}

}
