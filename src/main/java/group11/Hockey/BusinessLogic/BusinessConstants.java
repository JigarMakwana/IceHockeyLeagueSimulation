package group11.Hockey.BusinessLogic;

public enum BusinessConstants {
	Enter_Conference_Name("Enter Conference Name: "),
	Enter_Division_Name("Enter Division Name: "),
	Enter_Team_Name("Enter Team Name:"),
	Enter_General_Manger_Name("Enter generalManager Name: "),
	Enter_headCoach_Name("Enter headCoach Name: "),
	Select_Player("Select 20 players for team: 18 Skaters and 2 Goalies"),
	Number_Of_Skaters("18"),
	Number_Of_Goalies("2"),
	Number_Of_Total_Players("20"),
	Reduce_strength_by(2),
	Likelihood_Of_Retirement_Low(1000),
	Likelihood_Of_Retirement_High(750);
	

	
	String value;
	int intValue;
	BusinessConstants(String value){
		this.value = value;
	}
	
	BusinessConstants(int intValue) {
		this.intValue = intValue;
	}

	String getValue() {
		return value;
	}
	
	int getIntValue() {
		return intValue;
	}
}
