/**
* Jin Chang
* August 4, 2017
* TA: Miri Hyman
* Assignment 5: Anagrams.java
*
* An Anagram creates the maximum number of combinations of words that are
* contained within the desired phrase the client chooses. Anagram uses a
* dictionary to print all the anagram words of the desired phrase the client
* selected.The client has the option to limit the amount of words to print
* for each combination of words found.
*/
import java.util.*;

public class Anagrams {
   // Inventory of pre-processed dictionary words.
   private Map<String, LetterInventory> wordStorage;
   // The original dictionary in dictionary order.
   private List<String> dictionary;
   
   /**
   * post: Constructs a list of words from the given dictionary in
   *       dictionary order. It also constructs a new inventory of letter
   *       occurrences for each word within the dictionary.
   */
   public Anagrams(List<String> dictionary) {
      this.dictionary = dictionary;
      wordStorage = new HashMap<String, LetterInventory>();
      for(String word: dictionary) {
         wordStorage.put(word, new LetterInventory(word));
      }
   }
   
   /**
   * pre: If max is negative or less than zero then, it will throw an
   *      IllegalArgumentException.
   * post: Prints all the possible anagrams of the given phrase. With the
   *       given max the amount of words per anagram combination can be
   *       limited.
   */
   public void print(String text, int max) {
      if(max < 0) {
         throw new IllegalArgumentException();
      }
      LetterInventory txtLetters = new LetterInventory(text);
      Stack<String> output = new Stack<String>();
      // Prints all anagrams of the phrase with the pruned dictionary.
      print(max, txtLetters, pruneDict(txtLetters), output);
   }
   
   /**
   * pre: Assumes that the phrase is not null.
   * post: Each word from the given dictionary is used to create all the
   *       possible anagrams for the given phrase. Depending on the given
   *       max limit of words, each anagram within the given output storage
   *       will be printed out.
   */
   private void print(int max, LetterInventory phrase, List<String> dict, Stack<String> out) {
      if(phrase.isEmpty() && out.size() <= max || phrase.isEmpty() && max == 0) {
         System.out.println(out.toString());
      } else {
         for(String word : dict) {
            if(contains(wordStorage.get(word), phrase)) {
               // Selects a word and subtracts it from the phrase.
               out.push(word);
               // Explore other choices while subtracting the letters used.
               print(max, phrase.subtract(wordStorage.get(word)), dict, out);
               // unselect the word.
               out.pop();
            }
         }
      }
   }
   
   /**
   * Checks that the given word from the dictionary is found within the given
   * phrase. If it is found inside the phrase will return true. Otherwise,
   * it will return false.
   */
   private boolean contains(LetterInventory word, LetterInventory phrase) {
      if(phrase.subtract(word) == null) {
         return false;
      }
      return true;
   }
   
   /**
   * Creates and returns a smaller dictionary containing all the words that
   * are found within the given phrase.
   */
   private List<String> pruneDict(LetterInventory phrase) {
      List<String> smallDict = new ArrayList<String>();
      // Creates and checks each word with the text to see if theres a match.
      for(String word: dictionary) {
         if(contains(wordStorage.get(word), phrase)) {
            smallDict.add(word);
         }
      }
      return smallDict;
   }
}