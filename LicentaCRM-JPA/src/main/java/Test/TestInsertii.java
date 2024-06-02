package Test;

import Test.Inserari.*;

/*
ToDO    TestInserareLiniiTagProduse
        TestInserareSegmente
        TestInserareLiniiTagClienti
        TestInserareWishList
        TestInserareComunicate
        TestInserareCampanii
        TestInserareAchizitii *
        TestInserareLiniiAchiztii *
        TestInserareIstoricPuncte *
        TestInserareFeedback + repoFeedback onlyIfBought
 */

public class TestInsertii {

    public static void main(String[] args) {
        TestInserareTagProduse testInserareTagProduse = new TestInserareTagProduse();
        TestInserareClienti testInserareClienti = new TestInserareClienti();
        TestInserareAdrese testInserareAdrese = new TestInserareAdrese();
        TestInserareProduse testInserareProduse = new TestInserareProduse();
        TestInserareOferte testInserareOferte = new TestInserareOferte();
        TestInserareLiniiOferte testInserareLiniiOferte = new TestInserareLiniiOferte();

        try {
            testInserareTagProduse.insertTags(10);
            testInserareClienti.insertClients(10);
            testInserareAdrese.insertAdreseForClients();
            testInserareProduse.insertProduse(10);
            testInserareOferte.insertOferte(5);
            testInserareLiniiOferte.insertLiniiOferte();
        } finally {
            testInserareClienti.closeEm();
            testInserareTagProduse.closeEm();
            testInserareAdrese.closeEm();
            testInserareProduse.closeEm();
            testInserareOferte.closeEm();
            testInserareLiniiOferte.closeEm();
        }
    }
}
