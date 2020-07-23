package uns.ftn.mbrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uns.ftn.mbrs.model.ActivityRequestNotification;


@Repository
public interface ActivityRequestNotificationRepository extends JpaRepository<ActivityRequestNotification, Long> {

	List<ActivityRequestNotification> findByActivityRequestId(Long id);

}