package desserts;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DessertDAOImpl implements GenericDAO<DessertDTO> {

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
        String query = "select id, name, good from goodstuff";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
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

    public static interface GetGood {
        boolean good(DessertDTO dessertDTO);
    }

    public static interface Convert<I,O> {
        O execute(I in);
    }

    public List<String> sortedByName() {

        Convert<DessertDTO, String> out = (DessertDTO dessertDTO) -> {
            return dessertDTO.name;
        };

        return desserts.stream()
                .sorted(DessertDTO::compareByName)
                .map(dessertDTO -> dessertDTO.name)
                .collect(Collectors.toList());
    }

    @Override
    public DessertDTO create(DessertDTO dessert) {
        desserts.add(dessert);
        return dessert;
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
