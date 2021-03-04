package isen.project.db.daos;

import isen.project.model.daos.CategoryDao;
import isen.project.model.daos.DataSourceFactory;
import isen.project.model.daos.PersonDao;
import isen.project.model.entities.Category;
import isen.project.model.entities.Person;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.time.LocalDate;


import static org.assertj.core.api.Assertions.*;

public class PersonDaoTestCase {

    private PersonDao personDao = new PersonDao();
    private CategoryDao categoryDao = new CategoryDao();

    @Before
    public void initDb() throws Exception {
        Connection connection = DataSourceFactory.getConnection();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE person");
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
                        "category_id INT NOT NULL);"
        );


        stmt.executeUpdate("DELETE FROM person");
        stmt.executeUpdate("INSERT INTO person(person_id,lastname,firstname,nickname,phone_number,address,email_address,birth_date,name_file_icon, category_id) " +
                "VALUES (1, 'Barbo',  'Alexandre' , 'ElDeus','06060606','La vilette' ,'moi@moi.fr', '2020-2-25 12:00:00.000' ,'defaultImage.jpg',1 )");


        stmt.executeUpdate("INSERT INTO person(person_id,lastname,firstname,nickname,phone_number,address,email_address,birth_date,name_file_icon, category_id) " +
                "VALUES (2,'Jeannnnin',  'Louis' , 'ElPetou','01020304','Montpellier' ,'toi@toi.fr', '2010-1-25 12:00:00.000' ,'defaultImage.jpg',2 )");


        stmt.close();
        connection.close();
    }


    @Test
    public void ShouldGetPersonByID() {
        Person personById = personDao.GetPersonById(1);
        Person excepted = new Person(1, "Barbo", "Alexandre", "ElDeus", "06060606", "La vilette", "moi@moi.fr", LocalDate.of(2020, 2, 25), "defaultImage.jpg", categoryDao.getCategory("Friends").getCategory_id());

        assertThat(personById).isEqualToComparingFieldByField(excepted);

        personById = personDao.GetPersonById(2);
        excepted = new Person(2, "Jeannnnin", "Louis", "ElPetou", "01020304", "Montpellier", "toi@toi.fr", LocalDate.of(2010, 1, 25), "defaultImage.jpg", categoryDao.getCategory("Family").getCategory_id());

        assertThat(personById).isEqualToComparingFieldByField(excepted);

    }

    @Test
    public void ShouldModifyPerson() {
        Person modified = new Person(1, "Flavien", "Flavien2", "ElDeus", "06060606", "La vilette", "moi@moi.fr", LocalDate.of(2020, 2, 25), "defaultImage.jpg", new Category(1, "Friend").getCategory_id());
        Boolean modifiedPerson = personDao.ModifyPerson(modified);
        Person exceptedById = personDao.GetPersonById(1);


        assertThat(modifiedPerson).isEqualTo(true);
        assertThat(modified).isEqualToComparingFieldByField(exceptedById);


    }


}
