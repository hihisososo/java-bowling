package bowling.domain;

import java.security.InvalidParameterException;
import java.util.Optional;

public class FinalFrame implements Frame {

    public static final String NO_MORE_PITCH = "더이상 피칭할 수 없습니다.";
    private FinalFirstPitch finalFirstPitch;
    private FinalSecondPitch finalSecondPitch;
    private FinalBonusPitch finalBonusPitch;
    private int remainPin;

    public FinalFrame() {
        this.remainPin = BowlingGame.PIN_NUMBER;
    }

    @Override
    public void pitch(int downPin) {
        if (isBonusExist()) {
            throw new InvalidParameterException(NO_MORE_PITCH);
        } else if (isSecondExist() && canNotBonus()) {
            throw new InvalidParameterException(NO_MORE_PITCH);
        }

        if (isFirstNotExist()) {
            doFirstPitch(downPin);
            return;
        } else if (isSecondNotExist()) {
            doSecondPitch(downPin);
            return;
        }
        doBonusPitch(downPin);

    }

    @Override
    public boolean canPitch() {
        if (isFirstNotExist() || isSecondNotExist()) {
            return true;
        }
        return isBonusNotExist() && canBonus();
    }

    @Override
    public int getRemainPin() {
        return remainPin;
    }

    private void doBonusPitch(int downPin) {
        this.finalBonusPitch = new FinalBonusPitch(finalFirstPitch, finalSecondPitch, downPin);
    }

    private void doSecondPitch(int downPin) {
        this.finalSecondPitch = new FinalSecondPitch(finalFirstPitch, downPin);
        if (finalSecondPitch.isStrike() || finalSecondPitch.isSpare()) {
            remainPin = BowlingGame.PIN_NUMBER;
            return;
        }
        remainPin -= downPin;
    }

    private boolean isSecondNotExist() {
        return Optional.ofNullable(finalSecondPitch).isEmpty();
    }

    private void doFirstPitch(int downPin) {
        this.finalFirstPitch = new FinalFirstPitch(downPin);
        if (finalFirstPitch.isStrike()) {
            remainPin = BowlingGame.PIN_NUMBER;
            return;
        }
        remainPin -= downPin;
    }

    private boolean isFirstNotExist() {
        return Optional.ofNullable(finalFirstPitch).isEmpty();
    }

    private boolean canNotBonus() {
        return finalFirstPitch.getDownPin() + finalSecondPitch.getDownPin() < BowlingGame.PIN_NUMBER;
    }

    private boolean isBonusExist() {
        return Optional.ofNullable(finalBonusPitch).isPresent();
    }

    private boolean isSecondExist() {
        return Optional.ofNullable(finalSecondPitch).isPresent();
    }

    private boolean canBonus() {
        return finalFirstPitch.getDownPin() + finalSecondPitch.getDownPin() >= BowlingGame.PIN_NUMBER;
    }

    private boolean isBonusNotExist() {
        return Optional.ofNullable(finalBonusPitch).isEmpty();
    }

}
