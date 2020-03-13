package com.iyasu.addressbookfinal.database;

public class Pair<A, B> {

  /** Creates a new pair containing the given elements in order. */
  public static <A, B> Pair<A, B> of(A first, B second) {
    return new Pair<>(first, second);
  }

  /** The first element of the pair. */
  public final A first;

  /** The second element of the pair. */
  public final B second;

  private Pair(A first, B second) {
    this.first = first;
    this.second = second;
  }

  /** Returns the first element of this pair. */
  public A getFirst() {
    return first;
  }

  /** Returns the second element of this pair. */
  public B getSecond() {
    return second;
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof Pair) {
      Pair<?, ?> that = (Pair<?, ?>) object;
      return equal(this.getFirst(), that.getFirst())
          && equal(this.getSecond(), that.getSecond());
    }
    return false;
  }
  
  private boolean equal(Object left, Object right) {
    if (left == null) {
      return right == null;
    }
    return left.equals(right);
  }

  @Override
  public int hashCode() {
    int hash1 = first == null ? 0 : first.hashCode();
    int hash2 = second == null ? 0 : second.hashCode();
    return 31 * hash1 + hash2;
  }

  @Override
  public String toString() {
    return String.format("(%s, %s)", first, second);
  }
}
