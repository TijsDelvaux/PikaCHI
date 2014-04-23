package models;

public enum Language {
	
	Nederlands{

		public String toString() {return "Nederlands";}
		public String myTutorAdvertisements() {return "Mijn bijles-advertenties";}
		public String messages() {return "Berichten";}
		public String advertisement(){return "Advertentie";}
		public String viewAdvertisements(){return "Bekijk advertenties";}
		public String myAdvertisements(){return "Mijn advertenties";}
		public String postAdvertisement(){return "Advertentie posten: ";}
		public String searchTutor(){return "Bijles zoeken";}
		public String giveTutoring(){return "Bijles geven";}
		public String changeAdvertisements(){return "Advertentie's bekijken of aanpassen: ";}
		public String newStudentAdvertisement(){return "Nieuwe advertentie bijleszoekende";}
		public String newTutorAdvertisement(){return "Nieuwe advertentie bijlesgever";}
		public String studies(){return "Studies";}
		public String description(){return "Beschrijving";}
		public String post(){return "Post";}
		public String tutorAdvertisement(){return "Advertentie bijlesgever";}
		public String alreadyTutorAdvertisement(){return "Je hebt al een bijlesgeversadvertentie, je kan deze hier aanpassen:";}
		public String price(){return "Prijs";}
		public String notYetAstudentAdvertention(){return "Je hebt nog geen bijleszoekeradvertentie geplaatst. Klik hier om er nu 1 te plaatsen!";}
		public String notYetATutorAdvertention(){return "Je hebt nog geen bijlesgeversadvertentie geplaatst. Klik hier om er nu 1 te plaatsen!";}
		public String placeAdvertisement(){return "Plaats advertentie";}
	}, 
	
	English{

		public String toString() {return "English";}
		public String myTutorAdvertisements() {return null;}
		public String messages() {return null;}
		public String advertisement(){return null;}
		public String viewAdvertisements(){return null;}
		public String myAdvertisements(){return null;}
		public String postAdvertisement(){return null;}
		public String searchTutor(){return null;}
		public String giveTutoring(){return null;}
		public String changeAdvertisements(){return null;}
		public String newStudentAdvertisement(){return null;}
		public String newTutorAdvertisement(){return null;}
		public String studies(){return null;}
		public String description(){return null;}
		public String post(){return null;}
		public String tutorAdvertisement(){return null;}
		public String alreadyTutorAdvertisement(){return null;}
		public String price(){return null;}
		public String notYetAstudentAdvertention(){return null;}
		public String notYetATutorAdvertention(){return null;}
		public String placeAdvertisement(){return null;}
		
	};
	
	public abstract String toString();
	public abstract String myTutorAdvertisements();
	public abstract String messages();
	public abstract String advertisement();
	public abstract String viewAdvertisements();
	public abstract String myAdvertisements();
	public abstract String postAdvertisement();
	public abstract String searchTutor();
	public abstract String giveTutoring();
	public abstract String changeAdvertisements();
	public abstract String newStudentAdvertisement();
	public abstract String newTutorAdvertisement();
	public abstract String studies();
	public abstract String description();
	public abstract String post();
	public abstract String tutorAdvertisement();
	public abstract String alreadyTutorAdvertisement();
	public abstract String price();
	public abstract String notYetAstudentAdvertention();
	public abstract String notYetATutorAdvertention();
	public abstract String placeAdvertisement();
}
