package pl.net.oth.gatecontroller;

import java.net.SocketTimeoutException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories("pl.net.oth.gatecontroller")
@EnableAutoConfiguration
@EnableScheduling
public class Main {
	private final static Log LOGGER = LogFactory.getLog(Main.class);

	/*@Autowired
	private MqttCallback mqttCallback;*/

	//private MqttClient client;

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	public Main(String[] args) {
		LOGGER.info("Application restarted");
	/*	String address = args.length > 0 ? args[0] : "tcp://10.4.0.82:1883";
		try {

			connect(address);		
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
			CameraThread.sleep(1000);
			main(args);
		}*/		
	}

/*	public void connect(String address) {
		try {			
			client = new MqttClient(address, MqttClient.generateClientId(), new MqttDefaultFilePersistence(System.getProperty("java.io.tmpdir")));
			MqttConnectOptions options = new MqttConnectOptions();
			
			options.setAutomaticReconnect(true);
			options.setCleanSession(true);
			options.setConnectionTimeout(10);
			options.setKeepAliveInterval(120);
			client.connect(options);
			client.subscribe("telemetry/gate", (topic, msg) -> {
				mqttCallback.messageArrived(topic, msg);
			});		
			LOGGER.info("Client connected. Tmp folder: "+System.getProperty("java.io.tmpdir"));
		} catch (Exception e) {		
			System.out.println("ERROR CALLBACK");
			e.printStackTrace();
			CameraThread.sleep(1000);
			connect(address);
		}

	}*/

	/*public MqttClient getClient() {
		return client;
	}*/
	
}
