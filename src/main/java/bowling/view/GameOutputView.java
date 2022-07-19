package bowling.view;

import bowling.domain.*;

import java.util.Optional;

public class GameOutputView {

    public static final String PRINT_FORMAT = "|%s|";
    public static final String PITCH_RESULT_DELIM = "/";
    public static final int FILL_SPACE_LENGTH = 6;
    public static final String NUMBER_FORMAT = "%02d";
    private static GameOutputView instance;


    public static GameOutputView getInstance() {
        if (instance == null) {
            instance = new GameOutputView();
        }
        return instance;
    }

    public void printGame(BowlingGame game) {
        printFrameNumberLine();
        printFrameResult(game);
        System.out.println("");
    }

    private void printFrameResult(BowlingGame game) {
        System.out.printf(PRINT_FORMAT, game.getUser().getName());

        Frames frames = game.getFrames();
        for (int i = 0; i < frames.getValues().size() - 1; i++) {
            printNormalFrame((NormalFrame) frames.getValues().get(i));
        }
        printFinalFrame((FinalFrame) frames.getValues().get(Frames.FRAME_NUMBER - 1));
        System.out.println("");
    }

    private void printFinalFrame(FinalFrame frame) {
        FinalFirstPitch first = frame.getFinalFirstPitch();
        FinalSecondPitch second = frame.getFinalSecondPitch();
        FinalBonusPitch bonus = frame.getFinalBonusPitch();

        if (Optional.ofNullable(bonus).isPresent()) {
            printFinalBonusPitch(frame);
            return;
        } else if (Optional.ofNullable(second).isPresent()) {
            printFinalSecondPitch(frame);
            return;
        } else if (Optional.ofNullable(first).isPresent()) {
            printFinalFirstPitch(frame);
            return;
        }
        printEmptyFrame();
    }

    private void printFinalFirstPitch(FinalFrame frame) {
        FinalFirstPitch first = frame.getFinalFirstPitch();
        System.out.printf(PRINT_FORMAT, fillSpace(first.getStatus().getExpression()));
    }

    private void printFinalSecondPitch(FinalFrame frame) {
        FinalFirstPitch first = frame.getFinalFirstPitch();
        FinalSecondPitch second = frame.getFinalSecondPitch();

        String expression = first.getStatus().getExpression() + PITCH_RESULT_DELIM + second.getStatus().getExpression();
        System.out.printf(PRINT_FORMAT, fillSpace(expression));
    }

    private void printFinalBonusPitch(FinalFrame frame) {
        FinalFirstPitch first = frame.getFinalFirstPitch();
        FinalSecondPitch second = frame.getFinalSecondPitch();
        FinalBonusPitch bonus = frame.getFinalBonusPitch();

        String expression = first.getStatus().getExpression() + PITCH_RESULT_DELIM + second.getStatus().getExpression() + PITCH_RESULT_DELIM + bonus.getStatus().getExpression();
        System.out.printf(PRINT_FORMAT, fillSpace(expression));
    }

    private void printNormalFrame(NormalFrame frame) {
        NormalFirstPitch firstPitch = frame.getNormalFirstPitch();
        NormalSecondPitch secondPitch = frame.getNormalSecondPitch();
        if (Optional.ofNullable(secondPitch).isPresent()) {
            printNormalSecondPitch(frame);
            return;
        } else if (Optional.ofNullable(firstPitch).isPresent()) {
            printNormalFirstPitch(frame);
            return;
        }
        printEmptyFrame();
    }

    private void printEmptyFrame() {
        System.out.printf(PRINT_FORMAT, fillSpace(""));
    }

    private void printNormalFirstPitch(NormalFrame frame) {
        NormalFirstPitch first = frame.getNormalFirstPitch();
        System.out.printf(PRINT_FORMAT, fillSpace(first.getStatus().getExpression()));
    }

    private void printNormalSecondPitch(NormalFrame frame) {
        NormalFirstPitch first = frame.getNormalFirstPitch();
        NormalSecondPitch second = frame.getNormalSecondPitch();
        String expression = first.getStatus().getExpression() + PITCH_RESULT_DELIM + second.getStatus().getExpression();
        System.out.printf(PRINT_FORMAT, fillSpace(expression));
    }

    private String fillSpace(String value) {
        boolean left = true;
        while (value.length() < FILL_SPACE_LENGTH) {
            if (left) {
                value = " " + value;
            } else {
                value = value + " ";
            }
            left = !left;
        }
        return value;
    }

    private void printFrameNumberLine() {
        System.out.print("| NAME |");
        for (int i = 0; i < Frames.FRAME_NUMBER; i++) {
            System.out.printf(PRINT_FORMAT, fillSpace(String.format(NUMBER_FORMAT, i + 1)));
        }
        System.out.println("");
    }
}
