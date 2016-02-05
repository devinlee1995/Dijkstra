 import java.io.PrintWriter;


public class DijkstraSSS {
	private int N; //number of nodes in G
	private int sourceNode;
	private int minNode; 
	private int currentNode; 
	private int newCost; 
	
	private int costMatrix[][]; 
	private int fatherArray[]; 
	private int markedArray[];
	private int bestCostArray[]; 
	
	public DijkstraSSS(int value, int source) {
		N = value;
		sourceNode = source; 
		costMatrix = new int[N+1][N+1]; 
			for (int i = 0; i < N+1; i++) {
				for (int j = 0; j < N+1; j++) {
				costMatrix[i][j] = 99999;
				}
			}
		costMatrix[sourceNode][sourceNode] = 0; 
		
		fatherArray = new int[N+1]; 
			for (int i = 0; i < N+1; i++) {
				fatherArray[i] = sourceNode;
			}
		markedArray = new int[N+1]; 
			for (int i = 0; i < N; i++) {
				markedArray[i] = 0;
			}
		bestCostArray = new int[N+1]; 
			for (int i = 0; i < N+1; i++) {
				bestCostArray[i] = 99999; 
			}
			bestCostArray[sourceNode] = 0; 
	}
	
	public int computeCost(int min,int current) {
		if (costMatrix[min][current] != 99999) {
			return (bestCostArray[min] + costMatrix[min][current]); 
		}
		else {
			return 99999; 
		}
	}
	
	public void updateMarkedArray(int min) {
		markedArray[min] = 1;
	}

	public void updateFatherArray(int current, int min) {
		fatherArray[current] = min;
	}
	
	public void updateBestCostArray(int current, int cost) {
		bestCostArray[current] = cost; 
	}
	
	public void debugPrint(PrintWriter print_to_outputfile) {
		print_to_outputfile.print("Source Node: " + sourceNode + "\n"); 
		print_to_outputfile.print("Father Array: ");
			for (int i = 1; i < N+1; i++) {
				print_to_outputfile.print(fatherArray[i]+ " ");
			}
		print_to_outputfile.print("\n" + "Best Cost Array: ");
			for (int i = 1; i < N+1; i++) {
				print_to_outputfile.print(bestCostArray[i]+ " ");
			}
		print_to_outputfile.print("\n" + "Marked Array: ");
			for (int i = 1; i < N+1; i++) {
				print_to_outputfile.print(markedArray[i]+ " ");
			}
		print_to_outputfile.print("\n" + "\n");	
		
	}
	
	public void findNprintShortestPath(int k, PrintWriter print_to_outputfile) { //step 9
		print_to_outputfile.print("The path from " + sourceNode + " to " + k + " : ");
		String path_print = Integer.toString(k); 
		int i = k;
		
		while (fatherArray[i] != sourceNode){
			path_print = Integer.toString(fatherArray[i]) + " --> " + path_print; 
			i=fatherArray[i]; 
		}
		path_print = sourceNode + " --> " + path_print; 
		
		if (fatherArray[k] == sourceNode){
			path_print = Integer.toString(fatherArray[k]) + " --> " + k ;
		}
		print_to_outputfile.print(path_print +  " : cost = " + bestCostArray[k]);	
	}
	
	public void Dijkstra_method(PrintWriter print_to_outputfile) {
		minNode = sourceNode; 
		updateMarkedArray(sourceNode); 
		
		for(int i =1; i< N+1; i++){
			if(markedArray[i] == 0){
				currentNode = i;
				newCost = computeCost(minNode, currentNode);
				if(newCost < bestCostArray[currentNode]){
					updateFatherArray(currentNode, minNode); 
					updateBestCostArray(currentNode, newCost);
				}
			}
		}
		print_to_outputfile.print("\n" + "Min Node: " + minNode + "\n" + "\n");
		debugPrint(print_to_outputfile);
		
		while (!all_marked()) {
		//step 4: Getting the min Node 
			int value = bestCostArray[0]+1;
			int min = 0; 
			for (int i = 1; i < N+1; i++) {
				if (markedArray[i] == 0){ 
					if (bestCostArray[i] < value) {
						value = bestCostArray[i]; 
						min = i;
					}
				}
			}
			minNode = min; 
			updateMarkedArray(minNode);
			print_to_outputfile.print("Min Node: " + minNode + "\n" + "\n");
		
		//step 5: expanding minNode
			for (int i = 1; i < N+1; i++) {
				if (markedArray[i] == 0) {
					currentNode = i; 
					System.out.println("currentNode: " +currentNode);
					newCost = computeCost(minNode,currentNode); 
					System.out.println("newCost: " +newCost);
					if (newCost < bestCostArray[currentNode]) {
						updateFatherArray(currentNode, minNode); 
						updateBestCostArray(currentNode, newCost); 
						System.out.println("Updated!" + "\n");
					}
				}
				debugPrint(print_to_outputfile);
			}
		}
		for(int i =1; i < N + 1; i++){
			if(fatherArray[i] == i){
				fatherArray[i] = sourceNode; 
			}
		}
	}
	
	public int[][] getCostMatrix() {
		return costMatrix; 
	}
	
	public boolean all_marked() {
		int count = 0;
		for (int i = 1; i < N+1; i++) {
			if (markedArray[i] == 1) {
				count++; 
			}
		}
		if (count == N) {
			return true;
		}
		else {
			return false; 
		}
	}
}
