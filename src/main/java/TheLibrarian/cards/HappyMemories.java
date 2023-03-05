package TheLibrarian.cards;

import TheLibrarian.TheLibrarianMod;
import TheLibrarian.characters.TheLibrarian;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static TheLibrarian.TheLibrarianMod.makeCardPath;

public class HappyMemories extends AbstractDynamicCard {

    /*
     * Wiki-page:TheLibrarian
     *
     * Defend Gain 5 (8) block.
     */


    // TEXT DECLARATION

    public static final String ID = TheLibrarianMod.makeID(HappyMemories.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheLibrarian.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int BLOCK = 4;
    private static final int UPGRADE_PLUS_BLOCK = 2;
    private static final int PLATED_ARMOR = 1;
    private static final int UPGRADE_PLATED_ARMOR = 1;


    // /STAT DECLARATION/


    public HappyMemories() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        magicNumber = PLATED_ARMOR;

    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
            addToBot(new ApplyPowerAction(p, p, new PlatedArmorPower(p, this.magicNumber), this.magicNumber));;
        }
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            upgradeMagicNumber(UPGRADE_PLATED_ARMOR);
            initializeDescription();
        }
    }
}
