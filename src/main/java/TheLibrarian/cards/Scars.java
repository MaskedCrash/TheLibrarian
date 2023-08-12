package TheLibrarian.cards;

import TheLibrarian.TheLibrarianMod;
import TheLibrarian.actions.UncommonPowerAction;
import TheLibrarian.characters.TheLibrarian;
import TheLibrarian.powers.ScarsPower;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static TheLibrarian.TheLibrarianMod.makeCardPath;
import static TheLibrarian.util.anthologyMath.anthology;


@AutoAdd.Ignore
public class Scars extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * When you take health damage, gain block next turn.
     * Amount is determined by Anthology
     */

    // TEXT DECLARATION

    public static final String ID = TheLibrarianMod.makeID(Scars.class.getSimpleName());
    public static final String IMG = makeCardPath("Power.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheLibrarian.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int MAGIC = 3;
    private static final int MAGIC_UPGRADE = 1;



    // /STAT DECLARATION/

    public Scars() {

        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;

    }
    
    // Actions the card should do.
    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom( new ApplyPowerAction(p,p,new ScarsPower(p, p, magicNumber*anthology())));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            upgradeMagicNumber(MAGIC_UPGRADE);
            initializeDescription();
        }
    }
}