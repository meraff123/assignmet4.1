package coderscampus;

public class Student {

	private int ID;
	private String name;
	private String course;
	private int grade;
	
	public Student(int ID, String name, String course, int grade) {
		this.ID = ID;
		this.name = name;
		this.course = course;
		this.grade = grade;
	}
	
	public int getID() {
		return ID;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCourse() {
		return course;
	}
	
	public int getGrade() {
		return grade;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCourse(String course) {
		this.course = course;
	}
	
	public void setGrade(int grade) {
		this.grade = grade;
	}
}
