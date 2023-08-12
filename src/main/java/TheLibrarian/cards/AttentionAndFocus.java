package TheLibrarian.cards;

import TheLibrarian.TheLibrarianMod;
import TheLibrarian.characters.TheLibrarian;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static TheLibrarian.TheLibrarianMod.makeCardPath;


public class AttentionAndFocus extends AbstractDynamicCard {

    /*
     * Wiki-page:TheLibrarian
     * If no attacks were played this turn, gain 20 block, then lose 10 strength for the turn.
     * Otherwise, gain 6 block.
     */


    // TEXT DECLARATION

    public static final String ID = TheLibrarianMod.makeID(AttentionAndFocus.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheLibrarian.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int BLOCK = 5;
    private static final int UPGRADE_PLUS_BLOCK = 3;


    // /STAT DECLARATION/


    public AttentionAndFocus() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;

    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int attacksPlayed=0;
        for(AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            if (c.type == AbstractCard.CardType.ATTACK){
                attacksPlayed++;

            }
        }
        if(attacksPlayed==0){
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        }
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            initializeDescription();
        }
    }
}
