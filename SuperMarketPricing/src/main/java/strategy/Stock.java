package strategy;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Stock {
    private List<Item> items = new ArrayList<Item>();

    public void add(Item item) {
        items.add(item);
    }

    public double total() {
        return items.stream().mapToDouble(i -> i.total()).sum();
    }

    public List<Item> getItemsByArticle(Article article) {
        return items.stream().filter(c -> c.isArticle(article)).collect(Collectors.toList());
    }
}
