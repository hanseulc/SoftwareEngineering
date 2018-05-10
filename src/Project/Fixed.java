package Project;

import java.time.LocalDate;

public class Fixed extends Membership {

    private LocalDate expiryDate;

    public Fixed(String name, LocalDate startDate, double price, LocalDate expiryDate) {
        super(name, startDate, price);
        this.expiryDate = expiryDate;
        super.setType(this.getClass().getSimpleName());
        super.setPaymentOrEndDate(expiryDate);
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }
}
