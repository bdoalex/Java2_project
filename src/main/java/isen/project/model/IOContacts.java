package isen.project.model;

import isen.project.model.daos.DataSourceFactory;
import isen.project.model.daos.PersonDao;
import isen.project.model.entities.Category;
import isen.project.model.entities.Person;
import isen.project.util.Constant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.time.LocalDate;
import java.util.Scanner; // Import the Scanner class to read text files

public class IOContacts {

    public ObservableList<Person> getData() {
        ObservableList<Person> persons = FXCollections.observableArrayList();

        try (Connection connection = DataSourceFactory.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM person JOIN category ON person.category_id = category.category_id")) {
                try (ResultSet results = statement.executeQuery()) {
                    while (results.next()) {
                        Category category = new Category(results.getInt("category_id"),results.getString("category_name")) ;

                        Person person = new Person(
                                results.getInt("person_id"),
                                results.getString("lastname"),
                                results.getString("firstname"),
                                results.getString("nickname"),
                                results.getString("phone_number"),
                                results.getString("address"),
                                results.getString("email_address"),
                                results.getDate("birth_date")!=null ? results.getDate("birth_date").toLocalDate() : null,
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

    private void createVcfFile(){
        File myObj = new File("contacts.vcf");
        try {
            myObj.createNewFile();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportData(){
        createVcfFile();
        try {
            FileWriter myWriter = new FileWriter("contacts.vcf");
            PrintWriter printWriter = new PrintWriter(myWriter);

            ObservableList<Person> persons = getData();

            for(Person person : persons) {
                printWriter.println("BEGIN:VCARD");
                printWriter.println("VERSION:2.1");
                printWriter.println("N:"+person.getLastName()+";"+person.getFirstName());
                printWriter.println("FN:"+person.getNickName());
                printWriter.println("BDAY:"+person.getBirthDate().toString().replace("-",""));
                printWriter.println("ADR:"+person.getAddress());
                printWriter.println("TEL;PHONE:"+person.getPhoneNumber());
                printWriter.println("EMAIL:"+person.getEmailAddress());
                printWriter.println("END:VCARD");
                printWriter.println("");
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void importData(){
        try {
            File myObj = new File("contacts.vcf");
            Scanner myReader = new Scanner(myObj);
            PersonDao personDao = new PersonDao();

            Person person = null;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split(":");
                if(parts[0].equals("BEGIN"))
                    person = new Person();
                else if (parts[0].equals("N")){
                    parts = parts[1].split(";");
                    person.setLastName(parts[0]);
                    person.setFirstName(parts[1]);
                }
                else if (parts[0].equals("FN"))
                    person.setNickName(parts[1]);
                else if (parts[0].equals("BDAY")){
                    if(parts[1] != null){
                        String date = parts[1];
                        date = date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8);
                        LocalDate newdate = LocalDate.parse(date);
                        person.setBirthDate(newdate);
                    }
                }
                else if (parts[0].equals("ADR"))
                    person.setAddress(parts[1]);
                else if (parts[0].equals("TEL;PHONE"))
                    person.setPhoneNumber(parts[1]);
                else if (parts[0].equals("EMAIL"))
                    person.setEmailAddress(parts[1]);
                else if (parts[0].equals("END")){
                    person.setNameFileIcon(Constant.DEFAULT_IMAGE);
                    personDao.addPerson(person);
                }

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
