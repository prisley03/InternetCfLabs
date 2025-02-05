package utility;

public class StringUtility {

	//This function ensures a string does not contain symbols, etc
	public static boolean isAlphanumeric(String sentence) {
		for (char c : sentence.toCharArray()) {
			if(!Character.isLetterOrDigit(c)) {
				return false;
			}
		}
		return true;
	}	
	
	public static boolean isNumeric(String sentence) {
		for(char c : sentence.toCharArray()) {
			if(!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}
}
