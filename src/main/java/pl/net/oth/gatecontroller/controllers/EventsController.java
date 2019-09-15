package pl.net.oth.gatecontroller.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.net.oth.gatecontroller.model.GateEvent;
import pl.net.oth.gatecontroller.services.GateEventService;

@RestController(value = "/rest")
public class EventsController {
	
	@Autowired
	private GateEventService gateEventService;
			
		@RequestMapping(value ="/rest/list", method = RequestMethod.GET, produces = "application/json")
	    public List<GateEvent> index(@RequestParam int number) {
	        return gateEventService.getSortedByDate(number);
	    }
}
