package bowling.domain;

import static bowling.utl.RandomValueGenerator.getRandom;

public class NormalFrame implements Frame {

  private final NormalPitches normalPitches;

  private NormalFrame() {
    this.normalPitches = new NormalPitches();
  }

  public static NormalFrame first() {
    return new NormalFrame();
  }

  public NormalFrame next() {
    return new NormalFrame();
  }

  @Override
  public void pitch() {
    normalPitches.pitch(getRandom(normalPitches.remainPin()));
  }

  @Override
  public boolean canPitch() {
    return normalPitches.canPitch();
  }

  public int getPitchSize() {
    return normalPitches.size();
  }
}
