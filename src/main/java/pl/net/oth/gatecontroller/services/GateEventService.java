package pl.net.oth.gatecontroller.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.net.oth.gatecontroller.model.GateEvent;
import pl.net.oth.gatecontroller.repositories.GateEventRepository;

@Service
public class GateEventService {
	@Autowired
	private GateEventRepository gateEventRepository;
	
	public void save(GateEvent gateEvent) {
		gateEventRepository.save(gateEvent);
	}
}
