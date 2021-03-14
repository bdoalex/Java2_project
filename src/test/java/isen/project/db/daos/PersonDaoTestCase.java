package isen.project.db.daos;

import isen.project.model.daos.CategoryDao;
import isen.project.model.daos.DataSourceFactory;
import isen.project.model.daos.PersonDao;
import isen.project.model.entities.Category;
import isen.project.model.entities.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;


import static junit.framework.TestCase.assertNotNull;
import static org.assertj.core.api.Assertions.*;

/**
 * The type Person dao test case.
 */
public class PersonDaoTestCase {

    private PersonDao personDao = new PersonDao();
    private CategoryDao categoryDao = new CategoryDao();

    /**
     * Init db.
     *
     * @throws Exception the exception
     */
    @Before
    public void initDb() throws Exception {
        Connection connection = DataSourceFactory.getConnection();
        Statement stmt = connection.createStatement();

        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS person (" +
                        "person_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , " +
                        "lastname VARCHAR(45) NOT NULL," +
                        "firstname VARCHAR(45) NOT NULL," +
                        "nickname VARCHAR(45) NOT NULL," +
                        "phone_number VARCHAR(15) NOT NULL," +
                        "address VARCHAR(200) NOT NULL," +
                        "email_address VARCHAR(150) NOT NULL," +
                        "birth_date DATE NULL," +
                        "name_file_icon VARCHAR(50) NOT NULL," +
                        "category_id INT NULL );"
        );

        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS category(category_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, category_name VARCHAR(45) NOT NULL);");

        stmt.executeUpdate("DELETE FROM person");
        stmt.executeUpdate("DELETE FROM category");
        stmt.executeUpdate("INSERT INTO person(person_id,lastname,firstname,nickname,phone_number,address,email_address,birth_date,name_file_icon, category_id) " +
                "VALUES (1, 'Barbo',  'Alexandre' , 'ElDeus','06060606','La vilette' ,'moi@moi.fr', '2020-2-25 12:00:00.000' ,'defaultImage.jpg',1 )");


        stmt.executeUpdate("INSERT INTO person(person_id,lastname,firstname,nickname,phone_number,address,email_address,birth_date,name_file_icon, category_id) " +
                "VALUES (2,'Jeannnnin',  'Louis' , 'ElPetou','01020304','Montpellier' ,'toi@toi.fr', '2010-1-25 12:00:00.000' ,'defaultImage.jpg',null )");

        stmt.executeUpdate("INSERT INTO person(person_id,lastname,firstname,nickname,phone_number,address,email_address,birth_date,name_file_icon, category_id) " +
                "VALUES (3,'Vivier',  'Louis' , 'Lou','01020304','Lille' ,'toi@toi.fr', '2010-1-25 12:00:00.000' ,'defaultImage.jpg',2 )");
        stmt.executeUpdate("INSERT INTO person(person_id,lastname,firstname,nickname,phone_number,address,email_address,birth_date,name_file_icon, category_id) " +
                "VALUES (4,'Caron',  'Loic' , 'Lolo','01020304','Lens' ,'toi@toi.fr', '2010-1-25 12:00:00.000' ,'defaultImage.jpg',2 )");
        stmt.executeUpdate("INSERT INTO category(category_id,category_name) VALUES (1,'Friends')");
        stmt.executeUpdate("INSERT INTO category(category_id,category_name) VALUES (2,'Family')");
        stmt.executeUpdate("INSERT INTO category(category_id,category_name) VALUES (3,'Work')");

        stmt.close();
        connection.close();
    }

    /**
     * Should get persons.
     */
    @Test
    public void shouldGetPersons(){
        ObservableList<Person> persons = personDao.getPersons();

        assertThat(persons).hasSize(4);
        assertThat(persons).extracting("personId","lastName", "firstName", "nickName","phoneNumber", "address","emailAddress", "birthDate", "nameFileIcon","category")
                .containsOnly(
                        tuple(1, "Barbo",  "Alexandre" , "ElDeus","06060606","La vilette" ,"moi@moi.fr", LocalDate.of(2020 , 2, 25) ,"defaultImage.jpg",new Category(1, "Friends") ),
                        tuple(2,"Jeannnnin",  "Louis" , "ElPetou","01020304","Montpellier" ,"toi@toi.fr", LocalDate.of(2010 , 1, 25) ,"defaultImage.jpg",null ),
                        tuple(3,"Vivier",  "Louis" , "Lou","01020304","Lille" ,"toi@toi.fr", LocalDate.of(2010 , 1, 25) ,"defaultImage.jpg",new Category(2, "Family") ),
                        tuple(4,"Caron",  "Loic" , "Lolo","01020304","Lens" ,"toi@toi.fr", LocalDate.of(2010 , 1, 25) ,"defaultImage.jpg",new Category(2, "Family") ));

    }

    /**
     * Should get person by id.
     */
    @Test
    public void shouldGetPersonByID() {
        Person person = personDao.getPersonById(1);


        Assert.assertEquals(person, new Person(1,
                "Barbo",
                "Alexandre",
                "ElDeus",
                "06060606",
                "La vilette",
                "moi@moi.fr",
                LocalDate.of(2020, 2, 25), "defaultImage.jpg",
                new Category(1, "Friends")));

         person = personDao.getPersonById(2);

        Assert.assertEquals(person, new Person(2,
                "Jeannnnin",
                "Louis",
                "ElPetou",
                "01020304",
                "Montpellier",
                "toi@toi.fr",
                LocalDate.of(2010, 1, 25), "defaultImage.jpg",
                null));
    }

    /**
     * Should get persons.
     */
    @Test
    public void shouldGetPersonsFromCategories(){
        ObservableList<Person> persons = personDao.getPersonsFromCategory(new Category(2, "Family"));

        assertThat(persons).hasSize(2);
        assertThat(persons).extracting("personId","lastName", "firstName", "nickName","phoneNumber", "address","emailAddress", "birthDate", "nameFileIcon","category")
                .containsOnly(
                        tuple(3,"Vivier",  "Louis" , "Lou","01020304","Lille" ,"toi@toi.fr", LocalDate.of(2010 , 1, 25) ,"defaultImage.jpg",new Category(2, "Family") ),
                        tuple(4,"Caron",  "Loic" , "Lolo","01020304","Lens" ,"toi@toi.fr", LocalDate.of(2010 , 1, 25) ,"defaultImage.jpg",new Category(2, "Family") ));

    }


    /**
     * Should modify person.
     *
     * @throws SQLException the sql exception
     */
    @Test
    public void shouldModifyPerson() throws SQLException {
        Person modified = new Person(1, "Flavien", "Flavien2", "ElDeus", "06060606", "La vilette", "moi@moi.fr", LocalDate.of(2020, 2, 25), "defaultImage.jpg", categoryDao.getCategory("Friends"));
        Boolean modifiedPerson = personDao.modifyPerson(modified);

        Connection connection = DataSourceFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultModifyPerson = statement.executeQuery("SELECT * FROM person WHERE person_id =1");
        assertThat(resultModifyPerson.next()).isTrue();
        assertNotNull(modifiedPerson);
        assertThat(resultModifyPerson.getInt("person_id") == 1);
        assertThat(resultModifyPerson.getString("lastname")).isEqualTo("Flavien");
        assertThat(resultModifyPerson.getString("firstname")).isEqualTo("Flavien2");
        assertThat(resultModifyPerson.getString("nickname")).isEqualTo("ElDeus");
        assertThat(resultModifyPerson.getString("phone_number")).isEqualTo("06060606");
        assertThat(resultModifyPerson.getString("address")).isEqualTo("La vilette");
        assertThat(resultModifyPerson.getString("email_address")).isEqualTo("moi@moi.fr");
        assertThat(resultModifyPerson.getDate("birth_date")).isEqualTo(java.sql.Date.valueOf((LocalDate.of(2020, 2, 25))));
        assertThat(resultModifyPerson.getString("name_file_icon")).isEqualTo("defaultImage.jpg");
        assertThat(resultModifyPerson.getInt("category_id")).isEqualTo(1);

        resultModifyPerson.close();
        statement.close();
        connection.close();
    }

    /**
     * Should delete person.
     *
     * @throws SQLException the sql exception
     */
    @Test
    public void shouldDeletePerson() throws SQLException {

        personDao.deletePersonById(1);
        //THEN
        Connection connection = DataSourceFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM person WHERE person_id='1'");
        assertThat(resultSet.next()).isFalse();
        resultSet.close();
        statement.close();
        connection.close();
    }

    /**
     * Should add person.
     *
     * @throws SQLException the sql exception
     */
    @Test
    public void shouldAddPerson() throws SQLException {
        personDao.addPerson(new Person(1, "Boutillier", "Lisa", "Nose", "06060606", "Lille", "moi@moi.fr", LocalDate.of(1998, 12, 31), "defaultImage.jpg", categoryDao.getCategory("Friends")));

        Connection connection = DataSourceFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultAddPerson = statement.executeQuery("SELECT * FROM person WHERE nickname='Nose'");
        assertThat(resultAddPerson.next()).isTrue();
        assertNotNull(resultAddPerson);
        assertThat(resultAddPerson.getInt("person_id") == 1);
        assertThat(resultAddPerson.getString("lastname")).isEqualTo("Boutillier");
        assertThat(resultAddPerson.getString("firstname")).isEqualTo("Lisa");
        assertThat(resultAddPerson.getString("nickname")).isEqualTo("Nose");
        assertThat(resultAddPerson.getString("phone_number")).isEqualTo("06060606");
        assertThat(resultAddPerson.getString("address")).isEqualTo("Lille");
        assertThat(resultAddPerson.getString("email_address")).isEqualTo("moi@moi.fr");
        assertThat(resultAddPerson.getDate("birth_date")).isEqualTo(java.sql.Date.valueOf((LocalDate.of(1998, 12, 31))));
        assertThat(resultAddPerson.getString("name_file_icon")).isEqualTo("defaultImage.jpg");
        assertThat(resultAddPerson.getInt("category_id")).isEqualTo(1);

        resultAddPerson.close();
        statement.close();
        connection.close();
    }

}
