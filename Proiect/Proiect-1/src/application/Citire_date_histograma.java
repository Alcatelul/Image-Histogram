package application;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Citire_date_histograma {
	public  int[]	f_Citire_date_histograma(int a,int b,int c,String str) {
		
		File imagine=null;
		int[] histogram = new int[257];
		int width=a;
		int height=b;
		int paddingBytes=c;
		int R=0;
		int G=0;
		int B=0;
		int S=0;
		
		imagine=new File(str);
		try {
			InputStream input= new FileInputStream(imagine);
			//Citire date din imagine pentru vectorul necesar histogramei
			for(int i=0;i<256;i++)
			histogram[i]=0;
			for(int i=0;i<width;i++) {
				for(int j=0;j<height;j++) {
					B=input.read()+1;
					G=input.read()+1;
					R=input.read()+1;
					S=(B+G+R)/3;
					B=S;
					G=S;
					R=S;
					histogram[S]++;
				}
				for(int k=0;k<paddingBytes;k++)
					input.read();
			}
			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("Nu s-a putut citi datele pentru histograma!");
		} catch (IOException e) {
			System.out.println("Nu s-a putut citi din fisier!");
		}
		return histogram;
	}
}
