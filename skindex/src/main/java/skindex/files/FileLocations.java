package skindex.files;

import dLib.files.CommonFilePaths;

public class FileLocations {
    public static String skindexDirectory = CommonFilePaths.stsDirectory + "skindex/";

    public static String[] trackerFiles = {
        skindexDirectory + "unlocks0.skindex",
        skindexDirectory + "unlocks1.skindex",
        skindexDirectory + "unlocks2.skindex"
    };

    public static String skindexCreatorsDirectory = skindexDirectory + "creators/";
    public static String eulaFile = skindexCreatorsDirectory + "eula.txt";
}
