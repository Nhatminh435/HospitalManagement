
package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This is a class representing a Patient and extended from {@linkplain Person}.
 * It contains properties such as patient's id, age, gender, address, phone,
 * diagnosis, admission date, discharge date, nurses assigned. 
 * It implements the Serializable interface to enable object serialization.
 * The class provides methods to get and set these properties.
 * 
 * @author N.Minh
 */
public class Patient extends Person implements Serializable{
    private String diagnosis;
    private String admissionDate;
    private String dischargeDate;
    private ArrayList<String> nurseAssigned;

    /**
     * Default constructor of Patient class.
     */
    public Patient() {
    }

    /**
     * Constructor of Patient class.
     * @param id
     * @param name
     * @param age
     * @param gender
     * @param address
     * @param phone
     * @param diagnosis
     * @param admissionDate
     * @param dischargeDate
     * @param nurseAssigned 
     */
    public Patient(String id, String name, int age, String gender, String address, String phone, String diagnosis, String admissionDate, String dischargeDate, ArrayList<String> nurseAssigned) {
        super(id, name, age, gender, address, phone);
        setDiagnosis(diagnosis);
        setAdmissionDate(admissionDate);
        setDischargeDate(dischargeDate);
        setNurseAssigned(nurseAssigned);
    }

    /**
     * Sets the diagnosis of Patient.
     * If diagnosis parameter is empty, it will return "Null".
     *
     * @param diagnosis The diagnosis of Patient.
     */
    public void setDiagnosis(String diagnosis) {
        if (!diagnosis.isEmpty()) {
            this.diagnosis = diagnosis;
        }
        else{
            this.diagnosis = null;
        }
    }

    /**
     * Sets the admission date of Patient.
     * 
     * If admission date parameter is empty, it will return "Null".
     *
     * @param admissionDate The admission date of Patient.
     */
    public void setAdmissionDate(String admissionDate) {
         if(!(admissionDate.trim().isEmpty())){
            this.admissionDate = admissionDate;
        }
        else this.admissionDate = "Null";
    }

    /**
     * Sets the discharge date of Patient.
     * 
     * If discharge date parameter is empty, it will return "Null".
     *
     * @param dischargeDate The discharge date of Patient.
     */
    public void setDischargeDate(String dischargeDate) {
         if(!(dischargeDate.trim().isEmpty())){
            this.dischargeDate = dischargeDate;
        }
        else this.dischargeDate = "Null";
    }

    /**
     * Sets the nurses assigned of Patient.
     * 
     * If nurseAssigned parameter is empty and
     * does not have 2 nurses, it will return "Null".
     *
     * @param nurseAssigned The ArrayList nurses assigned of Patient.
     */
    public void setNurseAssigned(ArrayList<String> nurseAssigned) {
        if (!nurseAssigned.isEmpty() && (nurseAssigned.size() == 2)) {
            this.nurseAssigned = nurseAssigned;
        }
        else{
            this.nurseAssigned = null;
        }
    }

    /**
     * Returns the diagnosis of the Patient.
     *
     * @return The diagnosis of the Patient.
     */
    public String getDiagnosis() {
        return diagnosis;
    }

    /**
     * Returns the admission date of the Patient.
     *
     * @return The admission date of the Patient.
     */
    public String getAdmissionDate() {
        return admissionDate;
    }

    /**
     * Returns the discharge date of the Patient.
     *
     * @return The discharge date of the Patient.
     */
    public String getDischargeDate() {
        return dischargeDate;
    }

    /**
     * Returns the ArrayList nurses assigned of the Patient.
     *
     * @return The ArrayList nurses assigned of the Patient.
     */
    public ArrayList<String> getNurseAssigned() {
        return nurseAssigned;
    }

    @Override
    public String toString() {
        return getId() + ", " + getName() + ", " + getAge() + ", " 
             + getGender()+ ", " + getAddress() + ", " + getPhoneNumber()+ ", "
             + getDiagnosis() + ", " + getAdmissionDate() + ", " 
             + getDischargeDate() + ", " + getNurseAssigned().get(0) + ", " + getNurseAssigned().get(1);
    }
    
    
}
