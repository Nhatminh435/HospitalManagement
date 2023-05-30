package model;

import java.io.Serializable;

/**
 * This is a class representing a Nurse and extended from {@linkplain Person}.
 * It contains properties such as nurse's ID, age, gender, address, phone,
 * department, shift, salary.
 * It implements the Serializable interface to enable object serialization.
 * The class provides methods to get and set these properties.
 * 
 * @author N.Minh
 */
public class Nurse extends Person implements Serializable{
    private String staffID;
    private String department;
    private String shift;
    private double salary;

    /**
     * Default constructor of Nurse class.
     */
    public Nurse() {
    }

    /**
     * Constructor of Nurse class.
     * @param staffID
     * @param name
     * @param age
     * @param gender
     * @param address
     * @param phone
     * @param department
     * @param shift
     * @param salary 
     */
    public Nurse(String staffID, String name, int age, String gender, String address, String phone, String department, String shift, double salary) {
        super(name, age, gender, address, phone);
        setStaffID(staffID);
        setDepartment(department);
        setShift(shift);
        setSalary(salary);
    }

    /**
     * Sets the Nurse ID.
     *
     * If staffID parameter is empty or contains
     * blank(s) inside, it will return "Null".
     * 
     * @param id The  Nurse ID.
     */
    public void setStaffID(String staffID) {
        if((!(staffID.trim().isEmpty())) && (!(staffID.contains(" "))) ){
            this.staffID = staffID;
        }
        else this.staffID = "Null";
    }

    /**
     * Sets the department of the nurse.
     * If department parameter is empty or has under 3 
     * or over 50 characters, it will return "Null".
     *
     * @param department The department of the Nurse.
     */
    public void setDepartment(String department) {
        if((!(department.trim().isEmpty())) && (department.length() >= 3) && (department.length() <= 50)){
            this.department = department;
        }
        else this.department = "Null";
    }
    
    /**
     * Sets the shift of the nurse.
     * 
     * If shift parameter is empty, it will return "Null".
     *
     * @param shift The shift of the Nurse.
     */
    public void setShift(String shift) {
        if(!(shift.trim().isEmpty())){
            this.shift = shift;
        }
        else this.shift = "Null";
    }

    /**
     * Sets the salary of the nurse.
     * 
     * If salary parameter is empty or an
     * negative number, it will return "Null".
     *
     * @param salary The salary of the nurse.
     */
    public void setSalary(double salary) {
        if((!(Double.toString(salary).trim().isEmpty())) && (salary > 0.0)){
            this.salary = salary;
        }
        else this.salary = 0;
    }

    /**
     * Returns the Nurse ID of the Nurse.
     *
     * @return The Nurse ID.
     */
    public String getStaffID() {
        return staffID;
    }

    /**
     * Returns the Nurse's department of the Nurse.
     *
     * @return The department of the Nurse.
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Returns the Nurse's shift of the Nurse.
     *
     * @return The shift of the Nurse.
     */
    public String getShift() {
        return shift;
    }

    /**
     * Returns the Nurse's salary of the Nurse.
     *
     * @return The salary of the Nurse.
     */
    public double getSalary() {
        return salary;
    }
    
    @Override
    public String toString(){
        return getStaffID() + ", " + getName() + ", " + getAge() + ", " 
             + getGender() + ", " + getAddress() + ", " + getPhoneNumber()+ ", " 
             + getDepartment() + ", " + getShift() + ", " + getSalary();
    }
}
