package bowling.view;

import java.util.Scanner;

public class GameInputView {

    private static final String USER_INPUT_MSG = "플레이어 이름은(3 english letters)?: ";
    private static GameInputView instance;
    private final Scanner scanner;

    private GameInputView() {
        this.scanner = new Scanner(System.in);
    }


    public static GameInputView getInstance() {
        if (instance == null) {
            instance = new GameInputView();
        }
        return instance;
    }

    public String getUserName() {
        System.out.print(USER_INPUT_MSG);
        return scanner.nextLine();
    }
}
