package Project;

public class Company {

    private String name;
    private double price;
    private int timeframe;
    private boolean hasLoyalty;

    public Company(String name, double price, int timeframe, boolean hasLoyalty){
        this.name = name;
        this.price = price;
        this.timeframe = timeframe;
        this.hasLoyalty = hasLoyalty;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

    public int getTimeframe() {
        return timeframe;
    }

    public boolean isHasLoyalty() {
        return hasLoyalty;
    }
}
