package TheLibrarian.cards;

import TheLibrarian.TheLibrarianMod;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static TheLibrarian.TheLibrarianMod.makeCardPath;


    /*
    *
    * Status Card Used with Seven Bullets- Unplayable cards that exhaust themselves when drawn.
    *
    * Each one triggers the power.
     */

public class SBBullet extends AbstractDynamicCard {
    public static final String ID = TheLibrarianMod.makeID(SBBullet.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.STATUS;
    public static final CardColor COLOR = CardColor.COLORLESS;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Slimed");
    private static final int COST = -2;

    public SBBullet() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new RemoveSpecificPowerAction(p, p, "Friendship"));

    }

    public void triggerWhenDrawn() {
        addToTop(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
    }
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    public void upgrade() {}

    public AbstractCard makeCopy() {
        return new SBBullet();
    }
}