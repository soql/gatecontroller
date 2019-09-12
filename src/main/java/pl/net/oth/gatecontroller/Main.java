package pl.net.oth.gatecontroller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("pl.net.oth.gatecontroller")
public class Main {
	private final static Log LOGGER = LogFactory.getLog(Main.class);
	
	@Autowired
	private MqttCallback mqttCallback;
	
	public static void main(String[] args) {
		 SpringApplication.run(Main.class, args);
	}
	public Main() {
		MqttClient client;				
		LOGGER.info("Application started");
		try {				
			client = new MqttClient("tcp://10.4.0.82:1883", MqttClient.generateClientId());
			MqttConnectOptions options = new MqttConnectOptions();
			options.setAutomaticReconnect(true);
			options.setCleanSession(true);
			options.setConnectionTimeout(10);			
			client.connect(options);
			
			client.subscribe("telemetry/gate", 
					(topic, msg)-> {
						mqttCallback.messageArrived(topic, msg);
						});				
		} catch (MqttException e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();		
		}
	
	}
}
