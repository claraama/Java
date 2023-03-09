package repository;

import domain.Weather;
import org.sqlite.SQLiteDataSource;
import java.sql.*;
import java.util.ArrayList;

public class Repository {
    private static final String JDBC_URL = "jdbc:sqlite:data/test_db.db";
    private ArrayList<Weather> weather=new ArrayList<>();

    public ArrayList<Weather> getWeather(){
        return weather;
    }
    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null)
            openConnection();
        return conn;
    }

    private static void openConnection()
    {
        try
        {
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(JDBC_URL);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeConnection()
    {
        try
        {
            conn.close();
            conn = null;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void readFromDB()
    {
        if(conn==null) openConnection();
        try{
            this.weather=new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Weather2");

            while(rs.next())
            {

                int start_hour = rs.getInt("start_hour");
                int end_hour = rs.getInt("end_hour");
                int temperture = rs.getInt("temperature");
                String precipitation_prob = rs.getString("precipitation_prob");
                String description= rs.getString("description");


                Weather weather1 = new Weather(start_hour,end_hour,temperture,precipitation_prob,description);

                weather.add(weather1);

            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
