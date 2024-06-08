package Test;

import Test.Inserari.*;

public class TestInsertii {

    public static void main(String[] args) {
        TestInserareTagProduse testInserareTagProduse = new TestInserareTagProduse();
        TestInserareClienti testInserareClienti = new TestInserareClienti();
        TestInserareAdrese testInserareAdrese = new TestInserareAdrese();
        TestInserareProduse testInserareProduse = new TestInserareProduse();
        TestInserareOferte testInserareOferte = new TestInserareOferte();
        TestInserareLiniiTagProduse testInserareLiniiTagProduse = new TestInserareLiniiTagProduse();
        TestInserareSegmente testInserareSegmente = new TestInserareSegmente();
        TestInserareLiniiTagClienti testInserareLiniiTagClienti = new TestInserareLiniiTagClienti();
        TestInserareWishList testInserareWishList = new TestInserareWishList();
        TestInserareComunicate testInserareComunicate = new TestInserareComunicate();
        TestInserareCampanii testInserareCampanii = new TestInserareCampanii();
        TestInserareAchizitii testInserareAchizitii = new TestInserareAchizitii();
        TestInserareFeedbackuri testInserareFeedbackuri = new TestInserareFeedbackuri();

        try {
            testInserareTagProduse.insertTags(10);
            testInserareClienti.insertClients(10);
            testInserareAdrese.insertAdreseForClients();
            testInserareProduse.insertProduse(10);
            testInserareOferte.insertOferte(5);
            testInserareLiniiTagProduse.insertLiniiTagProduse();
            testInserareSegmente.insertSegmente(10);
            testInserareLiniiTagClienti.insertLiniiTagClienti();
            testInserareWishList.insertWishList();
            testInserareComunicate.insertComunicate();
            testInserareCampanii.insertCampanii();
            testInserareAchizitii.insertAchizitii();
            testInserareFeedbackuri.insertFeedbackuri(10);
        } finally {
            testInserareClienti.closeEm();
            testInserareTagProduse.closeEm();
            testInserareAdrese.closeEm();
            testInserareProduse.closeEm();
            testInserareOferte.closeEm();
            testInserareLiniiTagProduse.closeEm();
            testInserareSegmente.closeEm();
            testInserareLiniiTagClienti.closeEm();
            testInserareWishList.closeEm();
            testInserareComunicate.closeEm();
            testInserareCampanii.closeEm();
            testInserareFeedbackuri.closeEm();
        }
    }
}
