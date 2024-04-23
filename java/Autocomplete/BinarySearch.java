import java.util.Arrays;
import java.util.Comparator;

/**
 * Binary search.
 */
public class BinarySearch {

   /**
    * Returns the index of the first key in a[] that equals the search key, 
    * or -1 if no such key exists. This method throws a NullPointerException
    * if any parameter is null.
    */
   public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
      if (a == null || key == null || comparator == null) {
         throw new NullPointerException();
      }
      
      int index = -1;
      int left = 0, right = a.length-1;
      while (left <= right) {
         int middle = (left + right) / 2;
         if (comparator.compare(key, a[middle]) < 0) {
            right = middle - 1;
         }
         else if (comparator.compare(key, a[middle]) > 0) {
            left = middle + 1;
         }
         else if (comparator.compare(key, a[middle]) == 0) {
            right = middle - 1;
            index = middle;
         }
      }
      return index;
   }

   /**
    * Returns the index of the last key in a[] that equals the search key, 
    * or -1 if no such key exists. This method throws a NullPointerException
    * if any parameter is null.
    */
   public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
      if (a == null || key == null || comparator == null) {
         throw new NullPointerException();
      }
      
      int index = -1;
      Key result = a[0];
      int left = 0, right = a.length-1;
      while (left <= right) {
         int middle = (left + right) / 2;
         if (comparator.compare(key, a[middle]) < 0) {
            right = middle - 1;
         }
         else if (comparator.compare(key, a[middle]) > 0) {
            left = middle + 1;
         }
         else if (comparator.compare(key, a[middle]) == 0) {
            left = middle + 1;
            index = middle;
         }
      }
      return index;
   }
}
