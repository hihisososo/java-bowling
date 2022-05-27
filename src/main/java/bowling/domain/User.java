package bowling.domain;

import java.security.InvalidParameterException;

public class User {

  private static final int USER_NAME_LENGTH = 3;
  public static final String INVALID_NAME_LEN_MSG = "사용자 이름의 길이는 " + USER_NAME_LENGTH + " 이어야 합니다.";
  private final String name;


  public User(String name) {
    assertUser(name);
    this.name = name;
  }

  public String getName() {
    return name;
  }

  private void assertUser(String name) {
    if (name.length() != USER_NAME_LENGTH) {
      throw new InvalidParameterException(INVALID_NAME_LEN_MSG);
    }
  }
}
