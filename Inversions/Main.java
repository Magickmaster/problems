//NB: Do not add a package

//NB: Importing other classes is NOT ALLOWED
import java.util.Scanner;

// NB: For the judge to run the program, do not modify the declaration of the class Main or
// method main() below. The class has to be declared as "class Main { ... }"
// and the method as "public static void main(String[] args) { ... }"
class Main {
	// "A" is the input vector.

	// The number of elements of A can be accessed using A.length
	   static int sort(int arr[], int l, int r)
	    {
		   	int temp = 0; //Counts the amount of swaps performed in this sort step and its previous sorts
	        if (l < r)
	        {
	            int m = (l+r)/2; // Find the middle

	            temp += sort(arr, l, m);// Sort first and second halves
	            temp += sort(arr , m+1, r);
	            temp += merge(arr, l, m, r); // Merge the sorted halves
	         
	        }
	        return temp; //Return the amount of swaps
	    }
	
	static int merge(int arr[], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1; //size left
        int n2 = r - m;  //size right
        int swaps = 0; //counter for swaps
        /* Create temp arrays */
        int L[] = new int [n1];
        int R[] = new int [n2];
 
        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i) {
            L[i] = arr[l + i];
        }
        for (int j=0; j<n2; ++j) { 
            R[j] = arr[m + 1+ j];
        } 
 
        /* Merge the temp arrays */
 
        int i = 0, j = 0; //temp array indexes
 
  
        int k = l;      // merged subarray origin
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j]) //left is smaller
            {
                arr[k] = L[i];
                i++;
            }
            else // right element is smaller, therefore fulfilling condition
            {
                arr[k] = R[j];

                swaps += (n1-i); //there was an inversion and all remaining elemenets on the left are bigger
                j++;
            }
            k++;
        }
 
        /* Copy remaining elements of L[] if any */
        while (i < n1)
        {
            arr[k] = L[i];
               i++;
            k++;
        }
 
        /* Copy remaining elements of R[] if any */
        while (j < n2)
        {
        	swaps += (n1-i); //All remaining left elements were bigger than this right element, fulfilling condition
            
            arr[k] = R[j];
            j++;
            k++;
        }
        return swaps;
    }
 
 
	static int solve(int[] A) {

		
		// use mergesort, but count every time a right element was placed before a left element
		int k = sort(A, 0, A.length-1);
		//System.out.println(Arrays.toString(A));
		return k;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int ntestcases = scanner.nextInt();

		for (int testno = 0; testno < ntestcases; testno++) {
			int n = scanner.nextInt();
			int[] A = new int[n];

			for (int i = 0; i < n; i++)
				A[i] = scanner.nextInt();

			System.out.println(solve(A));
		}

		scanner.close();
	}
}
