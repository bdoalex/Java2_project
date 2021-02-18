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
                                results.getDate("birth_date").toLocalDate());
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
     * @param person you want to add to the database
     * @return the added film with its id
     */
    public Person addPerson(Person person) {
        try (Connection connection = DataSourceFactory.getConnection()) {
            String sqlQuery = "INSERT INTO person(lastname, firstname, nickname, phone_number, address, email_address, birth_date) VALUES(?,?,?,?,?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(
                    sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1,person.getLastName());
                statement.setString(2,person.getFirstName());
                statement.setString(3,person.getNickName());
                statement.setString(4,person.getPhoneNumber());
                statement.setString(5,person.getAddress());
                statement.setString(6,person.getEmailAddress());
                statement.setDate(7,Date.valueOf(person.getBirthDate()));
                statement.executeUpdate();
                try (ResultSet keys = statement.getGeneratedKeys()) {
                    keys.next();
                    person.setPersonId(keys.getInt(1));
                    return person;
                }
            }

        }catch (SQLException e) {
            // Manage Exception
            e.printStackTrace();
        }
        return null;
    }

}
