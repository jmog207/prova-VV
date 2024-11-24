package org.example;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class InvoiceCalculatorTest {

    private final InvoiceCalculator calculator = new InvoiceCalculator();

    @Test // Testa o cálculo de fatura com produtos válidos e desconto
    public void testFaturaComProdutosValidosEDesconto() {
        List<Product> products = Arrays.asList(
            new Product("Produto A", 300.00, 2),
            new Product("Produto B", 200.00, 3)
        );
        double discount = 10.0;
        double expected = 1080.00; // Total = 1200 - 10% = 1080
        assertEquals(expected, calculator.calculateInvoice(products, discount));
    }

    @Test // Testa o cálculo com preço negativo no produto
    public void testFaturaComPrecoNegativo() {
        List<Product> products = Arrays.asList(
            new Product("Produto A", -100.00, 2)
        );
        
        InvalidProductException exception = assertThrows(InvalidProductException.class, () -> {
            calculator.calculateInvoice(products, 10.0);
        });
        
        assertEquals("Preço do produto não pode ser negativo", exception.getMessage());
    }

    @Test // Testa o cálculo com quantidade negativa no produto
    public void testFaturaComQuantidadeNegativa() {
        List<Product> products = Arrays.asList(
            new Product("Produto A", 100.00, -2)
        );
        assertThrows(InvalidProductException.class, () -> calculator.calculateInvoice(products, 10.0));
    }

    @Test // Testa o cálculo com desconto e total acima de R$ 1000
    public void testFaturaComDescontoAcimaDe1000() {
        List<Product> products = Arrays.asList(
            new Product("Produto A", 600.00, 2),
            new Product("Produto B", 500.00, 1)
        );
        double discount = 10.0;
        double expected = 1100.00; // Total = 1700 - 10% - 100 = 1100
        assertEquals(expected, calculator.calculateInvoice(products, discount));
    }

    @Test // Testa o cálculo de fatura sem desconto
    public void testFaturaSemDesconto() {
        List<Product> products = Arrays.asList(
            new Product("Produto A", 500.00, 1),
            new Product("Produto B", 500.00, 1)
        );
        double discount = 0.0;
        double expected = 1000.00; // Total = 500 + 500 = 1000
        assertEquals(expected, calculator.calculateInvoice(products, discount));
    }

    @Test // Testa o cálculo com total abaixo de R$ 1000 e sem desconto adicional
    public void testFaturaComTotalAbaixoDe1000() {
        List<Product> products = Arrays.asList(
            new Product("Produto A", 300.00, 1),
            new Product("Produto B", 400.00, 1)
        );
        double discount = 10.0;
        double expected = 630.00; // Total = 700 - 10% = 630 (sem desconto adicional)
        assertEquals(expected, calculator.calculateInvoice(products, discount));
    }
}
