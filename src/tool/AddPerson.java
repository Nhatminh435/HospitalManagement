
package tool;

import business.NurseList;
import business.PatientList;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class provides a method to check if a string matches certain conditions.
 * @author N.Minh
 * @see AddPerson#addAge() 
 * @see AddPerson#addDate() 
 * @see AddPerson#addDepartment() 
 * @see AddPerson#addID(java.lang.String, business.NurseList, business.PatientList) 
 * @see AddPerson#addNurseAssigned(business.NurseList, business.PatientList) 
 * @see AddPerson#addPhoneNumber() 
 * @see AddPerson#addSalary() 
 * @see AddPerson#addStringWithBlank() 
 */
public class AddPerson {
    public static Scanner sc = new Scanner(System.in);
    
    /**
     * Ask to import, check whether the ID is suitable to conditions 
     *(unique, not empty, not having special characters, not contain blank)
     * and return the ID of Person. (type: String)
     *
     * <p> Parameters:
     * <ul>
     *   <li> role: import "nurse" or  "patient" to identify the role of person.
     *   <li> nurseList: import HashMap which contains information of Nurses.
     *   <li> patientList: import HashMap which contains information of Patients.
     * </ul>
     *
     * @param role
     * @param nurseList
     * @param patientList
     * @return  ID of imported Person (type: String)
     * @see NurseList#checkNurseID(java.lang.String, business.NurseList) 
     * @see PatientList#checkPatientID(java.lang.String, business.PatientList) 
     */
    //Return the entered ID of Person
    public static String addID(String role, NurseList nurseList, PatientList patientList){    
        String id = sc.nextLine().trim();
        
        if (role.equalsIgnoreCase("nurse")) {
            if (!NurseList.checkNurseID(id, nurseList)) {
                do {                            
                    System.out.print("➜ Please enter again: ");
                    id = sc.nextLine().trim();
                } while (!NurseList.checkNurseID(id, nurseList));
            }
        }
            if (role.equalsIgnoreCase("patient")) {
                if (!PatientList.checkPatientID(id, patientList)) {
                    do {                
                        System.out.print("➜ Please enter again: ");
                        id = sc.nextLine().trim();
                } while (!PatientList.checkPatientID(id, patientList));
            }
        }
        return id;
    }
    
     /**
     * Ask to import, check whether the Name is suitable to conditions 
     *(not empty, not having special characters) and return the Name of Person.
     * 
     * <p>Recommend to use for Name, Gender, Address, etc.
     * 
     * @return  Imported string
     * @see Checker#checkValidStringWithBlank(java.lang.String) 
     */
    //Return the entered Name of Person

    public static String addStringWithBlank(){
        String str = sc.nextLine().trim();

        if (!Checker.checkValidStringWithBlank(str)) {
            do {                    
                System.out.print("➜ Please enter again: ");
                str = sc.nextLine().trim();
            } while (!Checker.checkValidStringWithBlank(str));
        }

        return str;
    }
    
    /**
     * Ask to import, check whether the Age is suitable to conditions (not empty, not having special characters,
     * not containing blank and a positive number) and return the Age of Person.
     * @return  Age of imported Person (type: int)
     * @see Checker#checkIntegerNumber(java.lang.String) 
     */
    //Return the entered Age of Person 
    public static int addAge(){
        String ageString = sc.nextLine().trim();
        if (!Checker.checkIntegerNumber(ageString)) {
                do {                    
                    System.out.print("➜ Please enter again: ");
                    ageString = sc.nextLine().trim();
                } while (!Checker.checkIntegerNumber(ageString));
            }
            if (Integer.parseInt(ageString) <= 0) {
                do {                    
                    System.out.print("➜ Please enter again: ");
                    ageString = sc.nextLine().trim();
                } while (Integer.parseInt(ageString) <= 0);
            }
        return Integer.parseInt(ageString);
    }
      
    /**
     * Ask to import, check whether the Phone number is suitable to 
     *conditions (not empty, not having special characters, valid phone number)
     * and return the Phone number of Person.
     * @return  Phone of imported Person (type: String)
     * @see Checker#checkValidPhoneNumber(java.lang.String) 
     */
    //Return the entered Phone of Person
    public static String addPhoneNumber(){
        String phone = sc.nextLine().trim();
        if (!Checker.checkValidPhoneNumber(phone)) {
                do {                    
                    System.out.print("➜ Please enter again: ");
                    phone = sc.nextLine();
                } while (!Checker.checkValidPhoneNumber(phone));
        }
        return phone;
    }
    
    /**
     * Ask to import, check whether the Date is suitable to 
     *conditions (not empty, wrong format [dd/MM/yyyy])
     * and return the Date of Person. (type: String)
     * @return  Date of imported Person
     * @see Checker#checkDateFormat(java.lang.String) 
     */
    //Return the entered Date of Person
    public static String addDate(){
        String date = sc.nextLine().trim();
        if (!Checker.checkDateFormat(date)) {
                do {                    
                    System.out.print("➜ Please enter again: ");
                    date = sc.nextLine();
                } while (!Checker.checkDateFormat(date));
            }
        return date;
    }
    
    /**
     * Ask to import, check whether the Department is suitable to conditions 
     *(not empty, not having special characters, string length has 3-50 characters)
     * and return the Department of Person. (type: String)
     * 
     * @return  Department of imported Person
     * @see Checker#checkValidDepartment(java.lang.String) 
     */
    //Return the entered Department of Person
    public static String addDepartment(){
        String department = sc.nextLine().trim();

        if(!Checker.checkValidDepartment(department)){
                do {                    
                    System.out.print("➜ Please enter again: ");
                    department = sc.nextLine().trim();
                } while (!Checker.checkValidDepartment(department));
            }

        return department;
    }
    
    /**
     * Ask to import, check whether the Salary is suitable to conditions (not empty, not having special characters,
     * not containing blank and a positive number) and return the Salary of Person.
     * @return  Salary of imported Person (type: Double)
     * @see Checker#checkValidDoubleNumber(java.lang.String) 
     */
    //Return the entered Salary of Person
    public static double addSalary(){
        String salary = sc.nextLine().trim();
        if (!Checker.checkValidDoubleNumber(salary)) {
                do {                    
                    System.out.print("➜ Salary: ");
                    salary = sc.nextLine().trim();
                } while (!Checker.checkValidDoubleNumber(salary));
            }
            if (Double.parseDouble(salary) <= 0) {
                do {                    
                    System.out.print("➜ Salary: ");
                    salary = sc.nextLine().trim();
                } while (Integer.parseInt(salary) <= 0);
            }
        return Double.parseDouble(salary);
    }
    
    /**
     * Ask to import, check whether the Nurses who are assigned Patient are 
     * suitable to conditions (not empty, not having special characters,
     * not containing blank, exist, etc.) and return the Nurses takes care Patient.
     * @param nurseList
     * @param patientList
     * @return  Nurses of imported Patient (type: ArrayList)
     * @see NurseList#checkNurseWorkMax(java.lang.String, business.NurseList, business.PatientList) 
     */
    //Return the entered Nurses takes care Patient
    public static ArrayList<String> addNurseAssigned(NurseList nurseList, PatientList patientList){
        ArrayList<String> nurseAssigned = new ArrayList<>();
        for(int i = 0; i < 2; i++){
                System.out.print("➜ Nurse " + (i + 1) + " ID: "); 
                String nurseID = sc.nextLine().trim();
                
                if (nurseID.isEmpty()) {
                    do {                        
                        System.out.println("Cannot be left blank.");
                        System.out.println("➜ Nurse " + (i + 1) + "ID: ");
                        nurseID = sc.nextLine().trim();
                    } while (nurseID.isEmpty());
                }
                
                if (nurseList.get(nurseID) == null) {
                    do {                        
                        System.out.println("Nurse does not exist.");
                        System.out.println("➜ Nurse " + (i + 1) + " ID: ");
                        nurseID = sc.nextLine().trim();
                    } while (nurseList.get(nurseID) == null);
                    
                }
                if (NurseList.checkNurseWorkMax(nurseID, nurseList, patientList) == true) {
                    do {           
                        System.out.println(nurseID + " already takes care 2 patients.");
                        System.out.println("➜ Nurse " + (i + 1) + " ID: ");
                        nurseID = sc.nextLine().trim();
                    } while (NurseList.checkNurseWorkMax(nurseID, nurseList, patientList) == true);
                }
                
                nurseAssigned.add(nurseID);
            }
        return nurseAssigned;
    }
}
