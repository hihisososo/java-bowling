package bowling.domain;

import java.security.InvalidParameterException;

public class NormalSecondPitch {

    private static final String INVALID_PIN_MSG = "쓰러뜨린 핀 개수가 적합하지 않습니다.";
    private final int downPin;
    private final Status status;

    public NormalSecondPitch(NormalFirstPitch firstPitch, int downPin) {
        assertNormalSecondPitch(firstPitch, downPin);
        this.downPin = downPin;
        this.status = getStatus(firstPitch.getDownPin(), downPin);
    }

    private static Status getStatus(int firstDownPin, int secondDownPin) {
        int downPinSum = firstDownPin + secondDownPin;
        if (downPinSum == BowlingGame.PIN_NUMBER) {
            return Status.SPARE;
        } else if (0 == downPinSum) {
            return Status.MISS;
        } else if(0 == secondDownPin){
            return Status.GUTTER;
        }
        return Status.HIT;
    }

    private void assertNormalSecondPitch(NormalFirstPitch firstPitch, int secondDownPin) {
        int pinSum = firstPitch.getDownPin() + secondDownPin;
        if (pinSum < 0 || pinSum > BowlingGame.PIN_NUMBER) {
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
