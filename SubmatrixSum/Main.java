import java.util.Scanner;

/* NB: For the judge to run the program, do not modify the declaration of the class Main or
 *     method main() below. The class has to be declared as "class Main { ... }"
 *     and the method as "public static void main(String[] args) { ... }" */
class Main
{
	static int n; 
	
	//Stores the matrix M.
	//Element m_{i,j} (with 1<=i,j<=n) is stored in M[i][j]. Entries of the form M[i][0] and M[0][j] are equal to 0.
	static int[][] M;
	static int[][] preSum;
	
	static void preprocess()
	{
	        //Preprocess the matrix M to support fast queries. This gives for each element the sum of all elements towards the origin
			//Luckily, because the matrix is filled from position 1, we don't have to check for out-of-bounds exceptions
			//when traversing and summing up the n-1'th element
		preSum = new int[n+1][n+1];
		
		
		for(int i = 1; i < n+1; i++) { //Traverse vertical
			for(int j = 1; j < n+1; j++) {//Traverse horizontal
				preSum[i][j] =  M[i][j];        //Current element
				preSum[i][j] += preSum[i][j-1]; //Element plus the sum of current line elements prior
				
			}				
			for(int j= 1; j < n+1; j++) {
				preSum[i][j] += preSum[i-1][j]; //Element plus the sum of current row elements
			}
		}
		
		//System.out.println(Arrays.deepToString(M));                          //These help check correctness, not needed
		//System.out.println(Arrays.deepToString(preSum));
	}

	static int query(int a, int b, int c, int d)
	{
		int sum = (preSum[b][d] - preSum[b][c-1] - preSum[a-1][d] +  preSum[a-1][c-1]); //Takes the sum of the current square to the origin, 
																						//then subtracts all outliers and adds the square between the inner corner and origin, as it is subtracted twice
		return sum; 																	// Return the correct answer to the query
	}
	
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);	
		
		n = scanner.nextInt();

		M = new int[n+1][n+1];
		for(int i=1; i<=n; i++)
		{
			for(int j=1; j<=n; j++)
				M[i][j]=scanner.nextInt();
		}

		preprocess();
		
		int m = scanner.nextInt();
		for(int i=0; i<m; i++)
		{
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			int c = scanner.nextInt();
			int d = scanner.nextInt();
			
			System.out.println(query(a,b,c,d));
		}
		
		scanner.close();
	}
}

