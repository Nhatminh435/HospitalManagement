package business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import tool.Checker;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import model.Nurse;
import model.Patient;
import tool.AddPerson;

/**
 * This is a class representing a NurseList and extends Nurse class. It
 * implements the Serializable interface to enable object serialization. The
 * class provides methods to perform functions (create a nurse, find a nurse,
 * etc.)
 *
 * @author N.Minh
 */
public class NurseList extends HashMap<String, Nurse> implements Serializable {

    public static Scanner sc = new Scanner(System.in);

    /**
     * Return true if ID is valid (see conditions in 
     * {@linkplain Checker#checkValidStringWithoutBlank(java.lang.String)}
     * , and already exist; vice
     * versa if return false. , and already exist and vice versa.
     *
     * @param ID
     * @param nurseList
     * @return true if ID is valid (see more in), already exist and vice versa.
     *
     */
    public static boolean checkNurseID(String ID, NurseList nurseList) {
        boolean check1 = false;
        boolean check2 = false;

        if (nurseList.get(ID) == null) {
            check1 = true;
        } else {
            System.out.println("ID already exist.");
        }

        if (Checker.checkValidStringWithoutBlank(ID)) {
            check2 = true;
        }

        return check1 && check2;
    }

    /**
     * Create, check ID(know more in {@linkplain AddPerson}) and return new Nurse.
     *
     * @param nurseList
     * @param patientList
     * @return new Nurse (type: Nurse);
     */
    //Create a new nurse
    public Nurse addNurse(NurseList nurseList, PatientList patientList) {
        System.out.print("➜ Nurse ID: ");
        String staffID = AddPerson.addID("nurse", nurseList, patientList);    //Enter nurse's ID
        System.out.print("➜ Nurse's name: ");
        String staffName = AddPerson.addStringWithBlank();                    //Enter nurse's name         
        System.out.print("➜ Nurse's  age: ");
        int staffAge = AddPerson.addAge();                                    //Enter nurse's age        
        System.out.print("➜ Nurse's gender: ");
        String staffGender = AddPerson.addStringWithBlank();                  //Enter nurse's gender
        System.out.print("➜ Nurse's address: ");
        String staffAddress = AddPerson.addStringWithBlank();                 //Enter nurse's address
        System.out.print("➜ Nurse's phone number: ");
        String staffPhoneNumber = AddPerson.addPhoneNumber();                       //Enter nurse's phone        
        System.out.print("➜ Department (3-50 characters): ");
        String staffDepartment = AddPerson.addDepartment();                   //Enter nurse's department        
        System.out.print("➜ Shift: ");
        String staffShift = AddPerson.addStringWithBlank();                   //Enter nurse's shift        
        System.out.print("➜ Salary: ");
        Double staffSalary = AddPerson.addSalary();                           //Enter nurse's salary

        Nurse newNurse = new Nurse(staffID, staffName, staffAge, staffGender, staffAddress, staffPhoneNumber, staffDepartment, staffShift, staffSalary);

        return newNurse;
    }

    /**
     * Find nurses by inputting the nurse ‘s name or part of the name and return
     * the list of the relevant nurses. If the nurse does not exist, the message
     * “The nurse does not exist” is displayed.
     *
     * @param staffName
     * @param nurseList
     */
    public void findNurse(String staffName, NurseList nurseList) {
        if (!(staffName.trim().isEmpty())) {
            ArrayList<Nurse> nurses = new ArrayList<Nurse>();
            for (Entry<String, Nurse> entry : nurseList.entrySet()) {
                Nurse nurseValue = entry.getValue();
                if (nurseValue.getName().contains(staffName)) {
                    nurses.add(nurseValue);
                }
            }
            if (nurses.size() > 0){
                System.out.println("List of relevant names: ");
                    System.out.print("\n ----------------------------------------------------------------------------------------------\n");
                    System.out.printf("| %-10s | %-20s | %-3s | %-14s | %-20s | %-10s |\n", "Nurse Id", "Full name", "Age", "Phone", "Department", "Shift");
                    System.out.print(" ----------------------------------------------------------------------------------------------\n");
                for (int i = 0; i < nurses.size(); i++) {
                    System.out.printf("| %-10s | %-20s | %-3d | %-14s | %-20s | %-10s |\n", nurses.get(i).getStaffID(), nurses.get(i).getName(), nurses.get(i).getAge(), nurses.get(i).getPhoneNumber(), nurses.get(i).getDepartment(), nurses.get(i).getShift());
                    System.out.print(" ----------------------------------------------------------------------------------------------\n");
                }
            } else {
                System.out.println("➜ The nurse does not exist");
            }
        } else {
            System.out.println("➜ Cannot be left blank!");
        }
    }

    /**
     * <p>
     * Ask to import, check whether the ID is suitable to conditions (unique,
     * not empty, not having special characters, not contain blank) and return
     * the ID of Person. (type: String) Parameters:
     * <ul>
     * <li> role: import "nurse" or "patient" to identify the role of person.
     * <li> nurseList: import HashMap which contains information of Nurses.
     * <li> patientList: import HashMap which contains information of
     * Patients.</ul></p>
     * <p>
     * @param staffIdString
     * @param nurseList
     * @return ID of imported Person (type: String)
     * </p>
     */
    //Update nurse
    public boolean updateNurse(String staffIdString, NurseList nurseList) {
        if (!staffIdString.trim().isEmpty()) {
            if (nurseList.get(staffIdString) != null) {
                int choice = 0;
                System.out.println("Which information of " + nurseList.get(staffIdString).getName() + " you want to change.");
                do {
                    System.out.println("1: Name.                                4: Department.");
                    System.out.println("2: Address.                             5: Shift.");
                    System.out.println("3. Phone.                               6: Salary.");
                    System.out.println("Other: Exit.");
                    System.out.print("➜ Your choice: ");
                    choice = Integer.parseInt(sc.nextLine().trim());
                    switch (choice) {
                        case 1:
                            System.out.print("Enter new nurse's name: ");
                            String newName = sc.nextLine().trim();
                            nurseList.get(staffIdString).setName(newName);
                            break;
                        case 2:
                            System.out.print("Enter new nurse's address: ");
                            String newAddress = sc.nextLine().trim();
                            nurseList.get(staffIdString).setAddress(newAddress);
                            break;
                        case 3:
                            System.out.print("Enter new nurse's phone: ");
                            String newPhone = sc.nextLine().trim();
                            nurseList.get(staffIdString).setPhoneNumber(newPhone);
                            break;
                        case 4:
                            System.out.print("Enter new nurse's department: ");
                            String newDepartment = sc.nextLine().trim();
                            nurseList.get(staffIdString).setDepartment(newDepartment);
                            break;
                        case 5:
                            System.out.print("Enter new nurse's name: ");
                            String newShift = sc.nextLine().trim();
                            nurseList.get(staffIdString).setShift(newShift);
                            break;
                        case 6:
                            System.out.print("Enter new nurse's name: ");
                            String newSalaryString = sc.nextLine().trim();
                            Double newSalary = Double.parseDouble(newSalaryString);
                            nurseList.get(staffIdString).setSalary(newSalary);
                            break;
                    }
                    return true;
                } while ((choice > 0) && (choice < 7));
            } else {
                System.out.println("➜ The nurse does not exist");
                return false;
            }
        } else {
            System.out.println("➜ Cannot be left blank. Please try again.");
            return false;
        }
    }

    /**
     * Return true if Nurse is being assigned to Patient and vice versa.
     *
     * @param nurseID
     * @param nurseList
     * @param patientList
     * @return true if Nurse is being assigned to Patient and vice versa.
     */
    //Check nurse is working, if nurse is working, the method will return true.
    public boolean checkNurseWork(String nurseID, NurseList nurseList, PatientList patientList) {
        int work = 0;

        for (Entry<String, Patient> entry : patientList.entrySet()) {
            Patient patient = entry.getValue();
            for (int i = 0; i < patient.getNurseAssigned().size(); i++) {
                if (nurseID.equals(patient.getNurseAssigned().get(i))) {
                    work++;
                }
            }
        }
        return (work != 0);
    }

    /**
     * Return true if Nurse is being assigned to under or equal to 2 Patients
     * and vice versa.
     *
     * @param nurseID
     * @param nurseList
     * @param patientList
     * @return true if Nurse is being assigned to under or equal to 2 Patients
     * and vice versa.
     */
    //Check nurse is working, if nurse is working for under or equal 2 patients, the method will return true.
    public static boolean checkNurseWorkMax(String nurseID, NurseList nurseList, PatientList patientList) {
        int work = 0;

        for (Entry<String, Patient> entry : patientList.entrySet()) {
            Patient patient = entry.getValue();
            for (int i = 0; i < patient.getNurseAssigned().size(); i++) {
                if (nurseID.equals(patient.getNurseAssigned().get(i))) {
                    work++;
                }
            }
        }
        return (work >= 2);
    }

    /**
     * <p>
     * Remove the nurse from NurseList, more details below:
     * <ul>
     * <li> Check the import ID of Nurse needing to be deleted.
     * <li> If the nurse does not exist, the message “The nurse does not exist”
     * is displayed and this method return false.
     * <li> If Nurse has a task, this method return false.
     * <li> If deleted Nurse meets the condition, nurse will delete successfully
     * this method return true.</ul></p>
     * <p>
     * @param staffID
     * @param nurseList
     * @param patientList
     * @return true if deleted Nurse meets the condition and delete
     * successfully.
     * </p>
     */
    //Delete a nurse
    public boolean deleteNurse(String staffID, NurseList nurseList, PatientList patientList) {
        boolean check1 = false;
        boolean check2 = false;
        boolean check3 = false;
        if (!(staffID.isEmpty())) {
            check1 = true;
        } else {
            System.out.println("➜ Cannot be left blank. ");
        }
        if (nurseList.get(staffID) != null) {
            check2 = true;
        } else {
            System.out.print("➜ The nurse does not exist. ");
        }
        if (!checkNurseWork(staffID, nurseList, patientList)) {
            check3 = true;
        } else {
            System.out.print("➜ The nurse is working. ");
        }
        //Case nurse is having a task.
        if (check1 && check2 && check3) {
            nurseList.remove(staffID);
            System.out.print("\n");
            System.out.println("Delete successfully!");
            return true;
        } else {
            System.out.println("Delete failed\n");
            return false;
        }
    }

    /**
     * Get information of nurses from file with input path of file containing
     * information of nurses, put them into HashMap NurseList and return HashMap
     * NurseList
     *
     * @param pathNurse: input path of file containing information of nurse.
     * @return NurseList: list of information of nurses with the key is nurse's
     * id and value is nurse
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public NurseList getNurseFromFile(String pathNurse) throws IOException, ClassNotFoundException {
        NurseList nurseList = new NurseList();

        try {
            FileInputStream fileInput = new FileInputStream(new File(pathNurse));
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            while (true) {
                try {
                    Object object = objectInput.readObject();
                    if (object != null) {
                        if (object instanceof Nurse) {
                            Nurse nurse = (Nurse) object;
                            nurseList.put(nurse.getStaffID(), nurse);
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    break;
                }
            }
            objectInput.close();
            fileInput.close();
            System.out.println("Load information from Nurses.dat successfully!");
        } catch (FileNotFoundException f) {
            System.out.println("Error occur: " + f.getMessage());
        } catch (IOException e) {
            System.out.println("Fail to load file nurse.dat: " + e.getMessage());
        }
        return nurseList;
    }

    /**
     * Write information of nurses to Nurses.dat file through input path
     * [pathNurse]. If this method write file successfully, it will return true;
     * and vice versa
     *
     * @param pathNurse
     * @param nurseList
     * @return true if this method write file successfully.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public boolean saveNurseToFile(String pathNurse, NurseList nurseList) throws IOException, FileNotFoundException {
        try {
            FileOutputStream fileOutput = new FileOutputStream(new File(pathNurse));
            ObjectOutputStream objOutput = new ObjectOutputStream(fileOutput);
            for (Nurse nurse : nurseList.values()) {
                objOutput.writeObject(nurse);
            }
            objOutput.flush();
            objOutput.close();
            fileOutput.close();
            return true;
        } catch (FileNotFoundException f) {
            System.out.println("Error occur: " + f.getMessage());
            return false;
        } catch (IOException e) {
            System.out.println("Fail to save nurse's information to file.: " + e.getMessage());
            return false;
        }
    }
}
