package TheLibrarian.cards;

import TheLibrarian.TheLibrarianMod;
import TheLibrarian.characters.TheLibrarian;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import static TheLibrarian.TheLibrarianMod.makeCardPath;

public class BarrierOfVines extends AbstractDynamicCard {

    /*
     * Wiki-page:TheLibrarian
     *
     * Defend Gain 5 (8) block.
     */


    // TEXT DECLARATION

    public static final String ID = TheLibrarianMod.makeID(BarrierOfVines.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheLibrarian.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int BLOCK = 8;
    private static final int UPGRADE_PLUS_BLOCK = 3;
    private static final int WEAK_AMOUNT = 1;
    private static final int WEAK_UPGRADE_AMOUNT = 1;


    // /STAT DECLARATION/


    public BarrierOfVines() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        baseMagicNumber=magicNumber = WEAK_AMOUNT;

    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters)
            addToBot(new ApplyPowerAction(mo, p, new WeakPower(mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            upgradeMagicNumber(WEAK_UPGRADE_AMOUNT);
            initializeDescription();
        }
    }
}
