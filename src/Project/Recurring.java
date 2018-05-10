package Project;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Recurring extends Membership{
    private double cancellationFee;
    private int minimumDays;
    private LocalDate paymentDate;
    private int intervalDays;

    public Recurring(String name, LocalDate startDate, double price){
        super(name, startDate, price);

    }

    public void setPaymentDate(){
        if (super.getType().contains("Weekly")) {
            paymentDate = super.getStartDate().plus(1, ChronoUnit.WEEKS);
        }
        else if (super.getType().contains("Monthly")) {
            paymentDate = super.getStartDate().plus(1, ChronoUnit.MONTHS);
        }
        else if (super.getType().contains("Annual")){
            paymentDate = super.getStartDate().plus(1, ChronoUnit.YEARS);
        }
        super.setPaymentOrEndDate(paymentDate);
    }


    public int getIntervalDays() {
        return intervalDays;
    }

    public double getCancellationFee() {
        return cancellationFee;
    }

    public int getMinimumDays() {
        return minimumDays;
    }

    public LocalDate getPaymentDate(){
        return paymentDate;
    }

    public void setCancellationFee(double cancellationFee) {
        this.cancellationFee = cancellationFee;
    }

    public void setMinimumDays(int minimumDays) {
        this.minimumDays = minimumDays;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setType(String type){
        super.setType(type);
    }
}
