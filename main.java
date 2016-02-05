import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class main {
	public static void main (String [] args) {
		if (args.length < 2) {
			throw new IllegalArgumentException("Not enough arguments!"); 
		}
			String input_file_name = args[0]; 
			Scanner input_file;
			
			String input_file_name2 = args[1]; 
			Scanner input_file2;
			try {
				input_file = new Scanner(new File(input_file_name));
				input_file2 = new Scanner(new File(input_file_name2));
				String output_file_name = args[3]; 
				FileWriter fWriter;
				
				fWriter = new FileWriter(output_file_name, true);
				PrintWriter print_to_outputfile = new PrintWriter(fWriter);
				
				//step 0: Get N, sourceNode, bestCost[sourceNode], and initialize objects 
				int n = input_file.nextInt(); 
				int source =  input_file2.nextInt();
				DijkstraSSS d = new DijkstraSSS(n,source); 
				
				//step 1: : read next <Ni, Nj, cost> from input1 and put cost to costMatrix[Ni,Nj]
				while (input_file.hasNext()) { //step 2: repeat until the file is empty
					int Ni = input_file.nextInt(); 
					int Nj = input_file.nextInt(); 
					int cost = input_file.nextInt();
					d.getCostMatrix()[Ni][Nj] = cost; 
				}
				//step 3: Print costMatrix to output2 and debugPrint
				print_to_outputfile.print("Cost Matrix: " + "\n");
				for (int i = 0; i < n+1; i++) {
					for (int j = 0; j < n+1; j++) {
						print_to_outputfile.print(d.getCostMatrix()[i][j] + " ");
					}
					print_to_outputfile.print("\n");
				}
				d.debugPrint(print_to_outputfile);
				
				//steps 4,5,6,7
				d.Dijkstra_method(print_to_outputfile); 
				
				String output_file_name2 = args[2]; 
				FileWriter fWriter2;
				
				fWriter2 = new FileWriter(output_file_name2, true);
				PrintWriter print_to_outputfile2 = new PrintWriter(fWriter2);
				
				//steps 8, 9, 10: Print all shortest paths from sourceNode
				print_to_outputfile2.print("The graph has " + n + " nodes. The source node is " + source  + "\n");
				for (int i = 1; i <= n; i++) {
					d.findNprintShortestPath(i, print_to_outputfile2); 
					print_to_outputfile2.print("\n");
				}
		        
		        input_file.close();
		        input_file2.close();
		        print_to_outputfile.close();
		        print_to_outputfile2.close(); 
		       
				
				
			} 
				catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
	
	
			}
	}
