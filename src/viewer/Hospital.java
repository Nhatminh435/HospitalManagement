package viewer;

import business.NurseList;
import business.PatientList;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import model.Nurse;
import model.Patient;
import tool.AddPerson;

public class Hospital {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String[] nurseOptions = {"Create a nurse", "Find the nurse", "Update the nurse", "Delete the nurse"};
        String[] patientOptions = {"Add a patient", "Display patients", "Sort the patients list"};
        
        //Input and output file's path using System.getProperties()
        final String pathNurse = (System.getProperty("user.dir") + "/src/output/Nurses.dat"); 
        final String pathPatient  = (System.getProperty("user.dir") + "/src/output/Patients.dat"); 
        
        //Nurse and Patient isn't added to empList.
        NurseList empNurseList = new NurseList();
        PatientList empPatientList = new PatientList();
        
        Nurse n1 = new Nurse("A12", "Nguyen Tam", 20, "Male", "24 Nguyen Cong Tru", "0978234412", "A123", "M1", 15.5);
        Nurse n2 = new Nurse("A13", "Huy Tam", 21, "Male", "24 Nguyen Cong Tru", "0978234412", "A123", "M1", 15.5);
        Nurse n3 = new Nurse("A14", "Nguyen Hoang", 24, "Male", "24 Nguyen Cong Tru", "0978234412", "A123", "M1", 15.5);
        Nurse n4 = new Nurse("A15", "Huy Phuc", 25, "Male", "24 Nguyen Cong Tru", "0978234412", "A123", "M1", 15.5);
        Nurse n5 = new Nurse("A16", "Anh Khoa", 26, "Male", "24 Nguyen Cong Tru", "0978234412", "A123", "M1", 15.5);
        Nurse n6 = new Nurse("A17", "Minh Quan", 23, "Male", "24 Nguyen Cong Tru", "0978234412", "A123", "M1", 15.5);
        
        empNurseList.put(n1.getStaffID(), n1);
        empNurseList.put(n2.getStaffID(), n2);
        empNurseList.put(n3.getStaffID(), n3);
        empNurseList.put(n4.getStaffID(), n4);
        empNurseList.put(n5.getStaffID(), n5);
        empNurseList.put(n6.getStaffID(), n6);
        
        
        
        ArrayList nurse1 = new ArrayList();
        ArrayList nurse2 = new ArrayList();
        ArrayList nurse3 = new ArrayList();
        
        nurse1.add("A12");
        nurse1.add("A13");
        nurse2.add("A14");
        nurse2.add("A12");
        nurse3.add("A15");
        nurse3.add("A16");
        
        Patient p1 = new Patient("BN12", "Nguyen Huy", 16, "Male", "178 Quang Trung", "0978231456", "Flu", "12/03/2023", "12/04/2023", nurse1);
        Patient p2 = new Patient("BN13", "Huy Phuc", 20, "Male", "78 Nguyen Xien", "0978231456", "Blackdeath", "12/03/2023", "12/04/2023", nurse2);
        Patient p3 = new Patient("BN14", "Nguyen Tam", 21, "Male", "178 Thu Duc", "0978231456", "Corona", "12/03/2023", "12/04/2023", nurse3);
        
        empPatientList.put(p1.getId(), p1);
        empPatientList.put(p2.getId(), p2);
        empPatientList.put(p3.getId(), p3);
        try {
            int choiceMain = 0;
        
            do {    
                System.out.println(" --------------------------------------------------------------- ");
                System.out.println("|                      Hospital Management                      |");
                System.out.println("|---------------------------------------------------------------|");
                System.out.println("| 1: Nurse's management.        | 2: Patient's management.      |");
                System.out.println("| 3: Save data.                 | 4: Load data.                 |");
                System.out.println("|---------------------------------------------------------------|");
                System.out.println("|                          Other: Quit                          |");
                System.out.println(" --------------------------------------------------------------- ");
                System.out.print("➜ Your option: "); String choiceMainStr = sc.nextLine().trim();
                System.out.print("\n");
                if (choiceMainStr.matches("[1-4]")) {
                    choiceMain = Integer.parseInt(choiceMainStr);
                switch(choiceMain){
                case 1:
                    //Display menu of nurse's management
                    int nurseChoice = 0;

                    do {                  
                        nurseChoice = Menu.getChoice(nurseOptions, "nurse");
                        switch(nurseChoice){
                            case 1:
                                //Create a nurse
                                String loop1 = "y";
                                    if (loop1.equalsIgnoreCase("y")) {
                                        do {                 
                                            Nurse newNurse = empNurseList.addNurse(empNurseList, empPatientList);
                                            empNurseList.put(newNurse.getStaffID(), newNurse);
                                            System.out.println("\nEnter y to continue to create a nurse.");
                                            System.out.println("Enter any to exit.");
                                            System.out.print("➜ Your choice: ");
                                            loop1 = sc.nextLine().trim();
                                    } while (loop1.equalsIgnoreCase("y"));
                                }
                                    
                                break;  
                            case 2:
                                //Find the nurse
                                System.out.print("Enter the finding nurse's name: ");
                                String findNurseName = sc.nextLine().trim();
                                empNurseList.findNurse(findNurseName, empNurseList);
                                System.out.print("\n");
                                
                                break;
                            case 3:
                                //Update the nurse
                                System.out.print("Enter the updating nurse's ID: ");
                                String updateNurseID = sc.nextLine().trim();
                                if (empNurseList.updateNurse(updateNurseID, empNurseList)) {
                                    System.out.println("Success!");
                                }else{
                                    System.out.println("Fail!");
                                }
                                System.out.print("\n");

                                break;
                            case 4:
                                //Delete the nurse
                                System.out.print("Enter the deleting nurse's ID: ");
                                String deleteNurseID = sc.nextLine().trim();
                                empNurseList.deleteNurse(deleteNurseID, empNurseList, empPatientList);
                                System.out.print("\n");
                                
                                break;
                        }

                    } while ((nurseChoice >= 1) && (nurseChoice <= 4));
                        
                    break;
                case 2:
                    //Display menu of patient's management
                    int patientChoice = 0;
                    do {                  
                        patientChoice = Menu.getChoice(patientOptions, "patient");
                        switch(patientChoice){
                            case 1:
                            //Add a patient
                                String loop2 = "y";
                                if (loop2.equalsIgnoreCase("y")) {
                                    do {                                    
                                            Patient newPatient = empPatientList.addPatient(empPatientList, empNurseList);
                                            empPatientList.put(newPatient.getId(), newPatient);
                                            System.out.print("\n");
                                            System.out.println("Enter y to continue to create a patient.");
                                            System.out.println("Enter any to exit.");
                                            System.out.print("➜ Your choice: ");
                                            loop2 = sc.nextLine().trim();
                                    } while (loop2.equalsIgnoreCase("y"));
                                }
                                System.out.print("\n");
                                
                                break;
                            case 2:
                                //Display patients
                                System.out.println("LIST OF PATIENTS");
                                System.out.print("Start date (dd/MM/yyyy): "); String startDateStr = AddPerson.addDate();
                                System.out.print("End date (dd/MM/yyyy): "); String endDateStr = AddPerson.addDate();
                                empPatientList.displayPatientWithDay(empPatientList, startDateStr, endDateStr);
                                System.out.print("\n");
                            
                                break;
                            case 3:
                                //Sort patients
                                System.out.println("LIST OF PATIENTS");
                                System.out.print("Sorted by (patient id or patient's name): "); String sortField = sc.nextLine().trim();
                                System.out.print("Sorted order (ASC or DESC): ");               String sortOrder = sc.nextLine().trim();
                                empPatientList.sortPatient(empPatientList, sortField, sortOrder);
                                System.out.print("\n");
                                
                            break;
                            
                        }
                    } while ((patientChoice >= 1) && (patientChoice <= 3));
                    
                    break;
                case 3:
                    //Save data
                    if (empNurseList.saveNurseToFile(pathNurse, empNurseList)
                     && empPatientList.savePatientToFile(pathPatient, empPatientList)) {
                        System.out.println("Save succcessfully!");
                    }else{
                        System.out.println("Save failed!");
                    }
                    
                    break;
                case 4:
                    // Load data
                    empNurseList.putAll(empNurseList.getNurseFromFile(pathNurse));
                    empPatientList.putAll(empPatientList.getPatientFromFile(pathPatient));
                    
                    break;
                }
                }else{
                    System.out.print("Do you still want to exit (y/n)? :");
                    String exitProgram = sc.nextLine();
                    
                    if ((!exitProgram.equalsIgnoreCase("y")) && (!exitProgram.equalsIgnoreCase("n"))) {
                        do {                            
                            System.out.println("Unidentified request. Please try again.");
                            System.out.print("Do you still want to exit (y/n)? :");
                            exitProgram = sc.nextLine();
                        } while ((!exitProgram.equalsIgnoreCase("y")) && (!exitProgram.equalsIgnoreCase("n")));
                    }
                    if (exitProgram.equalsIgnoreCase("y")) {
                        choiceMain = 6;

                        empNurseList.saveNurseToFile(pathNurse, empNurseList);
                        empPatientList.savePatientToFile(pathPatient, empPatientList);
                    }
                    if(exitProgram.equalsIgnoreCase("n")){
                        choiceMain = 4;
                    }
                }
            } while ((choiceMain >= 1) && (choiceMain <= 4));
            
        System.out.println("=====================================END PROGRAM=====================================================");
        System.out.print("\n");
        System.out.println("Thank you for using. Goodbye!");
        
        } catch (IOException | ClassNotFoundException | NumberFormatException | ParseException e) {
            System.out.println("Error occur: " + e.getMessage());
        }
    }
}
