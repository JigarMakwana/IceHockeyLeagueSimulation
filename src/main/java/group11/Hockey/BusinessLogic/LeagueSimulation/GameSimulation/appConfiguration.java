/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation;

public class appConfiguration {
	public static int shifts = 40;
	public static int forwardMen = 3;
	public static int defenceMen = 2;
	public static int goalieMen = 1;
	public static int defenceMenStartIndex = 3;
	public static int goalieStartIndex = 5;
	public static int averageShootsPerTeam = 30;
	public static int maxDifferenceLimit = 10;
	public static int penaltyTime = 2;
	public static int penaltyRandomChance = 30;
	public static int penaltyOccuranceValue = 5;
	public static int saveChance = 5;
	public static float reduceDefenceStrength = (float) 0.75;
	public static int twoPeriodsTime = (int) (2 * shifts / 3);
	public static int finalPeriodsTime = (int) (shifts / 3);

}
