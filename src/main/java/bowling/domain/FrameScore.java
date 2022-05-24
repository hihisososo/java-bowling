package bowling.domain;

public enum FrameScore {
  STRIKE, SPARE, MISS, GUTTER;

  private int first;
  private int second;

  public FrameScore strike() {
    this.first = 10;
    this.second = 0;
    return STRIKE;
  }


}
