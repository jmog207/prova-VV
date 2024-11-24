package org.example;

import java.util.List;

public class InvoiceCalculator {

    public double calculateInvoice(List<Product> products, double discount) {
        double total = 0.0;
    
        for (Product product : products) {
            // Verificação de preço ou quantidade negativa
            if (product.getPrice() < 0 || product.getQuantity() < 0) {
                throw new InvalidProductException("Produto com preço ou quantidade inválida");
            }
            total += product.getPrice() * product.getQuantity();
        }
    
        // Aplica o desconto
        total -= total * (discount / 100);
    
        // Se o total for maior que 1000, aplica o desconto adicional
        if (total > 1000) {
            total -= 100.0;
        }
    
        return total;
    }
    
}
