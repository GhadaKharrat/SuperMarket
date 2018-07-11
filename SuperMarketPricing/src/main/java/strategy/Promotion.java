package strategy;

import java.util.*;
import java.util.function.Consumer;

public class Promotion {

    private List<Discount> discounts = new ArrayList<Discount>();

    public double total(Stock stock) {
        final double[] total = {stock.total()};
        discounts.forEach(new Consumer<Discount>() {

            public void accept(Discount discount) {
                total[0] -= discount.discount(stock);
            }
        });
        return total[0];
    }

    public void addDiscount(Discount discount) {
        discounts.add(discount);
    }
}
