package com.drathonix.nuclearearthmod.mixins.industrialforegoing;


import com.buuz135.industrial.tile.generator.PitifulFuelGeneratorTile;
import com.drathonix.nuclearearthmod.MixinConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = PitifulFuelGeneratorTile.class, remap = false)
public class MixinPitifulFuelGeneratorTile {
    //TODO: This is a pretty lazy way of nerfing the PFG. 2024 Drathonix does not approve.
    @Inject(method = "getEnergyProduced",at = @At("HEAD"), cancellable = true)
    public void deoppify(int burnTime, CallbackInfoReturnable<Long> cir){
        cir.setReturnValue(MixinConfig.pitifulGeneratorRFT);
    }
}
