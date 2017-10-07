/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package charlie.test;

import charlie.actor.House;
import charlie.card.Card;
import charlie.card.Card.Suit;
import charlie.card.Hand;
import charlie.dealer.Dealer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author blossom
 */
public class SplitTest {
    
    public SplitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void test(){
        
        Hand hand = new Hand();
        
        hand.getHid().setAmt(5.0);
        
        System.out.println(hand.getHid().getAmt());
        
        hand.hit(new Card(Card.ACE, Suit.CLUBS));
        hand.hit(new Card(Card.ACE, Suit.SPADES));
        
        System.out.println(hand.getCard(0));
        System.out.println(hand.getCard(1));
        
        System.out.println(hand.size());
        
        Hand split = hand.split();
        
        System.out.println(hand.getHid().getAmt());
        System.out.println(split.getHid().getAmt());
        
        System.out.println(hand.size());
        System.out.println(split.size());
        
        System.out.println(hand.getCard(0));
        System.out.println(split.getCard(0));
        
        System.out.println(hand.getCard(1));
        System.out.println(split.getCard(1));
        
        assert(true);
        
    }
}
