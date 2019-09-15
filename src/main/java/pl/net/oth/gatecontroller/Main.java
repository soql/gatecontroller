package pl.net.oth.gatecontroller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("pl.net.oth.gatecontroller")
@EnableAutoConfiguration
public class Main {
	private final static Log LOGGER = LogFactory.getLog(Main.class);
	
	@Autowired
	private MqttCallback mqttCallback;
	
	private MqttClient client;
	public static void main(String[] args) {
		 SpringApplication.run(Main.class, args);
	}
	public Main() {
						
		LOGGER.info("Application started");
		try {				
			
			connect();
			client.subscribe("telemetry/gate", 
					(topic, msg)-> {
						mqttCallback.messageArrived(topic, msg);
						});	
			client.setCallback(new MqttCallback() {
				
				@Override
				public void messageArrived(String topic, MqttMessage message) throws Exception {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void deliveryComplete(IMqttDeliveryToken token) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void connectionLost(Throwable cause) {
					try {
						connect();
					} catch (MqttException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
			//client.set
		} catch (MqttException e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();		
		}
	
	}
	public void connect() throws MqttException {
		client = new MqttClient("tcp://10.4.0.82:1883", MqttClient.generateClientId());
		MqttConnectOptions options = new MqttConnectOptions();
		options.setAutomaticReconnect(true);
		options.setCleanSession(true);
		options.setConnectionTimeout(10);			
		client.connect(options);
	}
}
