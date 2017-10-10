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
        split(player,hid);
    }

    public void split(IPlayer player, Hid hid){
        
        // Rules:
        //     Do not split a split, maybe make a flag? "isSplit" ? Hand or HID?
        //         can also let the "UI" keep track by not allowing split button
        //         to be enabled for that "player" who already split.
        //     One hand goes at a time
        //     Add to the bet -- but a one time thing
        
        // we need to call the hand "split method"
        // then we will have two hands to work with
        // the new hand and the original hand
        // each hand needs to go one at a time
        // no need to "recreate" anything - use methods here
        // for hit, stay, etc
        
        // handSeqIndex ?? add one to that? 
        // handSequence -- put the "new" hand after the original hand
        // but before any other players -- IE the left side bot (ArrayList.add(index, element)
        
        // *****************************************************
        // okay, some basic stuff...
        // Validate the request
        Hand origHand = validate(hid);
        
        if(origHand == null) {
            LOG.error("got invalid SPLIT player = "+player);
            return;
        }
        
        LOG.info("got split"); // = "+hid.getAmt()+" hid = "+hid);
        
        // Now we need the "other" hand.
        Hand newHand = SplitHand.split(origHand);
        
        // Now that we have two hands we need to manipulate the handSeqIndex
        int i = handSequence.indexOf(hid);
        handSequence.add((i),newHand.getHid());
        
        // Now we want to Play the new hand.
        hit(player, newHand.getHid());
        
        // Once we come back we want to play the other hand
        hit(player, hid);
        
    }
}
