package bowling.domain.player;


import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

import java.util.List;
import java.util.Objects;

public class Player {
    private PlayerName playerName;
    private Frames frames;

    public Player(String name) {
        this.playerName = new PlayerName(name);
        this.frames = new Frames();
    }

    public String getPlayerName() {
        return this.playerName.getValue();
    }

    public List<Frame> getFrames() {
        return this.frames.getFrames();
    }

    public boolean isFinish() {
        return this.frames.isFinish();
    }

    public void next(int number) {
        this.frames.next(number);
    }

    public int nextFrameNo() {
        return this.frames.nextFrameNo();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(playerName, player.playerName) &&
                Objects.equals(frames, player.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName, frames);
    }
}