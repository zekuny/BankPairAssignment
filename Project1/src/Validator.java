import java.util.Scanner;

public class Validator {
	public static String getString(Scanner sc, String prompt){
		System.out.print(prompt);
		String s = sc.next();
		sc.nextLine();
		return s;
	}
	
	public static int getInt(Scanner sc, String prompt){
		int i = 0;
		boolean isValid = false;
		while(isValid == false){
			System.out.print(prompt);
			if(sc.hasNextInt()){
				i = sc.nextInt();
				isValid = true;
			}else{
				System.out.println("Error! Invalid integer value.\nTry again.");
			}
			sc.nextLine();
		}
		return i;
	}
	
	public static double getDouble(Scanner sc, String prompt){
		double d = 0;
		boolean isValid = false;
		while(isValid == false){
			System.out.print(prompt);
			if(sc.hasNextDouble()){
				d = sc.nextDouble();
				isValid = true;
			}else{
				System.out.println("Error! Invalid integer value.\nTry again.");
			}
			sc.nextLine();
		}
		return d;
	}
}