package net.depo.dao;

import net.depo.beans.Transport;
import net.depo.db.ConnectionManager;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by andmat on 2019-08-18.
 */
public class MainDAO {

    public static List<Transport> getAllTransport() throws SQLException {

        String query = "select tr.transport_id, tr.number, cast(tr.created_at as char) as created_at, " +
                "cast(tr.modified_at as char) as modified_at, kd.kind, pk.park_number, pk.park_type, " +
                "lc.width, lc.length, td.capacity, td.tonnage,\n" +
                "(select group_concat( tp.type separator ', ')\n" +
                "from depo.types tp, depo.transport_type tt\n" +
                "where tr.transport_id = tt.transport_id and tt.type_id = tp.type_id\n" +
                ") as transport_type\n" +
                "from depo.transport tr, depo.kinds kd, depo.parks pk, depo.location lc, depo.transport_details td\n" +
                "where tr.kind_id = kd.kind_id and tr.park_id = pk.park_id and tr.transport_id = lc.transport_id and tr.transport_id = td.transport_id\n" +
                "group by tr.transport_id \n" +
                "order by tr.transport_id;";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {


            List<Transport> transportArray = new ArrayList<>();
            while (resultSet.next()) {
                int transport_id = resultSet.getInt("transport_id");
                String number = resultSet.getString("number");
//                Timestamp created_at = resultSet.getTimestamp("created_at");
//                Timestamp modified_at = resultSet.getTimestamp("modified_at");
                String created_at = resultSet.getString("created_at");
                String modified_at = resultSet.getString("modified_at");
                String kind = resultSet.getString("kind");
                int park_number = resultSet.getInt("park_number");
                String park_type = resultSet.getString("park_type");
                BigDecimal width = resultSet.getBigDecimal("width");
                BigDecimal length = resultSet.getBigDecimal("length");
                int capacity = resultSet.getInt("capacity");
                double tonnage = resultSet.getDouble("tonnage");
                String transport_type = resultSet.getString("transport_type");

//                LocalDateTime dateTime = created_at.toLocalDateTime();

                transportArray.add(new Transport(transport_id, number, created_at, modified_at, kind, park_number,
                        park_type, width, length, capacity, tonnage, transport_type));

                getMaxId();
                //TROUBLE WITH TIME ZONE !!!!!!!!!!!!
//                Timestamp time = (System.currentTimeMillis(created_at) - TimeUnit.HOURS.toMillis(4));
            }
            return transportArray;
        }
    }

    public static void deleteTransport(int transport_id) throws SQLException {
        String query = "delete from transport where transport_id = ?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, transport_id);
            preparedStatement.executeUpdate();
        }
    }

    public static void addToTransport(String number, String kind, String park_number) {
        String query = "insert into depo.transport (number, created_at, kind_id, park_id) " +
                "values (?, current_time, (select kind_id from kinds where kind = ?), " +
                "(select park_id from parks where park_number = ?))";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, number);
            preparedStatement.setString(2, kind);
            preparedStatement.setInt(3, Integer.parseInt(park_number));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addToDetails(String capacity) {
        String query = "insert into depo.transport_details (transport_id, capacity) " +
                "values ((select max(transport_id) from depo.transport), ?) ";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, Integer.parseInt(capacity));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addToTypes(String[] types) {
        String query = "insert into depo.transport_type (transport_id, type_id) " +
                "values ((select max(transport_id) from depo.transport), " +
                "(select depo.types.type_id from depo.types where depo.types.type = ?))";
        if (getMaxId2() != Transport.max) {
            try (Connection connection = ConnectionManager.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                if (types[types.length - 1] != null) {
                    for (int i = 0; i < types.length; i++) {
                        preparedStatement.setString(1, types[i]);
                        preparedStatement.executeUpdate();
                    }
                } else {
                    preparedStatement.setString(1, types[0]);
                    preparedStatement.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static void addToLocation(String width, String length) {
        String query = "insert into depo.location (transport_id, length, width) " +
                "VALUES ((select max(transport_id) from depo.transport), ?, ?)";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDouble(1, Double.parseDouble(width));
            preparedStatement.setDouble(2, Double.parseDouble(length));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getMaxId() {
        String id = "select max(transport_id) as id from depo.transport";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(id);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Transport.max = resultSet.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getMaxId2() {
        String id = "select max(transport_id) as id from depo.transport";

        int max = 0;
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(id);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                max = resultSet.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return max;
    }

    public static void addToPark(String park_number, String park_type) {
        String query = "insert into depo.parks (park_number, park_type) " +
                "values (?, ?)";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, Integer.parseInt(park_number));
            preparedStatement.setString(2, park_type);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updatePark(String current_park_number, String current_park_type, String edited_park_number) {
        String query = "update depo.parks pk set pk.park_number = ?, pk.park_type = ? where pk.park_number = ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, Integer.parseInt(current_park_number));
            preparedStatement.setString(2, current_park_type);
            preparedStatement.setInt(3, Integer.parseInt(edited_park_number));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}