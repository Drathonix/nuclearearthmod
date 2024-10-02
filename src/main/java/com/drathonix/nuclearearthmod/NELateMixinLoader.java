package com.drathonix.nuclearearthmod;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NELateMixinLoader implements ILateMixinLoader {
    @Override
    public List<String> getMixinConfigs() {
        List<String> out = new ArrayList<>();
        out.add("mixins.mod.industrialforegoing.json");
        out.add("mixins.mod.techreborn.json");
        out.add("mixins.mod.nuclearcraft.json");
        return out;
    }

    @Override
    public boolean shouldMixinConfigQueue(String mixinConfig) {
        String mid = mixinConfig.replace("mixins.mod.","").replace(".json","");
        //TODO: account for NCO inevitable issues.
        if(mid.equals("nuclearcraft")){

        }
        else if(mid.equals("nuclearcrafto")){

        }
        return Loader.isModLoaded(mid);
    }
}
