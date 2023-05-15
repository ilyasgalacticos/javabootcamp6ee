package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBConnector {

    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:8889/bootcamp6db",
                    "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Item> getItems() {
        ArrayList<Item> items = new ArrayList<>();
        try {

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT it.id, it.price, it.name, it.country_id, it.country_id, co.code, co.name AS countryName " +
                    "FROM items AS it " +
                    "INNER JOIN countries co ON co.id = it.country_id");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getInt("id"));
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getInt("price"));

                Country country = new Country();
                country.setId(resultSet.getInt("country_id"));
                country.setName(resultSet.getString("countryName"));
                country.setCode(resultSet.getString("code"));
                item.setCountry(country);

                items.add(item);
            }
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }

    public static void addItem(Item item) {
        try {

            PreparedStatement statement =
                    connection.prepareStatement("" +
                            "INSERT INTO items (name, price, country_id) " +
                            "VALUES (?, ?, ?)");

            statement.setString(1, item.getName());
            statement.setInt(2, item.getPrice());
            statement.setInt(3, item.getCountry().getId());
            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Item getItem(int id) {

        Item item = null;

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "" +
                            "SELECT it.id, it.price, it.name, it.country_id, it.country_id, co.code, co.name AS countryName " +
                            "FROM items AS it " +
                            "INNER JOIN countries co ON co.id = it.country_id " +
                            "WHERE it.id = ?"
            );
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                item = new Item();
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getInt("price"));
                item.setId(resultSet.getInt("id"));

                Country country = new Country();
                country.setId(resultSet.getInt("country_id"));
                country.setName(resultSet.getString("countryName"));
                country.setCode(resultSet.getString("code"));
                item.setCountry(country);

            }
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    public static void saveItem(Item item) {
        try {

            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE items SET name = ?, price = ?, country_id = ? " +
                    "WHERE id = ?");

            statement.setString(1, item.getName());
            statement.setInt(2, item.getPrice());
            statement.setInt(3, item.getCountry().getId());
            statement.setInt(4, item.getId());

            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteItem(int id) {
        try {

            PreparedStatement statement = connection.prepareStatement("" +
                    "DELETE FROM items WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Item> searchItems(String name, String priceFrom, String priceTo) {

        ArrayList<Item> items = new ArrayList<>();

        try {

            String query = "SELECT * FROM items " +
                    "WHERE name LIKE ? ";

            if (priceFrom != null) {
                try{
                    int priceF = Integer.parseInt(priceFrom);
                    query = query + " AND price >= "+priceF;
                }catch (Exception ex){
                }
            }
            if (priceTo != null) {
                try{
                    int priceT = Integer.parseInt(priceTo);
                    query = query + " AND price <= "+priceT;
                }catch (Exception ex){
                }
            }

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + name + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getInt("id"));
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getInt("price"));
                //item.setCountry(resultSet.getString("country"));
                items.add(item);
            }
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }

    public static ArrayList<Country> getCountries(){

        ArrayList<Country> countries = new ArrayList<>();

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM countries");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Country country = new Country();
                country.setId(resultSet.getInt("id"));
                country.setName(resultSet.getString("name"));
                country.setCode(resultSet.getString("code"));
                countries.add(country);
            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return countries;
    }

    public static Country getCountry(int id){

        Country country = null;
        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM countries WHERE id = ?");
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                country = new Country();
                country.setId(resultSet.getInt("id"));
                country.setName(resultSet.getString("name"));
                country.setCode(resultSet.getString("code"));
            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return country;
    }

}
