package utility;

public class StringUtility {

	public static boolean isAlphanumeric(String sentence) {
		for (char c : sentence.toCharArray()) {
			if(!Character.isLetterOrDigit(c)) {
				return false;
			}
		}
		return true;
	}	
}
