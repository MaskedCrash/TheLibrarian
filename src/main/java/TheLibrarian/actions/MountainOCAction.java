package TheLibrarian.actions;

import TheLibrarian.powers.CommonBook;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class MountainOCAction extends AbstractGameAction {

    private DamageInfo info;
    private int healing;
    private int strength;
    private static final float DURATION = 0.1F;

    public MountainOCAction(int strength, int healing) {
        this.strength = strength;
        this.healing = healing;
        this.actionType = ActionType.HEAL;

    }

    public void update() {
        int counter = 0;

        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            addToBot((AbstractGameAction)new ExhaustSpecificCardAction(c, AbstractDungeon.player.discardPile, true));
            counter++;
        }

        for (int x=0; x<(counter/5);x++) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player,
                    AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, strength), strength));
            AbstractDungeon.player.heal(this.healing);
        }
        tickDuration();
    }
}