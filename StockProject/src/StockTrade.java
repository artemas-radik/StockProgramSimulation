import java.time.LocalDateTime;

public class StockTrade {

	public static final String BUY_TRADE = "BUY";
	public static final String SELL_TRADE = "SELL";
	private String stockTicker;
	private String clientName;
	private double price;
	private int shares;
	private String time;
	private String type;
	
	public StockTrade(String stockTicker, String clientName,
			double price, String type, int shares) {
		this.stockTicker = stockTicker;
		this.clientName = clientName;
		this.price = price;
		this.type = type;
		this.shares = shares;
	}
	
	public StockTrade(StockTrade old) { //NEED METHODS HERE??
		//- does private allow class-wide access from any instance?
		this.stockTicker = old.stockTicker;
		this.clientName = old.clientName;
		this.price = old.price;
		this.type = old.type;
		this.shares = old.shares;
		this.time = old.time; //Remember this
	}
	
	public void setTradeTime() {
		this.time = LocalDateTime.now().toString();
	}
	
	public String toString() {
		return "[stockTicker]= "+stockTicker+", [clientName]= "+clientName+", [price]= "+
	price+", [shares]= "+shares+", [time]= "+time+", [type]= "+type+", \n";
	}
	
	public String getClientName() {
		return clientName;
	}
	
	public String getStockTicker() {
		return stockTicker;
	}
	
	public String getType() {
		return type;
	}
	
	public double getPrice() {
		return price;
	}
	
	public double getShares() {
		return shares;
	}

}