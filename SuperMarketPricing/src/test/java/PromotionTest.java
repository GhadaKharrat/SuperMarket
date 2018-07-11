import strategy.*;
import junit.framework.Assert;

import org.junit.Test;
public class PromotionTest {

    private ArticleFactory articleFactory = new ArticleFactory();
    private Article article0 = articleFactory.article(1.0);
    private Article article1 = articleFactory.article(1.5);
    private ItemBuilder itemBuilder = new ItemBuilder();

    @Test
    public void testNoItems() {
        Promotion promotion = new Promotion();
        Stock stock = new Stock();
        double total = promotion.total(stock);

        Assert.assertEquals(0.0, total, 0.001);
    }

    @Test
    public void testOneItem() {
        Promotion promotion = new Promotion();
        Stock stock = new Stock();
        stock.add(itemBuilder.create(article0).amount(1).item());
        double total = promotion.total(stock);

        Assert.assertEquals(1.0, total, 0.001);
    }

    @Test
    public void testItems() {
        Promotion promotion = new Promotion();
        Stock stock = new Stock();
        stock.add(itemBuilder.create(article0).amount(1).item());
        stock.add(itemBuilder.create(article1).amount(2).item());
        double total = promotion.total(stock);

        Assert.assertEquals(4.0, total, 0.001);
    }

    @Test
    public void test3For2() {
        Promotion promotion = new Promotion();
        promotion.addDiscount(new XForYDiscount(article1, 3, 2));
        Stock stock = new Stock();
        stock.add(itemBuilder.create(article0).amount(1).item());
        stock.add(itemBuilder.create(article1).amount(3).item());
        double total = promotion.total(stock);

        Assert.assertEquals(4.0, total, 0.001);
    }

    @Test
    public void test3ForPrice() {
        Promotion promotion = new Promotion();
        promotion.addDiscount(new AmountForPriceDiscount(article1, 3, new Price(2.5)));
        Stock stock = new Stock();
        stock.add(itemBuilder.create(article0).amount(1).item());
        stock.add(itemBuilder.create(article1).amount(3).item());
        double total = promotion.total(stock);

        Assert.assertEquals(3.5, total, 0.001);
    }
}
