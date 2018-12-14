package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import main.Input;
import main.RomanConverter;

public class Input {

	static Map<String, String> questionLine = new HashMap<String, String>();
	static Map<String, String> romanValue = new HashMap<String, String>();
	static Map<String, Float> integerValue = new HashMap<String, Float>();
	static ArrayList<String> missingValues = new ArrayList<String>();
	static Map<String, Float> elementValueList = new HashMap<String, Float>();
	
	public static void Input(String filePath) throws IOException {
		BufferedReader bufferedReader = null;
		if (filePath == null){
			InputStream in = Input.class.getResourceAsStream("Input");
			bufferedReader =new BufferedReader(new InputStreamReader(in));
		} else {
			FileReader fileReader = new FileReader(filePath);
			bufferedReader = new BufferedReader(fileReader);
		}
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			processStringLine(line);
		}
		bufferedReader.close();
	}
	
	public static void processStringLine(String line){
		String arr[] = line.split("((?<=:)|(?=:))|( )");

		if (line.endsWith("?")){
			questionLine.put(line,"");
		}
		else if (arr.length == 3 && arr[1].equalsIgnoreCase("is")){
			romanValue.put(arr[0], arr[arr.length-1]);
		}
		else if(line.toLowerCase().endsWith("credits")){
			missingValues.add(line);
		}
	}
	
	public static void MapTokentoIntegerValue(){

		Iterator it = romanValue.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry token = (Map.Entry)it.next();
			float Value = new RomanConverter().romanConverter(token.getValue().toString());
			integerValue.put(token.getKey().toString(), Value);
		}
	}

}
