package TheLibrarian.relics;

import TheLibrarian.TheLibrarianMod;
import TheLibrarian.cards.EmptyBook;
import TheLibrarian.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static TheLibrarian.TheLibrarianMod.makeRelicOutlinePath;
import static TheLibrarian.TheLibrarianMod.makeRelicPath;

public class TheInvitation extends CustomRelic {

    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     *
     * Gain 1 energy.
     */

    // ID, images, text.
    public static final String ID = TheLibrarianMod.makeID("TheInvitation");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));

    public TheInvitation() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    // Flash at the start of Battle.
    @Override


    public void atBattleStartPreDraw() {
        flash();
        addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new EmptyBook(), 1, false));
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
