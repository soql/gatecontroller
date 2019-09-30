package pl.net.oth.gatecontroller;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class KeepAlive {
	@Autowired
	private Main main;
	public SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Scheduled(fixedRate = 30000)
	private void sendToMqtt() {
/*		  MqttMessage msg = new MqttMessage();
	        msg.setQos(0);
	        msg.setRetained(true);
	        msg.setPayload(("{'time':'"+sdf.format(new Date())+"'}").getBytes());
		try {
			main.getClient().publish("telemetry/gate/keepalive", msg);
		} catch (MqttException e) {
			e.printStackTrace();
		}*/
	}
}
