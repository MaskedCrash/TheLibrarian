package TheLibrarian.cards;

import TheLibrarian.TheLibrarianMod;
import TheLibrarian.characters.TheLibrarian;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static TheLibrarian.TheLibrarianMod.makeCardPath;



public class EmptyHeartedAxe extends AbstractDynamicCard {

    /*
     * Empty Hearted Axe
     * Deal 22(8) damage, reduced by 6 for each energy you have after playing.
     */

    // TEXT DECLARATION

    public static final String ID = TheLibrarianMod.makeID(EmptyHeartedAxe.class.getSimpleName());
    public static final String IMG = makeCardPath("Power.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheLibrarian.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final int MAGIC = 6;

    private static final int DAMAGE = 22;
    private static final int DAMAGE_UPGRADE = 8;


    // /STAT DECLARATION/

    public EmptyHeartedAxe() {

        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;
        baseDamage = DAMAGE;


    }
    //SLASH_DIAGONAL
    // Actions the card should do.
    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom( // The action managed queues all the actions a card should do.
                new DamageAction(m, new DamageInfo(p, damage-(EnergyPanel.totalCount*magicNumber), damageTypeForTurn),
                     AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(DAMAGE_UPGRADE);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}