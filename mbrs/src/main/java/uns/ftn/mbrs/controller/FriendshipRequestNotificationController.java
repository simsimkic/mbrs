package uns.ftn.mbrs.controller;

import java.util.List;

import uns.ftn.mbrs.model.FriendshipRequestNotification;
import uns.ftn.mbrs.service.FriendshipRequestNotificationService;
import uns.ftn.mbrs.support.FriendshipRequestNotificationDTOToFriendshipRequestNotification;
import uns.ftn.mbrs.support.FriendshipRequestNotificationToFriendshipRequestNotificationDTO;
import uns.ftn.mbrs.dto.FriendshipRequestNotificationDTO;

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
@RequestMapping(value="/api/friendshipRequestNotification")
public class FriendshipRequestNotificationController {  

	@Autowired
	private FriendshipRequestNotificationService friendshipRequestNotificationService;
	
	@Autowired
	private FriendshipRequestNotificationToFriendshipRequestNotificationDTO toDTO;
	
	@Autowired
	private FriendshipRequestNotificationDTOToFriendshipRequestNotification toFriendshipRequestNotification;
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<FriendshipRequestNotificationDTO>> getFriendshipRequestNotificationList () {

		List<FriendshipRequestNotification> friendshipRequestNotificationList = friendshipRequestNotificationService.findAll();
	
		return new ResponseEntity<>(toDTO.convert(friendshipRequestNotificationList), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<FriendshipRequestNotificationDTO> getFriendshipRequestNotification(@PathVariable Long id) {
		FriendshipRequestNotification friendshipRequestNotification = friendshipRequestNotificationService.findOne(id);
		if (friendshipRequestNotification == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(friendshipRequestNotification), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<FriendshipRequestNotificationDTO> add(@RequestBody @Valid FriendshipRequestNotificationDTO newFriendshipRequestNotification) {

		FriendshipRequestNotification savedFriendshipRequestNotification = friendshipRequestNotificationService.save(toFriendshipRequestNotification.convert(newFriendshipRequestNotification));

		return new ResponseEntity<>(toDTO.convert(savedFriendshipRequestNotification), HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<FriendshipRequestNotificationDTO> edit(@RequestBody @Valid FriendshipRequestNotificationDTO friendshipRequestNotification, @PathVariable Long id) {

		if (id != friendshipRequestNotification.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		FriendshipRequestNotification persisted = friendshipRequestNotificationService.save(toFriendshipRequestNotification.convert(friendshipRequestNotification));

		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<FriendshipRequestNotificationDTO> delete(@PathVariable Long id) {
		FriendshipRequestNotification deleted = friendshipRequestNotificationService.remove(id);

		return new ResponseEntity<>(toDTO.convert(deleted), HttpStatus.OK);
	}

	@RequestMapping(value = "/filterBymyplugin.generator.fmmodel.FMType@14d9ef25Id/{id}", method = RequestMethod.GET)
	ResponseEntity<List<FriendshipRequestNotificationDTO>> getFriendshipRequestNotificationListBymyplugin.generator.fmmodel.FMType@14d9ef25Id(@PathVariable Long id) {

		List<FriendshipRequestNotification> friendshipRequestNotificationList = friendshipRequestNotificationService.findBymyplugin.generator.fmmodel.FMType@14d9ef25Id(id);
			
		return new ResponseEntity<>(toDTO.convert(friendshipRequestNotificationList), HttpStatus.OK);
	}

}
