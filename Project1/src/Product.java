import java.text.NumberFormat;

public class Product
{
    private String code;
    private String description;
    private double price;
    protected static int count = 0;   // a protected static variable

    public Product(){
        code = "";
        description = "";
        price = 0;
    }
    
    public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public static int getCount() {
		return count;
	}
	public static void setCount(int count) {
		Product.count = count;
	}

    @Override
    public String toString()
    {
        return "Code:        " + code + "\n" +
               "Description: " + description + "\n" +
               "Price:       " +
               this.getFormattedPrice() + "\n";
    }
    
    public String getFormattedPrice(){
    	return NumberFormat.getCurrencyInstance().format(price);
    }

    @Override
    public boolean equals(Object obj){
    	if(!(obj instanceof Product)){
    		return false;
    	}
    	if(obj == this){
    		return true;
    	}
    	Product p = (Product)obj;
    	return code.equals(p.getCode());
    }
}