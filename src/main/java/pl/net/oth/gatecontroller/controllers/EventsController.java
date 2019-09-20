package pl.net.oth.gatecontroller.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pl.net.oth.gatecontroller.CameraThread;
import pl.net.oth.gatecontroller.Main;
import pl.net.oth.gatecontroller.Utils;
import pl.net.oth.gatecontroller.model.GateEvent;
import pl.net.oth.gatecontroller.model.GateEventDAO;
import pl.net.oth.gatecontroller.services.GateEventService;

@RestController(value = "/rest")
public class EventsController {
	private final static Log LOGGER = LogFactory.getLog(EventsController.class);
	@Autowired
	private GateEventService gateEventService;

	@CrossOrigin
	@RequestMapping(value = "/rest/list", method = RequestMethod.GET, produces = "application/json")
	public TreeMap<String, List<GateEventDAO>> index(@RequestParam int number) {
		return gateEventService.getSortedByDate(number).stream().filter(element -> Utils.pathExist(element.getId())).map(element -> new GateEventDAO(element))
				.collect(Collectors.groupingBy(e -> Utils.getDateFromEvent(e),TreeMap::new, Collectors.toList()));		 
	}


	@CrossOrigin
	@RequestMapping(value = "/rest/foto", method = RequestMethod.GET, produces = "application/json")
		public @ResponseBody List<String> getLastFoto(@RequestParam String id) {
			String folder = CameraThread.MAINDIR+"/"+id;
				
			try (Stream<Path> walk = Files.walk(Paths.get(folder))) {

				List<String> result = walk.map(x -> x.toString())
						.filter(f -> f.endsWith(".jpg")).collect(Collectors.toList());

				return result.stream().map(foto -> getImageAsFile(foto)).collect(Collectors.toList());

			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ArrayList<String>();
	}
	private byte[] getFullPhoto(BufferedImage image) {
		ByteArrayOutputStream imagebuffer = null;
		try {
			imagebuffer = new ByteArrayOutputStream();
			ImageIO.write(image, "jpg", imagebuffer);

			byte[] toReturn = imagebuffer.toByteArray();
			imagebuffer.flush();
			imagebuffer.close();
			return toReturn;
		} catch (IOException e) {
			LOGGER.error("Error", e);
		}
		return null;
	}
	private String convertToBase64(byte[] image) {
		return "data:image/png;base64," + DatatypeConverter.printBase64Binary(image);
	}
	private String getImageAsFile(String path) {
		
		try {
			BufferedImage image = ImageIO.read(new File(path));
			return convertToBase64(getFullPhoto(image));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
