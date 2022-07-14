package bowling.domain;

public interface Frame {

  void pitch(int downPin);
  boolean canPitch();
  int getRemainPin();
}
