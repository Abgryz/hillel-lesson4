package ithillel.lesson14;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record Product(String category, double price, boolean canUseSale, LocalDate additionDate) {
    public static List<Product> booksFrom(List<Product> productList){
        return productList.stream()
                .filter(product -> Objects.equals(product.category, "Book") && product.price > 250)
                .toList();
    }

    public static List<Product> booksWithSale(List<Product> productList){
        return productList.stream()
                .filter(product -> product.category.equals("Book") && product.canUseSale)
                .map(product -> new Product(product.category, product.price*0.9, true, product.additionDate))
                .toList();
    }

    public static Product theCheapestBook(List<Product> productList){
        return productList.stream()
                .filter(product -> product.category.equals("Book"))
                .min((o1, o2) -> (int) (o1.price - o2.price))
                .orElseThrow(() -> new RuntimeException("Item doesn`t exist"));
    }

    public static List<Product> threeLastProducts(List<Product> productList){
        return productList.stream()
                .sorted((o1, o2) -> o2.additionDate.compareTo(o1.additionDate))
                .limit(3)
                .toList();
    }

    public static double calculateBooks(List<Product> productList){
        return productList.stream()
                .filter((product -> product.additionDate.getYear() == LocalDate.now().getYear()
                        && product.category.equals("Book")
                        && product.price <= 75))
                .mapToDouble(product -> product.price)
                .reduce(0, Double::sum);
    }

    public static Map<String, List<Product>> groupByCategory(List<Product> productList){
        return productList.stream()
                .collect(Collectors.toMap(
                        Product::category,
                        List::of,
                        (value1, value2) -> Stream.concat(value1.stream(), value2.stream())
                                .toList()
                ));
    }
}
