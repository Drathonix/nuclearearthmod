package com.drathonix.nuclearearthmod.mixins.nuclearcraft;

import com.drathonix.nuclearearthmod.MixinConfig;
import com.drathonix.nuclearearthmod.NEMod;
import com.drathonix.nuclearearthmod.utils.PlayerHelper;
import nc.capability.radiation.entity.IEntityRads;
import nc.radiation.RadiationHelper;
import net.minecraft.entity.EntityLivingBase;
import nc.util.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import nc.init.NCArmor;
import nc.capability.radiation.source.IRadiationSource;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static nc.config.NCConfig.radiation_rain_mult;
import static nc.config.NCConfig.radiation_swim_mult;
import static nc.radiation.RadiationHelper.getRadiationSource;

@Mixin(value = RadiationHelper.class, remap = false)
public class MixinRadiationHelper {
    /*
    /**
     * Originally an overwrite in NERTweaker, now changed to an @Inject. 2024 Drathon knows what's up.
     */
    /*@Inject(method = "addRadsToEntity",at = @At(value = "RETURN"))
    private static void applyRadsDamageToHazmat(IEntityRads entityRads, EntityLivingBase entity, double rawRadiation, boolean ignoreResistance, boolean ignoreMultipliers, int updateRate, CallbackInfoReturnable<Double> cir){
        if(cir.getReturnValue() <= 0.0){
            return;
        }
        if(!MixinConfig.hazmatTakesDamageFromRadiation){
            return;
        }
        NEMod.logger.info("FUCK YOU");
        if(entity instanceof EntityPlayer) {
            IRadiationSource chunkSource = getRadiationSource(PlayerHelper.getChunkFromEntity(entity));
            double radiationLevel = chunkSource.getRadiationLevel();
            if(radiationLevel < 3){
                for (ItemStack armor : PlayerHelper.getEntityArmor(entity)) {
                    if(armor.getItem() instanceof ItemArmor){
                        if(((ItemArmor) armor.getItem()).getArmorMaterial().equals(NCArmor.HAZMAT)){
                            if(radiationLevel/3 > Math.random()) {
                                PlayerHelper.attemptDamageItem(armor, 1, entity);
                            }
                        }
                    }
                }
            }
            else {
                for (ItemStack armor : PlayerHelper.getEntityArmor(entity)) {
                    if (armor.getItem() instanceof ItemArmor) {
                        if (((ItemArmor) armor.getItem()).getArmorMaterial().equals(NCArmor.HAZMAT)) {
                            PlayerHelper.attemptDamageItem(armor, (int) (radiationLevel / 3), entity);
                        }
                    }
                }
            }
        }
    }*/

    /**
     * @author Drathonix
     * @reason Makes hazmat take damage to radiation.
     */
    @Overwrite
    public static double addRadsToEntity(IEntityRads entityRads, EntityLivingBase entity, double rawRadiation, boolean ignoreResistance, boolean ignoreMultipliers, int updateRate) {
        if (rawRadiation <= 0D) {
            return 0D;
        }
        if (!ignoreMultipliers) {
            if (entity.isInWater()) {
                rawRadiation *= radiation_swim_mult;
            }
            else if (radiation_rain_mult != 1D && entity.isWet()) {
                rawRadiation *= radiation_rain_mult;
            }
        }
        if(entity instanceof EntityPlayer) {
            IRadiationSource chunkSource = getRadiationSource(PlayerHelper.getChunkFromEntity(entity));
            double radiationLevel = chunkSource.getRadiationLevel();
            if(radiationLevel < 3){
                for (ItemStack armor : PlayerHelper.getEntityArmor(entity)) {
                    if(armor.getItem() instanceof ItemArmor){
                        if(((ItemArmor) armor.getItem()).getArmorMaterial().equals(NCArmor.HAZMAT)){
                            if(radiationLevel/3 > Math.random()) {
                                PlayerHelper.attemptDamageItem(armor, 1, entity);
                            }
                        }
                    }
                }
            }
            else
                for (ItemStack armor : PlayerHelper.getEntityArmor(entity)) {
                    if(armor.getItem() instanceof ItemArmor){
                        if(((ItemArmor) armor.getItem()).getArmorMaterial().equals(NCArmor.HAZMAT)){
                            PlayerHelper.attemptDamageItem(armor, (int)(radiationLevel/3), entity);
                        }
                    }
                }
        }
        double resistance = ignoreResistance ? Math.min(0D, entityRads.getInternalRadiationResistance()) : entityRads.getFullRadiationResistance();
        double addedRadiation = resistance > 0D ? NCMath.square(rawRadiation)/(rawRadiation + resistance)  : rawRadiation * (1D - resistance);
        entityRads.setTotalRads(entityRads.getTotalRads() + addedRadiation * updateRate, true);
        return addedRadiation;
    }
}
