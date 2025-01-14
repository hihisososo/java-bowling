package qna.domain;

import qna.CannotDeleteException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Question extends AbstractEntity {

    public static final String NOT_DELETABLE_USER = "질문을 삭제할 권한이 없습니다.";
    public static final String ANOTHER_USER_ANSWER_EXIST = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";
    @Column(length = 100, nullable = false)
    private String title;

    @Lob
    private String contents;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;

    @Embedded
    private Answers answers;
    private boolean deleted = false;

    public Question() {
    }

    public Question(String title, String contents) {
        this.title = title;
        this.contents = contents;
        this.answers = new Answers();
    }

    public Question(long id, String title, String contents) {
        super(id);
        this.title = title;
        this.contents = contents;
        this.answers = new Answers();
    }

    public String getTitle() {
        return title;
    }

    public Question setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContents() {
        return contents;
    }

    public Question setContents(String contents) {
        this.contents = contents;
        return this;
    }

    public User getWriter() {
        return writer;
    }

    public Question writeBy(User loginUser) {
        this.writer = loginUser;
        return this;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public boolean isOwner(User loginUser) {
        return writer.equals(loginUser);
    }

    public Question setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Answers getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

    public void validateDeletable(User loginUser) throws CannotDeleteException {
        if (!loginUser.equals(writer)) {
            throw new CannotDeleteException(NOT_DELETABLE_USER);
        }

        if (!answers.isAllOwner(writer)) {
            throw new CannotDeleteException(ANOTHER_USER_ANSWER_EXIST);
        }
    }

    public List<DeleteHistory> delete() {
        deleted = true;
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, getId(), writer, LocalDateTime.now()));
        deleteHistories.addAll(answers.delete());
        return deleteHistories;
    }
}
