package main;

import java.util.HashMap;
import java.util.Map;

public class RomanConverter extends Rules{

	
	public float romanConverter(java.lang.String romanNumber) {

		float decimal = 0;
		float lastNumber = 0;
		char[] romanNumeral = romanNumber.toUpperCase().toCharArray();

		//Operation to be performed on upper cases even if user enters Roman values in lower case chars
		for (int x = romanNumeral.length- 1; x >= 0 ; x--) {
			Character convertToDecimal = romanNumeral[x];
			switch (convertToDecimal) {
				case 'I':
					super.checkValidity(convertToDecimal);
					decimal = processDecimal(1, lastNumber, decimal);
					lastNumber = 1;
					break;
				case 'V':
					super.checkValidity(convertToDecimal);
					decimal = processDecimal(5, lastNumber, decimal);
					lastNumber = 5;
					break;
				case 'X':
					super.checkValidity(convertToDecimal);
					decimal = processDecimal(10, lastNumber, decimal);
					lastNumber = 10;
					break;
				case 'L':
					super.checkValidity(convertToDecimal);
					decimal = processDecimal(50, lastNumber, decimal);
					lastNumber = 50;
					break;
				case 'C':
					super.checkValidity(convertToDecimal);
					decimal = processDecimal(100, lastNumber, decimal);
					lastNumber = 100;
					break;
				case 'D':
					super.checkValidity(convertToDecimal);
					decimal = processDecimal(500, lastNumber, decimal);
					lastNumber = 500;
					break;
				case 'M':
					super.checkValidity(convertToDecimal);
					decimal = processDecimal(1000, lastNumber, decimal);
					lastNumber = 1000;
					break;
			}
			}
		super.resetLiteralsCounter();
		return decimal;
	}


	public float processDecimal(float decimal, float lastNumber, float lastDecimal) {
		if (lastNumber > decimal) {
			return super.subtractionLogic(lastDecimal, decimal, lastNumber);
		}
		else {
			return lastDecimal + decimal;
		}
	}
}

