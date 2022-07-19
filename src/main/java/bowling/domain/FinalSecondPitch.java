package bowling.domain;

import java.security.InvalidParameterException;

public class FinalSecondPitch {

    private static final String INVALID_PIN_MSG = "쓰러뜨린 핀 개수가 적합하지 않습니다.";
    private final int downPin;
    private final Status status;

    public FinalSecondPitch(FinalFirstPitch firstPitch, int downPin) {
        assertSecondPitch(firstPitch, downPin);
        this.downPin = downPin;
        this.status = getStatus(firstPitch, downPin);
    }

    private Status getStatus(FinalFirstPitch firstPitch, int secondDownPin) {
        if (firstPitch.getStatus() == Status.STRIKE) {
            return getStatusFirstStrike(secondDownPin);
        }
        return getStatusFirstNotStrike(firstPitch.getDownPin(), secondDownPin);
    }

    private Status getStatusFirstNotStrike(int firstDownPin, int secondDownPin) {
        int downPinSum = firstDownPin + secondDownPin;
        if (downPinSum == BowlingGame.PIN_NUMBER) {
            return Status.SPARE;
        } else if (0 == downPinSum) {
            return Status.MISS;
        } else if (0 == secondDownPin) {
            return Status.GUTTER;
        }
        return Status.HIT;
    }

    private Status getStatusFirstStrike(int secondDownPin) {
        if (secondDownPin == BowlingGame.PIN_NUMBER) {
            return Status.STRIKE;
        } else if (0 == secondDownPin) {
            return Status.GUTTER;
        }
        return Status.HIT;
    }

    private void assertSecondPitch(FinalFirstPitch firstPitch, int secondDownPin) {
        if (firstPitch.getStatus() == Status.STRIKE) {
            assertFirstStrike(secondDownPin);
            return;
        }

        int pinSum = firstPitch.getDownPin() + secondDownPin;
        if (pinSum < 0 || pinSum > BowlingGame.PIN_NUMBER) {
            throw new InvalidParameterException(INVALID_PIN_MSG);
        }
    }

    private void assertFirstStrike(int secondDownPin) {
        if (secondDownPin < 0 || secondDownPin > BowlingGame.PIN_NUMBER) {
            throw new InvalidParameterException(INVALID_PIN_MSG);
        }
    }

    public int getDownPin() {
        return downPin;
    }

    public Status getStatus() {
        return status;
    }

    public boolean isStrike() {
        return this.status == Status.STRIKE;
    }

    public boolean isSpare() {
        return this.status == Status.SPARE;
    }
}
