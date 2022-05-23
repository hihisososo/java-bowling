package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {
    public static final Answers ANSWERS = new Answers(List.of(AnswerTest.A1, AnswerTest.A2));

    @Test
    @DisplayName("삭제 처리가 정상적으로 되는지 확인")
    void delete() {
        Answer answer1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answer answer2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
        Answers answers = new Answers(List.of(answer1, answer2));
        List<DeleteHistory> actualHistory = answers.delete();
        List<DeleteHistory> expectHistory = List.of(AnswerTest.A1_DELETE_HISOTRY, AnswerTest.A2_DELETE_HISOTRY);

        assertThat(answer1.isDeleted()).isTrue();
        assertThat(answer2.isDeleted()).isTrue();
        assertThat(actualHistory).isEqualTo(expectHistory);
    }

    @Test
    @DisplayName("답변 추가가 정상적으로 되는지 확인")
    void add() {
        Answers actual = new Answers(new ArrayList<>());
        actual.add(AnswerTest.A1);
        actual.add(AnswerTest.A2);

        assertThat(actual).isEqualTo(ANSWERS);
    }

    @Test
    @DisplayName("모든 답변이 하나의 유저에 의해 생성됐는지 확인")
    void isAllOwner() {
        Answers allOwner = new Answers(new ArrayList<>());
        allOwner.add(AnswerTest.A1);

        assertThat(allOwner.isAllOwner(AnswerTest.A1.getWriter())).isTrue();
        assertThat(ANSWERS.isAllOwner(AnswerTest.A1.getWriter())).isFalse();
    }
}