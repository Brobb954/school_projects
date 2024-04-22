import java.util.Comparator;

/**
 * Autocomplete term representing a (query, weight) pair.
 * 
 */
public class Term implements Comparable<Term> {

   private String query;
   private long weight;
   /**
    * Initialize a term with the given query and weight.
    * This method throws a NullPointerException if query is null,
    * and an IllegalArgumentException if weight is negative.
    */
   public Term(String query, long weight) {
   
      if (query == null) {
         throw new NullPointerException();
      }
      if (weight < 0) {
         throw new IllegalArgumentException("Weight cannot be negative");
      }
      
      this.query = query;
      this.weight = weight;
      
   }

   /**
    * Compares the two terms in descending order of weight.
    */
   public static Comparator<Term> byDescendingWeightOrder() {
   
      Comparator<Term> byWeight = 
         new Comparator<>() {
            public int compare(Term term1, 
                        Term term2) {
               Long weight1 = term1.weight;
               Long weight2 = term2.weight;
               return weight2.compareTo(weight1);  
            }
         };
      return byWeight;
   
   }

   /**
    * Compares the two terms in ascending lexicographic order of query,
    * but using only the first length characters of query. This method
    * throws an IllegalArgumentException if length is less than or equal
    * to zero.
    */
   public static Comparator<Term> byPrefixOrder(int length) {
      if (length <= 0) {
         throw new IllegalArgumentException("Length must be greater than 0");
      }
   
      Comparator<Term> comp = 
         new Comparator<Term>() {
            public int compare(Term term1,
                     Term term2) {
               int len1 = Math.min(length, term1.query.length());
               int len2 = Math.min(length, term2.query.length());
               String prefix1 = term1.query.substring(0, len1);
               String prefix2 = term2.query.substring(0,len1);
               return prefix1.compareTo(prefix2);
            }          
         };
      return comp;
   }
   
   /**
    * Compares this term with the other term in ascending lexicographic order
    * of query.
    */
   @Override
      public int compareTo(Term other) {
      
      String query1 = this.query;
      String query2 = other.query;
      
      return query1.compareTo(query2);
   }
   
   /**
    * Returns a string representation of this term in the following format:
    * query followed by a tab followed by weight
    */
   @Override
      public String toString(){
      
      String result = this.query + "\t" + this.weight;
      return result;
   }
   
}


