public class standardDeck extends   Deck {

    standardDeck(){
        this.setMaxDeckSize(-1);
        this.setMaxDeckSize(0);
        this.set_Lands(0);

    }
    @Override
       /*Add a card to the deck. Needs to check if adding the card would violate the no more than 4 copies rule
    returns -1 on failure */
    public int addCard(String card) {
    return 0;

    }

    @Override
    //needs to check at least 60 cards
    public boolean checkComplete() {
        return false;
    }
}

