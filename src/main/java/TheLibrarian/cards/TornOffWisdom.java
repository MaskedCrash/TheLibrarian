package TheLibrarian.cards;

import TheLibrarian.TheLibrarianMod;
import TheLibrarian.characters.TheLibrarian;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static TheLibrarian.TheLibrarianMod.makeCardPath;

@AutoAdd.Ignore
public class TornOffWisdom extends AbstractDynamicCard {

    /*
     * Discard any number of cards. Gain that much more energy this turn and next.
     * Draw 1(2) cards.
     */

    // TEXT DECLARATION

    public static final String ID = TheLibrarianMod.makeID(TornOffWisdom.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheLibrarian.Enums.COLOR_GRAY;

    private static final int COST = 0;

    private int AMOUNT = 1;

    // /STAT DECLARATION/


    public TornOffWisdom() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = AMOUNT;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.handCardSelectScreen.open(TEXT[0], 99, true, true);

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            if (!AbstractDungeon.handCardSelectScreen.selectedCards.group.isEmpty()) {
                AbstractDungeon.player.gainEnergy(AbstractDungeon.handCardSelectScreen.selectedCards.group.size());
                addToBot(new ApplyPowerAction(p, p, new EnergizedPower(p, AbstractDungeon.handCardSelectScreen.selectedCards.group.size()),
                        AbstractDungeon.handCardSelectScreen.selectedCards.group.size()));
                for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                    AbstractDungeon.player.hand.moveToDiscardPile(c);
                    GameActionManager.incrementDiscard(false);
                    c.triggerOnManualDiscard();
                }
                addToTop(new DrawCardAction(p, magicNumber));
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }

    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}