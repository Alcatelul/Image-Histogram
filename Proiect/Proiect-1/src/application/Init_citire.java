package application;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


public class Init_citire extends Citire_abs {
	
	int width=0,height=0;
	int paddingBytes=0;
	public  void f_Init_citire(String cale) {
		try {
			byte [] informatii=new byte[40];
			byte [] header=new byte[14];
			File imagine=null;
			
			//citirea informatiilor importante din imagine
			long startTime = System.currentTimeMillis();
			imagine=new File(cale);
			System.out.println("Fisierul a fost deschis!");
			InputStream input= new FileInputStream(imagine);
			input.read(header,0,header.length);
			input.read(informatii,0,informatii.length);
			// Citire height
			height = (((((((informatii[11] & 0xFF) << 8) | (informatii[10]& 0xFF)) << 8)| (informatii[9]& 0xFF)) << 8) | (informatii[8]& 0xFF));
			//Citire width
			width = (((((((informatii[7] & 0xFF) << 8) | (informatii[6]& 0xFF)) << 8)| (informatii[5]& 0xFF)) << 8) | (informatii[4]& 0xFF));
			paddingBytes=(byte) (width%4);
			
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			System.out.println("Timp citire header si informatii imagine:"+elapsedTime+" ms");
			input.close();
			} catch (FileNotFoundException e) {
				System.out.println("Nu s-a putut creea InputStream!");
			} catch (IOException e) {
				System.out.println("Nu s-a putut citi din stream!");
			}
	}		
}
