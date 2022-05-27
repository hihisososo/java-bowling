package bowling.domain;

import static bowling.utl.RandomValueGenerator.getRandom;

public class FinalFrame implements Frame {

  private final Score score;
  private final BonusScore bonusScore;

  public FinalFrame() {
    this.score = Score.init();
  }

  @Override
  public void play() {
    if (score.getStatus() == Stauts.EMPTY) {
      recordFirstPitch();
    } else if (score.getStatus() == Stauts.NEED_SECOND_PITCH) {
      recordSecondPitch();
    }
  }

  @Override
  public boolean isPlayable() {
    return score.isPlayable();
  }

  @Override
  public Score getScore() {
    return score;
  }

  private void recordSecondPitch() {
    score.record(new SecondPitch(getRandom(BowlingGame.PIN_NUMBER - score.getFirstKnockDownPin())));
  }

  private void recordFirstPitch() {
    score.record(new FirstPitch(getRandom(BowlingGame.PIN_NUMBER)));
  }
}
