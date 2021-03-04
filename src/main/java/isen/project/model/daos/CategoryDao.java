package isen.project.model.daos;

import isen.project.model.entities.Category;
import isen.project.model.entities.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {

    /**
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
     * adds a new category
     * @param name
     */
    public void addCategory(String name) {
        try (Connection cnx = DataSourceFactory.getConnection()) {
            try (PreparedStatement stmt = cnx.prepareStatement("INSERT INTO category(category_name) VALUES( ? )")) {
                stmt.setString(1, name);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Something went horribly wrong with our DB and you cannot do much about it !",
                    e);
        }
    }


}
