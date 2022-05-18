package bowling.model.frame;

import bowling.model.Pins;
import bowling.model.frame.state.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("프레임들")
class FramesTest {

    @Test
    @DisplayName("초기 상태로 생성")
    void instance() {
        assertThatNoException().isThrownBy(Frames::init);
    }

    @Test
    @DisplayName("프레임들로 생성")
    void instance_frames() {
        assertThatNoException().isThrownBy(() -> Frames.from(Collections.singletonList(NormalFrame.init(FrameNumber.FIRST))));
    }

    @Test
    @DisplayName("프레임들은 필수")
    void instance_nullArgument_thrownIllegalArgumentException() {
        assertThatIllegalArgumentException().isThrownBy(() -> Frames.from(null));
    }

    @ParameterizedTest(name = "[{index}] {0} 만큼 추가되면 {1}")
    @MethodSource
    @DisplayName("넘어진 핀이 추가된 프레임들")
    void bowling(List<Pins> pinsGroup, Frames expected) {
        //when
        Frames thrownFrames = pinsGroup.stream()
                .reduce(Frames.init(), Frames::bowling, (frames1, frames2) -> frames2);
        //then
        assertThat(thrownFrames).isEqualTo(expected);
    }

    @Test
    @DisplayName("종료된 프레임들에 핀 추가 불가")
    void bowling_endedFrames_thrownIllegalStateArgument() {
        //given
        List<Pins> pinsGroup = IntStream.range(0, 20)
                .mapToObj(i -> Pins.ZERO)
                .collect(Collectors.toList());
        Frames endedFrames = pinsGroup.stream()
                .reduce(Frames.init(), Frames::bowling, (frames1, frames2) -> frames2);
        //when, then
        assertThatIllegalStateException().isThrownBy(() -> endedFrames.bowling(Pins.MAX));
    }

    @ParameterizedTest(name = "[{index}] {0} 만큼 추가되면 종료 여부는 {1}")
    @MethodSource
    @DisplayName("프레임들 종료 여부")
    void isFinished(List<Pins> pinsGroup, boolean expected) {
        //when
        Frames thrownFrames = pinsGroup.stream()
                .reduce(Frames.init(), Frames::bowling, (frames1, frames2) -> frames2);
        //then
        assertThat(thrownFrames.isFinished()).isEqualTo(expected);
    }

    @Test
    @DisplayName("주어진 리스트 그대로 반환")
    void list() {
        //given
        List<Frame> firstFrames = Collections.singletonList(NormalFrame.init(FrameNumber.FIRST));
        //when, then
        assertThat(Frames.from(firstFrames).list()).isEqualTo(firstFrames);
    }

    @ParameterizedTest(name = "[{index}] {0} 만큼 핀이 추가되면 누적된 스코어는 {1}")
    @DisplayName("누적된 스코어들")
    @MethodSource
    void accumulatedScores(List<Pins> pinsGroup, List<Integer> expected) {
        Frames frames = pinsGroup.stream()
                .reduce(Frames.init(), Frames::bowling, (frames1, frames2) -> frames2);
        //when, then
        assertThat(frames.accumulatedScores()).containsExactlyElementsOf(expected);
    }

    private static Stream<Arguments> bowling() {
        return Stream.of(
                Arguments.of(
                        Collections.singletonList(Pins.MAX),
                        Frames.from(Collections.singletonList(NormalFrame.of(FrameNumber.FIRST, FrameState.from(Strike.INSTANCE))))
                ),
                Arguments.of(
                        Arrays.asList(Pins.MAX, Pins.MAX),
                        Frames.from(Arrays.asList(
                                NormalFrame.of(FrameNumber.FIRST, FrameState.of(Strike.INSTANCE, Collections.singletonList(Strike.INSTANCE))),
                                NormalFrame.of(FrameNumber.from(2), FrameState.from(Strike.INSTANCE))
                        ))
                )
        );
    }

    private static Stream<Arguments> isFinished() {
        return Stream.of(
                Arguments.of(Collections.emptyList(), false),
                Arguments.of(Collections.singletonList(Pins.MAX), false),
                Arguments.of(IntStream.range(0, 20).mapToObj(i -> Pins.ZERO).collect(Collectors.toList()), true)
        );
    }

    private static Stream<Arguments> accumulatedScores() {
        return Stream.of(
                Arguments.of(Collections.singletonList(Pins.MAX), Collections.emptyList()),
                Arguments.of(Arrays.asList(Pins.MAX, Pins.MAX), Collections.emptyList()),
                Arguments.of(Arrays.asList(Pins.MAX, Pins.MAX, Pins.MAX), Collections.singletonList(30)),
                Arguments.of(Arrays.asList(Pins.MAX, Pins.ZERO, Pins.ZERO), Arrays.asList(10, 10)),
                Arguments.of(Arrays.asList(Pins.ZERO, Pins.ZERO, Pins.MAX, Pins.MAX, Pins.ZERO, Pins.ZERO), Arrays.asList(0, 20, 30, 30))
        );
    }
}