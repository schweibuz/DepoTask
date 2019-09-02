package net.depo.dao;

import net.depo.beans.Transport;
import net.depo.db.ConnectionManager;
import org.json.JSONArray;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andmat on 2019-08-23.
 */
public class DbAttributeDAO {

    public static List<Transport> getKinds() throws SQLException {
        String query = "select kd.kind from depo.kinds kd";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement prepare = connection.prepareStatement(query);
             ResultSet resultSet = prepare.executeQuery()) {

            ArrayList<Transport> kinds = new ArrayList<>();

            while (resultSet.next()) {
                String kind = resultSet.getString("kind");

                kinds.add(new Transport(kind));
            }
            return kinds;
        }
    }

    public static List<Transport> getParks() throws SQLException {
        String query = "select pk.park_id, pk.park_type, pk.park_number from depo.parks pk";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement prepare = connection.prepareStatement(query);
             ResultSet resultSet = prepare.executeQuery()) {

            ArrayList<Transport> parks = new ArrayList<>();

            while (resultSet.next()) {
                int park_id = resultSet.getInt("park_id");
                String park_type = resultSet.getString("park_type");
                int park_number = resultSet.getInt("park_number");

                parks.add(new Transport(park_id, park_type, park_number));
            }
            return parks;
        }
    }

    public static String[] selectNumbers() {
        String query = "select tr.number from depo.transport tr;";

//        JSONArray arr = new JSONArray();
        String[] numbers = new String[100];
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            int i = 0;
            while (resultSet.next()) {
                String number = resultSet.getString("number");
//                numbers.add(number);
                numbers[i] = number;
                i++;
//                arr.put(number);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numbers;
    }
}

