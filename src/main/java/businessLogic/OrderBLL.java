package businessLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import businessLogic.validators.EmailValidator;

import businessLogic.validators.Validator;
import dataAccess.ClientDAO;
import dataAccess.OrderDAO;
import dataAccess.ProductDAO;
import model.Client;
import model.Order;
import model.Product;


public class OrderBLL {

    private List<Validator<Client>> validators;

    public OrderBLL() {
    }

    /**
     * Insereaza o noua comanda in baza de date și valideaza datele acesteia folosind
     * un set de obiecte Validator specificate.
     *
     * @param order obiectul Order de inserat
     * @return id-ul generat pentru orderul inserat
     */
    public int insertOrder(Order order) {
            return OrderDAO.insert(order);
    }

            /**
             * Sterge o comanda din baza de date.
             *
             * @param orderId id-ul comenzii de sters
             */
            public void deleteOrder(int orderId) {
            OrderDAO.delete(orderId);
            }

            /**
             * Returneaza o lista de IDs ale comenzilor care pot fi sterse din baza de date.
             *
             * @return o lista de IDs ale comenzilor care pot fi sterse din baza de date
             */
            public List<Integer> listIdDelete()
            {
            List<Integer>ids=OrderDAO.listId();
            return ids;
            }

            /**
             * Returneaza o lista cu toate comenzile din baza de date.
             *
             * @return o lista cu toate comenzile din baza de date
             */
            public List<Order> findAllOrders()
            {
                 List<Order> allOrders=OrderDAO.findAllOrders();
                return allOrders;
            }
        }


