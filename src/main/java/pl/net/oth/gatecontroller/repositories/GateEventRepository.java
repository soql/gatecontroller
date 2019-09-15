package pl.net.oth.gatecontroller.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import pl.net.oth.gatecontroller.model.GateEvent;

@Repository
public interface GateEventRepository extends CrudRepository<GateEvent, String>{
	@Query(nativeQuery = true, value ="SELECT * FROM gate_event ORDER BY TIME ASC LIMIT :number")
	List<GateEvent> getSortedByDate(@Param("number") int number);
}
