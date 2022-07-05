package bowling.domain;

import java.security.InvalidParameterException;

public class BonusPitch implements Pitch{
    private static final String INVALID_PIN_MSG = "쓰러뜨린 핀 개수가 적합하지 않습니다.";
    private final int downPin;
    private final Status status;

    public BonusPitch(int downPin, Status status) {
        this.downPin = downPin;
        this.status = status;
    }


    public static BonusPitch of(FinalPitch first, FinalPitch second, int bonusDownPin){
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

    private static BonusPitch makeBonusFirstStrike(FinalPitch second, int bonusDownPin) {
        if(second.getStatus() == Status.STRIKE){
            return new BonusPitch(bonusDownPin, )
        }
    }


    public int getDownPin() {
        return downPin;
    }

    public Status getStatus() {
        return status;
    }
}
