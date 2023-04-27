package TheLibrarian.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import TheLibrarian.TheLibrarianMod;
import TheLibrarian.characters.TheLibrarian;

import static TheLibrarian.TheLibrarianMod.makeCardPath;
import static TheLibrarian.util.anthologyMath.anthology;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class Curiosity extends AbstractDynamicCard {

    /*
     *
     *
     * Discard all cards, draw cards equal to anthology.
     */


    // TEXT DECLARATION

    public static final String ID = TheLibrarianMod.makeID(Curiosity.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheLibrarian.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADE_NEW_COST = 0;


    // /STAT DECLARATION/


    public Curiosity() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);


        //this.tags.add(CardTags.STARTER_DEFEND); //Tag your strike, defend and form (Wraith form, Demon form, Echo form, etc.) cards so that they function correctly.
    }

    public Curiosity(String id, String img, int cost, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, img, cost, type, color, rarity, target);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int cardsToDraw = anthology();
        int theSize = AbstractDungeon.player.hand.size();
        AbstractDungeon.actionManager.addToBottom(new DiscardAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature) AbstractDungeon.player, theSize, false));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(cardsToDraw));

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
