package pl.net.oth.gatecontroller;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import pl.net.oth.gatecontroller.model.GateEvent;
import pl.net.oth.gatecontroller.model.GateEventDAO;

public class Utils {
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public static String getDateFromEvent(GateEventDAO gateEvent) {
		GregorianCalendar calendar = new GregorianCalendar(TimeZone.getTimeZone("Europe/Warsaw"));
		calendar.setTimeInMillis(Long.parseLong(gateEvent.getId()));		
		return sdf.format(calendar.getTime());
	}
}
