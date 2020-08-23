//NB: Do not add a package

//NB: Importing classes in other packages is NOT ALLOWED.
//NB: Using classes in other packages in NOT ALLOWED (with the exception of the classes in java.lang.* that are imported by default)
import java.util.Scanner;

// NB: For the judge to run the program, do not modify the declaration of the class Main or
// method main() below. The class has to be declared as "class Main { ... }"
// and the method as "public static void main(String[] args) { ... }"
class Main
{		
	//C is the cost of the Java Coffee
	//n is the number of coins in Alice's wallet
	//coin_values[i] and coin_weight[i] are the value and the weight of 
	//the i-th coin, respectively (coins are numbered from 0 to n-1). 
	
	//O(c*n) --> Constant time for every field
	static int solve(int C, int n, int[] coin_values, int[] coin_weights)
	{		
		//Dynamic evaluation table
		int[][] dtable = new int[C+1][coin_values.length+1];
		//Invariant: Field [i|j] contains what?
		
		
		for (int i = 0; i < dtable.length; i++) {
			for (int j = 0; j < n+1; j++) {
				
			}
		}
		return 0;
	}
	

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		int ntestcases = scanner.nextInt();
		
		for(int testno=0; testno<ntestcases; testno++)
		{			
			int C = scanner.nextInt();
			
			int n = scanner.nextInt();
			int[] coin_values = new int[n];
			int[] coin_weights = new int[n];
		
			for(int i=0; i<n; i++)
				coin_values[i] = scanner.nextInt();

			for(int i=0; i<n; i++)
				coin_weights[i] = scanner.nextInt();

			System.out.println(solve(C, n, coin_values, coin_weights));
		}
		
		scanner.close();
	}
}
