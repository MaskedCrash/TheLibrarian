package TheLibrarian.cards;

import TheLibrarian.TheLibrarianMod;
import TheLibrarian.actions.UncommonPowerAction;
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


@AutoAdd.Ignore
public class RipSpace extends AbstractDynamicCard {

    /*
     * Deal  damage X times, damage increase
     */

    // TEXT DECLARATION

    public static final String ID = TheLibrarianMod.makeID(RipSpace.class.getSimpleName());
    public static final String IMG = makeCardPath("Power.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheLibrarian.Enums.COLOR_GRAY;

    private static final int COST = -1;
    private static final int MAGIC = 3;
    private static final int MAGIC_UPGRADE = 2;
    private static final int DAMAGE = 3;


    // /STAT DECLARATION/

    public RipSpace() {

        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;
        baseDamage = DAMAGE;

    }
    
    // Actions the card should do.
    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (energyOnUse < EnergyPanel.totalCount) {
            energyOnUse = EnergyPanel.totalCount;
        }
        int tempDamage=damage;
        for(int x = 0; x<energyOnUse;x++){
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, tempDamage, damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            tempDamage+=magicNumber;
        }
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(MAGIC_UPGRADE);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}