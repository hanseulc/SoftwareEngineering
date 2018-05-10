package Project;

import java.time.LocalDate;

public class Annual extends Recurring{

    public Annual(String name, LocalDate startDate, double price) {
        super(name, startDate, price);
        super.setType("Recurring: " + this.getClass().getSimpleName());
        super.setPaymentDate();
    }
}
