package isen.project.model.entities;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

/**
 * The type Person.
 */
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

    /**
     * Instantiates a new Person.
     */
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

    /**
     * Instantiates a new Person.
     *
     * @param personId     the person id
     * @param lastName     the last name
     * @param firstName    the first name
     * @param nickName     the nick name
     * @param phoneNumber  the phone number
     * @param address      the address
     * @param emailAddress the email address
     * @param birthDate    the birth date
     * @param nameFileIcon the name file icon
     * @param category     the category
     */
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


    /**
     * Instantiates a new Person.
     *
     * @param lastName     the last name
     * @param firstName    the first name
     * @param nickName     the nick name
     * @param phoneNumber  the phone number
     * @param address      the address
     * @param emailAddress the email address
     * @param birthDate    the birth date
     * @param nameFileIcon the name file icon
     * @param category     the category
     */
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


    /**
     * Gets person id.
     *
     * @return the person id
     */
    public int getPersonId() {
        return personId;
    }

    /**
     * Sets person id.
     *
     * @param personId the person id
     */
    public void setPersonId(int personId) {
        this.personId = personId;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets nick name.
     *
     * @return the nick name
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Sets nick name.
     *
     * @param nickName the nick name
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets email address.
     *
     * @return the email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets email address.
     *
     * @param emailAddress the email address
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Gets birth date.
     *
     * @return the birth date
     */
    public LocalDate getBirthDate() {
        return birthDate != null ? birthDate : null;
    }

    /**
     * Sets birth date.
     *
     * @param birthDate the birth date
     */
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Gets category.
     *
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets category.
     *
     * @param category the category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Gets name file icon.
     *
     * @return the name file icon
     */
    public String getNameFileIcon() {
        return nameFileIcon;
    }

    /**
     * Sets name file icon.
     *
     * @param nameFileIcon the name file icon
     */
    public void setNameFileIcon(String nameFileIcon) {
        this.nameFileIcon = nameFileIcon;
    }
}
