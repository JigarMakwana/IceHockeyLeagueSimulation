package group11.Hockey.InputOutput.JsonParsing;

public enum Attributes {
	LEAGUENAME("leagueName"),
	GAMEPLAYCONFIG("gameplayConfig"),
	AGING("aging"),
	AVERAGERETIREMENTAGE("averageRetirementAge"),
	MAXIMUMAGE("maximumAge"),
	GAMERESOLVER("gameResolver"),
	RANDOMWINCHANCE("randomWinChance"),
	INJURIES("injuries"),
	RANDOMINJURYCHANCE("randomInjuryChance"),
	INJURYDAYSLOW("injuryDaysLow"),
	INJURYDAYSHIGH("injuryDaysHigh"),
	TRAINING("training"),
	DAYSUNTILSTATINCREASECHECK("daysUntilStatIncreaseCheck"),
	TRADING("trading"),
	LOSSPOINT("lossPoint"),
	RANDOMTRADEOFFERCHANCE("randomTradeOfferChance"),
	MAXPLAYERSPERTRADE("maxPlayersPerTrade"),
	RANDOMACCEPTANCECHANCE("randomAcceptanceChance"),
	GMTABLE("gmTable"),
	SHREWD("shrewd"),
	GAMBLER("gambler"),
	NORMAL("normal"),
	CONFERENCES("conferences"),
	CONFERENCENAME("conferenceName"),
	DIVISIONS("divisions"),
	DIVISIONNAME("divisionName"),
	TEAMS("teams"),
	TEAMNAME("teamName"),
	GENERALMANAGER("generalManager"),
	HEADCOACH("headCoach"),
	NAME("name"),
	SKATING("skating"),
	SHOOTING("shooting"),
	CHECKING("checking"),
	SAVING("saving"),
	PLAYERS("players"),
	PLAYERNAME("playerName"),
	POSITION("position"),
	CAPTAIN("captain"),
	AGE("age"),
	FREEAGENTS("freeAgents"),
	COACHES("coaches"),
	GENERALMANAGERS("generalManagers");

	private String attribute;

	private Attributes(String attribute) {
		this.attribute = attribute;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

}
