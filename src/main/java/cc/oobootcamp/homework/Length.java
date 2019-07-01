package cc.oobootcamp.homework;

public class Length {

  private int value;

  public Length(int value) {
    if(value<0) throw new IndexOutOfBoundsException("Length can't be negative number");
    this.value = value;
  }

  public boolean isEquals(Length target) {
    return value == target.value;
  }

  public boolean isLongerThan(Length target) {
    return value > target.value;

  }

  public boolean isShorterThan(Length target) {
    return value < target.value;
  }
}
