package TheLibrarian.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.DrawPileToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

/*

Action called by the Nixie Divergence (tourGuidePower) power

 */


public class TourGuideDrawAction extends AbstractGameAction {

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DualWieldAction");

    public static final String[] TEXT = uiStrings.TEXT;

    private static final float DURATION_PER_CARD = 0.25F;

    private AbstractPlayer p;

    private int dupeAmount = 1;


    public TourGuideDrawAction(AbstractCreature source, int amount) {
        setValues((AbstractCreature)AbstractDungeon.player, source, amount);
        this.actionType = ActionType.DRAW;
        this.duration = 0.25F;
        this.p = AbstractDungeon.player;
        this.dupeAmount = amount;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {

            if (this.p.hand.group.size()!=0) {
                int howManyAtk = 0;
                int howManySkl = 0;
                int howManyPwr = 0;
                int atkInDraw = 0;
                int sklInDraw = 0;
                int pwrInDraw = 0;
                for (AbstractCard c : this.p.hand.group) {
                    if(c.type== AbstractCard.CardType.ATTACK)
                        howManyAtk++;
                    else if(c.type== AbstractCard.CardType.SKILL)
                        howManySkl++;
                    else if(c.type== AbstractCard.CardType.POWER)
                        howManyPwr++;
                }
                for (AbstractCard c : this.p.drawPile.group) {
                    if(c.type== AbstractCard.CardType.ATTACK)
                        atkInDraw++;
                    else if(c.type== AbstractCard.CardType.SKILL)
                        sklInDraw++;
                    else if(c.type== AbstractCard.CardType.POWER)
                        pwrInDraw++;
                }


                for(int x = 0; x<dupeAmount; x++) {
                    if ((howManyAtk <= howManySkl && howManyAtk <= howManyPwr) || (sklInDraw == 0 && pwrInDraw == 0) ||
                            (howManyAtk <= howManySkl && pwrInDraw == 0) || (howManyAtk <= howManyPwr && sklInDraw == 0)) {
                        addToBot(new DrawPileToHandAction(1, AbstractCard.CardType.ATTACK));
                        howManyAtk++;
                        atkInDraw--;
                    } else if (howManySkl <= howManyPwr && howManySkl <= howManyAtk || (atkInDraw == 0 && pwrInDraw == 0) ||
                            (howManySkl <= howManyAtk && pwrInDraw == 0) || (howManySkl <= howManyPwr && atkInDraw == 0)) {
                        addToBot(new DrawPileToHandAction(1, AbstractCard.CardType.SKILL));
                        howManySkl++;
                        sklInDraw--;
                    } else {
                        addToBot(new DrawPileToHandAction(1, AbstractCard.CardType.POWER));
                        howManyPwr++;
                        pwrInDraw--;
                    }
                }
            }



        }

        tickDuration();
    }

    private void returnCards() {
        this.p.hand.refreshHandLayout();
    }


}
