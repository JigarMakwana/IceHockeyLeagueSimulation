package group11.Hockey.BusinessLogic.models;

public interface IGameplayConfig {
    Aging getAging();
    GameResolver getGameResolver();
    Injuries getInjuries();
    Training getTraining();
    Trading getTrading();
}
