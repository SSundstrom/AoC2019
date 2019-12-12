package Utils;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class GeneralTest {

    @Disabled
    @RepeatedTest(20)
    void generateCombinations() {
        int m = (int) (Math.random() * 10) - 1;
        int n;
        do {
            n = (int) (Math.random() * 10);
        } while (n > m);

        int fact = 1;
        for (int i = 2; i < m - n; i++) {
            fact *= i;
        }

        assertEquals( fact, General.generateCombinations(n, m).size());
    }


}