package dao;

import connection.ConnectionFactory;
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


public abstract class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private String createSelectQuery(String field) {
        StringBuilder interogare = new StringBuilder();
        interogare.append("SELECT ");
        interogare.append(" * ");
        interogare.append(" FROM ");
        interogare.append(type.getSimpleName());
        interogare.append(" WHERE " + field + " =?");
        return interogare.toString();
    }

    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String interogare= "SELECT * FROM " + type.getSimpleName() ;
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(interogare);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException throwables) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + throwables.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;        }
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

    public String insertInstruction(){
        StringBuilder s=new StringBuilder();
        s.append("INSERT INTO ");
        return s.toString();

    }
    public String insertValues(){
        StringBuilder v=new StringBuilder();
        v.append("VALUES(");
        return v.toString();
    }
    public void insert(T t) {
        String ss=insertInstruction();
        String vv=insertValues();
        Connection connection = null;
        PreparedStatement statement = null;
        String interogare= ss+ type.getSimpleName()+" " + vv;

        try {
            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                Object obj = field.get(t);
                if(obj instanceof String){
                    interogare = interogare + "  '" +obj.toString()+ "',";
                }else{
                    interogare = interogare + obj.toString() + ",";
                }
            }
            interogare = interogare.substring(0,interogare.length()-1)+ ")";
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(interogare);
            System.out.println(interogare);
            statement.execute();
        } catch (IllegalAccessException | SQLException e){
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
    public String updateInstruction(){
        StringBuilder s=new StringBuilder();
        s.append("UPDATE ");
        return s.toString();

    }
    public String updateValues(){
        StringBuilder v=new StringBuilder();
        v.append("SET ");
        return v.toString();
    }


    public void update(T t) {

        String ss=updateInstruction();
        String vv=updateValues();
        Connection connection = null;
        PreparedStatement statement = null;
        String interogare = ss + type.getSimpleName()+" " + vv;
        int id=0;

        try {
            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                Object obj = field.get(t);
                if(field.getName().equals("id")){
                    id=(int) obj;
                }else{
                    if(obj instanceof String){
                        interogare = interogare + field.getName() + "='" + obj.toString() + "',";
                    }else{
                        interogare = interogare + field.getName() + "=" + obj.toString() + ",";
                    }
                }
            }
            interogare = interogare.substring(0,interogare.length()-1)+ " ";
            interogare += "WHERE id=?";
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(interogare);
            statement.setInt(1, id);
            statement.execute();


        } catch (IllegalAccessException | SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        }finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
    public String deleteInstruction(){
        StringBuilder s=new StringBuilder();
        s.append("DELETE FROM ");
        return s.toString();

    }
    public String deleteValues(){
        StringBuilder v=new StringBuilder();
        v.append("WHERE id= ");
        return v.toString();
    }
    private int getId(T t) throws IllegalAccessException {

        for (Field field : type.getDeclaredFields()) {
            field.setAccessible(true);
            Object obj = field.get(t);
            if(field.getName().equals("id")){
                return  (int) obj;
            }
        }
        return 0;

    }
    public void delete(T t){

        String ss=deleteInstruction();
        String vv=deleteValues();
        Connection connection = null;
        PreparedStatement statement = null;
        String interogare = ss + type.getSimpleName() +" "+ vv;
        try {
            interogare = interogare +getId(t);
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(interogare);
            statement.execute();
        } catch (IllegalAccessException | SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}