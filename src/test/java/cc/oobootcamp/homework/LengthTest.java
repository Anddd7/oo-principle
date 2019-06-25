package cc.oobootcamp.homework;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class LengthTest {

  @Test(expected = IndexOutOfBoundsException.class)
  public void should_throw__when_create_length_of_negative_number() {
    new Length(-1);
  }

  @ParameterizedTest(name = "{index} should return {0} is equals with {0} when compare given length of {0} with length of {0}")
  @ValueSource(ints = {1, 2, 3})
  public void isEquals_should_return_true(int length) {
    assertThat(new Length(length).isEquals(new Length(length))).isTrue();
  }

  @ParameterizedTest(name = "{index} should return {0} is not equals with 5 when compare given length of {0} with length of 5")
  @ValueSource(ints = {1, 2, 3})
  public void isEquals_should_return_false(int length) {
    assertThat(new Length(length).isEquals(new Length(5))).isFalse();
  }

  private static Stream<Arguments> provideForBiggerAndSmallerNumbers() {
    return Stream.of(
        Arguments.arguments(2, 1),
        Arguments.arguments(4, 3),
        Arguments.arguments(6, 5)
    );
  }

  @ParameterizedTest(name = "{index} should return {0} is longer than {1} when compare given length of {0} with length of {1}")
  @MethodSource("provideForBiggerAndSmallerNumbers")
  public void isLongerThan_should_return_true(int lengthOfBigger, int lengthOfSmaller) {
    assertThat(new Length(lengthOfBigger).isLongerThan(new Length(lengthOfSmaller))).isTrue();
  }

  @ParameterizedTest(name = "{index} should return {1} is not longer than {0} when compare given length of {1} with length of {0}")
  @MethodSource("provideForBiggerAndSmallerNumbers")
  public void isLongerThan_should_return_false(int lengthOfBigger, int lengthOfSmaller) {
    assertThat(new Length(lengthOfSmaller).isLongerThan(new Length(lengthOfBigger))).isFalse();
  }

  @ParameterizedTest(name = "{index} should return {1} is shorter than {0} when compare given length of {1} with length of {0}")
  @MethodSource("provideForBiggerAndSmallerNumbers")
  public void isShorterThan_should_return_true(int lengthOfBigger, int lengthOfSmaller) {
    assertThat(new Length(lengthOfSmaller).isShorterThan(new Length(lengthOfBigger))).isTrue();
  }

  @ParameterizedTest(name = "{index} should return {0} is not shorter than {1} when compare given length of {0} with length of {1}")
  @MethodSource("provideForBiggerAndSmallerNumbers")
  public void isShorterThan_should_return_false(int lengthOfBigger, int lengthOfSmaller) {
    assertThat(new Length(lengthOfBigger).isShorterThan(new Length(lengthOfSmaller))).isFalse();
  }
}
