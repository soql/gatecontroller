package pl.net.oth.gatecontroller;

import pl.net.oth.gatecontroller.model.GateEvent;

public class NotificationThread implements Runnable {
	
	private GateEvent gateEvent;
	public NotificationThread(GateEvent gateEvent) {
		this.gateEvent=gateEvent;
	}

	public void run() {
		String descr=generateDirectionDescr(gateEvent);
		if(descr!=null)
			MyPushOverClient.send(descr, String.valueOf(gateEvent.getSpeed()), String.valueOf(gateEvent.getTime()));
				
	}
	public String generateDirectionDescr(GateEvent gateEvent) {
		if(gateEvent.getDirection().equals("IN") && gateEvent.getFull()) {
			return "całkowity wjazd";
		}
		if(gateEvent.getDirection().equals("OUT") && gateEvent.getFull()) {
			return "całkowity wyjazd";
		}
		if(gateEvent.getDirection().equals("IN") && gateEvent.getRevert()) {
			return "wycofanie z wjazdu";
		}
		if(gateEvent.getDirection().equals("OUT") && gateEvent.getRevert()) {
			return "wycofanie z wyjazdu";
		}
		return null;
		
	}

}
