package skindex.modcompat;

import dLib.modcompat.ModManager;
import skindex.modcompat.duelistmod.skins.player.*;
import skindex.modcompat.packmaster.skins.player.PackmasterBaseSkin;
import skindex.modcompat.packmaster.skins.player.PackmasterBuilderSkin;
import skindex.modcompat.packmaster.skins.player.PackmasterHipsterSkin;
import skindex.modcompat.packmaster.skins.player.PackmasterPackmistressSkin;
import skindex.modcompat.skins.player.theUnchained.UnchainedBirthdaySkin;
import skindex.modcompat.skins.player.theUnchained.UnchainedMermanSkin;
import skindex.modcompat.skulHeroSlayer.skins.card.LittleBoneElMuerteCardSkinSet;
import skindex.modcompat.skulHeroSlayer.skins.card.LittleBoneLittleHelperCardSkinSet;
import skindex.modcompat.skulHeroSlayer.skins.card.LittleBoneRoyalGuardCardSkinSet;
import skindex.modcompat.skulHeroSlayer.skins.player.LittleBoneElMuerteSkin;
import skindex.modcompat.skulHeroSlayer.skins.player.LittleBoneLittleHelperSkin;
import skindex.modcompat.skulHeroSlayer.skins.player.LittleBoneRoyalGuardSkin;
import skindex.modcompat.unchained.skins.card.UnchainedPixelCardSkinSet;
import skindex.modcompat.unchained.skins.player.UnchainedPixelSkin;
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
import skindex.registering.SkindexRegistry;

public class SkindexModCompat {
    public static void registerAll(){
        registerCardSkins();

        registerDefaultPlayerSkins();
        registerPlayerSkins();
    }

    public static void registerDefaultPlayerSkins() {
        if(ModManager.Downfall.isActive()) registerDefaultDownfallSkins();
        if(ModManager.MarisaContinued.isActive()) registerDefaultMarisaContinuedSkins();
        if(ModManager.SkulHeroSlayer.isActive()) registerDefaultSkulHeroSlayerSkins();
        if(ModManager.TheAbyssal.isActive()) registerDefaultTheAbyssalSkins();
        if(ModManager.TheUnchained.isActive()) registerDefaultUnchainedSkins();
        if(ModManager.ThePackmaster.isActive()) registerDefaultPackmasterSkins();
        if(ModManager.DuelistMod.isActive()) registerDefaultDuelistModSkins();
    }

    private static void registerDefaultDownfallSkins(){
        SkindexRegistry.registerPlayerSkin(new AutomatonBaseSkin());
        SkindexRegistry.registerPlayerSkin(new ChampBaseSkin());
        SkindexRegistry.registerPlayerSkin(new CollectorBaseSkin());
        SkindexRegistry.registerPlayerSkin(new GremlinsBaseSkin());
        SkindexRegistry.registerPlayerSkin(new GuardianBaseSkin());
        SkindexRegistry.registerPlayerSkin(new HermitBaseSkin());
        SkindexRegistry.registerPlayerSkin(new HexaghostBaseSkin());
        SkindexRegistry.registerPlayerSkin(new SlimeboundBaseSkin());
        SkindexRegistry.registerPlayerSkin(new SneckoBaseSkin());
    }
    private static void registerDefaultMarisaContinuedSkins(){
        SkindexRegistry.registerPlayerSkin(new MarisaBaseSkin());
    }
    private static void registerDefaultSkulHeroSlayerSkins(){
        SkindexRegistry.registerPlayerSkin(new LittleBoneBaseSkin());
    }
    private static void registerDefaultTheAbyssalSkins(){
        SkindexRegistry.registerPlayerSkin(new AbyssalBaseSkin());
    }
    private static void registerDefaultUnchainedSkins(){
        SkindexRegistry.registerPlayerSkin(new UnchainedBaseSkin());
    }
    private static void registerDefaultPackmasterSkins(){
        SkindexRegistry.registerPlayerSkin(new PackmasterBaseSkin());
    }
    private static void registerDefaultDuelistModSkins(){
        SkindexRegistry.registerPlayerSkin(new DuelistBaseSkin());
    }


    private static void registerPlayerSkins() {
        if(ModManager.Downfall.isActive()) getDownfallSkinsToRegister();
        if(ModManager.TheUnchained.isActive()) getTheUnchainedSkinsToRegister();
        if(ModManager.SkulHeroSlayer.isActive()) getSkulHeroSlayerSkinsToRegister();
        if(ModManager.ThePackmaster.isActive()) getPackmasterSkinsToRegister();
        if(ModManager.DuelistMod.isActive()) getDuelistModSkinsToRegister();
    }

    private static void getDownfallSkinsToRegister(){
        SkindexRegistry.registerItem(new AutomatonBetaSkin());
        SkindexRegistry.registerItem(new AutomatonThePerfectSkin());

        SkindexRegistry.registerItem(new ChampChanSkin());
        SkindexRegistry.registerItem(new ChampBetaSkin());

        SkindexRegistry.registerItem(new GuardianChanSkin());
        SkindexRegistry.registerItem(new GuardianBetaSkin());

        SkindexRegistry.registerItem(new HexaghostChanSkin());
        SkindexRegistry.registerItem(new HexaghostBetaSkin());
        SkindexRegistry.registerItem(new HexaghostLampSkin());

        SkindexRegistry.registerItem(new SlimeboundChanSkin());
        SkindexRegistry.registerItem(new SlimeboundBetaSkin());

        SkindexRegistry.registerItem(new SneckoChanSkin());
        SkindexRegistry.registerItem(new SneckoBetaSkin());
    }
    private static void getTheUnchainedSkinsToRegister(){
        SkindexRegistry.registerItem(new UnchainedPrinceUnboundSkin());
        SkindexRegistry.registerItem(new UnchainedBirthdaySkin());
        SkindexRegistry.registerItem(new UnchainedPixelSkin());
        SkindexRegistry.registerItem(new UnchainedMermanSkin());
    }
    private static void getSkulHeroSlayerSkinsToRegister(){
        SkindexRegistry.registerItem(new LittleBoneElMuerteSkin());
        SkindexRegistry.registerItem(new LittleBoneLittleHelperSkin());
        SkindexRegistry.registerItem(new LittleBoneRoyalGuardSkin());
    }
    private static void getPackmasterSkinsToRegister(){
        SkindexRegistry.registerItem(new PackmasterPackmistressSkin());
        SkindexRegistry.registerItem(new PackmasterHipsterSkin());
        SkindexRegistry.registerItem(new PackmasterBuilderSkin());
    }
    private static void getDuelistModSkinsToRegister(){
        SkindexRegistry.registerItem(new DuelistClassicYugiSkin());
        SkindexRegistry.registerItem(new DuelistKaibaSkin());
        SkindexRegistry.registerItem(new DuelistAnimatedYugiSkin());
        SkindexRegistry.registerItem(new DuelistAnimatedKaibaSkin());
    }


    private static void registerCardSkins() {
        if(ModManager.TheUnchained.isActive()) getTheUnchainedCardSkinsToRegister();
        if(ModManager.SkulHeroSlayer.isActive()) getSkulheroSlayerCardSkinsToRegister();
    }

    private static void getTheUnchainedCardSkinsToRegister(){
        UnchainedPixelCardSkinSet.registerCardSkins();
    }

    private static void getSkulheroSlayerCardSkinsToRegister(){
        LittleBoneElMuerteCardSkinSet.registerCardSkins();
        LittleBoneLittleHelperCardSkinSet.registerCardSkins();
        LittleBoneRoyalGuardCardSkinSet.registerCardSkins();
    }
}
