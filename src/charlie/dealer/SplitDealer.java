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

    public void split(IPlayer player, Hid hid){
        
        // First we need to validate original hand
        Hand origHand = validate(hid);
        
        // Log any errors
        if(origHand == null) {
            LOG.error("got invalid SPLIT player = "+player);
            return;
        }
        
        // Create a new Hand ID from original.
        // Same seat, same bet amount, but no sidebet as player
        // does side bet and did or did not already.
        Hid newHid = new Hid(hid.getSeat(), hid.getAmt(), 0);
        
        // Let us split the original hand.
        SplitHand newHand = new SplitHand(newHid, origHand);
        
        // Log that we are doing a split action
        // Guess we will log what cards we are splitting and the "new hand amount"
        LOG.info("Player requested to split " 
                + origHand.getCard(0).getName() 
                + "'s."); // = "+hid.getAmt()+" hid = "+hid);

        // Add this hand to this player
        players.put(newHand.getHid(), player);
        
        // Now that we have two hands we need to manipulate the handSeqIndex
        // Think it will be easier to add it AFTER the current hand since that
        // hand is actually "in play" ... 
        int i = handSequence.indexOf(hid);
        handSequence.add((i+1),newHand.getHid());
        
        hands.put(newHid, newHand);
        
        // Send back to the ATable what has just occured.
        player.split(newHid, hid);
        
        // 'hit' one of the new hands
        super.hit(player, hid);
        
    }
}
