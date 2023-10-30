import java.util.Arrays;


/**
 * Autocomplete.
 */
public class Autocomplete {

   private Term[] terms;

	/**
	 * Initializes a data structure from the given array of terms.
	 * This method throws a NullPointerException if terms is null.
	 */
   public Autocomplete(Term[] terms) {
      if (terms == null) {
         throw new NullPointerException("Terms is null");
      }
      this.terms = Arrays.copyOf(terms,terms.length);
      Arrays.sort(this.terms);
   }

	/** 
	 * Returns all terms that start with the given prefix, in descending order of weight. 
	 * This method throws a NullPointerException if prefix is null.
	 */
   public Term[] allMatches(String prefix) {
   
      if (prefix == null) {
         throw new NullPointerException("Prefix is null");
      }
      
      Term tPrefix = new Term(prefix, 0);
      int sIndex = BinarySearch.firstIndexOf(terms, tPrefix, tPrefix.byPrefixOrder(prefix.length()));
      int eIndex = BinarySearch.lastIndexOf(terms, tPrefix, tPrefix.byPrefixOrder(prefix.length()));
      
      if (sIndex == -1 || eIndex == -1) {
         return new Term[0];
      }
      
      Term[] matches = Arrays.copyOfRange(terms, sIndex, eIndex + 1);
      
      Arrays.sort(matches, Term.byDescendingWeightOrder());
      
      return matches;
   }

}

