package models;

public abstract class Advertisement {
	
	private final Student student;
	private String studies;
	private String description;
	
	public Advertisement(Student student, String studies, String description){
		this.studies = studies;
		this.description = description;
		this.student = student;
	}

	public Advertisement(Student student){
		this.student = student;
		this.studies = "";
		this.description = "";
	}
	
	public Student getStudent() {
		return student;
	}
	
	public String getStudies() {
		return studies;
	}

	public void setStudies(String studies) {
		this.studies = studies;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
