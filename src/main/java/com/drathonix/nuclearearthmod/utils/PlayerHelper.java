package com.drathonix.nuclearearthmod.utils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.chunk.Chunk;

//This class allows me to avoid having to remap code. Its heavily redundant but I've had problems compiling without it.
public class PlayerHelper {
    public static Iterable<ItemStack> getEntityArmor(EntityLivingBase entity){
        return entity.getArmorInventoryList();
    }
    public static void attemptDamageItem(ItemStack item, int amount, EntityLivingBase entity){
        if(item.isItemStackDamageable()) {
            item.damageItem(amount, entity);
        }
    }
    public static Chunk getChunkFromEntity(Entity entity){
        return entity.getEntityWorld().getChunk(entity.getPosition());
    }
}
