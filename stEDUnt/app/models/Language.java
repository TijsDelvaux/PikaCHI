package models;

public enum Language {
	
	Nederlands{

		public String toString() {return "Nederlands";}
		public String myTutorAdvertisements() {return "Mijn bijles-advertenties";}
		public String messages() {return "Berichten";}
		public String viewAdvertisements(){return "Bekijk advertenties";}
		public String myAdvertisements(){return "Mijn advertenties";}
		public String postAdvertisement(){return "Advertentie posten: ";}
		public String searchTutor(){return "Bijles zoeken";}
		public String giveTutoring(){return "Bijles geven";}
		public String changeAdvertisements(){return "Advertentie's bekijken of aanpassen: ";}
	}, 
	
	English{

		public String toString() {return "English";}
		public String myTutorAdvertisements() {return null;}
		public String messages() {return null;}
		public String viewAdvertisements(){return null;}
		public String myAdvertisements(){return null;}
		public String postAdvertisement(){return null;}
		public String searchTutor(){return null;}
		public String giveTutoring(){return null;}
		public String changeAdvertisements(){return null;}
		
	};
	
	public abstract String toString();
	public abstract String myTutorAdvertisements();
	public abstract String messages();
	public abstract String viewAdvertisements();
	public abstract String myAdvertisements();
	public abstract String postAdvertisement();
	public abstract String searchTutor();
	public abstract String giveTutoring();
	public abstract String changeAdvertisements();
}
