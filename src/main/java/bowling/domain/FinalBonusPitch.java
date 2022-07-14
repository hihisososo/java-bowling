package bowling.domain;

import java.security.InvalidParameterException;

public class FinalBonusPitch {

    private static final String INVALID_PIN_MSG = "쓰러뜨린 핀 개수가 적합하지 않습니다.";
    public static final String INVALID_PREV_PITCH_MSG = "첫 피치와 둘째 피치가 스트라이크이거나 스페어가 아닙니다.";
    private final int downPin;
    private final Status status;

    public FinalBonusPitch(FinalFirstPitch firstPitch, FinalSecondPitch secondPitch, int downPin) {
        assertBonusPitch(firstPitch, secondPitch, downPin);
        this.downPin = downPin;
        this.status = getStatus(downPin);
    }

    private static Status getStatus(int downPin) {
        if (downPin == BowlingGame.PIN_NUMBER) {
            return Status.STRIKE;
        } else if (downPin == 0) {
            return Status.GUTTER;
        }
        return Status.HIT;
    }

    private static void assertBonusPitch(FinalFirstPitch firstPitch, FinalSecondPitch secondPitch, int downPin) {
        int downPinSum = firstPitch.getDownPin() + secondPitch.getDownPin();
        if (downPinSum < BowlingGame.PIN_NUMBER) {
            throw new InvalidParameterException(INVALID_PREV_PITCH_MSG);
        }

        if (downPin < 0 || downPin > BowlingGame.PIN_NUMBER) {
            throw new InvalidParameterException(INVALID_PIN_MSG);
        }
    }

    public int getDownPin() {
        return downPin;
    }

    public Status getStatus() {
        return status;
    }

}
