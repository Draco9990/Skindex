package skindex.modcompat.downfall.skins.player.gremlins;

import gremlin.patches.GremlinEnum;
import skindex.unlockmethods.FreeUnlockMethod;

public class GremlinsBaseSkin extends GremlinsAtlasSkin {
    /** Constructors */
    public GremlinsBaseSkin() {
        super(new SkinData());
    }

    /** Data */
    public static class SkinData extends GremlinsAtlasSkinData {
        public static String ID = "BASE";
        public SkinData(){
            gremlins.add(new AngryGremlinSkinData());
            gremlins.add(new FatGremlinSkinData());
            gremlins.add(new ShieldGremlinSkinData());
            gremlins.add(new SneakGremlinSkinData());
            gremlins.add(new WizardGremlinSkinData());
            gremlins.add(new NobGremlinSkinData());

            id = ID;
            name = "Base";

            unlockMethod = FreeUnlockMethod.methodId;
            playerClass = GremlinEnum.GREMLIN.name();

            isDefault = true;
        }

        /** Subclasses */
        public static class AngryGremlinSkinData extends GremlinAtlasSkinData{
            public AngryGremlinSkinData(){
                gremlinId = "angry";
                atlasUrl = "gremlinResources/images/char/angry/skeleton.atlas";
                skeletonUrl = "gremlinResources/images/char/angry/skeleton.json";
                resourceDirectoryUrl = "gremlinResources/images/char/angry/";
            }
        }
        public static class FatGremlinSkinData extends GremlinAtlasSkinData{
            public FatGremlinSkinData(){
                gremlinId = "fat";
                atlasUrl = "gremlinResources/images/char/fat/skeleton.atlas";
                skeletonUrl = "gremlinResources/images/char/fat/skeleton.json";
                resourceDirectoryUrl = "gremlinResources/images/char/fat/";
            }
        }
        public static class ShieldGremlinSkinData extends GremlinAtlasSkinData{
            public ShieldGremlinSkinData(){
                gremlinId = "shield";
                atlasUrl = "gremlinResources/images/char/shield/skeleton.atlas";
                skeletonUrl = "gremlinResources/images/char/shield/skeleton.json";
                resourceDirectoryUrl = "gremlinResources/images/char/shield/";
            }
        }
        public static class SneakGremlinSkinData extends GremlinAtlasSkinData{
            public SneakGremlinSkinData(){
                gremlinId = "sneak";
                atlasUrl = "gremlinResources/images/char/sneak/skeleton.atlas";
                skeletonUrl = "gremlinResources/images/char/sneak/skeleton.json";
                resourceDirectoryUrl = "gremlinResources/images/char/sneak/";
            }
        }
        public static class WizardGremlinSkinData extends GremlinAtlasSkinData{
            public WizardGremlinSkinData(){
                gremlinId = "wizard";
                atlasUrl = "gremlinResources/images/char/wizard/skeleton.atlas";
                skeletonUrl = "gremlinResources/images/char/wizard/skeleton.json";
                resourceDirectoryUrl = "gremlinResources/images/char/wizard/";
            }
        }
        public static class NobGremlinSkinData extends GremlinAtlasSkinData{
            public NobGremlinSkinData(){
                gremlinId = "nob";
                atlasUrl = "gremlinResources/images/char/nob/skeleton.atlas";
                skeletonUrl = "gremlinResources/images/char/nob/skeleton.json";
                resourceDirectoryUrl = "gremlinResources/images/char/nob/";
            }
        }
    }
}
