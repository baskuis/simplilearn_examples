package desserts;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DessertDAOImpl implements GenericDAO<DessertDTO> {

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
    public List<DessertDTO> getDesserts() {
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

    public interface GetGood {
        boolean good(DessertDTO dessertDTO);
    }

    public interface Convert<I,O> {
        O execute(I in);
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
        try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT_SQL)) {
            preparedStatement.setString(1, dessert.getName());
            preparedStatement.setInt(2, (dessert.isGood() ? 1 : 0));
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("unable to run query");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void eat(DessertDTO dessert) {
        desserts.remove(dessert);
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
