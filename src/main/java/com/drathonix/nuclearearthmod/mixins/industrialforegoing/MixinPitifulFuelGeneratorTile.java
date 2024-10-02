package com.drathonix.nuclearearthmod.mixins.industrialforegoing;


import com.drathonix.nuclearearthmod.MixinConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = com.buuz135.industrial.tile.generator.PitifulFuelGeneratorTile.class, remap = false)
public class MixinPitifulFuelGeneratorTile {
    //TODO: This overwrite is safe, however it is a pretty lazy way of nerfing the PFG. 2024 Drathonix does not approve.
    /**
     * @author Drathonix
     * @reason Brokenop
     */
    @Overwrite
    public long getEnergyProduced(int burnTime){
        return MixinConfig.pitifulGeneratorRFT;
    }
}
