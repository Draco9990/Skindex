package skindex.modcompat;

import dLib.modcompat.ModManager;
import skindex.modcompat.duelistmod.skins.player.*;
import skindex.modcompat.packmaster.skins.player.PackmasterBaseSkin;
import skindex.modcompat.packmaster.skins.player.PackmasterBuilderSkin;
import skindex.modcompat.packmaster.skins.player.PackmasterHipsterSkin;
import skindex.modcompat.packmaster.skins.player.PackmasterPackmistressSkin;
import skindex.modcompat.skins.player.theUnchained.UnchainedBirthdaySkin;
import skindex.modcompat.skulHeroSlayer.skins.card.LittleBoneElMuerteCardSkinSet;
import skindex.modcompat.skulHeroSlayer.skins.card.LittleBoneLittleHelperCardSkinSet;
import skindex.modcompat.skulHeroSlayer.skins.card.LittleBoneRoyalGuardCardSkinSet;
import skindex.modcompat.skulHeroSlayer.skins.player.LittleBoneElMuerteSkin;
import skindex.modcompat.skulHeroSlayer.skins.player.LittleBoneLittleHelperSkin;
import skindex.modcompat.skulHeroSlayer.skins.player.LittleBoneRoyalGuardSkin;
import skindex.modcompat.unchained.skins.card.UnchainedPixelCardSkinSet;
import skindex.modcompat.unchained.skins.player.UnchainedPixelSkin;
import skindex.registering.SkindexCardSkinRegistrant;
import skindex.registering.SkindexPlayerSkinRegistrant;
import skindex.modcompat.downfall.skins.player.automaton.AutomatonBaseSkin;
import skindex.modcompat.downfall.skins.player.automaton.AutomatonBetaSkin;
import skindex.modcompat.downfall.skins.player.automaton.AutomatonThePerfectSkin;
import skindex.modcompat.downfall.skins.player.champ.ChampBaseSkin;
import skindex.modcompat.downfall.skins.player.champ.ChampBetaSkin;
import skindex.modcompat.downfall.skins.player.champ.ChampChanSkin;
import skindex.modcompat.downfall.skins.player.collector.CollectorBaseSkin;
import skindex.modcompat.downfall.skins.player.gremlins.GremlinsBaseSkin;
import skindex.modcompat.downfall.skins.player.guardian.GuardianBaseSkin;
import skindex.modcompat.downfall.skins.player.guardian.GuardianBetaSkin;
import skindex.modcompat.downfall.skins.player.guardian.GuardianChanSkin;
import skindex.modcompat.downfall.skins.player.hermit.HermitBaseSkin;
import skindex.modcompat.downfall.skins.player.hexaghost.HexaghostBaseSkin;
import skindex.modcompat.downfall.skins.player.hexaghost.HexaghostBetaSkin;
import skindex.modcompat.downfall.skins.player.hexaghost.HexaghostChanSkin;
import skindex.modcompat.downfall.skins.player.hexaghost.HexaghostLampSkin;
import skindex.modcompat.downfall.skins.player.slimebound.SlimeboundBaseSkin;
import skindex.modcompat.downfall.skins.player.slimebound.SlimeboundBetaSkin;
import skindex.modcompat.downfall.skins.player.slimebound.SlimeboundChanSkin;
import skindex.modcompat.downfall.skins.player.snecko.SneckoBaseSkin;
import skindex.modcompat.downfall.skins.player.snecko.SneckoBetaSkin;
import skindex.modcompat.downfall.skins.player.snecko.SneckoChanSkin;
import skindex.modcompat.skins.player.marisaContinued.MarisaBaseSkin;
import skindex.modcompat.skulHeroSlayer.skins.player.LittleBoneBaseSkin;
import skindex.modcompat.skins.player.theAbyssal.AbyssalBaseSkin;
import skindex.modcompat.skins.player.theUnchained.UnchainedBaseSkin;
import skindex.modcompat.skins.player.theUnchained.UnchainedPrinceUnboundSkin;
import skindex.skins.cards.CardSkin;
import skindex.skins.player.AbstractPlayerSkin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SkindexModCompat implements SkindexPlayerSkinRegistrant, SkindexCardSkinRegistrant {
    /** Methods */
    @Override
    public List<AbstractPlayerSkin> getDefaultPlayerSkinsToRegister() {
        ArrayList<AbstractPlayerSkin> totalList = new ArrayList<>();
        if(ModManager.Downfall.isActive()) totalList.addAll(getDefaultDownfallSkinsToRegister());
        if(ModManager.MarisaContinued.isActive()) totalList.addAll(getDefaultMarisaContinuedSkinsToRegister());
        if(ModManager.SkulHeroSlayer.isActive()) totalList.addAll(getDefaultSkulHeroSlayerSkinsToRegister());
        if(ModManager.TheAbyssal.isActive()) totalList.addAll(getDefaultTheAbyssalSkinsToRegister());
        if(ModManager.TheUnchained.isActive()) totalList.addAll(getDefaultTheUnchainedSkinsToRegister());
        if(ModManager.ThePackmaster.isActive()) totalList.addAll(getDefaultPackmasterSkinsToRegister());
        if(ModManager.DuelistMod.isActive()) totalList.addAll(getDefaultDuelistModSkinsToRegister());
        return totalList;
    }

    private static List<AbstractPlayerSkin> getDefaultDownfallSkinsToRegister(){
        return Arrays.asList(
            new AutomatonBaseSkin(),
            new ChampBaseSkin(),
            new CollectorBaseSkin(),
            new GremlinsBaseSkin(),
            new GuardianBaseSkin(),
            new HermitBaseSkin(),
            new HexaghostBaseSkin(),
            new SlimeboundBaseSkin(),
            new SneckoBaseSkin()
        );
    }
    private static List<AbstractPlayerSkin> getDefaultMarisaContinuedSkinsToRegister(){
        return Arrays.asList(new MarisaBaseSkin());
    }
    private static List<AbstractPlayerSkin> getDefaultSkulHeroSlayerSkinsToRegister(){
        return Arrays.asList(new LittleBoneBaseSkin());
    }
    private static List<AbstractPlayerSkin> getDefaultTheAbyssalSkinsToRegister(){
        return Arrays.asList(new AbyssalBaseSkin());
    }
    private static List<AbstractPlayerSkin> getDefaultTheUnchainedSkinsToRegister(){
        return Arrays.asList(new UnchainedBaseSkin());
    }
    private static List<AbstractPlayerSkin> getDefaultPackmasterSkinsToRegister(){
        return Arrays.asList(new PackmasterBaseSkin());
    }
    private static List<AbstractPlayerSkin> getDefaultDuelistModSkinsToRegister(){
        return Arrays.asList(new DuelistBaseSkin());
    }


    @Override
    public List<AbstractPlayerSkin> getPlayerSkinsToRegister() {
        ArrayList<AbstractPlayerSkin> totalList = new ArrayList<>();
        if(ModManager.Downfall.isActive()) totalList.addAll(getDownfallSkinsToRegister());
        if(ModManager.TheUnchained.isActive()) totalList.addAll(getTheUnchainedSkinsToRegister());
        if(ModManager.SkulHeroSlayer.isActive()) totalList.addAll(getSkulHeroSlayerSkinsToRegister());
        if(ModManager.ThePackmaster.isActive()) totalList.addAll(getPackmasterSkinsToRegister());
        if(ModManager.DuelistMod.isActive()) totalList.addAll(getDuelistModSkinsToRegister());
        return totalList;
    }

    private static List<AbstractPlayerSkin> getDownfallSkinsToRegister(){
        return Arrays.asList(
            new AutomatonBetaSkin(),
            new AutomatonThePerfectSkin(),

            new ChampChanSkin(),
            new ChampBetaSkin(),

            new GuardianChanSkin(),
            new GuardianBetaSkin(),

            new HexaghostChanSkin(),
            new HexaghostBetaSkin(),
            new HexaghostLampSkin(),

            new SlimeboundChanSkin(),
            new SlimeboundBetaSkin(),

            new SneckoChanSkin(),
            new SneckoBetaSkin()
        );
    }
    private static List<AbstractPlayerSkin> getTheUnchainedSkinsToRegister(){
        return Arrays.asList(
            new UnchainedPrinceUnboundSkin(),
            new UnchainedBirthdaySkin(),
            new UnchainedPixelSkin()
            //new UnchainedMermanSkin()
        );
    }
    private static List<AbstractPlayerSkin> getSkulHeroSlayerSkinsToRegister(){
        return Arrays.asList(
            new LittleBoneElMuerteSkin(),
            new LittleBoneLittleHelperSkin(),
            new LittleBoneRoyalGuardSkin()
        );
    }
    private static List<AbstractPlayerSkin> getPackmasterSkinsToRegister(){
        return Arrays.asList(
            new PackmasterPackmistressSkin(),
            new PackmasterHipsterSkin(),
            new PackmasterBuilderSkin()
        );
    }
    private static List<AbstractPlayerSkin> getDuelistModSkinsToRegister(){
        return Arrays.asList(
            new DuelistClassicYugiSkin(),
            new DuelistKaibaSkin(),
            new DuelistAnimatedYugiSkin(),
            new DuelistAnimatedKaibaSkin()
        );
    }



    @Override
    public List<CardSkin> getCardSkinsToRegister() {
        ArrayList<CardSkin> totalList = new ArrayList<>();
        if(ModManager.TheUnchained.isActive()) totalList.addAll(getTheUnchainedCardSkinsToRegister());
        if(ModManager.SkulHeroSlayer.isActive()) totalList.addAll(getSkulheroSlayerCardSkinsToRegister());
        return totalList;
    }

    private static List<CardSkin> getTheUnchainedCardSkinsToRegister(){
        return UnchainedPixelCardSkinSet.collectCardSkins();
    }

    private static List<CardSkin> getSkulheroSlayerCardSkinsToRegister(){
        List<CardSkin> cardSkins = new ArrayList<>();
        cardSkins.addAll(LittleBoneElMuerteCardSkinSet.collectCardSkins());
        cardSkins.addAll(LittleBoneLittleHelperCardSkinSet.collectCardSkins());
        cardSkins.addAll(LittleBoneRoyalGuardCardSkinSet.collectCardSkins());
        return cardSkins;
    }
}
