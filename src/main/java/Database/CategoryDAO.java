package Database;

import Controller.ATTRIBUTE;
import Models.Category;
import Models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/exam3?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "MySQL0435565216@bc";
    public static  CategoryDAO instance= new CategoryDAO();

    private static final String INSERT_CATEGORIES_SQL = "insert into category(category) values(?)";

    private static final String SELECT_CATEGORY_BY_ID = "select * from category where category.categoryId =?";
    private static final String SELECT_ALL_CATEGORIES = "select * from category";
    private static final String DELETE_CATEGORIES_SQL = "delete from category where category.categoryId = ?";
    private static final String UPDATE_CATEGORIES_SQL = "update category set name = ? where category.categoryId = ?;";

    public CategoryDAO(){}

    protected Connection getConnection(){
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

    public void insertCategory(Category cate){
        try(Connection conn= getConnection();
            PreparedStatement preparedStatement= conn.prepareStatement(INSERT_CATEGORIES_SQL);){
            preparedStatement.setString(1, cate.getCategory());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Category getCategoryById(int categoryId){
        String name;
        try(Connection conn= getConnection();) {
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_CATEGORY_BY_ID);
            preparedStatement.setInt(1, categoryId);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                name= rs.getString(ATTRIBUTE.CATEGORY);
                return new Category(categoryId, name);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public List<Category> getAllCategory(){
        Connection conn= getConnection();
        PreparedStatement preparedStatement= null;
        List<Category> list= new ArrayList<>();
        try {
            preparedStatement = conn.prepareStatement(SELECT_ALL_CATEGORIES);
            ResultSet rs= preparedStatement.executeQuery();
            while(rs.next()){
                String name= rs.getString(ATTRIBUTE.CATEGORY);
                int id= rs.getInt(ATTRIBUTE.CATEGORY_ID);
                list.add(new Category(id, name));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public void deleteCategory(int id){
        Connection conn= getConnection();
        try {
            PreparedStatement preparedStatement= conn.prepareStatement(DELETE_CATEGORIES_SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateCategory(Category category){
        Connection conn= getConnection();
        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = conn.prepareStatement(UPDATE_CATEGORIES_SQL);
            preparedStatement.setString(1, category.getCategory());
            preparedStatement.setInt(2, category.getCategoryId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
