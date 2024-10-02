package com.drathonix.nuclearearthmod.utils;



import net.minecraft.item.ItemStack;

import java.util.List;

public class RecipeValidator {
    public static boolean validate(List<ItemStack> stacks, List<ItemStack> inputs){
        for(ItemStack i : stacks){
            boolean itemvalid = false;
            for(ItemStack ex : inputs){
                if(itemEqualsAndSufficient(i, ex)) {
                    itemvalid = true;
                    break;
                }
            }
            if(!itemvalid){
                return false;
            }
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
        return in.getTagCompound() != null ? in.getTagCompound().equals(expected.getTagCompound()) : expected.getTagCompound() == null;
    }
}