package com.drathonix.nuclearearthmod;

import com.vicious.persist.annotations.PersistentPath;
import com.vicious.persist.annotations.Save;
import com.vicious.persist.shortcuts.PersistShortcuts;

public class MixinConfig {
    @PersistentPath
    public static final String path = "config/nuclearearth/mixins.txt";

    @Save
    public static long pitifulGeneratorRFT = 20;
    @Save
    public static long petrifiedGeneratorRFT = 40;
    @Save
    public static boolean hazmatTakesDamageFromRadiation = true;

    static {
        PersistShortcuts.init(MixinConfig.class);
    }

}
