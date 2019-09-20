package pl.net.oth.gatecontroller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.stream.Stream;

import org.apache.tomcat.jni.File;

import pl.net.oth.gatecontroller.model.GateEvent;
import pl.net.oth.gatecontroller.model.GateEventDAO;

public class Utils {
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public static String getDateFromEvent(GateEventDAO gateEvent) {
		GregorianCalendar calendar = new GregorianCalendar(TimeZone.getTimeZone("Europe/Warsaw"));
		calendar.setTimeInMillis(Long.parseLong(gateEvent.getId()));		
		return sdf.format(calendar.getTime());
	}

	public static boolean pathExist(String id) {
		String folder = CameraThread.MAINDIR+"/"+id;
		
		return Files.exists(Paths.get(folder));
 
	}
}
