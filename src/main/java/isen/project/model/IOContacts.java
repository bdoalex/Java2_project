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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * The type Io contacts.
 */
public class IOContacts {


    /**
     * The Person dao.
     */
    PersonDao personDao = new PersonDao();

    private String getFilePath(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("VCARD Files", "*.vcf", "*.vcard"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File file = fileChooser.showOpenDialog(null);

        if(file != null){
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

    /**
     * Export data.
     */
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
                printWriter.println("CAT:"+(person.getCategory()==null?null:person.getCategory().getName()));
                printWriter.println("END:VCARD");
                printWriter.println("");
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Export data.
     *
     * @param category the category exported
     */
    public void exportData(String category){
        try {
            FileWriter myWriter;
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

    /**
     * Import data.
     *
     * @param homeSreenController the home sreen controller
     */
    public void importData(HomeScreenController homeSreenController){
        try {
            File file;
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
                switch (parts[0]) {
                    case "BEGIN":
                        person = new Person();
                        break;
                    case "N":
                        parts = parts[1].split(";");
                        assert person != null;
                        person.setLastName(parts[0]);
                        person.setFirstName(parts[1]);
                        break;
                    case "FN":
                        assert person != null;
                        person.setNickName(parts[1]);
                        break;
                    case "BDAY":
                        try {
                            String date = parts[1];
                            date = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
                            LocalDate newdate = LocalDate.parse(date);
                            assert person != null;
                            person.setBirthDate(newdate);
                        } catch (Exception e) {
                            assert person != null;
                            person.setBirthDate(null);
                        }
                        break;
                    case "ADR":
                        try {
                            assert person != null;
                            person.setAddress(parts[1]);
                        } catch (Exception e) {
                            person.setAddress("");
                        }
                        break;
                    case "TEL;PHONE":
                        try {
                            assert person != null;
                            person.setPhoneNumber(parts[1]);
                        } catch (Exception e) {
                            person.setPhoneNumber("");
                        }
                        break;
                    case "EMAIL":
                        try {
                            assert person != null;
                            person.setEmailAddress(parts[1]);
                        } catch (Exception e) {
                            person.setEmailAddress("");
                        }
                        break;
                    case "CAT":
                        if (parts[1].equals("null")) {
                            assert person != null;
                            person.setCategory(null);
                        } else {
                            assert person != null;
                            person.setCategory(new CategoryDao().getCategory(parts[1]));
                        }
                        break;
                    case "END":
                        assert person != null;
                        person.setNameFileIcon(Constant.DEFAULT_IMAGE);
                        homeSreenController.getHomeScreenModel().addOneContact(person);
                        break;
                }
                App.showSuccessSnackBar("Success");

            }
            myReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
