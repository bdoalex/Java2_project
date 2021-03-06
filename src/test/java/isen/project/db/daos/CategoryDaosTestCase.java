package isen.project.db.daos;

import isen.project.model.daos.CategoryDao;
import isen.project.model.daos.DataSourceFactory;
import isen.project.model.entities.Category;
import javafx.collections.ObservableList;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;


public class CategoryDaosTestCase {

    private CategoryDao categoryDao = new CategoryDao();

    @Before
    public void initDatabase() throws Exception {
        Connection connection = DataSourceFactory.getConnection();
        Statement stmt = connection.createStatement();

        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS category(category_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, category_name VARCHAR(45) NOT NULL);");
        stmt.executeUpdate("DELETE FROM category");
        stmt.executeUpdate("INSERT INTO category(category_id,category_name) VALUES (1,'Friends')");
        stmt.executeUpdate("INSERT INTO category(category_id,category_name) VALUES (2,'Family')");
        stmt.executeUpdate("INSERT INTO category(category_id,category_name) VALUES (3,'Work')");
        stmt.close();
        connection.close();
    }

    @Test
    public void shouldListCategories() {
        ObservableList<Category> categories = categoryDao.listCategories();

        assertThat(categories).hasSize(3);
        assertThat(categories).extracting("category_id", "category_name").containsOnly(tuple(1, "Friends"), tuple(2, "Family"),
                tuple(3, "Work"));
    }


    @Test
    public void shouldAddCategories() throws Exception {
        // WHEN
        categoryDao.addCategory("School");
        // THEN
        Connection connection = DataSourceFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM category WHERE category_name='School'");
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getInt("category_id")).isNotNull();
        assertThat(resultSet.getString("category_name")).isEqualTo("School");
        assertThat(resultSet.next()).isFalse();
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void shouldGetCategoryByName() {
        Category category = this.categoryDao.getCategory("Family");
        Assertions.assertThat(category.getId()).isEqualTo(2);
        Assertions.assertThat(category.getName()).isEqualTo("Family");
    }

}
