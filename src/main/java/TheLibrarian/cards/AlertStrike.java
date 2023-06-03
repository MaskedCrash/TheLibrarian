package TheLibrarian.cards;

import TheLibrarian.TheLibrarianMod;
import TheLibrarian.characters.TheLibrarian;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static TheLibrarian.TheLibrarianMod.makeCardPath;
import static TheLibrarian.util.anthologyMath.anthology;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static java.lang.Math.min;

public class AlertStrike extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Alert Strike 9(15)
     * Anthology: Inflict 1 Vulnerable
     */

    // TEXT DECLARATION

    public static final String ID = TheLibrarianMod.makeID(AlertStrike.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("Attack.png");

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheLibrarian.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int DAMAGE = 9;
    private static final int UPGRADE_PLUS_DMG = 6;
    private static final int MAGIC = 1;



    public AlertStrike() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        // Aside from baseDamage/MagicNumber/Block there's also a few more.
        // Just type this.base and let intelliJ auto complete for you, or, go read up AbstractCard

        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        this.tags.add(CardTags.STRIKE);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom( // The action managed queues all the actions a card should do.
                // addToTop - first
                // addToBottom - last
                // 99.99% of the time you just want to addToBottom all of them.
                // Please do that unless you need to add to top for some specific reason.
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn),
                        // a list of existing actions can be found at com.megacrit.cardcrawl.actions but
                        // Chances are you'd instead look at "hey my card is similar to this basegame card"
                        // Let's find out what action *it* uses.
                        // I.e. i want energy gain or card draw, lemme check out Adrenaline
                        // P.s. if you want to damage ALL enemies OUTSIDE of a card, check out the custom orb.
                        AbstractGameAction.AttackEffect.SLASH_HORIZONTAL)); // The animation the damage action uses to hit.
        for (int x = 0; x< anthology();x++){
            addToBot((AbstractGameAction)new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false), this.magicNumber));

        }

    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}
