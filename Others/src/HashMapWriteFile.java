import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.BufferedReader;

public class HashMapWriteFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<Integer, String> map = new HashMap<Integer, String>();
		Scanner sc = new Scanner(System.in);
		String next = "y";
		while(!next.equalsIgnoreCase("n")){
			int x = 0;
			System.out.println("Enter a number: ");
			try{
				x = Integer.parseInt(sc.next());
			}catch(NumberFormatException e){
				e.printStackTrace();
			}finally{
				sc.nextLine();
			}
			
			if(map.containsKey(x)){
				System.out.println("You entered: " + map.get(x));
			}else{
				System.out.println("Please add the number to the map.");
				System.out.println("Word value: ");
				String str = sc.nextLine();
				map.put(x, str);
				System.out.println("Done!");
			}
			System.out.println("Would you like to try again? Y/N");
			next = sc.next();
		}
		sc.nextLine();
		System.out.println("Game Over.");
		System.out.println();
		
		//Scanner sc = new Scanner(System.in);
		ArrayList<String> books = new ArrayList<String>();
		System.out.println("Enter file name");
		String fname = sc.nextLine();
		System.out.println((System.getProperty("user.dir") + File.separatorChar + fname));
		String filename = (System.getProperty("user.dir") + File.separatorChar + fname);
		
		PrintWriter writer;
		try{
			writer = new PrintWriter(new File(filename));
			for (int i : map.keySet()){
				writer.println(i + " " + map.get(i));
			}
			//you must close the PrintWriter
			writer.close();	
		}catch(FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try{
			System.out.println("Read in list of books");
		    books = readLines(new File(filename));
			for(String book : books)
			{
				System.out.println(book.toString());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	 public static ArrayList<String> readLines(File file) throws Exception {
	      if (!file.exists()) {
	          return new ArrayList<String>();
	      }
	      BufferedReader reader = new BufferedReader(new FileReader(file));
	      ArrayList<String> results = new ArrayList<String>();
	      String line = reader.readLine();
	      while (line != null) {
	          results.add(line);
	          line = reader.readLine();
	      }
	      reader.close();
	      return results;
	  }

}
