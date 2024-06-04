package Test;

import Test.Inserari.*;

public class ProductionTest {
    public static void main(String[] args) {
        TestInserareFeedbackuri t = new TestInserareFeedbackuri();

        try {
            t.insertFeedbackuri(5);
        } finally {
            t.closeEm();
        }
    }
}
