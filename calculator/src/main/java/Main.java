
public class Main {
    public static void main(String arg[]){
        System.out.println("hello junit");

        MarketApi marketApi = new MarketApi();
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);

        Calculator calculator = new Calculator(new KrwCalculator());
        Calculator calculator2 = new Calculator(dollarCalculator);

        System.out.println(calculator.sum(10, 10));
        System.out.println(calculator2.sum(10, 10));
    }
}
