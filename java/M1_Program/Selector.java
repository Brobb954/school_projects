import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Brandon Robb (bar0086@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  18 Oct 23
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
    * Selects the minimum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int min(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("Array is either empty or null");
      }
      int result = a[0];
      for (int i = 0; i < a.length;i++) {
         if (result > a[i]) {
            result = a[i];
         }
      }
      return result;
   }


   /**
    * Selects the maximum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int max(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("Array is either empty or null");
      }
      int result = a[0];
      for (int i = 0; i < a.length;i++) {
         if (result < a[i]) {
            result = a[i];
         }
      }
      return result;
   }


   /**
    * Selects the kth minimum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth minimum value. Note that there is no kth
    * minimum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmin(int[] a, int k) {
   
      int result= 0;
      int index = 0;
      
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("Array is either empty or null");
      }
      if (k < 1 || k > a.length) {
         throw new IllegalArgumentException("K is an invalid value");
      }
      
      int[] aNew = a.clone();
      int[] dValue = new int[0];
   
      Arrays.sort(aNew);
      
      for (int i = 0; i < aNew.length; i++) {
         if (i != 0) {
            if (aNew[i] != aNew[i - 1]) {
               dValue = Arrays.copyOf(dValue, dValue.length + 1);
               dValue[index] = aNew[i];
               index++;
            }
         }
         else {
            dValue = Arrays.copyOf(dValue, dValue.length + 1);
            dValue[index] = aNew[i];
            index++;
         }
      }
      
      if (k > dValue.length) {
         throw new IllegalArgumentException("K is larger than the number of distinct values in the array");
      }
         
      result = dValue[k - 1];
      return result;
   }
   
   // private static int[] minOrder(int[] a) {
      // int index = 0;
      // int[] aNew = a.clone();
      // int[] dValue = new int[0];
   // 
      // Arrays.sort(aNew);
      // for (int i = 0; i < aNew.length; i++) {
         // if (i != 0) {
            // if (aNew[i] != aNew[i - 1]) {
               // dValue = Arrays.copyOf(dValue, dValue.length + 1);
               // dValue[index] = aNew[i];
               // index++;
            // }
         // }
         // else {
            // dValue = Arrays.copyOf(dValue, dValue.length + 1);
            // dValue[index] = aNew[i];
            // index++;
         // }
      // }
      // return dValue;
   // }

   /**
    * Selects the kth maximum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth maximum value. Note that there is no kth
    * maximum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmax(int[] a, int k) {
   
      int result = 0;
      int index = 0;
      
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("Array is either empty or null");
      }
      if (k < 1 || k > a.length) {
         throw new IllegalArgumentException("K is an invalid value");
      }
   
      int[] rdOrder = new int[0];
      int[] aNew = a.clone();
      Arrays.sort(aNew);
      
      for (int i = 0; i < aNew.length; i++) {
         if (i != 0) {
            if (aNew[i] != aNew[i - 1]) {
               rdOrder = Arrays.copyOf(rdOrder, rdOrder.length + 1);
               rdOrder[index] = aNew[i];
               index++;
            }
         }
         else {
            rdOrder = Arrays.copyOf(rdOrder, rdOrder.length + 1);
            rdOrder[index] = aNew[i];
            index++;
         }
      }
      
      int j = rdOrder.length - 1;
      for (int i = 0; i < j; i++) {
         int temp = rdOrder[i];
         rdOrder[i] = rdOrder[j];
         rdOrder[j] = temp;
         j--;
      }
       
      if (k > rdOrder.length) {
         throw new IllegalArgumentException("K is larger than the number of distinct values in the array");
      }
      
      result = rdOrder[k - 1];
      return result;
   }
   
   // private static int[] maxOrder(int [] a) {
      // int[] rdOrder = new int[0];
      // int[] aNew = a.clone();
      // Arrays.sort(aNew);
      // int index = 0;
   //    
      // for (int i = 0; i < aNew.length; i++) {
         // if (i != 0) {
            // if (aNew[i] != aNew[i - 1]) {
               // rdOrder = Arrays.copyOf(rdOrder, rdOrder.length + 1);
               // rdOrder[index] = aNew[i];
               // index++;
            // }
         // }
         // else {
            // rdOrder = Arrays.copyOf(rdOrder, rdOrder.length + 1);
            // rdOrder[index] = aNew[i];
            // index++;
         // }
      // }
   //    
      // int j = rdOrder.length - 1;
      // for (int i = 0; i < j; i++) {
         // int temp = rdOrder[i];
         // rdOrder[i] = rdOrder[j];
         // rdOrder[j] = temp;
         // j--;
      // }
   //    
      // return rdOrder;
   // }


   /**
    * Returns an array containing all the values in a in the
    * range [low..high]; that is, all the values that are greater
    * than or equal to low and less than or equal to high,
    * including duplicate values. The length of the returned array
    * is the same as the number of values in the range [low..high].
    * If there are no qualifying values, this method returns a
    * zero-length array. Note that low and high do not have
    * to be actual values in a. This method throws an
    * IllegalArgumentException if a is null or has zero length.
    * The array a is not changed by this method.
    */
   public static int[] range(int[] a, int low, int high) {
      int[] range = new int[0];
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("Array is either empty or null");
      }
      
      for (int i = 0; i < a.length; i++) {
         if (a[i] >= low && a[i] <= high) {
            range = Arrays.copyOf(range, range.length + 1);
            range[range.length - 1] = a[i];
            Arrays.sort(range);
         }
      }
      return range;
   }


   /**
    * Returns the smallest value in a that is greater than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int ceiling(int[] a, int key) {
   
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("Array is either empty or null");
      }
      int i = 0;
      int num1;
      int result = Integer.MAX_VALUE;
      boolean valid = false;
      for (i = 0; i < a.length; i++) {
         if (a[i] >= key) {
            num1 = a[i];
            if (num1 < result) {
               result = num1;
               valid = true;
            }
         }
      }
      
      if (i == a.length && valid == false) {
         throw new IllegalArgumentException("There is no qualifying value");
      }
      return result;
   }


   /**
    * Returns the largest value in a that is less than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int floor(int[] a, int key) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException("Array is either empty or null");
      }
      int i = 0;
      int num1;
      int result = Integer.MIN_VALUE;
      boolean valid = false;
      for (i = 0; i < a.length; i++) {
         if (a[i] <= key) {
            num1 = a[i];
            if (num1 > result) {
               result = num1;
               valid = true;
            }
         }
      }
      
      if (i == a.length && valid == false) {
         throw new IllegalArgumentException("There is no qualifying value");
      }
   
      return result;
   }

}
