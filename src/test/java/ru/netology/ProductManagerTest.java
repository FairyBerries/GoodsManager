package ru.netology;

import org.junit.jupiter.api.Test;
import ru.netology.domain.*;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {

    @Test
    public void ShouldProductEmpty() {
        Product[] expectedProduct = new Product[0];

        ProductRepository repository = new ProductRepository();
        ProductManager productManager = new ProductManager(repository);

        Product[] foundedProduct = productManager.searchBy("book1");

        assertArrayEquals(expectedProduct, foundedProduct);
    }

    @Test
    public void add() {
        Book book1 = new Book(1, "book1", 100, "Vasya Pupkin");
        Product[] expectedProduct = new Product[1];
        expectedProduct[0] = book1;

        ProductRepository repository = new ProductRepository();
        ProductManager productManager = new ProductManager(repository);

        productManager.add(book1);

        Product[] foundedProduct = productManager.searchBy("book1");

        assertArrayEquals(expectedProduct, foundedProduct);
    }

    @Test
    void searchBy() {
        Book book1 = new Book(1, "book1", 100, "Vasya Pupkin");
        Smartphone phone1 = new Smartphone(1, "phone1", 200, "Apple");
        Product[] expectedProduct = new Product[1];
        expectedProduct[0] = phone1;

        ProductRepository repository = new ProductRepository();
        ProductManager productManager = new ProductManager(repository);

        productManager.add(book1);
        productManager.add(phone1);

        Product[] foundedProduct = productManager.searchBy("phone1");

        assertArrayEquals(expectedProduct, foundedProduct);
    }

    @Test
    void shouldNotFound() {
        Book book1 = new Book(1, "book1", 100, "Vasya Pupkin");
        Smartphone phone1 = new Smartphone(1, "phone1", 200, "Apple");
        Product[] expectedProduct = new Product[0];

        ProductRepository repository = new ProductRepository();
        ProductManager productManager = new ProductManager(repository);

        productManager.add(book1);
        productManager.add(phone1);

        Product[] foundedProduct = productManager.searchBy("Garry Potter");

        assertArrayEquals(expectedProduct, foundedProduct);
    }

    @Test
    void matches() {
        Book book1 = new Book(1, "book1", 100, "Vasya Pupkin");
        Smartphone phone1 = new Smartphone(1, "phone1", 200, "Apple");
        Product[] expectedProduct = new Product[1];
        expectedProduct[0] = phone1;

        ProductRepository repository = new ProductRepository();
        ProductManager productManager = new ProductManager(repository);

        productManager.add(book1);
        productManager.add(phone1);

        Product[] foundedProduct = productManager.searchBy("phone1");

        assertArrayEquals(expectedProduct, foundedProduct);
    }

    @Test
    void shouldAllMatches() {
        Book book1 = new Book(1, "book about phone", 100, "Vasya Pupkin");
        Smartphone phone1 = new Smartphone(1, "phone", 200, "Apple");
        Product[] expectedProduct = new Product[2];
        expectedProduct[0] = book1;
        expectedProduct[1] = phone1;

        ProductRepository repository = new ProductRepository();
        ProductManager productManager = new ProductManager(repository);

        productManager.add(book1);
        productManager.add(phone1);

        Product[] foundedProduct = productManager.searchBy("phone");

        assertArrayEquals(expectedProduct, foundedProduct);
    }
}