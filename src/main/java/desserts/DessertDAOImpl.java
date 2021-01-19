package desserts;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DessertDAOImpl implements DessertDAO {

    final static String SELECT_ALL_SQL = "select id, name, good from goodstuff";
    final static String INSERT_SQL = "insert into goodstuff (name, good) values (?, ?)";
    final static String BY_NAME_SQL = "select id, name, good from goodstuff order by name asc";

    List<DessertDTO> desserts = new ArrayList<>();

    Connection conn = null;

    public DessertDAOImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/desserts?createDatabaseIfNotExist=true", "root", "root");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
        } catch (SQLException e) {
            System.out.println("SQL exception");
            e.printStackTrace();
        }
    }

    @Override
    public List<DessertDTO> getAll() {
        List<DessertDTO> desserts = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(SELECT_ALL_SQL);
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                boolean good = rs.getBoolean("good");
                desserts.add(new DessertDTO(id, name, good));
            }
        } catch (SQLException e) {
            System.out.println("unable to run query");
            e.printStackTrace();
        }
        return desserts;
    }

    public String getGoodDesserts(String dessertName, boolean good) {
        try (CallableStatement cs = conn.prepareCall("{CALL AddDessertAndArt(?, ?, ?)}")) {
            cs.setString(1, dessertName);
            cs.setInt(2, (good ? 1 : 0));
            cs.registerOutParameter(3, Types.VARCHAR);
            cs.executeUpdate();
            return cs.getString(3);
        } catch (SQLException e) {
            System.out.println("Unable to run query");
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Error Code: " + e.getErrorCode());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Example on how to call a stored procedure
     */
    public boolean isGood(Long dessertId) {
        try (CallableStatement cs = conn.prepareCall("{CALL GetIsGood(?, ?)}")) {
            cs.setLong(1, dessertId);
            cs.registerOutParameter(2, java.sql.Types.INTEGER);
            cs.executeUpdate();
            int good = cs.getInt(2);
            return (good == 1);
        } catch (SQLException e) {
            System.out.println("Unable to run query");
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Error Code: " + e.getErrorCode());
            e.printStackTrace();
        }
        return false;
    }

    public List<DessertDTO> sortedByName() {
        List<DessertDTO> desserts = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(BY_NAME_SQL);
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                boolean good = rs.getBoolean("good");
                desserts.add(new DessertDTO(id, name, good));
            }
        } catch (SQLException e) {
            System.out.println("unable to run query");
            e.printStackTrace();
        }
        return desserts;
    }

    @Override
    public DessertDTO create(DessertDTO dessert) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                INSERT_SQL,
                Statement.RETURN_GENERATED_KEYS
        )) {
            preparedStatement.setString(1, dessert.getName());
            preparedStatement.setInt(2, (dessert.isGood() ? 1 : 0));
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Unable to create record");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    dessert.setId(generatedKeys.getLong(1));
                    return dessert;
                } else {
                    throw new SQLException("Creating dessert failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            System.out.println("unable to run query");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(DessertDTO dessert) {
        desserts.remove(dessert);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public DessertDTO update(DessertDTO dessert) {
        return null;
    }

    @Override
    public int count() {
        return desserts.size();
    }

}
