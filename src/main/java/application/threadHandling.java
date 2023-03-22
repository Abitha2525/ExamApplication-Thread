package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class threadHandling extends Thread{
	Appliers applier;
	public threadHandling(Appliers applier) {
		this.applier = applier;
	}

	public void run() {
		threadAppliers();
	}
	
	public synchronized void threadAppliers() {
		try {
	ArrayList<String> list = new ArrayList<>();
	
	File file = new File("/home/abitha-zstk276/GroupExamApplications/src/index.txt");
	
	FileReader fileReader = new FileReader(file);
	BufferedReader buffer = new BufferedReader(fileReader);
	String str5 = "";
	while((str5 = buffer.readLine()) != null) {
		list.add(str5);
		
	   }


   FileWriter writer = new FileWriter (file);
   BufferedWriter bufferWriter1 = new BufferedWriter(writer);

   for(int i = 0; i < list.size(); i++) {
	bufferWriter1.write(list.get(i));
    bufferWriter1.newLine();
 	}
   System.out.println(applier.aadhar);
   bufferWriter1.write(applier.aadhar);
   bufferWriter1.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}

class Appliers{
	String aadhar;
	public Appliers(String aadhar){
		this.aadhar = aadhar;
	}
}
