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
                    "SELECT it.id, it.price, it.name, it.country_id, it.country_id, " +
                    "co.code, co.name AS countryName, us.full_name, it.user_id, us.email " +
                    "FROM items AS it " +
                    "INNER JOIN countries co ON co.id = it.country_id " +
                    "INNER JOIN users us ON us.id = it.user_id");

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

                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setEmail(resultSet.getString("email"));
                user.setFullName(resultSet.getString("full_name"));
                item.setUser(user);

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
                            "INSERT INTO items (name, price, country_id, user_id) " +
                            "VALUES (?, ?, ?, ?)");

            statement.setString(1, item.getName());
            statement.setInt(2, item.getPrice());
            statement.setInt(3, item.getCountry().getId());
            statement.setInt(4, item.getUser().getId());
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
                            "SELECT it.id, it.price, it.name, it.country_id, it.country_id, " +
                            "co.code, co.name AS countryName, it.user_id, us.full_name, us.email " +
                            "FROM items AS it " +
                            "INNER JOIN countries co ON co.id = it.country_id " +
                            "INNER JOIN users us ON us.id = it.user_id " +
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

                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setFullName(resultSet.getString("full_name"));
                user.setEmail(resultSet.getString("email"));
                item.setUser(user);

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

    public static void deleteItem(int id, int userId) {
        try {

            PreparedStatement statement = connection.prepareStatement("" +
                    "DELETE FROM items WHERE id = ? AND user_id = ?");
            statement.setInt(1, id);
            statement.setInt(2, userId);

            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Item> searchItems(String name, String priceFrom, String priceTo, String countryId) {

        ArrayList<Item> items = new ArrayList<>();

        try {

            String query = "SELECT it.id, it.price, it.name, it.country_id, it.country_id, " +
                    "co.code, co.name AS countryName, us.full_name, it.user_id, us.email " +
                    "FROM items AS it " +
                    "INNER JOIN countries co ON co.id = it.country_id " +
                    "INNER JOIN users us ON us.id = it.user_id " +
                    "WHERE it.name LIKE ? ";

            if (priceFrom != null) {
                try{
                    int priceF = Integer.parseInt(priceFrom);
                    query = query + " AND it.price >= "+priceF;
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

            int cntId = 0;

            try{
                if(countryId!=null){
                    cntId = Integer.parseInt(countryId);
                }
            }catch (Exception e){
            }

            if(cntId!=0){
                query = query + " AND it.country_id = "+cntId + " ";
            }

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + name + "%");

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

                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setEmail(resultSet.getString("email"));
                user.setFullName(resultSet.getString("full_name"));
                item.setUser(user);

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

        public static User getUser(String email){
        User user = null;
        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM users WHERE email = ?");

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setFullName(resultSet.getString("full_name"));
            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public static void addUser(User user){

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO users (email, password, full_name) " +
                    "VALUES (?, ?, ?)");

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullName());

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void addComment(Comment comment){
        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO comments (item_id, user_id, comment, post_date) " +
                    "VALUES (?, ?, ?, NOW())");

            statement.setInt(1, comment.getItem().getId());
            statement.setInt(2, comment.getUser().getId());
            statement.setString(3, comment.getComment());

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Comment> getComments(int itemId){

        ArrayList<Comment> comments = new ArrayList<>();

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT c.id, c.item_id, c.comment, c.post_date, c.user_id, u.full_name, u.email " +
                    "FROM comments c " +
                    "INNER JOIN users u ON u.id = c.user_id " +
                    "WHERE c.item_id = ? " +
                    "ORDER BY c.post_date DESC ");

            statement.setInt(1, itemId);

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){

                Comment comment = new Comment();
                comment.setId(resultSet.getInt("id"));
                comment.setComment(resultSet.getString("comment"));
                comment.setPostDate(resultSet.getTimestamp("post_date"));

                Item item = new Item();
                item.setId(resultSet.getInt("item_id"));
                comment.setItem(item);

                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setFullName(resultSet.getString("full_name"));
                user.setEmail(resultSet.getString("email"));
                comment.setUser(user);

                comments.add(comment);
            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return comments;
    }
}