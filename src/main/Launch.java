package main;

import main.Input;
import main.Output;

public class Launch{

	public static void main(String[] args) {
		String filePath = null;
		if (args.length != 0)
			filePath = args[0];
		try{
			Input.inputFile(filePath);
			Input.mapTokentoIntegerValue();
			Output.processReplyForQuestion();
		}
		catch(Exception e){
			System.out.println("File Not Found");
		}
	}

}
