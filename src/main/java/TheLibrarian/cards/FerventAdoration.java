package TheLibrarian.cards;

import TheLibrarian.TheLibrarianMod;
import TheLibrarian.actions.FerventAction;
import TheLibrarian.actions.SchadenfreudeAction;
import TheLibrarian.characters.TheLibrarian;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

import static TheLibrarian.TheLibrarianMod.makeCardPath;

public class FerventAdoration extends AbstractDynamicCard{

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Force a creature to attack all enemies
     */

    // TEXT DECLARATION

    public static final String ID = TheLibrarianMod.makeID(FerventAdoration.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheLibrarian.Enums.COLOR_GRAY;

    private static final int COST = 1;


    private static final int MAGIC = 2;
    private static final int UPGRADE_REDUCED_COST = 0;

    // /STAT DECLARATION/


    public FerventAdoration() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new FerventAction(m));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_REDUCED_COST);
            initializeDescription();
        }
    }
}