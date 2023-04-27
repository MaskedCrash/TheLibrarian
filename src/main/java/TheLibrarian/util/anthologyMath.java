package TheLibrarian.util;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.HashSet;
import java.util.Set;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;


public class anthologyMath {
    int imCreated = 0;
    //invitationmath uses static imports- should never be created. This is for debugging purposes.
    anthologyMath(){
        int imCreated = 1;
    };

    public static int anthology(){

       return (player.masterDeck.size()/10);
    };


}
