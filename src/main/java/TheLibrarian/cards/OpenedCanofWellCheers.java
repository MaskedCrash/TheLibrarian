package TheLibrarian.cards;

import TheLibrarian.TheLibrarianMod;
import TheLibrarian.characters.TheLibrarian;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import java.util.ArrayList;
import java.util.Collections;

import static TheLibrarian.TheLibrarianMod.makeCardPath;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static java.lang.Math.min;

public class OpenedCanofWellCheers extends AbstractDynamicCard {

    /*
     * Gives choice of X potions to brew, max 4
     * Same selection of 4, but randomized.
     *
     */

    // TEXT DECLARATION

    public static final String ID = TheLibrarianMod.makeID(OpenedCanofWellCheers.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheLibrarian.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADE_NEW_COST = 1;


    private int AMOUNT = 1;

    // /STAT DECLARATION/


    public OpenedCanofWellCheers() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = AMOUNT;
        this.exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> drinkList = new ArrayList<>();

        drinkList.add(new WellcheerBlueCan());
        drinkList.add(new WellcheerRedCan());
        drinkList.add(new WellcheerPurpleCan());
        drinkList.add(new WellcheerOddCan());
        Collections.shuffle(drinkList);
        ArrayList<AbstractCard> drinkChoices = new ArrayList<>();
        for (int x = 0; x< min((player.masterDeck.size()/10),4);x++){
            drinkChoices.add(drinkList.get(x));
        }
        if(player.masterDeck.size()/10!=0) {
            AbstractDungeon.actionManager.addToBottom(new ChooseOneAction(drinkChoices));
        }

    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_NEW_COST);
            initializeDescription();
        }
    }
}