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
        this.status = getStatus(firstPitch, secondPitch, downPin);
    }

    private Status getStatus(FinalFirstPitch firstPitch, FinalSecondPitch secondPitch, int downPin) {
        if (firstPitch.getStatus() == Status.STRIKE) {
            return getStatusFirstStrike(secondPitch, downPin);
        }
        return getStatusFirstNotStrike(downPin);
    }

    //첫째가 스트라이크가 아니라면, 무조건 스페어 처리 된 것이다.
    private Status getStatusFirstNotStrike(int downPin) {
        return getStatusWithPrevStrikeOrSpare(downPin);
    }

    private Status getStatusFirstStrike(FinalSecondPitch secondPitch, int downPin) {
        if (secondPitch.getStatus() == Status.STRIKE) {
            return getStatusWithPrevStrikeOrSpare(downPin);
        }
        return getStatusWithRemainPin(secondPitch, downPin);
    }

    private Status getStatusWithRemainPin(FinalSecondPitch secondPitch, int downPin) {
        int downPinSum = secondPitch.getDownPin() + downPin;
        if (downPinSum == BowlingGame.PIN_NUMBER) {
            return Status.SPARE;
        } else if (0 == downPinSum) {
            return Status.MISS;
        } else if (0 == downPin) {
            return Status.GUTTER;
        }
        return Status.HIT;
    }

    private Status getStatusWithPrevStrikeOrSpare(int downPin) {
        if (downPin == BowlingGame.PIN_NUMBER) {
            return Status.STRIKE;
        } else if (downPin == 0) {
            return Status.GUTTER;
        }
        return Status.HIT;
    }

    private static void assertBonusPitch(FinalFirstPitch firstPitch, FinalSecondPitch secondPitch, int downPin) {
        int downPinSum = firstPitch.getDownPin() + secondPitch.getDownPin();
        //핀의 합이 10을 넘어야 보너스 게임을 할 수 있음
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
