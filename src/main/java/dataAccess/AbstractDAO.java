package dataAccess;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import dataAccess.connectionFactory.Connector;

public class AbstractDAO<T>{
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    /**
     * This function is used to generate select querys
     * @param field
     * @return a select query
     */

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * This function retuns all the objects found in the db of that certain type T
     * @return resultSet
     */
    public List<T> findAll() {
        List<T> res = new ArrayList<T>();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());


        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = sb.toString();
        try {
            connection = Connector.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connector.close(resultSet);
            Connector.close(statement);
            Connector.close(connection);
        }
        return null;
    }

    /**
     *
     * @param id
     * @return returns the object with the ID equal to the param
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = Connector.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            Connector.close(resultSet);
            Connector.close(statement);
            Connector.close(connection);
        }
        return null;
    }

    /**
     * Transfroms the resultSet into a list of objects
     * @param resultSet
     * @return a list of the objects
     */
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
                T instance = (T)ctor.newInstance();
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

    /**
     * Creates the insert Query
     * @param t
     * @return
     * @throws IllegalAccessException
     */
    private String createInsertStatement(T t) throws IllegalAccessException {

        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append(" ( ");
        for (Field field : type.getDeclaredFields()) {
            String fieldName = field.getName();
            if (!fieldName.equals("id")){
                sb.append(fieldName);
                sb.append(",");
            }
        }
        if(sb.length() > 0){
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append(" ) ");
        sb.append(" VALUES ");
        sb.append(" ( ");
        for (Field field : type.getDeclaredFields()) {
            if(field.getName() == "id")
                continue;
            field.setAccessible(true);
            Object value = null;
            value = field.get(t);
            sb.append("\"" +value +"\"");
            sb.append(",");
        }
        if(sb.length() > 0){
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append(");");
        //System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * Interogates the date base
     * @param t
     * @return t
     */
    public T insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String query = createInsertStatement(t);
            connection = Connector.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * Creates the update Statement
     * @param t
     * @return String
     * @throws IllegalAccessException
     */
    private String createUpdateStatement (T t) throws IllegalAccessException {

        int id = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");
        for (Field field : type.getDeclaredFields()) {
            String fieldName = field.getName();
            field.setAccessible(true);
            Object value = null;
            value = field.get(t);

            if (!fieldName.equals("id")) {
                sb.append(fieldName);
                sb.append(" = ");
                sb.append("\""+value+"\"");
                sb.append(",");
            } else {
                id = Integer.parseInt(value.toString());
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        sb.append(" WHERE ");
        sb.append(" id =  ");
        sb.append(id);
        //System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     *
     * @param t
     * @return
     */
    public T update(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String query = createUpdateStatement(t);
            connection = Connector.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * Creates a delete Statement
     * @param id
     * @return String
     */
    private String createDeleteStatement(int id){
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE id = " + id);
        return sb.toString();
    }

    /**
     * Does the delete query on the db
      * @param id
     */
    public void delete(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String query = createDeleteStatement(id);
            connection = Connector.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int getRowCount(){
        List<T> list = findAll();
        return list.size();
    }
}
