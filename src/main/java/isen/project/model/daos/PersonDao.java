package isen.project.model.daos;

import isen.project.model.entities.Person;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/**
 * @author Alexandre BARBOSA DE OLIVEIRA
 * Data Access class for person
 */
public class PersonDao {

    private ObservableList<Person> persons;

    /**
     * @return the persons in the database
     */
    public ObservableList<Person> getPersons() {
        ObservableList<Person> persons = FXCollections.observableArrayList();

        try (Connection connection = DataSourceFactory.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM person")) {
                try (ResultSet results = statement.executeQuery()) {
                    while (results.next()) {
                        Person person = new Person(
                                results.getInt("person_id"),
                                results.getString("lastname"),
                                results.getString("firstname"),
                                results.getString("nickname"),
                                results.getString("phone_number"),
                                results.getString("address"),
                                results.getString("email_address"),
                                results.getDate("birth_date") != null ? results.getDate("birth_date").toLocalDate() : null,
                                results.getString("name_file_icon"));
                        persons.add(person);
                    }
                    return persons;
                }
            }
        } catch (SQLException e) {
            return null; //toDo : bien gérer les exceptions
        }
    }

    /**
     * @param id of person in the database
     * @return person in the database
     */
    public Person getPersonById(int id) {
        try (Connection connection = DataSourceFactory.getConnection()) {
            String sqlQuery = "SELECT * FROM person WHERE person_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(
                    sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, id);
                try (ResultSet results = statement.executeQuery()) {
                    results.next();
                    return new Person(
                            results.getInt("person_id"),
                            results.getString("lastname"),
                            results.getString("firstname"),
                            results.getString("nickname"),
                            results.getString("phone_number"),
                            results.getString("address"),
                            results.getString("email_address"),
                            results.getDate("birth_date") != null ? results.getDate("birth_date").toLocalDate() : null,
                            results.getString("name_file_icon"));
                }
            }

        } catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
            return null;
        }
    }

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
     * @param person you want to update
     * @return the if the person was correctly modified
     */
    public Boolean ModifyPerson(Person person) {
        try (Connection connection = DataSourceFactory.getConnection()) {
            String sqlQuery = "UPDATE person SET lastname = ? , firstname = ? , nickname = ? , phone_number = ?, address = ? , email_address = ?, birth_date = ?  WHERE person_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, person.getLastName());
                statement.setString(2, person.getFirstName());
                statement.setString(3, person.getNickName());
                statement.setString(4, person.getPhoneNumber());
                statement.setString(5, person.getAddress());
                statement.setString(6, person.getEmailAddress());
                statement.setDate(7, person.getBirthDate() != null ? Date.valueOf(person.getBirthDate()) : null);
                statement.setInt(8, person.getPersonId());
                statement.executeUpdate();
                return true;

            }

        } catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param person you want to add to the database
     * @return the added film with its id
     */
    public Person addPerson(Person person) {
        try (Connection connection = DataSourceFactory.getConnection()) {
            String sqlQuery = "INSERT INTO person(lastname, firstname, nickname, phone_number, address, email_address, birth_date,name_file_icon) VALUES(?,?,?,?,?,?,?,?)";
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

                statement.executeUpdate();
                try (ResultSet keys = statement.getGeneratedKeys()) {
                    keys.next();
                    person.setPersonId(keys.getInt(1));
                    return person;
                }
            }

        } catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
            return null;
        }
    }

}
