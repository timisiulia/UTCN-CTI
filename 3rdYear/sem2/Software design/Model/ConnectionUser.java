package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionUser {
    private String utilizator = "utilizator";
    protected Logger LOGGER = Logger.getLogger(utilizator);

    private String createSelectQuery() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        query.append(" * ");
        query.append(" FROM ");
        query.append(utilizator);
        return query.toString();
    }
    public List<Utilizator> findGivenUsers() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query=createSelectQuery() ;
        try{
            List<Utilizator>listaUtilizatori= new ArrayList<>();
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                String username = resultSet.getObject("username").toString();
                String password = resultSet.getObject("password").toString();
                String role = resultSet.getObject("role").toString();
                Utilizator user = new Utilizator(username,password,role);
                listaUtilizatori.add(user);
            }
            return listaUtilizatori;

        } catch (SQLException throwables) {
            LOGGER.log(Level.WARNING, utilizator  + "DAO:findAll " + throwables.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }



}

