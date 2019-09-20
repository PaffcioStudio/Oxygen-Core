package austeretony.oxygen_core.common.api;

import austeretony.oxygen_core.common.config.ConfigHolder;
import austeretony.oxygen_core.common.config.ConfigManager;

public class OxygenHelperCommon {

    //*** initialization - start

    public static void registerConfig(ConfigHolder configHolder) {
        ConfigManager.instance().registerConfig(configHolder);
    }

    //*** initialization - end

    public static String getConfigFolder() {
        return CommonReference.getGameFolder() + "/config/oxygen/";
    }
}