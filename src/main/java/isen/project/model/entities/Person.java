package isen.project.model.entities;

import java.sql.Date;
import java.time.LocalDate;

public class Person {

    private int personId;
    private String lastName;
    private String firstName;
    private String nickName;
    private String phoneNumber;
    private String address;
    private String emailAddress;
    private LocalDate birthDate;
    private int categoryId;


    private String nameFileIcon;

    public Person() {

    }

    public Person(int personId, String lastName, String firstName, String nickName, String phoneNumber, String address, String emailAddress, LocalDate birthDate, String nameFileIcon, int categoryId) {
        this.personId = personId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.emailAddress = emailAddress;
        this.birthDate = birthDate;
        this.nameFileIcon = nameFileIcon;
        this.categoryId = categoryId;

    }


    public Person(String lastName, String firstName, String nickName, String phoneNumber, String address, String emailAddress, LocalDate birthDate, String nameFileIcon, int categoryId) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.emailAddress = emailAddress;
        this.birthDate = birthDate;
        this.nameFileIcon = nameFileIcon;
        this.categoryId = categoryId;
    }

    public Person(LocalDate date) {
        this.lastName = "Coucou";
        this.firstName = "Coucou";
        this.nickName = "Coucou";
        this.phoneNumber = "Coucou";
        this.phoneNumber = "0612027120";
        this.address = "coucou";
        this.emailAddress = "coucou";
        this.birthDate = date;
    }


    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getNameFileIcon() {
        return nameFileIcon;
    }

    public void setNameFileIcon(String nameFileIcon) {
        this.nameFileIcon = nameFileIcon;
    }
}
