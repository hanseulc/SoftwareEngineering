package Project;

import java.time.LocalDate;

public class Monthly extends Recurring {

    public Monthly(String name, LocalDate startDate, double price) {
        super(name, startDate, price);
        super.setType("Recurring: " + this.getClass().getSimpleName());
        super.setPaymentDate();
    }

}
