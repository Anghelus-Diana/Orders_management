package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Client;
import model.Product;

public class ProductDAO {

    protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO product (name,stock,price)"
            + " VALUES (?,?,?)";
    private static final String editInsertStatementString = "UPDATE product"+" SET name=?,stock=?,price=?"+" WHERE id=?";
    private final static String findStatementString = "SELECT * FROM product where id = ?";
    private final static String findByNameStatementString = "SELECT * FROM product where name = ?";

    private final static String findAllProductsString = "SELECT * FROM product ";

    private final static String listDeleteStatementString = "SELECT name FROM product ";
    private final static String deleteStatementString="DELETE FROM product where name=?";

    public static Product findById(int productId) {
        Product toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, productId);
            rs = findStatement.executeQuery();
            rs.next();

            String name = rs.getString("name");
            int stock = rs.getInt("stock");
            int price = rs.getInt("price");
            toReturn = new Product(productId, name, stock, price);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    public static Product findByName(String productName) {
        Product toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findByNameStatementString);
            findStatement.setString(1, productName);
            rs = findStatement.executeQuery();
            rs.next();

            String name = rs.getString("name");
            int productId=rs.getInt("id");
            int stock = rs.getInt("stock");
            int price = rs.getInt("price");
            toReturn = new Product(productId, name, stock, price);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    public static List<Product> findAllProducts() {
        List<Product> products = new ArrayList<Product>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findAllProductsStatement = null;
        ResultSet rs = null;
        try {
            findAllProductsStatement = dbConnection.prepareStatement(findAllProductsString);
            rs = findAllProductsStatement.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("id");
                String name = rs.getString("name");
                int stock = rs.getInt("stock");
                int price = rs.getInt("price");

                Product product = new Product(productId, name, stock, price);
                products.add(product);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO:findAllProducts " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findAllProductsStatement);
            ConnectionFactory.close(dbConnection);
        }

        return products;
    }

    public static int insert(Product product) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, product.getName());
            insertStatement.setInt(2, product.getStock());
            System.out.println("Pret in dao "+product.getPrice()+"\n");
            insertStatement.setInt(3, product.getPrice());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    public static int editInsert(Product product) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement editInsertStatement = null;
        int insertedId = -1;
        try {
            editInsertStatement = dbConnection.prepareStatement(editInsertStatementString, Statement.RETURN_GENERATED_KEYS);
            editInsertStatement.setString(1, product.getName());
            editInsertStatement.setInt(2, product.getStock());
            editInsertStatement.setInt(3, product.getPrice());
            editInsertStatement.setInt(4, product.getId());
            editInsertStatement.executeUpdate();

            ResultSet rs = editInsertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:edit " + e.getMessage());
        } finally {
            ConnectionFactory.close(editInsertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    public static void delete(String product) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement deleteStatement = null;
        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setString(1, product);
            deleteStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }

    }

    public static List<String> listNames()
    {  List<String> toReturn = new ArrayList<>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement listDeleteStatement = null;
        ResultSet rs = null;
        try {
            listDeleteStatement = dbConnection.prepareStatement(listDeleteStatementString);
            rs = listDeleteStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                toReturn.add(name);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO:listOfProducts " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(listDeleteStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
}

