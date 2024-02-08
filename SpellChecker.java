
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
			if (str.length() == 0) {
				return "";
			}
	
			return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		// Your code goes here
		String w1 = word1.toLowerCase();
		String w2 = word2.toLowerCase();
		int l1 = w1.length();
		int l2 = w2.length();
		int cost = 0;
		if (l1 == 0)
			return l2;
		else if (l2 == 0)
			return l1;
		else if (w1.charAt(0) == w2.charAt(0)) 
			cost = levenshtein(tail(w1), tail(w2));
		else 
			cost = 1 + Math.min(Math.min(levenshtein(tail(w1), w2), levenshtein(w1, tail(w2))), levenshtein(tail(w1), tail(w2)));
			
			
		return cost;
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for (int i = 0; i < dictionary.length; i++) {
			dictionary[i] = in.readString();
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		// Your code goes here
		int minimum = 100000;
		int mini = 1;
		String word1 = word;
		for (int i = 0; i < dictionary.length; i++){
			mini = levenshtein(word, dictionary[i]);
			if (mini < minimum){
				minimum = mini;
				word1 = dictionary[i];
			}	
		}
		if (minimum <= threshold)
			return word1;
		return word;	
	}
}
