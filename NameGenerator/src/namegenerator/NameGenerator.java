package namegenerator;
import java.io.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.HashSet;

public class NameGenerator {

	public static void main(String[] args) {
		
		String[] peoples = {"Bob","Carol","Ted","Alice", "Hamza", "Diego", "Pamela", "Melissa", "Luke", "Louis", "Hrideta", "Diego", "Carol"};
		
		
        List<String> names = Arrays.asList(peoples); 
        Collections.shuffle(names);
        
        
        System.out.println("=============================== Shuffled Names ==============================");
        // Print each name in the console
        for (String name : names) {
        	System.out.print(name + " ");
        	}
        
        System.out.println();
                
        String file = "C:\\temp\\names.dat";
               
        writeNamesToFile(names, file);
        readNamesFromFile(file);
        
        }

	public static void writeNamesToFile(List<String> names, String file) {
		FileWriter fw = null; // used to write to the file
		BufferedWriter bw = null;
		
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			for (String name : names) {
				bw.write(name + " ");
			}			
			System.out.println("\nThe names have been saved to the file.\n");
		} catch(IOException e) {
			e.printStackTrace();
		
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void readNamesFromFile(String file) {
			FileReader fr = null;
			BufferedReader br = null;
			
			List<String> namesArr = new ArrayList<>();
			
			try {
				fr = new FileReader(file); // reads the file
				br = new BufferedReader(fr); // associated with the file reader
				String line = "";
								
				while((line = br.readLine()) != null) {
					String [] arr = line.split(" "); 
					
					for(String name : arr) {
						namesArr.add(name); // using the add method from the Array List
					}
				}
				System.out.println("Your file contents have beed read.\n");
				fr.close();
				br.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			Map<String, Integer> nameCount = new HashMap<>();
			for(String name : namesArr) {
				if(nameCount.containsKey(name)) {
					int count = nameCount.get(name);
					nameCount.put(name, count + 1);
				} else {
					nameCount.put(name, 1);
				}

			}
			
			System.out.println("==================== Removing Duplicate Names ====================");
			List<String> noDupsList = new ArrayList<>(new HashSet<>(namesArr));
			Collections.sort(noDupsList);
			for (String dupe : noDupsList) {
				System.out.print(dupe + " ");
			}
			
			System.out.println();
			// Here you print duplicate information
			
			System.out.print("\nNumber of duplicates: ");
			int dupeCount = 0;
			for (Map.Entry<String, Integer> set : nameCount.entrySet()) {
				if (set.getValue() > 1) {
					dupeCount++;
				}
			}
			System.out.print(dupeCount);
			
			System.out.println();
			System.out.print("\nDuplicate names: ");
			for (Map.Entry<String, Integer> set : nameCount.entrySet()) {
				if (set.getValue() > 1) {
					System.out.print(set.getKey() + " ");
				}
								
			}
			
		}	
}
