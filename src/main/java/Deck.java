import javax.xml.stream.FactoryConfigurationError;
import java.util.ArrayList;

public abstract class Deck implements java.io.Serializable{
    //current number of cards in the deck
    private int deckSize;
    //Maximum number of cards in a deck
    private int  maxDeckSize;
    //Number of lands in the deck
    int land_count;
    //dictionary representing cards currently in the deck Need to find better data structure before actual implementation
    ArrayList cards;
    // Says if the deck is complete
    private  boolean isComplete;

    protected Deck() {
        ArrayList cards= new ArrayList<>();
        isComplete = false;
        this.maxDeckSize = -1;
        this.deckSize=0;
    }

    // Setters and getters for generic deck properties
    public void setMaxDeckSize(int size) {
        this.maxDeckSize=size;
    }

    public int getMaxDeckSize (){
        return this.maxDeckSize;
    };
    public int getLand_count(){
        return  this.land_count;
    };
    public abstract int addCard(String card);

    public void set_Lands(int i) {
        this.land_count=i;
    }
    public abstract boolean checkComplete();
}
