package uns.ftn.mbrs.dto;

import java.util.Date;

import java.util.ArrayList; 
import uns.ftn.mbrs.dto.UserDTO;

public class LikeReactionDTO{
	private Long id;
	     private UserDTO  user;
		
	
	
	
	public LikeReactionDTO(){}
	public LikeReactionDTO(Long id, UserDTO  user){
		this.id = id;
			this.user  = user ;
		}
	public LikeReactionDTO(UserDTO  user ){
			this.user  = user ;
		}
	
	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	    public UserDTO getUser (){
	           return user;
	    }
	      
	    public void setUser(UserDTO  User){
	           this.user = user;
	    }
	    
	      
	     

}