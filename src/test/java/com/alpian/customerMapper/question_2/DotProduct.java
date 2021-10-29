package com.alpian.customerMapper.question_2;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

public class DotProduct {

  public Double calculateDotProduct(double[] a, double[] b) {

    BiFunction<double[], double[], Double> calculateDotFunction = (doubles, doubles2) -> {
      if (doubles.length != doubles2.length) {
        throw new IllegalArgumentException("Input arrays have to have same size");
      }
      List<Double> aDoubles = stream(doubles).boxed().collect(toList());
      List<Double> bDoubles = stream(doubles2).boxed().collect(toList());
      return IntStream.range(0, doubles.length)
          .mapToDouble(index -> aDoubles.get(index) * bDoubles.get(index))
          .sum();
    };
    return calculateDotFunction.apply(a, b);
  }

  @Test
  public void calculateDotProductTest() {

    double[] dotList1 = {1, 3, -5};
    double[] dotList2 = {4, -2, -1};

    DotProduct dotProduct = new DotProduct();
    Double result = dotProduct.calculateDotProduct(dotList1, dotList2);

    assertEquals(result, 3.0);
  }
}
