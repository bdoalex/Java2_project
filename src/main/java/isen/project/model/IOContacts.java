package isen.project.model;

import isen.project.App;
import isen.project.model.daos.CategoryDao;
import isen.project.model.daos.PersonDao;
import isen.project.model.entities.Person;
import isen.project.util.Constant;
import isen.project.view.HomeScreenController;
import javafx.collections.ObservableList;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.FileWriter;
import java.io.PrintWriter;

import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.time.LocalDate;
import java.util.Scanner; // Import the Scanner class to read text files

public class IOContacts {


    PersonDao personDao = new PersonDao();

    private String getFilePath(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("VCARD Files", "*.vcf", "*.vcard"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File file = fileChooser.showOpenDialog(null);

        if(file != null){
            System.out.println(file.getPath());
            return file.getPath();
        }

        else
            return null;
    }

    private String getDirectoryPath(){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Open Resource File");
        File directory = directoryChooser.showDialog(null);

        if(directory != null){
            System.out.println(directory.getPath());
            return directory.getPath();
        }

        else
            return null;
    }

    private void createVcfFile(String path){
        File file = new File(path);
        try {
            file.createNewFile();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportData(){
        try {
            FileWriter myWriter = null;
            String path = getDirectoryPath();

            if(path != null){
                createVcfFile(path + "\\contacts.vcf");
                myWriter = new FileWriter(path + "\\contacts.vcf");
            }
            else
                return;


            PrintWriter printWriter = new PrintWriter(myWriter);

            ObservableList<Person> persons = personDao.getPersons();

            for(Person person : persons) {
                printWriter.println("BEGIN:VCARD");
                printWriter.println("VERSION:2.1");
                printWriter.println("N:"+person.getLastName()+";"+person.getFirstName());
                printWriter.println("FN:"+person.getNickName());
                if(person.getBirthDate() != null)
                    printWriter.println("BDAY:"+ person.getBirthDate().toString().replace("-",""));
                else
                    printWriter.println("BDAY:"+person.getBirthDate());
                printWriter.println("ADR:"+person.getAddress());
                printWriter.println("TEL;PHONE:"+person.getPhoneNumber());
                printWriter.println("EMAIL:"+person.getEmailAddress());
                printWriter.println("CAT:"+person.getCategory().getName());
                printWriter.println("END:VCARD");
                printWriter.println("");
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportData(String category){
        try {
            FileWriter myWriter = null;
            String path = getDirectoryPath();

            if(path != null){
                createVcfFile(path + "\\contacts.vcf");
                myWriter = new FileWriter(path + "\\contacts.vcf");
            }
            else
                return;

            PrintWriter printWriter = new PrintWriter(myWriter);

            ObservableList<Person> persons = personDao.getPersons();

            for(Person person : persons) {
                printWriter.println("BEGIN:VCARD");
                printWriter.println("VERSION:2.1");
                printWriter.println("N:"+person.getLastName()+";"+person.getFirstName());
                printWriter.println("FN:"+person.getNickName());
                if(person.getBirthDate() != null)
                    printWriter.println("BDAY:"+person.getBirthDate().toString().replace("-",""));
                else
                    printWriter.println("BDAY:"+person.getBirthDate());
                printWriter.println("ADR:"+person.getAddress());
                printWriter.println("TEL;PHONE:"+person.getPhoneNumber());
                printWriter.println("EMAIL:"+person.getEmailAddress());
                printWriter.println("CAT:"+person.getCategory().getName());
                printWriter.println("END:VCARD");
                printWriter.println("");
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void importData(HomeScreenController homeSreenController){
        try {
            File file = null;
            String path = getFilePath();
            if(path != null)
                file = new File(path);
            else
                return;

            Scanner myReader = new Scanner(file);
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
                else if (parts[0].equals("BDAY"))
                    try {
                        String date = parts[1];
                        date = date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8);
                        LocalDate newdate = LocalDate.parse(date);
                        person.setBirthDate(newdate);
                    }
                    catch (Exception e){
                        person.setBirthDate(null);
                    }
                else if (parts[0].equals("ADR"))
                    try{
                        person.setAddress(parts[1]);
                    }
                    catch (Exception e){
                        person.setAddress("");
                    }
                else if (parts[0].equals("TEL;PHONE"))
                    try {
                        person.setPhoneNumber(parts[1]);
                    }
                    catch (Exception e){
                        person.setPhoneNumber("");
                    }
                else if (parts[0].equals("EMAIL"))
                    try {
                        person.setEmailAddress(parts[1]);
                    }
                    catch (Exception e){
                        person.setEmailAddress("");
                    }
                else if (parts[0].equals("CAT"))
                    person.setCategory(new CategoryDao().getCategory(parts[1]));
                else if (parts[0].equals("END")){
                    person.setNameFileIcon(Constant.DEFAULT_IMAGE);
                    personDao.addPerson(person);
                    homeSreenController.getHomeScreenModel().addOneContact(person);
                }
                App.showSuccessSnackBar("Success");

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
