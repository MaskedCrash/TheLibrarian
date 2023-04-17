package TheLibrarian.powers;

import TheLibrarian.TheLibrarianMod;
import TheLibrarian.cards.DefaultRareAttack;
import TheLibrarian.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class April23rd extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = TheLibrarianMod.makeID("April23rd");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("TheLibrarianResources/images/powers/placeholder_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("TheLibrarianResources/images/powers/placeholder_power32.png");

    public April23rd(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;

        type = PowerType.DEBUFF;
        isTurnBased = false;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        ArrayList<AbstractCard> attackPlayed = new ArrayList<>();
        for(AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {

            if(c.type== AbstractCard.CardType.ATTACK){
                attackPlayed.add(c);
            }

        }
        if (attackPlayed.size()!=0) {
            for(int x = 0; x<amount;x++) {
                Collections.shuffle(attackPlayed);
                AbstractCard playCard =  attackPlayed.get(0).makeCopy(); // Make copy of random valid card.
                AbstractMonster targetMonster = AbstractDungeon.getRandomMonster(); // Declare Target - Random Monster. We will name the monster 'targetMonster'.

                playCard.freeToPlayOnce = true; //Self Explanatory


                AbstractDungeon.actionManager.addToBottom(new NewQueueCardAction(playCard, targetMonster)); // Play the card on the target.
            }
        }
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        } else if (amount > 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new April23rd(owner, source, amount);
    }
}
