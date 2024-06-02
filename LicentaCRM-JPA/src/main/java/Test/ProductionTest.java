package Test;

import Test.Inserari.*;
import Test.Inserari.*;

public class ProductionTest {
    public static void main(String[] args) {
        TestInserareSegmente t = new TestInserareSegmente();

        try {
            t.insertSegmente(10);
        } finally {
            t.closeEm();
        }
    }
}
