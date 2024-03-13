package dataAccess;

import connection.ConnectionFactory;
import model.Bill;
import model.Client;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.reflect.*;


public class AbstractDAO<T> {
    /*protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    /*private String createSelectAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        //sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    private String insertStatement(T t) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append(" (");
        Field[] fields = type.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            sb.append(fields[i].getName());
            if (i < fields.length - 1) {
                sb.append(",");
            }
        }
        sb.append(") VALUES (");
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            try {
                Object value = fields[i].get(t);
                sb.append("?");
                if (i < fields.length - 1) {
                    sb.append(",");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        sb.append(")");

        return sb.toString();
    }

   /* public List<T> findAll() {
        List<T> objects = new ArrayList<T>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findAllStatement = null;
        String query;
        ResultSet rs = null;
        try {
            findAllStatement = dbConnection.prepareStatement(createSelectAllQuery());
            rs = findAllStatement.executeQuery();

            return (List<T>) createObjects(rs).get(0);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findAllStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T) ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int insert(T t) {
            Connection dbConnection = ConnectionFactory.getConnection();

            PreparedStatement insertStatement = null;
            int insertedId = -1;
            try {
                Field[] fields = type.getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    fields[i].setAccessible(true);
                    Object value = fields[i].get(t);
                    insertStatement.setObject(i + 1, value);
                }
                insertStatement.executeUpdate();

                ResultSet rs = insertStatement.getGeneratedKeys();
                if (rs.next()) {
                    insertedId = rs.getInt(1);
                }
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } finally {
                ConnectionFactory.close(insertStatement);
                ConnectionFactory.close(dbConnection);
            }
            return insertedId;

    }*/
}
