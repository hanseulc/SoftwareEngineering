package Project;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Membership {
    private double price;
    private LocalDate startDate;
    private String name;
    private String type;
    private LocalDate paymentOrEndDate;
    private String status;


    public Membership(String name, LocalDate startDate, double price){
        this.name = name;
        this.startDate = startDate;
        this.price = price;
        status = "Active";
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public String getType() { return type; };

    public String getStatus() {
        return status;
    }

    public LocalDate getPaymentOrEndDate() {
        return paymentOrEndDate;
    }

    public String getPaymentOrEndDateFormatted(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return paymentOrEndDate.format(formatter);
    }

    public String getStartDateFormatted(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return startDate.format(formatter);
    }


    public void setType(String type){
        this.type = type;
    }

    public void setPaymentOrEndDate(LocalDate date){
        paymentOrEndDate = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setExpired() {
        status = "Expired";
    }
}


