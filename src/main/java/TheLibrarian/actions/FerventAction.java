package TheLibrarian.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

public class FerventAction extends AbstractGameAction {



    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("OpeningAction");

    public static final String[] TEXT = uiStrings.TEXT;
    private AbstractMonster targetMonster;
    private static final float DURATION = 0.1F;

    public int multiDamage;
    public FerventAction(AbstractMonster m) {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;

        this.targetMonster = m;
    }

    public void update() {
        if (this.targetMonster != null && this.targetMonster.getIntentBaseDmg() >= 0) {

            this.multiDamage =  this.targetMonster.getIntentDmg();
            addToBot((AbstractGameAction)new DamageAllEnemiesAction(this.targetMonster, DamageInfo.createDamageMatrix(this.multiDamage), this.damageType, AbstractGameAction.AttackEffect.NONE, true));
        } else {
            AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, TEXT[0], true));
        }
        this.isDone = true;
    }
}
