package isen.project.model.entities;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

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

    public Person() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return personId == person.personId &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(nickName, person.nickName) &&
                Objects.equals(phoneNumber, person.phoneNumber) &&
                Objects.equals(address, person.address) &&
                Objects.equals(emailAddress, person.emailAddress) &&
                Objects.equals(birthDate, person.birthDate) &&
                Objects.equals(category, person.category) &&
                Objects.equals(nameFileIcon, person.nameFileIcon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, lastName, firstName, nickName, phoneNumber, address, emailAddress, birthDate, category, nameFileIcon);
    }

    public Person(int personId, String lastName, String firstName, String nickName, String phoneNumber, String address, String emailAddress, LocalDate birthDate, String nameFileIcon, Category category) {
        this.personId = personId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.emailAddress = emailAddress;
        this.birthDate = birthDate;
        this.nameFileIcon = nameFileIcon;
        this.category = category;
    }


    public Person(String lastName, String firstName, String nickName, String phoneNumber, String address, String emailAddress, LocalDate birthDate, String nameFileIcon, Category category) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.emailAddress = emailAddress;
        this.birthDate = birthDate;
        this.nameFileIcon = nameFileIcon;
        this.category = category;
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
        return birthDate != null ? birthDate : null;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Category getCategory() {
        return category;
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
}
