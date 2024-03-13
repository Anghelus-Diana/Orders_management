package businessLogic.validators;

import model.Product;

public class StockValidator implements Validator<Product> {

    /**
     * Valideaza un produs verificand daca stock-ul este pozitiv.
     *
     * @param t produsul de validat
     * @throws IllegalArgumentException daca stock-ul produsului este negativ
     */
    public void validate(Product t) {

        if (t.getStock() < 0) {
            throw new IllegalArgumentException("The Price can not be negative");
        }

    }

}