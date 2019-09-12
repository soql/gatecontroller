package pl.net.oth.gatecontroller.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import pl.net.oth.gatecontroller.model.GateEvent;

@Repository
public interface GateEventRepository extends CrudRepository<GateEvent, String>{

}
