import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class HashMapExample {

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
		System.out.println("Game Over.");
	}

}
