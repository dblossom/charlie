/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package charlie.controller;

import charlie.card.Card;
import charlie.card.Hid;
import java.util.List;

/**
 * Messages sent to the player from house or dealer.
 * @author roncoleman125
 */
public interface IPlayer {    
    abstract public void startGame(List<Hid> hids,int shoeSize);
    abstract public void endGame(int shoeSize);
    abstract public void deal(Hid hid, Card card, int[] values);
    abstract public void insure();
    abstract public void bust(Hid hid);
    abstract public void win(Hid hid);
    abstract public void blackjack(Hid hid);
    abstract public void charlie(Hid hid);
    abstract public void loose(Hid hid);
    abstract public void push(Hid hid);
    abstract public void shuffling();
    abstract public void play(Hid hid);
}
