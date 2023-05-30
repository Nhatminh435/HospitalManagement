package business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import model.Patient;
import tool.AddPerson;
import tool.Checker;

/**
 * This is a class representing a PatientList, extends Patient class. 
 * It implements the Serializable interface to enable object serialization.
 * The class provides methods to perform functions (add a patient, display patient,etc.)
 *
 * @author N.Minh
 */
public class PatientList extends HashMap<String, Patient> implements Serializable {

    /**
     * Return true if ID meet conditions (more conditions in 
     * {@linkplain Checker#checkValidStringWithoutBlank(java.lang.String)})
     * and does not exist in imported PatientList.
     *
     * @param ID
     * @param patientList
     * @return
     */
    //Check id exist.
    public static boolean checkPatientID(String ID, PatientList patientList) {
        if (patientList.get(ID) == null) {
            return Checker.checkValidStringWithoutBlank(ID);
        } else {
            System.out.println("ID already exist.");
            return false;
        }
    }

    /**
     * Add, check (know more in {@linkplain AddPerson}) and return new Patient.
     *
     * @param nurseList
     * @param patientList
     * @return new Patient (type: Patient);
     */
    //Add new patient
    public Patient addPatient(PatientList patientList, NurseList nurseList) {
        Scanner sc = new Scanner(System.in);
        System.out.print("➜ ID: ");
        String patientID = AddPerson.addID("patient", nurseList, patientList);              //Enter patient's id        
        System.out.print("➜ Name: ");
        String patientName = AddPerson.addStringWithBlank();                                //Enter patient's name        
        System.out.print("➜ Age: ");
        int patientAge = AddPerson.addAge();                                                //Enter patient's age          
        System.out.print("➜ Gender: ");
        String patientGender = AddPerson.addStringWithBlank();                              //Enter patient's gender
        System.out.print("➜ Adress: ");
        String patientAddress = AddPerson.addStringWithBlank();                             //Enter patient's address
        System.out.print("➜ Phone number: ");
        String patientPhoneNumber = AddPerson.addPhoneNumber();                                   //Enter patient's phone        
        System.out.print("➜ Diagnosis: ");
        String patientDiagnosis = AddPerson.addStringWithBlank();                           //Enter patient's diagnosis    
        System.out.print("➜ Admission date (dd/MM/yyyy): ");
        String admissionDate = AddPerson.addDate();                                         //Enter patient's admission date    
        System.out.print("➜ Discharge date (dd/MM/yyyy): ");
        String dischargeDate = AddPerson.addDate();                                         //Enter patient's discharge date
        System.out.print("➜ Nurses are assigned: ");
        ArrayList<String> nurseAssigned = AddPerson.addNurseAssigned(nurseList, patientList);//Enter patient's nurse assigned
        Patient patient = new Patient(patientID, patientName, patientAge, patientGender, patientAddress, patientPhoneNumber, patientDiagnosis, admissionDate, dischargeDate, nurseAssigned);
        System.out.println("Add successfully!");
        return patient;
    }

    /**
     * Display the list of patients who have admission date or discharge date
     * between the imported startDate and endDate (if they have correct form
     * [dd/MM/yyyy]) with value: no., patient id, admission date, full name,
     * phone, diagnosis.
     *
     * @param patientList
     * @param startDateStr
     * @param endDateStr
     * @throws ParseException
     */
    public void displayPatientWithDay(PatientList patientList, String startDateStr, String endDateStr) throws ParseException {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date startDate = dateFormat.parse(startDateStr);
            Date endDate = dateFormat.parse(endDateStr);

            System.out.print("\n---------------------------------------------------------------------------------------------------\n");
            System.out.printf("| No. | %-10s | %14s | %-20s | %-14s | %20s |\n", "Patient Id", "Admission Date", "Full name", "Phone", "Diagnosis");
            System.out.print("---------------------------------------------------------------------------------------------------\n");

            int i = 0;
            for (PatientList.Entry<String, Patient> entry : patientList.entrySet()) {
                ++i;
                Patient patientInfo = entry.getValue();
                Date patientDay = dateFormat.parse(patientInfo.getAdmissionDate());
                if ((patientDay.after(startDate) && patientDay.before(endDate)) || (patientDay.equals(startDate)) || (patientDay.equals(endDate))) {
                    System.out.printf("| %3d | %-10s | %14s | %-20s | %-14s | %20s |", i, patientInfo.getId(), patientInfo.getAdmissionDate(), patientInfo.getName(), patientInfo.getPhoneNumber(), patientInfo.getDiagnosis());
                    System.out.print("\n---------------------------------------------------------------------------------------------------\n");
                }
            }
        } catch (ParseException e) {
            System.out.println("Error occur: " + e.getMessage());
        }
        
    }

    /**
     * Sort the list of patients by imported sortField(patient id or patient's
     * name) and sortOrder (ASC or DESC) with value: no., patient id, admission
     * date, full name, phone, diagnosis.
     *
     * @param patientList
     * @param sortField
     * @param sortOrder
     */
    public void sortPatient(PatientList patientList, String sortField, String sortOrder) {
        if ((sortField.matches("patient id") && sortOrder.matches("ASC")) || (sortField.matches("id") && sortOrder.matches("ASC"))) {
            sortPatientIDASC(patientList);
        }
        if (sortField.matches("patient id") && sortOrder.matches("DESC") || (sortField.matches("id") && sortOrder.matches("DESC"))) {
            sortPatientIDDESC(patientList);
        }
        if (sortField.matches("patient's name") && sortOrder.matches("ASC") || (sortField.matches("name") && sortOrder.matches("ASC"))) {
            sortPatientNameASC(patientList);
        }
        if (sortField.matches("patient's name") && sortOrder.matches("DESC") || (sortField.matches("name") && sortOrder.matches("DESC"))) {
            sortPatientNameDESC(patientList);
        }
        if ((!((sortField.matches("patient id")) || sortField.matches("patient's name"))) && (!((sortOrder.matches("ASC")) || sortOrder.matches("DESC")))) {
            System.out.println("Unvalid request");
        }
    }

    /**
     * Sort the list of patients by ID in ascending order.
     *
     * @param patientList
     */
    public void sortPatientIDASC(PatientList patientList) {
        ArrayList<PatientList.Entry<String, Patient>> list = new ArrayList<PatientList.Entry<String, Patient>>(patientList.entrySet());
        ArrayList<PatientList.Entry<String, Patient>> list1 = new ArrayList<PatientList.Entry<String, Patient>>(patientList.entrySet());

        Comparator<PatientList.Entry<String, Patient>> compareID = new Comparator<PatientList.Entry<String, Patient>>() {
            @Override
            public int compare(PatientList.Entry<String, Patient> o1, PatientList.Entry<String, Patient> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        };

        Collections.sort(list, compareID);

        displayListOfPatient(list, list1);
    }

    /**
     * Sort the list of patients by ID in descending order.
     *
     * @param patientList
     */
    public void sortPatientIDDESC(PatientList patientList) {
        ArrayList<PatientList.Entry<String, Patient>> list = new ArrayList<PatientList.Entry<String, Patient>>(patientList.entrySet());
        ArrayList<PatientList.Entry<String, Patient>> list1 = new ArrayList<PatientList.Entry<String, Patient>>(patientList.entrySet());

        Comparator<PatientList.Entry<String, Patient>> compareID = new Comparator<PatientList.Entry<String, Patient>>() {
            @Override
            public int compare(PatientList.Entry<String, Patient> o1, PatientList.Entry<String, Patient> o2) {
                return o2.getKey().compareTo(o1.getKey());
            }
        };

        Collections.sort(list, compareID);

        displayListOfPatient(list, list1);
    }

    /**
     * Sort the list of patients by name in ascending order.
     *
     * @param patientList
     */
    public void sortPatientNameASC(PatientList patientList) {
        ArrayList<PatientList.Entry<String, Patient>> list = new ArrayList<PatientList.Entry<String, Patient>>(patientList.entrySet());
        ArrayList<PatientList.Entry<String, Patient>> list1 = new ArrayList<PatientList.Entry<String, Patient>>(patientList.entrySet());

        Comparator<PatientList.Entry<String, Patient>> compareName = new Comparator<PatientList.Entry<String, Patient>>() {
            @Override
            public int compare(PatientList.Entry<String, Patient> o1, PatientList.Entry<String, Patient> o2) {
                return o1.getValue().getName().compareTo(o2.getValue().getName());
            }
        };

        Collections.sort(list, compareName);

        displayListOfPatient(list, list1);
    }

    /**
     * Sort the list of patients by name in descending order.
     *
     * @param patientList
     */
    public void sortPatientNameDESC(PatientList patientList) {
        ArrayList<PatientList.Entry<String, Patient>> list = new ArrayList<PatientList.Entry<String, Patient>>(patientList.entrySet());
        ArrayList<PatientList.Entry<String, Patient>> list1 = new ArrayList<PatientList.Entry<String, Patient>>(patientList.entrySet());
        
        Comparator<PatientList.Entry<String, Patient>> compareName = new Comparator<PatientList.Entry<String, Patient>>() {
            @Override
            public int compare(PatientList.Entry<String, Patient> o1, PatientList.Entry<String, Patient> o2) {
                return o2.getValue().getName().compareTo(o1.getValue().getName());
            }
        };

        Collections.sort(list, compareName);

        displayListOfPatient(list, list1);

    }

    /**
     * Display the list of patients in PatientList with value: no., patient id,
     * admission date, full name, phone, diagnosis.
     *
     * @param list
     * @param list1
     */
    public void displayListOfPatient(ArrayList<PatientList.Entry<String, Patient>> list, ArrayList<PatientList.Entry<String, Patient>> list1) {
        System.out.print("\n---------------------------------------------------------------------------------------------------\n");
        System.out.printf("| %3s | %-10s | %14s | %-20s | %-14s | %20s |\n", "No.", "Patient Id", "Admission Date", "Full name", "Phone", "Diagnosis");
        System.out.print("---------------------------------------------------------------------------------------------------\n");
        for (PatientList.Entry<String, Patient> entry : list) {
            System.out.printf("| %3d | %-10s | %14s | %-20s | %-14s | %20s |", (list1.indexOf(entry) + 1), entry.getValue().getId(), entry.getValue().getAdmissionDate(), entry.getValue().getName(), entry.getValue().getPhoneNumber(), entry.getValue().getDiagnosis());
            System.out.print("\n---------------------------------------------------------------------------------------------------\n");
        }
    }

    /**
     * Get information of patients from file with input path of file containing
     * information of patient, put them into HashMap PatientList and return
     * HashMap PatientList
     *
     * @param pathPatient: input path of file containing information of
     * patients.
     * @return PatientList: list of information of patients with the key is
     * patient's id and value is patient.
     * @throws java.io.FileNotFoundException
     * @throws IOException
     */
    //Get information of Patient from encrypted file Patient.dat
    public PatientList getPatientFromFile(String pathPatient) throws FileNotFoundException, IOException {
        PatientList patientList = new PatientList();

        try {
            FileInputStream fileInput = new FileInputStream(new File(pathPatient));
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            while (true) {
                try {
                    Object object = objectInput.readObject();
                    if (object != null) {
                        if (object instanceof Patient) {
                            Patient patient = (Patient) object;
                            patientList.put(patient.getId(), patient);
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    break;
                }
            }
            objectInput.close();
            fileInput.close();
            System.out.println("Load information from Patients.dat successfully!");
        }catch(FileNotFoundException f){
            System.out.println("Error occur: " + f.getMessage());
        } catch (IOException e) {
            System.out.println("Fail to load file nurse.dat");
        }
        return patientList;
    }

    /**
     * Write information of patients to Patients.dat file through input path
     * [pathPatient]. If this method write file successfully, it will return
     * true; and vice versa
     *
     * @param pathPatient
     * @param patientList
     * @return true if this method write file successfully.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public boolean savePatientToFile(String pathPatient, PatientList patientList) throws IOException {
        try {
            FileOutputStream fileOutput = new FileOutputStream(new File(pathPatient));
            ObjectOutputStream objOutput = new ObjectOutputStream(fileOutput);
            for (Patient patient : patientList.values()) {
                objOutput.writeObject(patient);
            }
            objOutput.flush();
            objOutput.close();
            fileOutput.close();
            return true;
        }catch(FileNotFoundException f){
            System.out.println("Error occur: " + f.getMessage());
            return false;
        } catch (IOException e) {
            System.out.println("Fail to save patient file.");
            return false;
        }
    }
}
