package bowling.domain;

import java.security.InvalidParameterException;

public class FinalFirstPitch {

    private static final String INVALID_PIN_MSG = "쓰러뜨린 핀 개수가 적합하지 않습니다.";
    private final int downPin;
    private final Status status;

    public FinalFirstPitch(int downPin) {
        assertFirstPitch(downPin);
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

    private static void assertFirstPitch(int downPin) {
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

    public boolean isStrike() {
        return this.status == Status.STRIKE;
    }
}
