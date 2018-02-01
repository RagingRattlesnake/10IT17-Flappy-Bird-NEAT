package neat;

import game.Bird;
import game.Settings;

import java.util.ArrayList;
import java.sql.*;

public class Generations {
    private static ArrayList<Bird> prevGeneration;

    static ArrayList<Bird> getPrevGeneration() {
        return prevGeneration;
    }
    private static MySQLConnect mysqlConnect = new MySQLConnect();
    private static Connection db = mysqlConnect.connect();

    public static void disconnect(){
        mysqlConnect.disconnect();
    }

    public static void setPrevGeneration(ArrayList<Bird> prevGeneration) {
        Generations.prevGeneration = prevGeneration;

        String sql = "INSERT INTO `fitness` (`generation`, `max-fitness`, `highest`, `score`) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement statement = db.prepareStatement(sql);

            prevGeneration.sort((Bird o1, Bird o2) -> -(o1.getFitness()-o2.getFitness()));

            statement.setInt(1, Settings.GENERATION );
            statement.setInt(2, Settings.MAX_FITNESS);
            statement.setInt(3, prevGeneration.get(0).getFitness());
            statement.setInt(4, Settings.SCORE/250);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
