package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.security.InvalidParameterException;
import org.junit.jupiter.api.Test;

public class NormalPitchesTest {

  @Test
  void 피치들_생성() {
    NormalPitches normalPitches = new NormalPitches();
    assertThat(normalPitches.size()).isEqualTo(0);
  }

  @Test
  void 첫피치_추가() {
    NormalPitches normalPitches = new NormalPitches();
    normalPitches.pitch(10);
    assertThat(normalPitches.size()).isEqualTo(1);
  }

  @Test
  void 두번째_피치_추가() {
    NormalPitches normalPitches = new NormalPitches();
    normalPitches.pitch(5);
    normalPitches.pitch(3);
    assertThat(normalPitches.size()).isEqualTo(2);
  }

  @Test
  void 두개이상_추가_에러() {
    NormalPitches normalPitches = new NormalPitches();
    assertThatThrownBy(() -> {
      normalPitches.pitch(1);
      normalPitches.pitch(2);
      normalPitches.pitch(3);
    }).isInstanceOf(InvalidParameterException.class);
  }

  @Test
  void 피칭_가능_여부() {
    NormalPitches canPitch = new NormalPitches();
    NormalPitches canNotPitch = new NormalPitches();

    canNotPitch.pitch(2);
    canNotPitch.pitch(3);

    assertThat(canPitch.canPitch()).isEqualTo(true);
    assertThat(canNotPitch.canPitch()).isEqualTo(false);
  }

}
