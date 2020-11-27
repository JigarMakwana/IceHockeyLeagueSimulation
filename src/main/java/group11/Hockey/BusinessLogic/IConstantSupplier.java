/*
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic;

public interface IConstantSupplier {
    int getActiveRosterSize();
    int getInActiveRosterSize();
    int getForwardSize();
    int getDefenseSize();
    int getGoalieSize();
}
