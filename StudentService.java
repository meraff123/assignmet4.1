package coderscampus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StudentService {
	
	private String filename;
	
	public static boolean checkNumbers(String s) {
		if(s.length() == 0 || s == null)
			return false;
		else {
			boolean numbers = true;
			for(int i = 0; i < s.length(); i++) {
				if(!Character.isDigit(s.charAt(i)))
					numbers = false;
			}
			return numbers;
		}
	}
	
	
	public StudentService(String filename) {
		this.filename = filename;
	}
	
	public ArrayList<Student> readStudents(){
		try {
			ArrayList<Student> students = new ArrayList<Student>();
	        File file = new File(filename);
	        FileReader fr = new FileReader(file);
	        BufferedReader br = new BufferedReader(fr);
	        String line = "";
	        String[] parts;
	        line = br.readLine();
	        while((line = br.readLine()) != null) {
	            parts = line.split(",");
	            String sID = parts[0];
	            String sName = parts[1];
	            String sCourse = parts[2];
	            String sGrade = parts[3];
	            
	            if(checkNumbers(sID) && checkNumbers(sGrade) && sName.length() > 1 && sCourse.length() > 1) {
	            	Student st = new Student(Integer.valueOf(sID), parts[1], parts[2], Integer.valueOf(parts[3]));
	            	students.add(st);
	            }
	         }
	         br.close();
	         return students;
		}
		catch(Exception ex) {
			System.out.println("Error in reading student data! - " + ex.getMessage());
			return null;
		}
	}
	
	public void printStudents(ArrayList<Student> students) {
		System.out.println("There are " + students.size() + " students in the master list!");
		for(Student st : students) {
			System.out.printf("%-10d %-25s %-20s %-20d\n", st.getID(),st.getName(),st.getCourse(), st.getGrade());
		}
	}
	
	public ArrayList<Student> splitStudents(String course, ArrayList<Student> students){
		ArrayList<Student> courseStudents = new ArrayList<Student>();
		for(Student st : students) {
			if(st.getCourse().contains(course)) {
				courseStudents.add(st);
			}
		}
		sortStudents(courseStudents);
		return courseStudents;
	}
	
	public void sortStudents(ArrayList<Student> students) {
		Comparator<Student> gradeComparator = (s1, s2) -> (int)(s1.getGrade() - s2.getGrade());
		students.sort(Collections.reverseOrder(gradeComparator));
	}
	
	public boolean saveStudents(String course, ArrayList<Student> students) {
		String filename = "";
		ArrayList<Student> courseStudents = splitStudents(course, students);
		boolean success = true;
		
		if(course.contains("COMPSCI")){
			filename = "course1.csv";
		}
		else if(course.contains("STAT")){
			filename = "course2.csv";
		}
		else if(course.contains("APMTH")) {
			filename = "course3.csv";
		}
		else {
			System.out.println("Unknown course name!");
			success = false;
		}
		
		
		if(success && filename.contains(".csv")) {
			try {			
				FileWriter fw = new FileWriter(filename);
				fw.write("Student ID,Student Name,Course,Grade\n");
				for(Student s : courseStudents) {
					fw.write(s.getID() + "," + s.getName() + "," + s.getCourse() + "," + s.getGrade() + "\n");
				}
				fw.close();
			}
			catch(Exception ex) {
				System.out.println("Error writing to file " + filename + "! - " + ex.getMessage());
				success = false;
			}
		}
		
		return success;
	}	
}
