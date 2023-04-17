package TheLibrarian.util;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.HashSet;
import java.util.Set;



public class invitationMath {
    int imCreated = 0;
    //invitationmath uses static imports- should never be created. This is for debugging purposes.
    invitationMath(){
        int imCreated = 1;
    };

    public static boolean invitationCheck(int attacksReq, int skillsReq, int powersReq){

        Set<String> played = new HashSet<String>();
        int attackCount=0;
        int skillCount=0;
        int powerCount=0;
        for(AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if(played.contains(c.cardID)) continue;
            played.add(c.cardID);
            if(c.type== AbstractCard.CardType.ATTACK){
                attackCount++;
            }else if(c.type== AbstractCard.CardType.SKILL){
                skillCount++;
            }else if(c.type== AbstractCard.CardType.POWER){
                powerCount++;
            }



        }
        if(skillCount>=skillsReq&&attackCount>=attacksReq&&powerCount>=powersReq)
            return true;







        return false;
    };


}
