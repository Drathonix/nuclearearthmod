package com.drathonix.nuclearearthmod;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = NERTweaks.MODID,
        name = NERTweaks.NAME,
        version = NERTweaks.VERSION,
        dependencies = "after:industrialforegoing")
public class NERTweaks
{
    public static final String MODID = "nertweaks";
    public static final String NAME = "Nuclear Earth Tweaker";
    public static final String VERSION = "1.0.3";

    private static Logger logger;
    private static boolean isIndustrialForegoingLoaded = false;
    private static boolean isNuclearcraftLoaded = false;
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        if(Loader.isModLoaded("industrialforegoing")){
            isIndustrialForegoingLoaded = true;
        }
        if(Loader.isModLoaded("nuclearcraft")){
            isNuclearcraftLoaded = true;
        }
        logger.info("Drathonix's Nuclear Earth Mod Initialized!");
    }
}
