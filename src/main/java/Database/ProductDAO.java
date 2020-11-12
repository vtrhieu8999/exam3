package Database;

import Controller.ATTRIBUTE;
import Models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/exam3?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "MySQL0435565216@bc";
    public static ProductDAO instance= new ProductDAO();

    private static final String INSERT_PRODUCT_SQL =
            "insert into product(name, price, amount, color, description, categoryId) values(?, ?, ?, ?, ?, ?)";
    private static final String SELECT_PRODUCT_BY_ID = "select * from product where product.productId =?";
    private static final String SELECT_ALL_PRODUCTS = "select * from product";
    private static final String DELETE_PRODUCT_SQL = "delete from product where product.productId = ?";
    private static final String UPDATE_PRODUCT_SQL =
            "update product set name = ?, price=?, amount=?, color=?, description=?, categoryID= ? where product.productId = ?;";
    private static final String SELECT_ALL_PRODUCT_BY_CATEGORY= "select * from product where categoryId=?";

    public ProductDAO(){}

    public Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

//    name, price, amount, color, description, categoryId
    public void addProduct(Product product){
        Connection connection= getConnection();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(INSERT_PRODUCT_SQL);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setInt(3, product.getAmount());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, product.getCategoryId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Product selectProductById(int id){
        Connection connection= getConnection();
        PreparedStatement preparedStatement= null;
        Product product= null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs= preparedStatement.executeQuery();
            rs.next();
            //    name, price, amount, color, description, categoryId
            int categoryId = rs.getInt(ATTRIBUTE.CATEGORY_ID);
            String name= rs.getString(ATTRIBUTE.NAME);
            int price= rs.getInt(ATTRIBUTE.AMOUNT);
            int amount= rs.getInt(ATTRIBUTE.AMOUNT);
            String color= rs.getString(ATTRIBUTE.COLOR);
            String description= rs.getString(ATTRIBUTE.DESCRIPTION);

            product= new Product(id, name, price, amount, color, description, categoryId);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }

    public List<Product> getAllProduct(){
        Connection connection= getConnection();
        List<Product> list= new ArrayList<>();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(SELECT_ALL_PRODUCTS);
            ResultSet rs= preparedStatement.executeQuery();
            while(rs.next()){
                int productId= rs.getInt(ATTRIBUTE.PRODUCT_ID);
                int categoryId = rs.getInt(ATTRIBUTE.CATEGORY_ID);
                String name= rs.getString(ATTRIBUTE.NAME);
                int price= rs.getInt(ATTRIBUTE.AMOUNT);
                int amount= rs.getInt(ATTRIBUTE.AMOUNT);
                String color= rs.getString(ATTRIBUTE.COLOR);
                String description= rs.getString(ATTRIBUTE.DESCRIPTION);

                list.add(new Product(productId, name, price, amount, color, description, categoryId)) ;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public void deleteProduct(int id){
        Connection connection= getConnection();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(DELETE_PRODUCT_SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateProduct(Product product){
        Connection connection= getConnection();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(UPDATE_PRODUCT_SQL);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setInt(3, product.getAmount());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, product.getCategoryId());
            preparedStatement.setInt(7, product.getProductId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Product> getAllProductByCategory(int id){
        Connection connection= getConnection();
        List<Product> list= new ArrayList<>();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(SELECT_ALL_PRODUCT_BY_CATEGORY);
            preparedStatement.setInt(1, id);
            ResultSet rs= preparedStatement.executeQuery();
            while(rs.next()){
                int productId= rs.getInt(ATTRIBUTE.PRODUCT_ID);
                int categoryId = rs.getInt(ATTRIBUTE.CATEGORY_ID);
                String name= rs.getString(ATTRIBUTE.NAME);
                int price= rs.getInt(ATTRIBUTE.AMOUNT);
                int amount= rs.getInt(ATTRIBUTE.AMOUNT);
                String color= rs.getString(ATTRIBUTE.COLOR);
                String description= rs.getString(ATTRIBUTE.DESCRIPTION);

                list.add(new Product(productId, name, price, amount, color, description, categoryId)) ;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
