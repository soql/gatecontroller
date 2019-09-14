package pl.net.oth.gatecontroller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.FormBodyPart;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.web.multipart.MultipartRequest;

import pl.net.oth.gatecontroller.model.GateEvent;

public class MyPushOverClient {
	public static final ContentType TEXT_PLAIN = ContentType.create("text/plain", Consts.UTF_8);
	
	public static void send(String descr, String speed, String time) {
		try {
			
			
			
			String url = "https://api.pushover.net/1/messages.json";
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost request = new HttpPost(url);

			// add request header
			request.addHeader("User-Agent", "JAVA");

			HttpEntity data = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
					.addTextBody("token", "ao7ybsrfjvex7ktbd6vm23tau4muf1")
					.addTextBody("user", "u3gp6k2aok9urodeyg6zmjdbqzgnvi")
					.addTextBody("title", "Aktywność zalesie",ContentType.TEXT_PLAIN.withCharset("UTF-8"))
					.addTextBody("message", descr+"\nprędkość: "+speed+"km/h\nczas: "+time+"ms", ContentType.TEXT_PLAIN.withCharset("UTF-8")).build();

			request.setEntity(data);

			HttpResponse response = client.execute(request);
			System.out.println(response);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
