package coderscampus;

import java.util.ArrayList;

public class Main {

		public static void main(String[] args) {
			System.out.println("*** Tester program for Students Master List ***");
			
			// creating a student service object
			StudentService ss = new StudentService("student-master-list.csv");
			
			// reading students from the master list file
			ArrayList<Student> students = ss.readStudents();
			
			// for testing purposes: sorting and printing all students
			ss.sortStudents(students);
			ss.printStudents(students);
					
			// for testing purposes: separating and printing students per courses (with sorting)	
			ArrayList<Student> course1 = ss.splitStudents("COMPSCI", students);
			ArrayList<Student> course2 = ss.splitStudents("STAT", students);
			ArrayList<Student> course3 = ss.splitStudents("APMTH", students);
			
			System.out.println("\nCOMPSCI STUDENTS");
			ss.printStudents(course1);
			
			System.out.println("\nSTAT STUDENTS");
			ss.printStudents(course2);
			
			System.out.println("\nAPMTH STUDENTS");
			ss.printStudents(course3);
					
			// the main part of the assignment: separating and saving students in 3 csv files
			boolean success1 = ss.saveStudents("COMPSCI", students);
			boolean success2 = ss.saveStudents("STAT", students);
			boolean success3 = ss.saveStudents("APMTH", students);
			
			// writing a status message, just to be sure that all 3 files were generated successfully
			System.out.println("\nSuccess of writing to separate csv files (1, 2, 3) : " 
								+ success1 + ", " + success2 + ", " + success3);
		}
	}


