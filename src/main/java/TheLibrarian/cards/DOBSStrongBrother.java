package TheLibrarian.cards;

import TheLibrarian.characters.TheLibrarian;
import TheLibrarian.TheLibrarianMod;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static TheLibrarian.TheLibrarianMod.makeCardPath;

public class DOBSStrongBrother extends AbstractDynamicCard {
    public static final String ID = TheLibrarianMod.makeID(DOBSStrongBrother.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.STATUS;
    public static final CardColor COLOR = AbstractCard.CardColor.COLORLESS;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Slimed");
    private static final int COST = 0;

    public DOBSStrongBrother() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new StrengthPower(p, -1), -1));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(1));

    }

    public void upgrade() {}

    public AbstractCard makeCopy() {
        return new DOBSStrongBrother();
    }
}