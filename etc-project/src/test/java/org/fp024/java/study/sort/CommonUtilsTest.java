package org.fp024.java.study.sort;

import org.junit.jupiter.api.Test;
import static org.fp024.java.study.sort.CommonUtils.*;

import static org.junit.jupiter.api.Assertions.*;

class CommonUtilsTest {

  @Test
  void testGetMedianIndex() {
    //             0  1  2  3  4
    int[] array = {1, 2, 3, 5, 4};
    assertEquals(4, getMedianIndex(array, 2, 4));

    //                 0  1  2  3  4
    array = new int[] {1, 2, 3, 5, 4};
    assertEquals(2, getMedianIndex(array, 2, 3));

    //                 0  1  2  3  4
    array = new int[] {1, 2, 9, 5, 4};
    assertEquals(4, getMedianIndex(array, 0, 4));
  }
}
