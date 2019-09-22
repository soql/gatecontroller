package pl.net.oth.gatecontroller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CameraThread implements Runnable{
	public final static String MAINDIR="/records/camera";
	public SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String id;
	private String direction;
	public CameraThread(String id, String direction) {
		this.id=id;
		this.direction=direction;
	}
	public void run() {		
		try {
			Files.createDirectories(Paths.get(MAINDIR+"//"+id));
		
			int MAX_PHOTOS="OUT".equals(direction)?3:8;
			System.out.println(id+": RUNNING");
			for(int i=0; i<MAX_PHOTOS; i++) {
				System.out.println(id+": Taking image nr "+i+" ("+sdf.format(new Date())+")");				
				saveImage("http://10.4.0.80:8765/picture/1/current/", MAINDIR+"//"+id+"//"+i+".jpg");
				if("IN".equals(direction))
					sleep(1500);
				else
					sleep(100);
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
