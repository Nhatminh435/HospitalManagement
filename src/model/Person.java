package model;

import java.io.Serializable;

/**
 * This is a class representing a Person.
 * It contains properties such as id, age, gender, address, phone.
 * It implements the Serializable interface to enable object serialization.
 * The class provides methods to get and set these properties.
 * 
 * @author N.Minh
 */
public class Person implements Serializable{
    private String id;
    private String name;
    private int age;
    private String gender;
    private String address;
    private String phoneNumber;

    /**
     * Default constructor of Person class.
     */
    public Person() {
    }

    /***
     * Constructor of Person class.
     * @param name
     * @param age
     * @param gender
     * @param address
     * @param phoneNumber
     */
    public Person(String name, int age, String gender, String address, String phoneNumber) {
        setName(name);
        setAge(age);
        setGender(gender);
        setAddress(address);
        setPhoneNumber(phoneNumber);
    }
    
    /**
     * Constructor of Person class.
     * @param id
     * @param name
     * @param age
     * @param gender
     * @param address
     * @param phone 
     */
    public Person(String id, String name, int age, String gender, String address, String phoneNumber) {
        setId(id);
        setName(name);
        setAge(age);
        setGender(gender);
        setAddress(address);
        setPhoneNumber(phoneNumber);
    }

    /**
     * Sets the id of the person.
     * 
     * If id parameter is empty, it will return "Null".
     *
     * @param id The ID of the person.
     */
    public void setId(String id) {
        if(!(id.trim().isEmpty())){
            this.id = id;
        }
        else this.id = "Null";
    }

    /**
     * Sets the name of the person.
     * 
     * If name parameter is empty, it will return "Null".
     *
     * @param name The name of the person.
     */
    public void setName(String name) {
        if(!(name.trim().isEmpty())){
            this.name = name;
        }
        else this.name = "Null";
    }

    /**
     * Sets the age of the person.
     * 
     * If age parameter is empty or an 
     * negative number, it will return "Null".
     *
     * @param age The age of the person.
     */
    public void setAge(int age) {
        if((!(Integer.toString(age).isEmpty())) && (age > 0)){
            this.age = age;
        }
        else this.age = 0;
    }

    /**
     * Sets the gender of the person.
     * 
     * If gender parameter is empty, it will return "Null".
     *
     * @param gender The gender of the person.
     */
    public void setGender(String gender) {
        if(!(gender.trim().isEmpty())){
            this.gender = gender;
        }
        else this.gender = "Null";
    }

    /**
     * Sets the address of the person.
     * 
     * If address parameter is empty, it will return "Null".
     *
     * @param address The address of the person.
     */
    public void setAddress(String address) {
        if(!(address.trim().isEmpty())){
            this.address = address;
        }
        else this.address = "Null";
    }

    /**
     * Sets the phone of the person.
     * 
     * If phone parameter is empty or
     * does not start with "0", it will return "Null".
     *
     * @param phone The phone of the person.
     */
    public void setPhoneNumber(String phone) {
        if(!(phone.trim().isEmpty() && phone.startsWith("0"))){
            this.phoneNumber = phone;
        }
        else this.phoneNumber = "Null";
    }

    /**
     * Returns the id of the person.
     *
     * @return The id of the person.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of the person.
     *
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the age of the person.
     *
     * @return The age of the person.
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns the gender of the person.
     *
     * @return The gender of the person.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Returns the gender of the person.
     *
     * @return The gender of the person.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Returns the phone of the person.
     *
     * @return The phone of the person.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    @Override
    public String toString(){
        return getId() + ", " + getName() + ", " + getAge() + ", " + getGender() + ", " + getAddress() + ", " + getPhoneNumber();
    }
}
