package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.security.InvalidParameterException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserTest {
    @ParameterizedTest
    @DisplayName("사용자 이름을 받아 잘 생성되는지 확인")
    @ValueSource(strings = {"pip", "jav", "pjs", "str"})
    void make(String name) {
        User user = new User(name);
        assertThat(user.getName()).isEqualTo(name);
    }

    @ParameterizedTest
    @DisplayName("잘못된 사용자 이름일 경우 에러")
    @ValueSource(strings = {"", "hihisososo", "apple", "next"})
    void exception(String name) {
        assertThatThrownBy(() -> new User(name))
                .isInstanceOf(InvalidParameterException.class);
    }

}