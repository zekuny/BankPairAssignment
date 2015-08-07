import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.text.NumberFormat;
import java.util.Map;

public class InvoiceApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Invoice invoice = new Invoice();
		List<Invoice> invoices = new ArrayList<Invoice>();
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		System.out.println("Welcome to the invoice application.");
		System.out.println();
		String next = "y";
		while(next.equalsIgnoreCase("y")){
			String code = Validator.getString(sc, "Enter product code: ");
			int quantity = Validator.getInt(sc, "Enter quantity:     ");
			if(!map.containsKey(code)){
				map.put(code, quantity);
			}else{
				map.put(code, map.get(code) + quantity);
			}
			Product p = ProductDB.getProduct(code);
			LineItem item = new LineItem();
			item.setProduct(p);
			item.setQuantity(quantity);
			invoice.getInvoiceList().add(item);
			next = Validator.getString(sc, "Another line item? (y/n): ");
			while(!next.equalsIgnoreCase("y") && !next.equalsIgnoreCase("n")){
				next = Validator.getString(sc, "Please enter valid information... (y/n): ");
			}
			if(next.equalsIgnoreCase("n")){
				next = Validator.getString(sc, "Do you want to add a new invoice? (y/n) ");
				if(next.equalsIgnoreCase("n")){
					invoices.add(invoice);
					break;
				}else{
					invoices.add(invoice);
					invoice = new Invoice();
					next = "y";
				}
			}
			System.out.println();
		}
		
		System.out.println();
		int count = 1;
		for(Invoice in : invoices){
			System.out.println("For invoice: " + count++);
			System.out.println("Code    Description                        Price    Qty      Total");
			System.out.println("----    -----------                        -----    ---      -----");
			for(LineItem item : in.getInvoiceList()){
				System.out.println(item.getProduct().getCode() + "\t" + item.getProduct().getDescription() + "\t" + item.getProduct().getFormattedPrice() + "\t" + item.getQuantity() + "\t" + item.getFormattedTotal());
			}
			System.out.println("                                           Invoice total: " + in.getFormattedTotal());
			System.out.println();
		}
		System.out.println();
		System.out.println("Code    Qty    Total");
		System.out.println("----    ---    -----");
		for(String s : map.keySet()){
			System.out.println(s + "\t" + map.get(s) + "\t" + NumberFormat.getCurrencyInstance().format(map.get(s) * ProductDB.getProduct(s).getPrice()));
		}
	}

}
