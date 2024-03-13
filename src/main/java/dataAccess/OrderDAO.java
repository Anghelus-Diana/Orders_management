package dataAccess;

import connection.ConnectionFactory;
import model.Client;
import model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDAO {
    protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO  `order` (clientName,productName,quantity)"
            + " VALUES (?,?,?)";
    private final static String findAllOrdersString = "SELECT * FROM `order` ";

    private final static String listDeleteStatementString = "SELECT id FROM `order` ";
    private final static String deleteStatementString="DELETE FROM client where id=?";

    public static List<Order> findAllOrders() {
        List<Order> orders = new ArrayList<Order>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findAllOrdersStatement = null;
        ResultSet rs = null;
        try {
            findAllOrdersStatement = dbConnection.prepareStatement(findAllOrdersString);
            rs = findAllOrdersStatement.executeQuery();

            while (rs.next()) {
                int orderId = rs.getInt("id");
                String clientName = rs.getString("clientName");
                String productName = rs.getString("productName");
                int quantity = rs.getInt("quantity");

                Order order = new Order(orderId, clientName, productName, quantity);
                orders.add(order);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"OrderDAO:findAllOrders " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findAllOrdersStatement);
            ConnectionFactory.close(dbConnection);
        }
        return orders;
    }

    public static int insert(Order order) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {

            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, order.getClientName());
            insertStatement.setString(2, order.getProductName());
            insertStatement.setInt(3, order.getQuantity());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    public static void delete(int orderId) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement deleteStatement = null;
        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setInt(1, orderId);
            deleteStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }

    }

    public static List<Integer> listId()
    {  List<Integer> toReturn = new ArrayList<>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement listDeleteStatement = null;
        ResultSet rs = null;
        try {
            listDeleteStatement = dbConnection.prepareStatement(listDeleteStatementString);
            rs = listDeleteStatement.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt("id");
                toReturn.add(orderId);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"OrderDAO:listOfClients " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(listDeleteStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
}
