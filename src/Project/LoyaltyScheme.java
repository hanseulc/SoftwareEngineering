package Project;

public class LoyaltyScheme {

    private String name;
    private int points;
    private String status;

    public LoyaltyScheme(String name){
        this.name = name;
        status = "Active";
    }

    public LoyaltyScheme(String name, int points){
        this.name = name;
        this.points = points;
        status = "Active";
    }

    public void addPoints(){
        points += Math.random()*41;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public String getStatus() {
        return status;
    }

    public void setExpired(){
        status = "Expired";
    }
}
