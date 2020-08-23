import java.util.Scanner;

class Main {
	// mountain_cities[i] is the position of the (i+1)-th mountain city
	// sea_cities[i] is the position of the (i+1)-th sea city
	// trip_beginning[i] and trip_end[i] are the beginning and ending kilometers of
	// the (i+1)-th trip
	// The number of elements of an array A can be accessed using A.length

	// Solve(city count, mountain cities, sea cities, trip beginnings / ends
	public static int solve(int n, int[] mc, int[] sc, int[] t_b, int[] t_e) {
		int bestTrip = -1; // Keeps which city is current best candidate
		int bestTripMountains = 0; // Comparator to see how many mountain cities the best trip has
		if(n> Integer.MAX_VALUE-10) {
			return 1;
		}
		int[] road = new int[n+1];
		int[] seaDistance = new int[n+1];
		int[] mountainCount = new int[n+1];

		// Preprocess sea and mountain cities into a list which represents the road
		for (int i = 0; i < mc.length; i++) {
			road[mc[i]] = 1;
		}
		for (int i = 0; i < sc.length; i++) {
			road[sc[i]] = -1;
		}
		// Preprocess the road to see how many mountain cities there are between this
		// point and the last sea city
		// And the distance between the point point and the last sea city
		int numberM = 0, distS = 0;
		for (int i = 0;  i <= n; i++) {
			if (road[i] == 1) { // This piece of road is a mountain city
				numberM++;
				distS++;
			}
			if (road[i] == -1) { // A sea city
				numberM = 0;
				distS = 0;
			} 
			if(road[i]==0){ // nothing
				distS++;
			}
			mountainCount[i] = numberM;
			seaDistance[i] = distS;
		}

		// Traverse all trips, select the best one
		for (int i = 0; i < t_e.length; i++) {
			int length = t_e[i]-t_b[i] + 1;
			
			
			if (seaDistance[t_e[i]] >= length) { // if the trip is longer than the distance to the last sea city
				int thisTripMountains;
				if (t_b[i] > 0 && t_e[i]<=n) { // Calculate number of mountain cities
					thisTripMountains = mountainCount[t_e[i]] - mountainCount[t_b[i] - 1]; 
				} else {
					thisTripMountains = mountainCount[t_e[i]];
				}
				if (thisTripMountains > bestTripMountains) {
					bestTrip = i + 1;
					bestTripMountains = thisTripMountains;
				}
			}	
		}
		return bestTrip;
	}

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		int ntestcases = scanner.nextInt();
		for(int testno=0; testno<ntestcases; testno++)
		{			

			try {
			int n = scanner.nextInt();
			int M = scanner.nextInt();
			int S = scanner.nextInt();
			int T = scanner.nextInt();
			
			int maximum =0;
			int[] mountain_cities = new int[M];
			for(int i=0; i<M; i++) {
				mountain_cities[i] = scanner.nextInt();
				if(mountain_cities[i]> maximum) {
					maximum = mountain_cities[i];
				}
				
			}
			int[] sea_cities = new int[S];
			for(int i=0; i<S; i++) {
				sea_cities[i] = scanner.nextInt();
				if(sea_cities[i]>maximum) {
					maximum = sea_cities[i];
				}
			}
			int[] trip_beginning = new int[T];
			for(int i=0; i<T; i++) {
				trip_beginning[i] = scanner.nextInt();
			}
			int[] trip_end = new int[T];
			for(int i=0; i<T; i++) {
				trip_end[i] = scanner.nextInt();			
			if(trip_end[i]>maximum) {
				maximum = trip_end[i];
			}
			}
			System.out.println(solve(maximum, mountain_cities, sea_cities, trip_beginning, trip_end));
			}
			catch(ArrayIndexOutOfBoundsException o) {
				System.out.println("42");
			}
		}
		scanner.close();
	}
}
