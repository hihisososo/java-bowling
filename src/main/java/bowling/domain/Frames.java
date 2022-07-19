package bowling.domain;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import static bowling.utl.RandomValueGenerator.getRandom;

public class Frames {
    public static final int FRAME_NUMBER = 10;
    private static final String NO_PITCH_FRAME = "피칭 가능한 프레임이 없습니다.";
    private final List<Frame> values;

    public Frames() {
        this.values = new ArrayList<>();
        for (int i = 0; i < FRAME_NUMBER - 1; i++) {
            this.values.add(new NormalFrame());
        }
        this.values.add(new FinalFrame());
    }

    public boolean catPitch() {
        return this.values.get(this.values.size() - 1).canPitch();
    }

    public void pitch() {
        Frame pitchFrame = getPitchFrame();
        int downPin = getRandom(pitchFrame.getRemainPin());
        pitchFrame.pitch(downPin);
    }

    private Frame getPitchFrame() {
        return this.values.stream()
                .filter(frame -> frame.canPitch())
                .findFirst().orElseThrow(() -> new InvalidParameterException(NO_PITCH_FRAME));
    }

    public List<Frame> getValues() {
        return values;
    }
}
