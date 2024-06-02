package Test;

import Test.Inserari.TestInserareAdrese;
import Test.Inserari.TestInserareClienti;
import Test.Inserari.TestInserareTagProduse;
import Test.Inserari.TestInserareProduse;

public class TestInsertii {

    public static void main(String[] args) {
        TestInserareTagProduse testInserareTagProduse = new TestInserareTagProduse();
        TestInserareClienti testInserareClienti = new TestInserareClienti();
        TestInserareAdrese testInserareAdrese = new TestInserareAdrese();
        TestInserareProduse testInserareProduse = new TestInserareProduse();

        try {
            testInserareTagProduse.insertTags(10);
            testInserareClienti.insertClients(10);
            testInserareAdrese.insertAdreseForClients();
            testInserareProduse.insertProduse(10);
        } finally {
            testInserareClienti.closeEm();
            testInserareTagProduse.closeEm();
            testInserareAdrese.closeEm();
            testInserareProduse.closeEm();
        }
    }
}