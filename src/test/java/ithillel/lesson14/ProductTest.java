package ithillel.lesson14;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    private static final List<Product> PRODUCT_LIST = List.of(
            new Product("Book", 250, true, LocalDate.of(2023, 1, 1)),
            new Product("Food", 12, true, LocalDate.of(2023, 1, 2)),
            new Product("Book", 50, false, LocalDate.of(2023, 1, 3)),
            new Product("Furniture", 2500, false, LocalDate.of(2023, 1, 4)),
            new Product("Book", 123, false, LocalDate.of(2023, 1, 5)),
            new Product("Book", 300, true, LocalDate.of(2023, 1, 6)),
            new Product("Book", 50, true, LocalDate.of(2023, 1, 7)),
            new Product("Furniture", 2000, true, LocalDate.of(2023, 1, 8)),
            new Product("Food", 75, true, LocalDate.of(2023, 1, 9)),
            new Product("Book", 100, true, LocalDate.of(2022, 12, 31))
            );

    private static final List<Product> BOOK_LIST = List.of(
            new Product("Book", 300, true, LocalDate.of(2023, 1, 6))
    );

    private static final List<Product> BOOK_SALES_LIST = List.of(
            new Product("Book", 250*0.9, true, LocalDate.of(2023, 1, 1)),
            new Product("Book", 300*0.9, true, LocalDate.of(2023, 1, 6)),
            new Product("Book", 50*0.9, true, LocalDate.of(2023, 1, 7)),
            new Product("Book", 100*0.9, true, LocalDate.of(2022, 12, 31))
    );

    private static final List<Product> THREE_LAST_PRODUCTS_LIST = List.of(
            new Product("Food", 75, true, LocalDate.of(2023, 1, 9)),
            new Product("Furniture", 2000, true, LocalDate.of(2023, 1, 8)),
            new Product("Book", 50, true, LocalDate.of(2023, 1, 7))

    );

    private static final Map<String, List<Product>> PRODUCT_MAP = Map.of(
            "Book", List.of(
                    new Product("Book", 250, true, LocalDate.of(2023, 1, 1)),
                    new Product("Book", 50, false, LocalDate.of(2023, 1, 3)),
                    new Product("Book", 123, false, LocalDate.of(2023, 1, 5)),
                    new Product("Book", 300, true, LocalDate.of(2023, 1, 6)),
                    new Product("Book", 50, true, LocalDate.of(2023, 1, 7)),
                    new Product("Book", 100, true, LocalDate.of(2022, 12, 31))
            ),
            "Food", List.of(
                    new Product("Food", 12, true, LocalDate.of(2023, 1, 2)),
                    new Product("Food", 75, true, LocalDate.of(2023, 1, 9))
            ),
            "Furniture", List.of(
                    new Product("Furniture", 2500, false, LocalDate.of(2023, 1, 4)),
                    new Product("Furniture", 2000, true, LocalDate.of(2023, 1, 8))
            )
    );
    @Test
    void theCheapestBook() {
        assertEquals(Product.theCheapestBook(PRODUCT_LIST),new Product("Book", 50, false, LocalDate.of(2023, 1, 3)));
    }

    @Test
    void booksFrom() {
        assertEquals(Product.booksFrom(PRODUCT_LIST), BOOK_LIST);
    }

    @Test
    void booksWithSale() {
        assertEquals(Product.booksWithSale(PRODUCT_LIST), BOOK_SALES_LIST);
    }

    @Test
    void threeLastProducts() {
        assertEquals(Product.threeLastProducts(PRODUCT_LIST), THREE_LAST_PRODUCTS_LIST);
    }

    @Test
    void calculateBooks() {
        assertEquals(Product.calculateBooks(PRODUCT_LIST), 100);
    }

    @Test
    void groupByCategory() {
        assertEquals(Product.groupByCategory(PRODUCT_LIST), PRODUCT_MAP);
    }
}