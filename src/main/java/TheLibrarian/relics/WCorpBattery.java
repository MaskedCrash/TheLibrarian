package TheLibrarian.relics;

import TheLibrarian.TheLibrarianMod;
import TheLibrarian.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static TheLibrarian.TheLibrarianMod.makeRelicOutlinePath;
import static TheLibrarian.TheLibrarianMod.makeRelicPath;

public class WCorpBattery extends CustomRelic {
    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     *
     * Gain 1 energy, lose 1 energy for the combat when you shuffle your deck.
     */

    // ID, images, text.
    public static final String ID = TheLibrarianMod.makeID("WCorpBattery");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic2.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic2.png"));

    public WCorpBattery() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.FLAT);
    }


    // Gain 1 Strength on on equip.
    @Override
    public void atBattleStart() {
        flash();
        this.counter = 0;

    }

    public void onEquip() {
        AbstractDungeon.player.energy.energyMaster++;
    }

    public void onUnequip() {
        AbstractDungeon.player.energy.energyMaster--;
    }


    public void onShuffle() {
       this.counter++;
       addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));

    }

    @Override
    public void atTurnStart() {
        addToBot(new GainEnergyAction(-this.counter));
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
