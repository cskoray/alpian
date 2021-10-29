package com.alpian.customerMapper.question_1;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class CollatzTailRecursive {

  private Set<Integer> collatz;

  public void collatzTailRecursive(int n) {

    System.out.print(n + " ");
    collatz.add(n);
    if (n <= 1) {
      return;
    } else if (n % 2 == 0) {
      collatzTailRecursive(n / 2);
    } else {
      collatzTailRecursive(3 * n + 1);
    }
  }

  @Test
  public void collatzTailRecursiveTest() {
    collatz = new HashSet<>();

    collatzTailRecursive(12);

    assertEquals(collatz, new HashSet<>(Arrays.asList(12, 6, 3, 10, 5, 16, 8, 4, 2, 1)));
  }
}
