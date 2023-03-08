package TheLibrarian.cards;

import TheLibrarian.TheLibrarianMod;
import TheLibrarian.characters.TheLibrarian;
import TheLibrarian.powers.RarePower;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static TheLibrarian.TheLibrarianMod.makeCardPath;

public class DreamOfABlackSwan extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * In-Progress Form At the start of your turn, play a TOUCH.
     */

    // TEXT DECLARATION

    public static final String ID = TheLibrarianMod.makeID(DreamOfABlackSwan.class.getSimpleName());
    public static final String IMG = makeCardPath("Power.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheLibrarian.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final int UPGRADE_COST = 1;

    private static final int MAGIC = 2;

    // /STAT DECLARATION/


    public DreamOfABlackSwan() {

        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;

         //Tag your strike, defend and form cards so that they work correctly.

    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int x=0; x<magicNumber; x++) {
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber), magicNumber));
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(p, p, new DexterityPower(p, magicNumber), magicNumber));
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(p, p, new MetallicizePower(p, magicNumber), magicNumber));
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new DOBSStrongBrother(), 1));
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new DOBSHardyBrother(), 1));
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new DOBSQuickBrother(), 1));
        }
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_COST);
            initializeDescription();
        }
    }
}
