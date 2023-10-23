package skindex.modcompat;

import dLib.modcompat.ModManager;
import skindex.registering.SkindexPlayerSkinRegistrant;
import skindex.registering.SkindexRegistry;
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
import skindex.modcompat.skins.player.skulHeroSlayer.LittleBoneBaseSkin;
import skindex.modcompat.skins.player.theAbyssal.AbyssalBaseSkin;
import skindex.modcompat.skins.player.theUnchained.UnchainedBaseSkin;
import skindex.modcompat.skins.player.theUnchained.UnchainedPrinceUnboundSkin;
import skindex.skins.player.PlayerSkin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SkindexModCompat implements SkindexPlayerSkinRegistrant {
    /** Methods */
    @Override
    public List<PlayerSkin> getDefaultPlayerSkinsToRegister() {
        ArrayList<PlayerSkin> totalList = new ArrayList<>();
        if(ModManager.Downfall.isActive()) totalList.addAll(getDefaultDownfallSkinsToRegister());
        if(ModManager.MarisaContinued.isActive()) totalList.addAll(getDefaultMarisaContinuedSkinsToRegister());
        if(ModManager.SkulHeroSlayer.isActive()) totalList.addAll(getDefaultSkulHeroSlayerSkinsToRegister());
        if(ModManager.TheAbyssal.isActive()) totalList.addAll(getDefaultTheAbyssalSkinsToRegister());
        if(ModManager.TheUnchained.isActive()) totalList.addAll(getDefaultTheUnchainedSkinsToRegister());
        return totalList;
    }

    private static List<PlayerSkin> getDefaultDownfallSkinsToRegister(){
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
    private static List<PlayerSkin> getDefaultMarisaContinuedSkinsToRegister(){
        return Arrays.asList(new MarisaBaseSkin());
    }
    private static List<PlayerSkin> getDefaultSkulHeroSlayerSkinsToRegister(){
        return Arrays.asList(new LittleBoneBaseSkin());
    }
    private static List<PlayerSkin> getDefaultTheAbyssalSkinsToRegister(){
        return Arrays.asList(new AbyssalBaseSkin());
    }
    private static List<PlayerSkin> getDefaultTheUnchainedSkinsToRegister(){
        return Arrays.asList(new UnchainedBaseSkin());
    }


    @Override
    public List<PlayerSkin> getPlayerSkinsToRegister() {
        ArrayList<PlayerSkin> totalList = new ArrayList<>();
        if(ModManager.Downfall.isActive()) totalList.addAll(getDownfallSkinsToRegister());
        if(ModManager.TheUnchained.isActive()) totalList.addAll(getTheUnchainedSkinsToRegister());
        return totalList;
    }

    private static List<PlayerSkin> getDownfallSkinsToRegister(){
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
    private static List<PlayerSkin> getTheUnchainedSkinsToRegister(){
        return Arrays.asList(new UnchainedPrinceUnboundSkin());
    }
}
