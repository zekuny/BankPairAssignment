import java.util.ArrayList;
import java.util.List;
import java.text.NumberFormat;

public class Invoice {
	List<LineItem> invoiceList;
	double total;
	
	public Invoice(){
		invoiceList = new ArrayList<LineItem>();
		total = 0.0;
	}

	public List<LineItem> getInvoiceList() {
		return invoiceList;
	}
	
	public void addItem(LineItem lineItem){
		invoiceList.add(lineItem);
	}
	
	public double getInvoiceTotal(){
		for(LineItem item : invoiceList){
			total += item.getTotal();
		}
		return total;
	}
	
	public String getFormattedTotal(){
		return NumberFormat.getCurrencyInstance().format(this.getInvoiceTotal());
	}
}
