package TheLibrarian.cards;

import TheLibrarian.TheLibrarianMod;
import TheLibrarian.characters.TheLibrarian;
import TheLibrarian.powers.BulletTracker;
import TheLibrarian.powers.ScarsPower;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

import static TheLibrarian.TheLibrarianMod.makeCardPath;
import static TheLibrarian.util.anthologyMath.anthology;


@AutoAdd.Ignore
public class SevenBullets extends AbstractDynamicCard {

    /*
     * Shuffle 7 Bullets into your deck.
     * Every time you draw one, deal 5 damage to each enemy.
     * When you draw 7 bullets...TOFPebble
     */


    // TEXT DECLARATION

    public static final String ID = TheLibrarianMod.makeID(SevenBullets.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheLibrarian.Enums.COLOR_GRAY;
    private static final int COST = 2;

    // /STAT DECLARATION/


    public SevenBullets() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        isEthereal=true;

    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom( new ApplyPowerAction(p,p,new BulletTracker(p, p, 0)));
        addToBot((AbstractGameAction)new MakeTempCardInDrawPileAction(new SBBullet(), 7, true, true));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();

        }
    }
}