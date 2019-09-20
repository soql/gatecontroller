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
	@Query(nativeQuery = true, value ="SELECT * FROM gate_event  WHERE (FULL=1 OR REVERT=1) AND ID>UNIX_TIMESTAMP(DATE_SUB(DATE_FORMAT(now(), \"%Y-%m-%d\"), INTERVAL :number DAY))*1000 ORDER BY ID DESC")
	List<GateEvent> getSortedByDate(@Param("number") int number);
}
