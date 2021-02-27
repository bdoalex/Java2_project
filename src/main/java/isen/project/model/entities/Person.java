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
    private Category category;


    private String nameFileIcon;

    public Person(int personId, String lastName, String firstName, String nickName, String phoneNumber, String address, String emailAddress, LocalDate birthDate, String nameFileIcon) {
        this.personId = personId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.emailAddress = emailAddress;
        this.birthDate = birthDate;
        this.nameFileIcon = nameFileIcon;
    }

    public Person(String lastName, String firstName, String nickName, String phoneNumber, String address, String emailAddress, LocalDate birthDate, String nameFileIcon) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.emailAddress = emailAddress;
        this.birthDate = birthDate;
        this.nameFileIcon = nameFileIcon;
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

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getNameFileIcon() {
        return nameFileIcon;
    }

    public void setNameFileIcon(String nameFileIcon) {
        this.nameFileIcon = nameFileIcon;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", birthDate=" + birthDate +
                ", category=" + category +
                '}';
    }
}
