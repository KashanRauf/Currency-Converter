
public class Currency {
   private String fullName;
   private char symbol;
   private int decimals;
   private double rate;

   Currency(String fullName, char symbol, int decimals, double rate) {
      this.fullName = fullName;
      this.symbol = symbol;
      this.decimals = decimals;
      this.rate = rate;
   }

   /**
    * Returns the full name of currency
    * @return full name of currency
    */
   public String toString() {
      return fullName;
   }

   /**
    * Returns the symbol of currency
    * @return symbol of currency
    */
   public char getSymbol() {
      return symbol;
   }
 
   /**
    * Returns the decimals of currency
    * @return decimals of currency
    */
   public int getDecimals() {
      return decimals;
   }
  
   /**
    * Returns the rate of currency
    * @return rate of currency
    */
   public double getRate() {
      return rate;
   }
}