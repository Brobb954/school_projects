import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  Brandon Robb (bar0086@auburn.edu)
 *
 */
public final class Selector {

/**
* Can't instantiate this class.
*
* D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
*
*/
   private Selector() { }


   /**
    * Returns the minimum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the minimum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T min(Collection<T> coll, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException("Array is either empty or null");
      }
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      T min = coll.iterator().next();
      for ( T num : coll) {
         if (comp.compare(num, min) < 0) {
            min = num;
         }
      }
      return min;
   }


   /**
    * Selects the maximum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the maximum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T max(Collection<T> coll, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException("Array is either empty or null");
      }
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      T max = coll.iterator().next();
      for ( T num : coll) {
         if (comp.compare(num, max) > 0) {
            max = num;
         }
      }
      return max;
   }


   /**
    * Selects the kth minimum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth minimum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth minimum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
   
      if (coll == null || comp == null) {
         throw new IllegalArgumentException("Array is either empty or null");
      }
      if (coll.size() == 0 || k < 0) {
         throw new NoSuchElementException();
      }
      T result;
      List<T> list = (List<T>) coll;
      java.util.Collections.sort(list, comp);
      
      int i = 0;
      while (i < list.size()) {
         if (i!= 0) {
            if (comp.compare(list.get(i), list.get(i - 1)) == 0) {
               list.remove(i);
            }
            else {
               i++;
            }
         }
         else if (i == 0) {
            i++;
         }
      }
      
      if (k > list.size()) {
         throw new NoSuchElementException();
      }
      result = list.get(k-1);
      
      return result;
   }


   /**
    * Selects the kth maximum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth maximum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth maximum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
   
      if (coll == null || comp == null) {
         throw new IllegalArgumentException("Collection or Comparator is null");
      }
      if (coll.size() == 0 || k < 0) {
         throw new NoSuchElementException();
      }
      T result;
      List<T> list = new ArrayList<>(coll);
      java.util.Collections.sort(list, comp);
      java.util.Collections.reverse(list);
      
      int i = 0;
      while (i < list.size()) {
         if (i!= 0) {
            if (comp.compare(list.get(i), list.get(i - 1)) == 0) {
               list.remove(i);
            }
            else {
               i++;
            }
         }
         else if (i == 0) {
            i++;
         }
      }
      
      if (k > list.size()) {
         throw new NoSuchElementException();
      }
      result = list.get(k-1);
      
      return result;
   }


   /**
    * Returns a new Collection containing all the values in the Collection coll
    * that are greater than or equal to low and less than or equal to high, as
    * defined by the Comparator comp. The returned collection must contain only
    * these values and no others. The values low and high themselves do not have
    * to be in coll. Any duplicate values that are in coll must also be in the
    * returned Collection. If no values in coll fall into the specified range or
    * if coll is empty, this method throws a NoSuchElementException. If either
    * coll or comp is null, this method throws an IllegalArgumentException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the range values are selected
    * @param low     the lower bound of the range
    * @param high    the upper bound of the range
    * @param comp    the Comparator that defines the total order on T
    * @return        a Collection of values between low and high
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                                     Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException("Collection or Comparator is null");
      }
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      
      ArrayList<T> collection = new ArrayList<T>();
      for (T num : coll) {
         if (comp.compare(num, low) >= 0) {
            if (comp.compare(num, high) <= 0) {
               collection.add(num);
            }
         }
      }
      if (collection.size() == 0) {
         throw new NoSuchElementException();
      }
      return collection;
   }


   /**
    * Returns the smallest value in the Collection coll that is greater than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the ceiling value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the ceiling value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
   
      if (coll == null || comp == null) {
         throw new IllegalArgumentException("Collection or Comparator is null");
      }
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      
      T num1;
      boolean valid = false;
      Iterator<T> itr = coll.iterator();
      T result = itr.next();
      
      while (itr.hasNext()) {
         T current = itr.next();
         if (comp.compare(current, result) > 0) {
            result = current;
         }
      }
      
      for (T num : coll) {
         if (comp.compare(num, key) >= 0) {
            num1 = num;
            if (comp.compare(num, result) <= 0) {
               result = num1;
               valid = true;
            }
         }
      }
      
      if (valid == false) {
         throw new NoSuchElementException();
      }
   
      return result;
   }


   /**
    * Returns the largest value in the Collection coll that is less than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the floor value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the floor value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
   
      if (coll == null || comp == null) {
         throw new IllegalArgumentException("Collection or Comparator is null");
      }
      if (coll.size() == 0) {
         throw new NoSuchElementException();
      }
      
      boolean valid = false;
      T num1;
      Iterator<T> itr = coll.iterator();
      T result = itr.next();
      
      while (itr.hasNext()) {
         T current = itr.next();
         if (comp.compare(current, result) < 0) {
            result = current;
         }
      }
      
      for (T num : coll) {
         if (comp.compare(num, key) <= 0) {
            num1 = num;
            if (comp.compare(num, result) >= 0) {
               result = num1;
               valid = true;
            }
         }
      }
      
      if (valid == false) {
         throw new NoSuchElementException();
      }
   
      return result;
   }

}
