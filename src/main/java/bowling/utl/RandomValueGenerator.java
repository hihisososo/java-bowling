package bowling.utl;

import java.util.Random;

public class RandomValueGenerator {

  private static final Random random = new Random();

  public static int getRandom(int bound) {
    return random.nextInt(bound + 1);
  }

}
