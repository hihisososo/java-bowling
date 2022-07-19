package bowling.domain;

public class BowlingGame {

    public static final int PIN_NUMBER = 10;
    private final User user;
    private final Frames frames;

    public BowlingGame(User user) {
        this.user = user;
        this.frames = new Frames();

    }

    public boolean isEnd() {
        return frames.catPitch();
    }

    public void play() {
        frames.pitch();
    }

    public User getUser() {
        return user;
    }
}
