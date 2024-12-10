package com.drathonix.nuclearearthmod.utils;



import com.drathonix.nuclearearthmod.NEMod;
import net.minecraft.item.ItemStack;

import java.util.List;

public class RecipeValidator {
    public static boolean validate(List<ItemStack> actual, List<ItemStack> inputs){
        l1: for (ItemStack input : inputs) {
            for (ItemStack act : actual) {
                if(itemEqualsAndSufficient(act,input)){
                    continue l1;
                }
            }
            return false;
        }
        return true;
    }
    public static boolean itemEqualsAndSufficient(ItemStack in, ItemStack expected){
        if(in.getCount() < expected.getCount()){
            return false;
        }
        if(!in.getItem().equals(expected.getItem())){
            return false;
        }
        if(in.getMetadata() != expected.getMetadata()){
           return false;
        }
        return in.getTagCompound() != null ? in.getTagCompound().equals(expected.getTagCompound()) : expected.getTagCompound() == null;
    }
}