package bowling;

import bowling.view.GameInputView;

public class BowlingGameApp {

    public static void main(String[] args) {
        GameInputView gameInputView = GameInputView.getInstance();
        System.out.println(gameInputView.getUserName());

    }
}
