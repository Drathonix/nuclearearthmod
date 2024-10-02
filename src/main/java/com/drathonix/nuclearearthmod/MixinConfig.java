package com.drathonix.nuclearearthmod;

import com.vicious.persist.annotations.PersistentPath;
import com.vicious.persist.annotations.Save;

public class MixinConfig {
    @PersistentPath
    public static final String path = "config/nuclearearth/mixins.txt";

    @Save
    public static int pitifulGeneratorRFT = 20;
    @Save
    public static int petrifiedGeneratorRFT = 40;
    @Save
    public static boolean hazmatTakesDamageFromRadiation = true;

}
