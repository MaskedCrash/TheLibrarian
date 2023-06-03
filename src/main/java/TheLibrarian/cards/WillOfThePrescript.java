package TheLibrarian.cards;

import TheLibrarian.TheLibrarianMod;
import TheLibrarian.characters.TheLibrarian;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;

import java.util.HashSet;
import java.util.Set;

import static TheLibrarian.TheLibrarianMod.makeCardPath;

public class WillOfThePrescript extends AbstractDynamicCard {

    /*
     * Will of The Prescript
     *
     * Deal damage equal to twice the number of unique cards played this combat.
     * Invitation: 3 Attacks, 3 Skills
     * Draw 2 cards. During your next turn, Draw two more cards.
     */

    // TEXT DECLARATION

    public static final String ID = TheLibrarianMod.makeID(WillOfThePrescript.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheLibrarian.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int DAMAGE = 2;
    private static final int MAGIC = 2;
    private static final int MAGIC_UPGRADE = 1;

    // /STAT DECLARATION/


    public WillOfThePrescript() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber=MAGIC;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        Set<String> played = new HashSet<String>();
        int attackcount=0;
        int skillcount=0;
        for(AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if(played.contains(c.cardID)) continue;
            played.add(c.cardID);
            if(c.type==CardType.ATTACK){
                attackcount++;
            }else if(c.type==CardType.SKILL){
                skillcount++;
            }

        }

        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, played.size()*2, damageTypeForTurn),
                        AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        if(skillcount>=3&&attackcount>=3){
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(magicNumber));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, magicNumber), magicNumber));
        }
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(MAGIC_UPGRADE);
            initializeDescription();
        }
    }
}