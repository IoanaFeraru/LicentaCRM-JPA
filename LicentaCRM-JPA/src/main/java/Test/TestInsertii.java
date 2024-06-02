package Test;

import Test.Inserari.TestInserareClienti;
import Test.Inserari.TestInserareTagProduse;

public class TestInsertii {

    public static void main(String[] args) {
        TestInserareTagProduse testInserareTagProduse = new TestInserareTagProduse();
        TestInserareClienti testInserareClienti = new TestInserareClienti();
        try {
            testInserareTagProduse.insertTags(10);
            testInserareClienti.insertClients(10);
        } finally {
            testInserareClienti.closeEm();
            testInserareTagProduse.closeEm();
        }
    }
}
