package bowling.exception;

public class PitchNotRecordException extends RuntimeException {

  public static final String PITCH_NOT_PROCESSED = "피치가 아직 수행되지 않았습니다.";

  public PitchNotRecordException() {
    super(PITCH_NOT_PROCESSED);
  }
}
