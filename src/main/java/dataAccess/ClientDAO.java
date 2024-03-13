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

public class ClientDAO {

    protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO client (name,address,email)"
            + " VALUES (?,?,?)";
    private static final String editInsertStatementString = "UPDATE client"+" SET name=?,address=?,email=?"+" WHERE id=?";
    private final static String findStatementString = "SELECT * FROM client where id = ?";

    private final static String findAllClientsString = "SELECT * FROM client ";

    private final static String listDeleteStatementString = "SELECT name FROM client ";
    private final static String deleteStatementString="DELETE FROM client where name=?";

    public static Client findById(int clientId) {
        Client toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, clientId);
            rs = findStatement.executeQuery();
            rs.next();

            String name = rs.getString("name");
            String address = rs.getString("address");
            String email = rs.getString("email");
            toReturn = new Client(clientId, name, address, email);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    public static List<Client> findAllClients() {
        List<Client> clients = new ArrayList<Client>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findAllClientsStatement = null;
        ResultSet rs = null;
        try {
            findAllClientsStatement = dbConnection.prepareStatement(findAllClientsString);
            rs = findAllClientsStatement.executeQuery();

            while (rs.next()) {
                int clientId = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String email = rs.getString("email");

                Client client = new Client(clientId, name, address, email);
                clients.add(client);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:findAllClients " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findAllClientsStatement);
            ConnectionFactory.close(dbConnection);
        }

        return clients;
    }

    /**
     * Insereaza un nou client în baza de date.
     *
     * @param client obiectul de tip Client care trebuie inserat in baza de date
     * @return ID-ul clientului inserat sau -1 in cazul in care inserarea a eșuat
     */
    public static int insert(Client client) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, client.getName());
            insertStatement.setString(2, client.getAddress());
            insertStatement.setString(3, client.getEmail());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    public static int editInsert(Client client) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement editInsertStatement = null;
        int insertedId = -1;
        try {
            editInsertStatement = dbConnection.prepareStatement(editInsertStatementString, Statement.RETURN_GENERATED_KEYS);
            editInsertStatement.setString(1, client.getName());
            editInsertStatement.setString(2, client.getAddress());
            editInsertStatement.setString(3, client.getEmail());
            editInsertStatement.setInt(4, client.getId());
            editInsertStatement.executeUpdate();

            ResultSet rs = editInsertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:edit " + e.getMessage());
        } finally {
            ConnectionFactory.close(editInsertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    public static void delete(String client) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement deleteStatement = null;
        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setString(1, client);
            deleteStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:delete " + e.getMessage());
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
            LOGGER.log(Level.WARNING,"ClientDAO:listOfClients " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(listDeleteStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
}

