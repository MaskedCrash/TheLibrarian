package TheLibrarian.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.BetterDrawPileToHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import TheLibrarian.TheLibrarianMod;
import TheLibrarian.characters.TheLibrarian;

import static TheLibrarian.TheLibrarianMod.makeCardPath;
import static TheLibrarian.util.anthologyMath.anthology;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static java.lang.Math.max;

public class SkimCollection extends AbstractDynamicCard {

    /*
     *
     *
     * Seek but doesn't exhaust and costs 5
     */


    // TEXT DECLARATION

    public static final String ID = TheLibrarianMod.makeID(SkimCollection.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheLibrarian.Enums.COLOR_GRAY;

    private static final int COST = 5;



    // /STAT DECLARATION/


    public void applyPowers() {
        this.costForTurn=max(anthology(),0);
        super.applyPowers();
    }

    public SkimCollection() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;

        //this.tags.add(CardTags.STARTER_DEFEND); //Tag your strike, defend and form (Wraith form, Demon form, Echo form, etc.) cards so that they function correctly.
    }

    public SkimCollection(String id, String img, int cost, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, img, cost, type, color, rarity, target);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new BetterDrawPileToHandAction(this.magicNumber));

    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);

            initializeDescription();
        }
    }
}
