package com.drathonix.nuclearearthmod.mixins.techreborn;

import com.drathonix.nuclearearthmod.NEMod;
import com.drathonix.nuclearearthmod.techreborn.OverrideCentrifugeRecipeHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import reborncore.api.praescriptum.recipes.RecipeHandler;
import techreborn.api.recipe.Recipes;
import techreborn.init.recipes.CentrifugeRecipes;

/**
 * Fixes the centrifuge incorrectly handling NBT.
 */
@Mixin(CentrifugeRecipes.class)
public class MixinCentrifugeRecipes {
    @Inject(remap=false, method = "init",at = @At(value = "INVOKE", ordinal=0, target = "Lreborncore/api/praescriptum/recipes/RecipeHandler;createRecipe()Lreborncore/api/praescriptum/recipes/Recipe;",shift = At.Shift.BEFORE))
    private static void overwriteRecipeHandler(CallbackInfo ci){
        Recipes.centrifuge=new OverrideCentrifugeRecipeHandler();
        NEMod.logger.info("Replaced the Tech Reborn centrifuge recipe handler: " + Recipes.centrifuge);
    }
}
