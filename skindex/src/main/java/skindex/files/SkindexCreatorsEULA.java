package skindex.files;

import dLib.files.JsonDataFile;

public class SkindexCreatorsEULA extends JsonDataFile {
    /** Singleton */
    public static SkindexCreatorsEULA instance;
    public static SkindexCreatorsEULA get(){
        if(instance == null){
            instance = load();
        }
        return instance;
    }

    /** Variables */
    public static boolean eulaAccepted = false;

    /** Constructors */
    public SkindexCreatorsEULA() {
        super(FileLocations.eulaFile);
    }

    /** Loading */
    public static SkindexCreatorsEULA load(){
        SkindexCreatorsEULA eula = (SkindexCreatorsEULA) load(FileLocations.eulaFile, SkindexCreatorsEULA.class);
        if(eula == null){
            eula = new SkindexCreatorsEULA();
            eula.save();
        }
        return eula;
    }

    /** Misc Methods */
    public void accept(){
        eulaAccepted = true;
        save();
    }
}
