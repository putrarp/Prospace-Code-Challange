package main;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Rules {

	private static final Character[] NonRepeatingRoman = {'D', 'L', 'V'};
	private static final Character[] RepeatingRoman = {'I','V','X','M'};
	private static Map<Character,Integer> RepeatableCount = getRepeatableCount();

	private static Map<Character,Integer> getRepeatableCount(){
		Map<Character,Integer>map = new HashMap<Character,Integer>() {
			{
				put('I', 0);
				put('X', 0);
				put('C', 0);
				put('M', 0);
			}
		};
		return map;
	}

	private static Map<Character,Integer> NonRepeatableCount = getNonRepeatableCount();

	private static Map<Character,Integer> getNonRepeatableCount(){
		Map<Character,Integer>map = new HashMap<Character,Integer>() {
			{
				put('V', 0);
				put('L', 0);
				put('D', 0);
			}
		};
		return map;
	}

	private static Map<Integer, Integer[]> ROMAN_SUBTRACTABLE_MAPPING = Collections.unmodifiableMap(
			new HashMap<Integer, Integer[]>() {
				{
					put(1, new Integer[] {5, 10});
					put(5, new Integer[] {});
					put(10, new Integer[] {50,100});
					put(50, new Integer[] {});
					put(100, new Integer[] {100,1000});
					put(500, new Integer[] {});
					put(1000, new Integer[] {});
				}
			});

	private static Map<Character, Integer> ROMAN_TO_NUMERIC_MAPPING = Collections.unmodifiableMap(
			new HashMap<Character, Integer>() {
				{
					put('I', 1);
					put('V', 5);
					put('X', 10);
					put('L', 50);
					put('C', 100);
					put('D', 500);
					put('M', 1000);
				}
			});

	public static void checkValidity(Character CurrentLiteral){
		if(checkLitereal(NonRepeatingRoman, CurrentLiteral)){
			NonRepeatableCount.put(CurrentLiteral, NonRepeatableCount.get(CurrentLiteral) + 1);
			if(NonRepeatableCount.containsValue(3)){
				System.err.println("Error : Roman Numeral V,L,D cannot be repeated.");	
				System.exit(0);
			}
		}
		else if(checkLitereal(RepeatingRoman, CurrentLiteral)){
			Character keyForValueContainingThree = getKeyForValueContainingThree();
			if(keyForValueContainingThree != '\0'){
				if (CurrentLiteral.equals(keyForValueContainingThree)){
					System.err.println("Error : Roman Numeral "+CurrentLiteral+" cannot repeat 4 times successively");
					System.exit(0);
				}
				else if(currentSmallerThanPrevious(CurrentLiteral, keyForValueContainingThree)) {
					RepeatableCount.put(CurrentLiteral, RepeatableCount.get(CurrentLiteral) +1);
					RepeatableCount.put(keyForValueContainingThree, 0);
				}
			}
			else{
				RepeatableCount.put(CurrentLiteral, RepeatableCount.get(CurrentLiteral) +1);
			}
		}
	}

	private static char getKeyForValueContainingThree(){
		char key = '\0';
		Iterator<Map.Entry<Character,Integer>> iter = RepeatableCount.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<Character,Integer> entry = iter.next();
			if (entry.getValue().equals(3)) {
				return Character.valueOf(entry.getKey());
			}
		}
		return key;
	}

	private static boolean currentSmallerThanPrevious(char CurrentLiteral, char keyForValueContainingThree){
		if (ROMAN_TO_NUMERIC_MAPPING.get(CurrentLiteral)> ROMAN_TO_NUMERIC_MAPPING.get(keyForValueContainingThree)){
			System.err.println("Error : Should have been a lesser Roman Numeral next instead of "+CurrentLiteral);
			System.exit(0);
			return false;
		}
		else{
			return true;
		}
	}

	public static float subtractionLogic(Float lastDecimal, Float decimal, Float lastNumber){
		if(Arrays.asList(ROMAN_SUBTRACTABLE_MAPPING.get(Math.round(decimal))).contains(Math.round(lastNumber))){
			return lastDecimal - decimal;
		}
		else
			return lastDecimal + decimal;
	}

	public static boolean checkLitereal(Character[] array, Character literal){
		boolean result = false;
		for (int i = 0; i < array.length; i++) {
			if(array[i].equals(literal))
				result =  true;
		}
		return result;
	}

	public static void resetLiteralsCounter(){
		RepeatableCount = getRepeatableCount();
		NonRepeatableCount = getNonRepeatableCount();

	}
}

