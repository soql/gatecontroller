package pl.net.oth.gatecontroller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.net.oth.gatecontroller.model.GateEvent;
import pl.net.oth.gatecontroller.repositories.GateEventRepository;
import pl.net.oth.gatecontroller.services.GateEventService;

@Component
public class MqttCallback implements org.eclipse.paho.client.mqttv3.MqttCallback {
	private final static Log LOGGER = LogFactory.getLog(MqttCallback.class);
	
	@Autowired
	private GateEventService gateEventService;

	

	public void connectionLost(Throwable cause) {

	}

	public void messageArrived(String topic, MqttMessage message) throws Exception {

		try {
			LOGGER.info("Odebrano wiadomość MQTT: " + message.toString());
			String threadId = String.valueOf(new Date().getTime());
			LOGGER.info("Tworzę wątek " + threadId);
			
			GateEvent gateEvent = createFromMqttMessage(message.toString(), threadId);
			gateEventService.save(gateEvent);
			new Thread(new CameraThread(gateEvent.getId(), gateEvent.getDirection())).start();

			new Thread(new NotificationThread(gateEvent)).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub

	}

	private GateEvent createFromMqttMessage(String message, String threadId) {
		GateEvent result = new GateEvent();
		result.setId(threadId);
		JSONObject jsonObject = new JSONObject(message.toString());

		try {
			result.setDirection(jsonObject.getString("direction"));
			result.setFull(jsonObject.getString("full").equals("true") ? true : false);
			result.setRevert(jsonObject.getString("revert").equals("true") ? true : false);
			result.setFast(jsonObject.getString("isFast").equals("true") ? true : false);
			result.setTime(Math.abs(jsonObject.getInt("time")));
			result.setSpeed(Math.abs(jsonObject.getDouble("speed")));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
