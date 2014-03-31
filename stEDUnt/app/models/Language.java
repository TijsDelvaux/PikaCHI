package models;

public enum Language {
	
	Nederlands{

		public String toString() {return "Nederlands";}
		public String myTutorAdvertisements() {return "Mijn bijles-advertenties";}
		public String messages() {return "Berichten";}
		public String viewAdvertisements(){return "Bekijk berichten";}
		
		
	}, 
	
	English{

		public String toString() {return "English";}
		public String myTutorAdvertisements() {return null;}
		public String messages() {return null;}
		public String viewAdvertisements(){return null;}
		
	};
	
	public abstract String toString();
	public abstract String myTutorAdvertisements();
	public abstract String messages();
	public abstract String viewAdvertisements();
	
}
