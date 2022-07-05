package bowling.domain;

import java.security.InvalidParameterException;

public class FinalPitch implements Pitch{
    private static final String INVALID_PIN_MSG = "쓰러뜨린 핀 개수가 적합하지 않습니다.";
    private final int downPin;
    private final Status status;

    private FinalPitch(int downPin, Status status) {
        this.downPin = downPin;
        this.status = status;
    }

    public static FinalPitch first(int downPin) {
        assertFirstPitch(downPin);
        return new FinalPitch(downPin, getStatus(downPin));
    }

    public FinalPitch second(int secondDownPin) {
        if(this.status == Status.STRIKE){
            return makeSecondFirstStrike(secondDownPin);
        }
        return makeSecondFirstNotStrike(secondDownPin);
    }

    public static FinalPitch bonus(FinalPitch first, FinalPitch second, int bonusDownPin) {
        //첫, 둘째 피치의 합이 10을 넘지 않으면, 최소한 스페어 처리 되지 않은 것이므로 에러
        if(first.getDownPin() + second.getDownPin() < BowlingGame.PIN_NUMBER){
            throw new InvalidParameterException(INVALID_PIN_MSG);
        }

        if(first.getStatus() == Status.STRIKE){
            return makeBonusFirstStrike(second, bonusDownPin);
        }

        //첫째 피칭이 스트라이크가 아닌 상태에서는, 둘째가 스트라이크이거나 스페어여야만 한다.
        if(second.getStatus() == Status.STRIKE){
            return makeBonusSecondStrike(bonusDownPin);
        }else if(second.getStatus() == Status.SPARE){
            return makeSecondFirstNotStrike(bonusDownPin);
        }

        throw new InvalidParameterException(INVALID_PIN_MSG);

    }

    private static FinalPitch makeBonusFirstStrike(FinalPitch second, int bonusDownPin) {
        //첫, 둘째가 스트라이크면 보너스 피칭은 첫째 생성과 동일
        if(second.getStatus() == Status.STRIKE){
            return first(bonusDownPin);
        }else if(second.getStatus() == Status.HIT){
            return new FinalPitch()
        }
    }

    private FinalPitch makeSecondFirstNotStrike(int secondDownPin) {
        assertSecondFirstNotStrike(secondDownPin);
        int pinSum = this.downPin + secondDownPin;
        if (pinSum == 0) {
            return new FinalPitch(secondDownPin, Status.MISS);
        } else if (pinSum == BowlingGame.PIN_NUMBER) {
            return new FinalPitch(secondDownPin, Status.SPARE);
        }
        return new FinalPitch(secondDownPin, Status.HIT);
    }

    private FinalPitch makeSecondFirstStrike(int secondDownPin) {
        //첫 피치가 스트라이크였다면, 두번째 피치는 처음 던진것과 동일
        return first(secondDownPin);
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

    private void assertSecondFirstNotStrike(int secondDownPin) {
        int pinSum = this.downPin + secondDownPin;
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
