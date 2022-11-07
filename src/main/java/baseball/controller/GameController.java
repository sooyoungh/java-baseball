package baseball.controller;

import baseball.model.Computer;
import baseball.model.Judge;
import baseball.model.Player;
import baseball.model.Score;
import baseball.view.InputView;
import baseball.view.OutputView;

public class GameController {

    private Player player;
    private Computer computer;
    private Judge judge;

    public void init() {
        createComputerAnswer();

        do {
            loadPlayerNumber();
        } while (!isGameSuccess());

        isGameRestartOrStop();
    }

    public void createComputerAnswer() {
        OutputView.printStart();
        computer = new Computer();
        computer.setRandomNumber();
    }

    public void loadPlayerNumber() {
        String playerNumber = InputView.playerInputNumber();

        player = new Player();
        player.setPlayerNumbers(playerNumber);
    }

    public Boolean isGameSuccess() {
        judge = new Judge(computer, player);

        Boolean gameSuccess = judge.isGameSuccess();
        printSuccessOrFailHint(gameSuccess);
        return gameSuccess;
    }

    public void printSuccessOrFailHint(Boolean gameSuccess) {
        Score score = judge.countScoreOfStrikeAndBall();
        OutputView.printHint(score);

        if (gameSuccess) OutputView.printGameClear();
    }
}