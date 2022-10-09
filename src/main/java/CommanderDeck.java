public class CommanderDeck extends Deck {
    String commander;
    CommanderDeck(){
        setMaxDeckSize(100);
    }
    @Override
    /*Add a card to the deck. Needs to check if there is room and if there is a copy that exist in the deck already
    returns -1 on failure */
    public int addCard(String card) {
return 0;
    }

    @Override
    // Needs 99 cards and 1 Commander
    public boolean checkComplete() {
        return false;
    }
}
