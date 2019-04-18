import java.util.ArrayList;

public class StockBroker {

	public final String BROKER_NAME;
	private ArrayList<StockTrade> trades = new ArrayList<StockTrade>();
	private ArrayList<String> names = new ArrayList<String>();
	private ArrayList<String> tickers = new ArrayList<String>();
	
	public StockBroker(String name) {
		BROKER_NAME = name;
	}
	
	public void process(StockTrade temp) {
		String name = temp.getClientName();
		String ticker = temp.getStockTicker();
		if(!names.contains(name)) {
			names.add(name);
		}
		if(!tickers.contains(ticker)) {
			tickers.add(ticker);
		}
		temp.setTradeTime();
		trades.add(temp);
	}
	
	public ArrayList<StockTrade> getTradesByClient(String client) { //CLIENTS PER BROKER!!!!!!
		ArrayList<StockTrade> toReturn = new ArrayList<StockTrade>();
		for(StockTrade element : trades) {
			if(element.getClientName().equals(client)) {
				toReturn.add(new StockTrade(element));
			}
		}
		return toReturn;
	}
	
	public ArrayList<StockTrade> getTradesByTicker(String ticker) {
		ArrayList<StockTrade> toReturn = new ArrayList<StockTrade>();
		for(StockTrade element : trades) {
			if(element.getStockTicker().equals(ticker)) {
				toReturn.add(new StockTrade(element));
			}
		}
		return toReturn;
	}
	
	public double sellTotalByClient(String client) { //CLIENTS PER BROKER!!!!!!
		double total = 0;
		for(StockTrade element : getTradesByClient(client)) {
			if(element.getType().equals(StockTrade.SELL_TRADE)) {
				total+= (element.getPrice()*element.getShares());
			}
		}
		return total;
	}
	
	public double buyTotalByClient(String client) { //CLIENTS PER BROKER!!!!!!
		double total = 0;
		for(StockTrade element : getTradesByClient(client)) {
			if(element.getType().equals(StockTrade.BUY_TRADE)) {
				total+= (element.getPrice()*element.getShares());
			}
		}
		return total;
	}
	
	public double sellTotalByTicker(String ticker) {
		double total = 0;
		for(StockTrade element : getTradesByTicker(ticker)) {
			if(element.getType().equals(StockTrade.SELL_TRADE)) {
				total+= (element.getPrice()*element.getShares());
			}
		}
		return total;
	}
	
	public double buyTotalByTicker(String ticker) {
		double total = 0;
		for(StockTrade element : getTradesByTicker(ticker)) {
			if(element.getType().equals(StockTrade.BUY_TRADE)) {
				total+= (element.getPrice()*element.getShares());
			}
		}
		return total;
	}
	
	public void merge(StockBroker old) {
		for(String name : old.names) {
			if(!this.names.contains(name)) {
				this.names.add(name);
				for(StockTrade trade : old.trades) {
					if(trade.getClientName().equals(name)) {
						this.trades.add(trade);
					}
				}
			}
		}
	}
	
	public String toString() {
		String toReturn =  "[BROKER_NAME]: "+BROKER_NAME+", [number of trades]: "+
	trades.size()+", [number of clients]: "+names.size()+", \n";
		
		toReturn+= "--------------------INFO BY CLIENT---------------------\n";
		for(String name : names) {
			toReturn+= "=========================================\n";
			toReturn+= "[clientName]= "+name+", [sellTotalByClient]: "+sellTotalByClient(name)
		+", [buyTotalByClient]: "+buyTotalByClient(name)+"\n";
			for(StockTrade trade : getTradesByClient(name)) {
				toReturn+= trade.toString();
			}
		}
		toReturn+= "--------------------INFO BY TICKER---------------------\n";
		for(String ticker : tickers) {
			toReturn+= "=========================================\n";
			toReturn+= "[stockTicker]= "+ticker+", [sellTotalByTicker]: "+sellTotalByTicker(ticker)
		+", [buyTotalByTicker]: "+buyTotalByTicker(ticker)+"\n";
			for(StockTrade trade : getTradesByTicker(ticker)) {
				toReturn+= trade.toString();
			}
		}
		return toReturn;
	}
	
	public void removeClient(String client) {
		this.names.remove(client);
		for(int x = 0; x<this.trades.size(); x++) {
			if(this.trades.get(x).getClientName().equals(client)) {
				this.trades.remove(x);
				x--;
			}
		}
	}
}