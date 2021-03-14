package isen.project.model.daos;

import isen.project.model.entities.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;


/**
 * The type Category dao.
 */
public class CategoryDao {


    /**
     * List categories observable list.
     *
     * @return list with categories
     */
    public ObservableList<Category> listCategories() {
        ObservableList<Category> categories = FXCollections.observableArrayList();

        try (Connection cnx = DataSourceFactory.getConnection()) {
            try (Statement stmt = cnx.createStatement()) {
                try (ResultSet res = stmt.executeQuery("SELECT * FROM category")) {
                    while (res.next()) {
                        categories.add(new Category(res.getInt("category_id"), res.getString("category_name")));
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Something went wrong with the db !",
                    e);
        }
        return categories;
    }

    /**
     * Delete category by id boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public Boolean deleteCategoryById(int id) {


        try (Connection connection = DataSourceFactory.getConnection()) {
            String sqlQuery = "DELETE FROM category WHERE category_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(
                    sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, id);
                PersonDao personDao = new PersonDao();
                personDao.setCategoryIdToNull(id);

                statement.execute();

                return true;
            }

        } catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
            return false;
        }
    }

    /**
     * adds a new category
     *
     * @param name the name
     * @return the category
     */
    public Category addCategory(String name) {
        try (Connection cnx = DataSourceFactory.getConnection()) {
            try (PreparedStatement stmt = cnx.prepareStatement("INSERT INTO category(category_name) VALUES( ? )")) {
                stmt.setString(1, name);

                stmt.executeUpdate();

                try (ResultSet keys = stmt.getGeneratedKeys()) {
                    keys.next();

                    return new Category(keys.getInt(1), name);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Something went horribly wrong with our DB",
                    e);
        }
    }

    /**
     * Gets category.
     *
     * @param name the name
     * @return the category
     */
    public Category getCategory(String name) {
        try (Connection connection = DataSourceFactory.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM category WHERE category_name = ?")) {
                statement.setString(1, name);
                try (ResultSet results = statement.executeQuery()) {
                    if (results.next()) {
                        return new Category(results.getInt("category_id"), results.getString("category_name"));
                    }

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Something went horribly wrong with our DB",
                    e);
        }
        return null;
    }


}
