package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.User;
import bowling.view.GameInputView;
import bowling.view.GameOutputView;

public class BowlingGameApp {

    public static void main(String[] args) {
        GameInputView gameInputView = GameInputView.getInstance();
        GameOutputView gameOutputView = GameOutputView.getInstance();

        BowlingGame bowlingGame = new BowlingGame(new User(gameInputView.getUserName()));
        while (bowlingGame.isEnd()){
            bowlingGame.play();
            gameOutputView.printGame(bowlingGame);
        }

    }
}
