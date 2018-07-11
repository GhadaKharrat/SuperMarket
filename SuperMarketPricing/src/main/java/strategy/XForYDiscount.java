package strategy;

public class XForYDiscount implements Discount {

    private final Article article;
    private final double itemAmount;
    private final double forAmount;

    public XForYDiscount(Article article, double xAmount, double yAmount) {
        this.article = article;
        this.itemAmount = xAmount;
        this.forAmount = yAmount;
    }

    @Override
    public double discount(Stock stock) {
        double amount = stock.getItemsByArticle(article).stream().mapToDouble(i -> i.getAmount()).sum();
        return (amount / itemAmount) * article.total(itemAmount - forAmount);
    }

}