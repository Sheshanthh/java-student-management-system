import java.util.*; //importing all nesssary modules 
import java.io.*;

class Student { // creating student class for task2 
    String name; //student class attributes 
    String id;
    Module module;

    public Student(String name, String id) { //constrcuter for student class
        this.name = name;//set name and id 
        this.id = id;
        this.module = new Module(0, 0, 0); // module marks are 0 in default 
    }

    public Module getModule() {// method for geting module info
        return module;
    }

    public void setModule(Module module) {// method to set module info
        this.module = module;
    }
}

class Module { // Module class for task2 
    public int Mmarks1; // attributes 
    public int Mmarks2;
    public int Mmarks3;
    public String Grade;

    public Module(int Mmarks1, int Mmarks2, int Mmarks3) { // contructer for module class
        this.Mmarks1 = Mmarks1;
        this.Mmarks2 = Mmarks2;
        this.Mmarks3 = Mmarks3;
        this.Grade = ModuleGrade();// calling the method to calculate grade 
    }

    public int getMmarks1() {
        return Mmarks1;
    }

    public void setMmarks1(int Mmarks1) {
        this.Mmarks1 = Mmarks1;
        this.Grade = ModuleGrade();
    }

    public int getMmarks2() {
        return Mmarks2;
    }

    public void setMmarks2(int Mmarks2) {
        this.Mmarks2 = Mmarks2;
        this.Grade = ModuleGrade();
    }

    public int getMmarks3() {
        return Mmarks3;
    }

    public void setMmarks3(int Mmarks3) {
        this.Mmarks3 = Mmarks3;
        this.Grade = ModuleGrade();
    }

    public String getModuleGrade() {
        return Grade;
    }

    private String ModuleGrade() { // Calculate the grade based on average marks
        double average = (Mmarks1 + Mmarks2 + Mmarks3) / 3.0;

        if (average >= 80) {
            return "Distinction";
        } else if (average >= 70) {
            return "Merit";
        } else if (average >= 40) {
            return "Pass";
        } else {
            return "Fail";
        }
    }
}

public class cw_sheshanth_Studentmanagement_system { // main class
    static int totalStudents = 100; // Total seats available
    static int option = 0;//initilizing varialbe 
    static int countStudents = 0;//no of students registerd 
    static int deleteIndex;
    static String[] id;//array to store name ids and objects 
    static String[] name;
    static Student[] students;

    static {// static block to initialize arrays first 
        id = new String[totalStudents];
        name = new String[totalStudents];
        students = new Student[totalStudents];
    }

    public static void main(String[] args) {// main function
        Scanner scan = new Scanner(System.in);// scanner object to read inputs from user 

        try {//exception handling for wrong inputs 
            while (true) { // this loops runs until the user enters number 9
                menu();// calling the menu function to displauy the menu 
                try {
                    option = scan.nextInt();
                    scan.nextLine(); // Consume newline after reading integer

                    if (option == 1) {
                        availableseat();
                    } else if (option == 2) {
                        register(scan);
                    } else if (option == 3) {
                        deleteStudent(scan);
                    } else if (option == 4) {
                        search(scan);
                    } else if (option == 5) {
                        store();
                    } else if (option == 6) {
                        load();
                    } else if (option == 7) {
                        view();
                    } else if (option == 8) {
                        additionalOptions(scan);
                    } else if (option == 9) {
                        System.out.println("Exiting.");
                        break;
                    } else {
                        System.out.println("INVALID OPTION");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scan.nextLine(); // Consume newline left after nextInt
                }
            }
        } finally {
            scan.close();//closing scanner object 
        }
    }

    public static void menu() {
        System.out.println("\nStudent Management System Menu:");
        System.out.println("press 1 Check available seats");
        System.out.println("press 2 Register student");
        System.out.println("press 3 Remove student");
        System.out.println("press 4 Search student");
        System.out.println("press 5 Store student details into a file");
        System.out.println("press 6 Load student details from file");
        System.out.println("press 7 View student list");
        System.out.println("press 8 Additional Controls");
        System.out.println("press 9 Exit");
        System.out.print("Enter your choice: ");
    }

    public static void availableseat() { // function to check the availbe seats 
        System.out.print(" Available seats for this semester is " + (totalStudents - countStudents));
    }

    public static void register(Scanner scan) { // method to register student 
        if (countStudents >= totalStudents) { 
            System.out.println(" no seats available for registering ");
        } else {
            System.out.println("enter student ID");//asking input from user 
            String studentid = scan.nextLine();//stroring in the varaible called student id 
            System.out.println(" enter student name for registration ");
            String studentname = scan.nextLine();

            Student newStudent = new Student(studentname, studentid);// creating new objects for student class
            students[countStudents] = newStudent;// inserting of student object into array 
            id[countStudents] = studentid;// inserting student name and ids in to  the corresponding arrays 
            name[countStudents] = studentname;
            countStudents++;// increaseing the counter by 1 
            System.out.println("REGISTERED");
        }
    }

    public static void deleteStudent(Scanner scan) {
        System.out.print("Enter student ID to delete: ");
        String deleteID = scan.nextLine();
        boolean studentDeleted = false;
    
        for (int i = 0; i < countStudents; i++) {
            if (id[i].equals(deleteID)) {
                deleteIndex = i;
                studentDeleted = true; // Mark student as found and delete
                break;
            }
        }
        if (studentDeleted) {
            // Shift elements to remove deleted student
            students[deleteIndex] = students[countStudents - 1];
            id[deleteIndex] = id[countStudents - 1];
            name[deleteIndex] = name[countStudents - 1];
    
            // Clear the last element
            students[countStudents - 1] = null;
            id[countStudents - 1] = null;
            name[countStudents - 1] = null;
    
            countStudents--; // Decrease count of registered students
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student ID not found.");
        }
    }      

    public static void search(Scanner scan) {
        System.out.print("Enter student ID to find: ");// asking user input for searching 
        String studentid = scan.nextLine();//storing it in the varaible 

        for (int i = 0; i < countStudents; i++) { //itterates throgh the array to find the matching student id 
            if (id[i] != null && id[i].equals(studentid)) {
                System.out.println("Student found: ID = " + id[i] + ", Name = " + name[i]);// prints the student information
                return;
            }
        }

        System.out.println("Student not found.");
    }

    public static void store() {
        try {
            File file = new File("students_list.txt");// creating new file named student list.txt
            try (PrintWriter writer = new PrintWriter(file)) {// Try-with-resources: Create a PrintWriter that will write to the file
                for (int i = 0; i < countStudents; i++) {// itterates throgh the array till the current count 
                    writer.println(id[i] + " " + name[i]);// prints the student id and name in the file 
                }
                System.out.println("Student details stored successfully.");
                System.out.println("File location: " + file.getAbsolutePath());
            }
        } catch (Exception e) {
            System.out.println("Error occurred: " );//prints error if any exception occured 
        }
    }

    public static void load() {// creating load method 
        try { // opening try block 
            File file = new File("students_list.txt"); // opening the file named students_list.txt to load the transcations 
            if (!file.exists()) {// checking the files if that exist 
                System.out.println("File not found: " + file.getAbsolutePath());
                return;
            }
            Scanner fileScanner = new Scanner(file);// creating scanner object 
            countStudents = 0;// making the counter to 0 before stating
    
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" "); // splitng the line into two parts to take name and id 
                if (parts.length < 2) {// if the part is < 2 the line foramt is invalid and skips the  iteration
                    System.out.println("Invalid line format: " + line);
                    continue;
                }
    
                String studentId = parts[0];// extract student name and id 
                String studentName = parts[1];
    
                id[countStudents] = studentId;// storing the nessary deatails to the arrays 
                name[countStudents] = studentName;
                students[countStudents] = new Student(studentName, studentId);// creating a new student object and storing it in the file 
                countStudents++;// increasing the student counter by 1 
            }
            fileScanner.close();// closing scanner object 
            System.out.println("Loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: students_list.txt");
        } catch (Exception e) {
            System.out.println("Error: ");
        }
    }                    
    public static void view() {
        // Sorting the array of names using bubble sort 
        for (int i = 1; i < countStudents; i++) {
            for (int j = 0; j < countStudents - 1; j++) {
                if (name[j].compareTo(name[j + 1]) > 0) {// Compare adjacent names and swap if they are out of order
                    String tempName = name[j];
                    name[j] = name[j + 1];
                    name[j + 1] = tempName;

                    String tempId = id[j];
                    id[j] = id[j + 1];
                    id[j + 1] = tempId;
                }
            }
        }

        // Displaying the sorted list
        System.out.println("Student list sorted by name:");
        for (int i = 0; i < countStudents; i++) {
            System.out.println("ID: " + id[i] + ", Name: " + name[i]);
        }
    }

    public static void additionalOptions(Scanner scan) {// method to print additional controls 
        System.out.println("\nAdditional Controls Menu:");
        System.out.println("a. Add student name");
        System.out.println("b. Module marks 1, 2, and 3");
        System.out.println("c. View module marks of a student");
        System.err.println("d. to view the summarry ");
        System.err.println("e. to view the report ");

        System.out.print("Enter your choice: ");
        char choice = scan.nextLine().charAt(0);// reading users input 
    
        if (choice == 'a') {// if users input is a it updates the exitting user name 
            System.out.println("Enter student ID to add name: ");
            String studentID = scan.nextLine();
            for (int i = 0; i < countStudents; i++) {
                if (id[i].equals(studentID)) {// iterating throgh the loop to find the mathcing student id 
                    System.out.println("Enter new name for student: ");
                    String newName = scan.nextLine();
                    name[i] = newName;
                    System.out.println("Name updated successfully.");
                    return;
                }
            }
            System.out.println("Student ID not found.");// prints not found if student id is not found 
        } else if (choice == 'b') {
            addMmarks(scan);// if the user choice is b it calls the method addmodule marks 
        } else if (choice == 'c') {
            viewModuleMarks(scan);
        } else if (choice == 'e') {
            Report();
        } else if (choice == 'd') {
            Summary();
        }        
    }
    

    public static void addMmarks(Scanner scan) {// defining a method to add module marks 
        System.out.println("Enter student ID to add module marks: ");
        String studentID = scan.nextLine();// reading the student id from user input 
        
        for (int i = 0; i < countStudents; i++) {// lopping over student array to findout the matching id
            if (students[i] != null && id[i].equals(studentID)) {// Check the current student is not null and if the student's ID matches the input ID
                System.out.println("Enter module marks (Module marks 1 Module marks 2 Module marks 3): ");// asking user input for the matching studend id module marks 
                int marks1 = scan.nextInt();
                int marks2 = scan.nextInt();
                int marks3 = scan.nextInt();
                scan.nextLine(); // Consume newline
        
                Module module = new Module(marks1, marks2, marks3);// creating a new module object with the marks 
                students[i].setModule(module);// caling set method to update the module marks for found student 
                System.out.println("Module marks updated successfully.");
                return;
            }
        }
        
        System.out.println("Student ID not found.");// prints error message if user id is not found 
    }

    public static void viewModuleMarks(Scanner scan) { //initinlizing a method named view modulemarks 
        System.out.println("Enter student ID to view module marks: ");
        String studentID = scan.nextLine();// reading user input 
        
        boolean studentFound = false; // Initializing a varaible  to track if student ID is found
        
        for (int i = 0; i < countStudents; i++) {// looping throgh the list of students
            if (id[i].equals(studentID)) {
                // Print student information if found
                if (students[i] != null) {// checks if data exist 
                    Module module = students[i].getModule();// getting the module data for the student 
                    System.out.println("Module Marks of  Student ID " + studentID );
                    System.out.println("Module 1: " + module.getMmarks1());
                    System.out.println("Module 2: " + module.getMmarks2());
                    System.out.println("Module 3: " + module.getMmarks3());
                    System.out.println("Module Grade: " + module.getModuleGrade());
                } else {
                    System.out.println("Student data is not available " + studentID);
                }
                studentFound = true; // Set to true if student ID is found
                break; // Exit the loop once the student is found or processed
            }
        }
        
        if (!studentFound) {
            System.out.println("Student ID not found.");
        }
    }
    public static void Summary() {// initizling method to create summary of transactions 
        System.out.println("Total student registrations: " + countStudents);
        int Module1Pass = 0;
        int Module2Pass = 0;
        int Module3Pass = 0; 
        for (int i = 0; i < countStudents; i++) {
            Module module = students[i].getModule();
            if (module.getMmarks1() > 40) {
                Module1Pass++;
            }
            if (module.getMmarks2() > 40) {
                Module2Pass++;
            }
            if (module.getMmarks3() > 40) {
                Module3Pass++;
            }
        }
        System.out.println("Total number of students scored  more than 40 marks in each module :");
        System.out.println("Module 1 = " + Module1Pass);
        System.out.println("Module 2 = " + Module2Pass);
        System.out.println("Module 3 = " + Module3Pass);
    }

    public static void Report() {//sorting using bubble sort 
        for (int i = 0; i < countStudents - 1; i++) {
            for (int j = 0; j < countStudents - i - 1; j++) {
                double avg1 = calavg(students[j]);
                double avg2 = calavg(students[j + 1]);
    
                if (avg1 < avg2) {
                    Student tempStudent = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = tempStudent;
    
                    String tempId = id[j];
                    id[j] = id[j + 1];
                    id[j + 1] = tempId;
    
                    String tempName = name[j];
                    name[j] = name[j + 1];
                    name[j + 1] = tempName;
                }
            }
        }
        System.out.println("\nStudent Report sorted by Average Marks (Highest to Lowest):");
        for (int i = 0; i < countStudents; i++) {
            Student student = students[i];
            String studentID = id[i];
            String studentName = name[i];
            Module module = student.getModule();
            double average = calavg(student);
            String grade = module.getModuleGrade();
    
            System.out.println("Student ID:" + studentID);
            System.out.println("Student Name:" + studentName);
            System.out.println("Module 1 Marks:" + module.getMmarks1());
            System.out.println("Module 2 Marks:" + module.getMmarks2());
            System.out.println("Module 3 Marks:" + module.getMmarks3());
            System.out.println("Total Marks: "+ (module.getMmarks1() + module.getMmarks2() + module.getMmarks3()));
            System.out.println("Average Marks:" + average);
            System.out.println("Grade:" + grade);
            System.out.println();
        }
    }

    public  static double calavg(Student student) {
        Module module = student.getModule();
        return (module.getMmarks1() + module.getMmarks2() + module.getMmarks3()) / 3.0;
    }
    

    
    
    
}
