package Project;

import java.time.LocalDate;

public class Weekly extends Recurring {

    public Weekly(String name, LocalDate startDate, double price) {
        super(name, startDate, price);
        super.setType("Recurring: " + this.getClass().getSimpleName());
        super.setPaymentDate();
    }
}
