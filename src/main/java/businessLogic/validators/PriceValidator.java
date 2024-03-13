package businessLogic.validators;

import model.Product;

    public class PriceValidator implements Validator<Product> {

        /**
         * Valideaza un produs verificand daca prețul este pozitiv.
         *
         * @param t produsul de validat
         * @throws IllegalArgumentException daca pretul produsului este negativ
         */
        public void validate(Product t) {
            if (t.getPrice() < 0) {
                throw new IllegalArgumentException("The Price can not be negative");
            }

        }

    }


