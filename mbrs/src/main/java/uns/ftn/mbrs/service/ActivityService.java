package uns.ftn.mbrs.service;

import uns.ftn.mbrs.model.Activity;

import java.util.List;
import java.util.Date;
import uns.ftn.mbrs.model.*;

public interface ActivityService{

	
	Activity findOne(Long id); 
	
	Activity save(Activity activity);
	
	List<Activity> findAll();
	Activity remove(Long id);
	

}