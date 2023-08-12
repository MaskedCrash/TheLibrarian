package TheLibrarian.cards;

import TheLibrarian.TheLibrarianMod;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.PutOnDeckAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static TheLibrarian.TheLibrarianMod.makeCardPath;

public class TOFPebble extends AbstractDynamicCard {
    public static final String ID = TheLibrarianMod.makeID(TOFPebble.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.STATUS;
    public static final CardColor COLOR = CardColor.COLORLESS;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Slimed");
    private static final int COST = 1;

    public TOFPebble() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new RemoveSpecificPowerAction(p, p, "Friendship"));

    }

    @Override
    public void onMoveToDiscard() {
        AbstractPlayer pl = AbstractDungeon.player;
        pl.discardPile.moveToDeck(this, false);
    }

    public void upgrade() {}

    public AbstractCard makeCopy() {
        return new TOFPebble();
    }
}