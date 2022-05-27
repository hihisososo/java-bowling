package bowling.domain;

public enum ScoreStauts {
  EMPTY, NEED_SECOND_PITCH, STRIKE, SPARE, MISS, GUTTER;

  public static final String WRONG_PITCH_SUM =
      "첫, 두번째 피칭을 합해서 " + Frame.PIN_NUMBER + " 를 초과할 수 없습니다.";

  public ScoreStauts nextStatus(FirstPitch firstPitch) {
    if (firstPitch.isStrike()) {
      return STRIKE;
    }
    return NEED_SECOND_PITCH;
  }

  public ScoreStauts nextStatus(FirstPitch firstPitch, SecondPitch secondPitch) {
    int knockDownAll = firstPitch.getKnockDownPin() + secondPitch.getKnockDownPin();
    assertPitch(knockDownAll);
    if (knockDownAll == Frame.PIN_NUMBER) {
      return SPARE;
    } else if (knockDownAll == 0) {
      return GUTTER;
    }
    return MISS;
  }

  private void assertPitch(int knockDownAll) {
    if (knockDownAll > Frame.PIN_NUMBER) {
      throw new IllegalArgumentException(WRONG_PITCH_SUM);
    }
  }
}
