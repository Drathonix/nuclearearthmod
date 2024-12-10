package com.drathonix.nuclearearthmod.world;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;

public class WorldTypeNuclearEarth extends WorldType {
    /**
     * Creates a new world type, the ID is hidden and should not be referenced by modders.
     * It will automatically expand the underlying workdType array if there are no IDs left.
     */
    public WorldTypeNuclearEarth() {
        super("nuclear_earth");
    }

    @Override
    public BiomeProvider getBiomeProvider(World world) {
        return super.getBiomeProvider(world);
    }

    @Override
    public IChunkGenerator getChunkGenerator(World world, String generatorOptions) {
        return super.getChunkGenerator(world, generatorOptions);
    }

    @Override
    public boolean isCustomizable() {
        return false;
    }
}
