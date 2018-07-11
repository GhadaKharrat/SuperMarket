package strategy;

import java.util.List;

public class AmountForPriceDiscount implements Discount {

    private final Article article;
    private final int amount;
    private final Price price;

    public AmountForPriceDiscount(Article article, int amount, Price price) {
        this.article = article;
        this.amount = amount;
        this.price = price;
    }

    @Override
    public double discount(Stock stock) {
        double numberOfItems = stock.getItemsByArticle(article).stream().mapToDouble(i -> i.getAmount()).sum();
        return article.total(numberOfItems) - price.total(numberOfItems / amount);
    }
}
