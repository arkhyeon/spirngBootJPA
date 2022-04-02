public class DollarCalculator implements ICalculator{

    private int price = 1;
    private MarketApi marketApi;

    public DollarCalculator(MarketApi marketApi){
        this.marketApi = marketApi;
    }

    public void init(){
        this.price = marketApi.connect();
    }

    @Override
    public int sum(int x, int y) {
        return 0;
    }

    @Override
    public int minus(int x, int y) {
        return 0;
    }
}
