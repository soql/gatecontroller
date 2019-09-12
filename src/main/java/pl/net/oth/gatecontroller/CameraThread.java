package pl.net.oth.gatecontroller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Paths;


public class CameraThread implements Runnable{
	public final static String MAINDIR="c://TEMP";
	private String id;
	public CameraThread(String id) {
		this.id=id;
	}
	public void run() {		
		try {
			Files.createDirectories(Paths.get(MAINDIR+"//"+id));
		
		
			System.out.println(id+": RUNNING");
			for(int i=0; i<5; i++) {
				System.out.println(id+": Taking image "+i);
				saveImage("http://10.4.0.80:8765/picture/1/current/", MAINDIR+"//"+id+"//"+i+".jpg");				
			}
			
			System.out.println(id+": STOP");
		} catch (IOException e) {			
			e.printStackTrace();
		}	
	}
	
	public static void saveImage(String imageUrl, String destinationFile) throws IOException {
	    URL url = new URL(imageUrl);
	    InputStream is = url.openStream();
	    OutputStream os = new FileOutputStream(destinationFile);

	    byte[] b = new byte[2048];
	    int length;

	    while ((length = is.read(b)) != -1) {
	        os.write(b, 0, length);
	    }

	    is.close();
	    os.close();
	}
	
	public static void sleep(long ms) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
