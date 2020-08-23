import java.util.Scanner;

// NB: For the judge to run the program, do not modify the declaration of the class Main or
// method main() below. The class has to be declared as "class Main { ... }"
// and the method as "public static void main(String[] args) { ... }"
class Main {
	static int n; // the number of vertices in G
	static int[] outdegrees; // outdegrees[u] is the out-degree of vertex u in G
	static int[][] adjLists; // adjLists[u][j] is the j-th neighbor of vertex u in G (i.e., (u,
								// adjLists[u][j]) is an edge in G)

	static int[] winNodes; // Saves the number of nodes from which the current player would guaranteedly win

	public static char solve(int currentNode, char selectingPlayer) // Returns winner. The selecting player selects
																	// which node to visit next.
	{
		char nextPlayer = (selectingPlayer == 'A') ? 'B' : 'A'; // Set who is the next player (I have shortened this to
																// an one-line condition because solving this is trivial
																// and adds nothing to code understandability

		if (outdegrees[currentNode] == 0) { // If the current player can't move, there are no neighbors, and the other
											// wins.
			return nextPlayer;
		}

		else if (winNodes[currentNode] == 0) { // Else, if the node has not been visited, there are neighbors

			for (int i = 0; i < outdegrees[currentNode]; i++) { // Traverse all neighbors

				char result = solve(adjLists[currentNode][i], nextPlayer); // Looks for every neighbor who would win if
																			// that node would be visited (and recurses
																			// on that)

				if (result == selectingPlayer) {
					winNodes[currentNode]++; // If from the next node the other can't win, the player on this node has a
												// guarantee to win if he goes to that side.
					break; // Thus, the player should visit this neighbor and can ignore all other
							// neighbors (this saves time in optimal scenarios)
				}

			}
		}

		if (winNodes[currentNode] != 0) { // If there is at least one guaranteed path to victory from this node, return
											// the winner for this node.
			return selectingPlayer;
		} else {
			return nextPlayer; // else the other player wins if you visit this node
		}

	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int ntestcases = scanner.nextInt();

		for (int testno = 0; testno < ntestcases; testno++) {
			n = scanner.nextInt();
			outdegrees = new int[n];
			adjLists = new int[n][];
			winNodes = new int[n];
			for (int i = 0; i < n; i++) {
				outdegrees[i] = scanner.nextInt();
				adjLists[i] = new int[outdegrees[i]];
				for (int j = 0; j < outdegrees[i]; j++) {
					adjLists[i][j] = scanner.nextInt();
				}
			}

			System.out.println(solve(0, 'A')); // Select which node to start from, and who goes first
		}

		scanner.close();
	}
}
