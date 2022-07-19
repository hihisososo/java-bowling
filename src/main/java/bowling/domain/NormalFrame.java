package bowling.domain;

import java.security.InvalidParameterException;
import java.util.Optional;

public class NormalFrame implements Frame {

    public static final String NO_MORE_PITCH = "더이상 피칭할 수 없습니다.";
    private NormalFirstPitch normalFirstPitch;
    private NormalSecondPitch normalSecondPitch;
    private int remainPin;

    public NormalFrame() {
        this.remainPin = BowlingGame.PIN_NUMBER;
    }

    @Override
    public void pitch(int downPin) {
        if (isFirstStrike() || isSecondExist()) {
            throw new InvalidParameterException(NO_MORE_PITCH);
        }
        remainPin -= downPin;
        if (isFirstNotExist()) {
            doFirstPitch(downPin);
            return;
        }
        doSecondPitch(downPin);
    }

    private void doSecondPitch(int downPin) {
        this.normalSecondPitch = new NormalSecondPitch(normalFirstPitch, downPin);
    }

    private void doFirstPitch(int downPin) {
        this.normalFirstPitch = new NormalFirstPitch(downPin);
    }

    private boolean isFirstNotExist() {
        return Optional.ofNullable(normalFirstPitch).isEmpty();
    }

    private boolean isSecondExist() {
        return Optional.ofNullable(normalSecondPitch).isPresent();
    }

    private boolean isFirstStrike() {
        return Optional.ofNullable(normalFirstPitch).isPresent() && normalFirstPitch.getStatus() == Status.STRIKE;
    }

    @Override
    public boolean canPitch() {
        return !(isFirstStrike() || isSecondExist());
    }

    @Override
    public int getRemainPin() {
        return remainPin;
    }

    public NormalFirstPitch getNormalFirstPitch() {
        return normalFirstPitch;
    }

    public NormalSecondPitch getNormalSecondPitch() {
        return normalSecondPitch;
    }
}
