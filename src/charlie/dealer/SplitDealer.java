package charlie.dealer;

import charlie.actor.House;
import charlie.card.Hand;
import charlie.card.Hid;
import charlie.card.SplitHand;
import charlie.plugin.IPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class that adds split functionality to dealer
 * 
 * TODO: Consider refactor to "AdvancedDealer" for other functions?
 * 
 * @author Dan Blossom
 */
public class SplitDealer extends Dealer {
    
    private final Logger LOG = LoggerFactory.getLogger(SplitDealer.class); 
    
    public SplitDealer(House house){
        super(house);
    }
    
    @Override
    public void hit(IPlayer player, Hid hid){
        Hand hand = validate(hid);
        
        if(!hand.isPair()){
            super.hit(player, hid);
        }else
            split(player,hid);
    }

    public void split(IPlayer player, Hid hid){
        
        // First we need to validate the request
        Hand origHand = validate(hid);
        
        // Log any errors
        if(origHand == null) {
            LOG.error("got invalid SPLIT player = "+player);
            return;
        }
        
        // Log that we are doing a split action
        // Guess we will log what cards we are splitting and the "new hand amount"
        LOG.info("Player requested to split " 
                + origHand.getCard(0).getName() 
                + "'s."); // = "+hid.getAmt()+" hid = "+hid);
        
        // Now we need the "other" hand.
        Hand newHand = SplitHand.split(origHand);
        
        // Add this hand to the dealers array of hands
        hands.put(newHand.getHid(), newHand);
        
        // Add this hand to this player
        players.put(newHand.getHid(), player);
        
        // Now that we have two hands we need to manipulate the handSeqIndex
        // Think it will be easier to add it AFTER the current hand since that
        // hand is actually "in play" ... 
        int i = handSequence.indexOf(hid);
        handSequence.add((i+1),newHand.getHid());
        
          //      player.play(newHand.getHid());
        
        // Now we want to Play the new hand.
        // For testing, let's leave this but the end design
        // will be a helper function to ensure only one hand
        // is played at a time.
        super.hit(player, hid);
      //  super.stay(player, newHand.getHid());
        
        // Once we come back we want to play the other hand
       // super.hit(player, hid);
      //  super.stay(player, hid);

        
    }
}
