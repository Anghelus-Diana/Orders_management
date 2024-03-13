package dataAccess;

import connection.ConnectionFactory;
import model.Bill;
import model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BillDAO {
    protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO  bills (clientName,productName,price,quantity)"
            + " VALUES (?,?,?,?)";
    private final static String findAllBillsString = "SELECT * FROM bills ";

    public static List<Bill> findAllBills() {
        List<Bill> bills = new ArrayList<Bill>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findAllBillsStatement = null;
        ResultSet rs = null;
        try {
            findAllBillsStatement = dbConnection.prepareStatement(findAllBillsString);
            rs = findAllBillsStatement.executeQuery();

            while (rs.next()) {
                int billId = rs.getInt("id");
                String clientName = rs.getString("clientName");
                String productName = rs.getString("productName");
                int price=rs.getInt("price");
                int quantity = rs.getInt("quantity");

                Bill bill = new Bill(billId, clientName, productName,price, quantity);
                bills.add(bill);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"BillDAO:findAllBills " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findAllBillsStatement);
            ConnectionFactory.close(dbConnection);
        }
        return bills;
    }

    public static int insert(Bill bill) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {

            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, bill.clientName());
            insertStatement.setString(2, bill.productName());
            insertStatement.setInt(3, bill.price());
            insertStatement.setInt(4, bill.quantity());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "BillDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }
}
