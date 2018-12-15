package main;

import main.Input;

public class Launch{

	/**
	 * If no argument is provided then the input file present inside main.com.app.logic package is
	 * picked up as input file by default.
	 * @param args
	 */
	public static void main(String[] args) {
		String filePath = null;
		if (args.length != 0)
			filePath = args[0];
		try{
			Input.inputFile(filePath);
			Input.mapTokentoIntegerValue();
		}
		catch(Exception e){
			System.out.println("File Not Found");
		}
	}

}
