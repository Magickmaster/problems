//NB: Do not add a package

//NB: Importing classes in other packages is NOT ALLOWED.
//NB: Using classes in other packages in NOT ALLOWED (with the exception of the classes in java.lang.* that are imported by default)
import java.util.Scanner;

// NB: For the judge to run the program, do not modify the declaration of the class Main or
// method main() below. The class has to be declared as "class Main { ... }"
// and the method as "public static void main(String[] args) { ... }"
class Main {
	/**
	 * L is the length of the desert (positions in the desert are indexed from 0 to
	 * L-1) D is the distance Dino can jump, i.e., if Dino is at position p and it
	 * jumps, it lands at position p+D. C is the number of cacti in the desert cacti
	 * is an array of C elements containing the cacti positions, in increasing
	 * order.
	 **/

	// Cacti: Sorted!
	public static int solve(final int L, final int D, final int C, final int[] cacti) {

		// The current position we are checking: The furthest distance we can jump over
		// the cactus from

		int maxDist = 0;
		int[] desert = new int[L];

		// Check out all cactus positions, set the desert road elements to -1
		for (int i = 0; i < cacti.length; i++) {
			desert[cacti[i]] = -1;
		}

		if (desert[0] != -1) {
			desert[0] = 1;
		}
		// Traverse the desert with dyno
		for (int i = 0; i < L; i++) {

			if (desert[i] == 1) {
				maxDist = i;
				if (i < L - 1 && desert[i + 1] != -1) {
					desert[i + 1] = 1;
				}
				if (i < L - D && desert[i + D] != -1) {
					desert[(i + D)] = 1;
				}
			}

		}
		return maxDist;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int ntestcases = scanner.nextInt();

		for (int testno = 0; testno < ntestcases; testno++) {
			int L = scanner.nextInt();
			int D = scanner.nextInt();
			int C = scanner.nextInt();

			int[] cacti = new int[C];
			for (int j = 0; j < C; j++)
				cacti[j] = scanner.nextInt();

			System.out.println(solve(L, D, C, cacti));
		}

		scanner.close();
	}
}
