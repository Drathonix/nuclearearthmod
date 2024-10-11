package com.drathonix.nuclearearthmod;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = NEMod.MODID,
        name = NEMod.NAME,
        version = NEMod.VERSION,
        dependencies = "after:industrialforegoing")
public class NEMod
{
    public static final String MODID = "nuclearearthmod";
    public static final String NAME = "Nuclear Earth Mod";
    public static final String VERSION = "1.0.0";

    public static Logger logger = LogManager.getLogger("nuclearearth");
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        logger.info("Drathonix' Nuclear Earth Mod Initialized!");
    }
}
