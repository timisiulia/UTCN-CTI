package Model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFloare {
    private String floare = "floare";
    protected Logger LOGGER = Logger.getLogger(floare);

    private String createSelectQuery() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        query.append(" * ");
        query.append(" FROM ");
        query.append(floare);
        return query.toString();
    }
    public List<Florarie> findAllGivenFlowerShops() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query=createSelectQuery() ;
        try{
            ArrayList<Floare>listaFlori= new ArrayList<>();
            List<Florarie>listaFlorari=new ArrayList<>();
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                String flowerType = resultSet.getObject("flowerType").toString();
                Float pretFloare = Float.valueOf(resultSet.getObject("pretFloare").toString());
                String culoareFloare = resultSet.getObject("culoareFloare").toString();
                Integer nrFlori= Integer.valueOf(resultSet.getObject("nrFlori").toString());
                String disponibilitateFloare=resultSet.getObject("disponibilitateFloare").toString();
                String numeFlorarie = resultSet.getObject("numeFlorarie").toString();

                Floare flower = new Floare(pretFloare,culoareFloare,nrFlori,disponibilitateFloare,flowerType);
                Florarie florarie= new Florarie(numeFlorarie,listaFlori);

                listaFlori.add(flower);
                listaFlorari.add(florarie);
            }
            //return listaFlori;
            return listaFlorari;

        } catch (SQLException throwables) {
            LOGGER.log(Level.WARNING, floare  + "DAO:findAll " + throwables.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
    public List<Floare> findAllGivenFlowers() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query=createSelectQuery() ;
        try{
            ArrayList<Floare>listaFlori= new ArrayList<>();
            List<Florarie>listaFlorari=new ArrayList<>();
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                String flowerType = resultSet.getObject("flowerType").toString();
                Float pretFloare = Float.valueOf(resultSet.getObject("pretFloare").toString());
                String culoareFloare = resultSet.getObject("culoareFloare").toString();
                Integer nrFlori= Integer.valueOf(resultSet.getObject("nrFlori").toString());
                String disponibilitateFloare=resultSet.getObject("disponibilitateFloare").toString();
                String numeFlorarie = resultSet.getObject("numeFlorarie").toString();

                Floare flower = new Floare(pretFloare,culoareFloare,nrFlori,disponibilitateFloare,flowerType);
                //   Florarie florarie= new Florarie(numeFlorarie,listaFlori);

                listaFlori.add(flower);
                // listaFlorari.add(florarie);
            }
            return listaFlori;
            // return listaFlorari;

        } catch (SQLException throwables) {
            LOGGER.log(Level.WARNING, floare  + "DAO:findAll " + throwables.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }



}
