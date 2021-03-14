package isen.project.model.daos;

import isen.project.model.entities.Category;
import isen.project.model.entities.Person;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;


/**
 * The type Person dao.
 */
public class PersonDao {


    /**
     * Gets persons.
     *
     * @return the persons in the database
     */
    public ObservableList<Person> getPersons() {
        ObservableList<Person> persons = FXCollections.observableArrayList();

        try (Connection connection = DataSourceFactory.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM person LEFT JOIN category ON person.category_id = category.category_id")) {
                try (ResultSet results = statement.executeQuery()) {
                    while (results.next()) {
                        Category category = null;
                        int categoryId = results.getInt("category_id");
                        if (!results.wasNull()) {
                            category = new Category(categoryId, results.getString("category_name"));
                        }

                        Person person = new Person(
                                results.getInt("person_id"),
                                results.getString("lastname"),
                                results.getString("firstname"),
                                results.getString("nickname"),
                                results.getString("phone_number"),
                                results.getString("address"),
                                results.getString("email_address"),

                                results.getDate("birth_date") != null ? results.getDate("birth_date").toLocalDate() : null,
                                results.getString("name_file_icon"),
                                category);
                        ;
                        persons.add(person);
                    }
                    return persons;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error", e);
        }
    }

    /**
     * Gets person by id.
     *
     * @param id of person in the database
     * @return person in the database
     */
    public Person getPersonById(int id) {
        try (Connection connection = DataSourceFactory.getConnection()) {
            String sqlQuery = "SELECT * FROM person LEFT JOIN category ON person.category_id = category.category_id WHERE person_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(
                    sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, id);
                try (ResultSet results = statement.executeQuery()) {
                    results.next();
                    Category category = null;
                    int catId = results.getInt("category_id");
                    if (!results.wasNull()) {
                        category = new Category(catId, results.getString("category_name"));
                    }


                    return new Person(
                            results.getInt("person_id"),
                            results.getString("lastname"),
                            results.getString("firstname"),
                            results.getString("nickname"),
                            results.getString("phone_number"),
                            results.getString("address"),
                            results.getString("email_address"),

                            results.getDate("birth_date") != null ? results.getDate("birth_date").toLocalDate() : null,
                            results.getString("name_file_icon"),
                            category);

                }
            }

        } catch (SQLException e) {
            // Manage Exception
            throw new RuntimeException("Error", e);

        }
    }

    /**
     * Sets category id to null.
     *
     * @param id the id
     * @return the category id to null
     */
    public Boolean setCategoryIdToNull(int id) {
        try (Connection connection = DataSourceFactory.getConnection()) {
            String sqlQuery = "UPDATE person SET category_id = NULL WHERE  category_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(
                    sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, id);
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
     * Delete person by id .
     *
     * @param id the id
     * @return the boolean
     */
    public Boolean deletePersonById(int id) {
        try (Connection connection = DataSourceFactory.getConnection()) {
            String sqlQuery = "DELETE FROM person WHERE person_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(
                    sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, id);
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
     * Modify person boolean.
     *
     * @param person you want to update
     * @return if the person was correctly modified
     */
    public Boolean modifyPerson(Person person) {
        try (Connection connection = DataSourceFactory.getConnection()) {
            String sqlQuery = "UPDATE person SET lastname = ? , firstname = ? , nickname = ? , phone_number = ?, address = ? , email_address = ?, birth_date = ?, category_id = ? , name_file_icon = ?  WHERE person_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {

                statement.setString(1, person.getLastName());
                statement.setString(2, person.getFirstName());
                statement.setString(3, person.getNickName());
                statement.setString(4, person.getPhoneNumber());
                statement.setString(5, person.getAddress());
                statement.setString(6, person.getEmailAddress());
                statement.setDate(7, person.getBirthDate() != null ? Date.valueOf(person.getBirthDate()) : null);
                if (person.getCategory() == null) {
                    statement.setNull(8, Types.INTEGER);
                } else {
                    statement.setInt(8, person.getCategory().getId());
                }
                statement.setString(9, person.getNameFileIcon());
                statement.setInt(10, person.getPersonId());
                statement.executeUpdate();
                return true;

            }

        } catch (SQLException e) {
            // Manage Exception
            throw new RuntimeException("Error", e);
        }
    }

    /**
     * Add person person.
     *
     * @param person you want to add to the database
     * @return the added person with its id
     */
    public Person addPerson(Person person) {
        try (Connection connection = DataSourceFactory.getConnection()) {
            String sqlQuery = "INSERT INTO person(lastname, firstname, nickname, phone_number, address, email_address, birth_date,name_file_icon, category_id) VALUES(?,?,?,?,?,?,?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(
                    sqlQuery, Statement.RETURN_GENERATED_KEYS)) {

                statement.setString(1, person.getLastName());
                statement.setString(2, person.getFirstName());
                statement.setString(3, person.getNickName());
                statement.setString(4, person.getPhoneNumber());
                statement.setString(5, person.getAddress());
                statement.setString(6, person.getEmailAddress());
                statement.setDate(7, person.getBirthDate() != null ? Date.valueOf(person.getBirthDate()) : null);
                statement.setString(8, person.getNameFileIcon());
                if (person.getCategory() == null) {
                    statement.setNull(9, Types.INTEGER);
                } else {
                    statement.setInt(9, person.getCategory().getId());
                }


                statement.executeUpdate();
                try (ResultSet keys = statement.getGeneratedKeys()) {
                    keys.next();
                    person.setPersonId(keys.getInt(1));
                    return person;
                }
            }

        } catch (SQLException e) {
            // Manage Exception
            throw new RuntimeException("Error", e);
        }
    }

}
