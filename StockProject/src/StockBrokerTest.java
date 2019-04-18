import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StockBrokerTest {
	
	private final static String[] availibleTickers = {"AMZN", "GOOG", "AAPL"};
	private final static String[] availibleClients = {"Elon_Musk", "Tim_Cook", "Sergey_Brin"};
	private final static double MIN_PRICE = 0;
	private final static double MAX_PRICE = 1000;
	private final static String[] availibleTypes = {StockTrade.BUY_TRADE, StockTrade.SELL_TRADE};
	private final static int MIN_SHARES = 0;
	private final static int MAX_SHARES = 1000;
	private final static String SEPERATOR = ", ";
	private final static int NUM_TRADES = 20;
	private final static int NUM_BROKERS = 3;
	
	private static ArrayList<StockBroker> brokers = new ArrayList<StockBroker>();
	
	public static void main(String[] args) {
		
		for(int x = 0; x<NUM_BROKERS; x++) {
			brokers.add(new StockBroker("Broker"+(x+1)));
			setRandomTrades(brokers.get(x));
			System.out.println(brokers.get(x).toString());
		}
		
		brokers.get(2).removeClient("Elon_Musk");
		System.out.println("\"Elon_Musk\" removed from \"Broker3\" version:\n"+brokers.get(2).toString());
		
	}
	
	public static void setRandomTrades(StockBroker stockBroker) {
		final String fileName = "autoGeneratedTrades"+stockBroker.BROKER_NAME+".txt";
		autoGenerateTrades(fileName);
		String[] trades = loadTrades(fileName);
		
		for(String element : trades) {
			String[] parameters = element.split(", ");
			String stockTicker = parameters[0];
			String clientName = parameters[1];
			double price = Double.parseDouble(parameters[2]);
			String type = parameters[3];
			int shares = Integer.parseInt(parameters[4]);
			stockBroker.process(new StockTrade(stockTicker, clientName, price, type, shares));
		}
	}
	
	public static void autoGenerateTrades(String fileName) {
		try {
			File file = new File(fileName);
			FileWriter fileWriter = new FileWriter(file);
			for(int x = 0; x<NUM_TRADES; x++) {
				String toWrite = "";
				toWrite += availibleTickers[(int) (Math.random() * availibleTickers.length)] + SEPERATOR;
				toWrite += availibleClients[(int) (Math.random() * availibleClients.length)] + SEPERATOR;
				toWrite += (Math.random() * (MAX_PRICE - MIN_PRICE) + MIN_PRICE) + SEPERATOR;
				toWrite += availibleTypes[(int) (Math.random() * availibleTypes.length)] + SEPERATOR;
				toWrite += (int) (Math.random() * (MAX_SHARES - MIN_SHARES) + MIN_SHARES) + SEPERATOR;
				fileWriter.write(toWrite + "\n");
			}
			fileWriter.flush();
			fileWriter.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String[] loadTrades(String fileName) {
		//read!!!!!!
		try {
			FileReader fileReader = new FileReader(fileName);
			StringBuffer stringBuffer = new StringBuffer();
			int charsRead;
			char[] charArr = new char[1024];
			while((charsRead = fileReader.read(charArr)) > 0) {
				stringBuffer.append(charArr, 0, charsRead);
			}
			fileReader.close();
			return stringBuffer.toString().split("\n");
		}catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}