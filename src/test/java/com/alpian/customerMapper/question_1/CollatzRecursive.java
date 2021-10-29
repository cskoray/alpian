package com.alpian.customerMapper.question_1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class CollatzRecursive {

  private Set<Integer> collatz;

  public void collatzRecursive(int n) {

    collatz.add(n);

    if (n == 1) {
      System.out.print(n + " ");
      return;
    } else if (n % 2 == 0) {
      collatzRecursive(n / 2);
    } else {
      collatzRecursive(3 * n + 1);
    }
    System.out.print(n + " ");
  }

  @Test
  public void collatzRecursiveTest() {
    collatz = new HashSet<>();

    collatzRecursive(12);

    assertEquals(collatz, new HashSet<>(Arrays.asList(12, 6, 3, 10, 5, 16, 8, 4, 2, 1)));
  }
}
