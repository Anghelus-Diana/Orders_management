package businessLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import businessLogic.validators.EmailValidator;

import businessLogic.validators.PriceValidator;
import businessLogic.validators.StockValidator;
import businessLogic.validators.Validator;
import dataAccess.ClientDAO;
import dataAccess.ProductDAO;
import model.Client;
import model.Product;


public class ProductBLL {

    private List<Validator<Product>> validatorsStock;
    private List<Validator<Product>> validatorsPrice;


    public ProductBLL() {
        validatorsStock = new ArrayList<Validator<Product>>();
        validatorsStock.add(new StockValidator());
        validatorsPrice = new ArrayList<Validator<Product>>();
        validatorsPrice.add(new PriceValidator());
    }

    /**
     * Gaseste un produs in baza de date dupa ID.
     *
     * @param id ID-ul produsului de cautat in baza de date
     * @return obiectul de tip Produs cu ID-ul specificat
     * @throws NoSuchElementException daca nu exista un produs cu ID-ul specificat în baza de date
     */
    public Product findProductById(int id) {
        Product st = ProductDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return st;
    }

    /**
     * Insereaza un nou produs in baza de date și valideaza datele acestuia folosind
     * un set de obiecte Validator specificate.
     *
     * @param product obiectul Produs de inserat
     * @return id-ul generat pentru produsul inserat
     */
    public int insertProduct(Product product) {
        for (Validator<Product> v : validatorsStock) {
            v.validate(product);
        }
        for (Validator<Product> v : validatorsPrice) {
            v.validate(product);
        }
        System.out.println("Price in bll "+product.getPrice()+"\n");
        return ProductDAO.insert(product);
    }

    /**
     * Editeaza un nou produs in baza de date și valideaza datele acestuia folosind
     * un set de obiecte Validator specificate.
     *
     * @param product obiectul Produs de editat
     * @return id-ul generat pentru produsul editat
     */
    public int editInsertStudent(Product product) {
        for (Validator<Product> v : validatorsStock) {
            v.validate(product);
        }
        for (Validator<Product> v : validatorsPrice) {
            v.validate(product);
        }
        return ProductDAO.editInsert(product);
    }

    /**
     * Sterge un produs din baza de date.
     *
     * @param product numele produsului de sters
     */
    public void deleteStudent(String product) {
        ProductDAO.delete(product);
    }

    /**
     * Returneaza o lista de nume ale produselor care pot fi sterse din baza de date.
     *
     * @return o lista de nume ale produselor care pot fi sterse din baza de date
     */
    public List<String> listNamesDelete()
    {
        List<String>names=ProductDAO.listNames();
        return names;
    }

    /**
     * Returneaza o lista cu toti produsii din baza de date.
     *
     * @return o lista cu toti produsii din baza de date
     */
    public List<Product> findAllProducts()
    {
        List<Product> allProducts=ProductDAO.findAllProducts();
        return allProducts;
    }
}
