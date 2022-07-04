package bowling.domain;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class NormalPitches {

  private static final String NO_MORE_PITCH = "더이상 피치를 할 수 없습니다.";
  private final List<NormalPitch> values;

  public NormalPitches() {
    this.values = new ArrayList<>();
  }


  public int size() {
    return values.size();
  }

  public void pitch(int downPin) {
    if(values.size() == 2){
      throw new InvalidParameterException(NO_MORE_PITCH);
    }
    if(values.size() == 0){
      values.add(NormalPitch.first(downPin));
      return;
    }
    values.add(values.get(0).second(downPin));
  }

  public boolean canPitch() {
    if(isStrike() || values.size() == 2){
      return false;
    }
    return true;
  }

  private boolean isStrike() {
    return values.size() == 1 && values.get(0).getStatus() == Status.STRIKE;
  }
}
