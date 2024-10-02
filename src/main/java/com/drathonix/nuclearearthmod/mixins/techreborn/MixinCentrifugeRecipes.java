package com.drathonix.nuclearearthmod.mixins.techreborn;

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
    @Inject(method = "init",at = @At(value = "INVOKE", target = "Lreborncore/api/praescriptum/recipes/RecipeHandler;<init>(Ljava/lang/String;)V",shift = At.Shift.AFTER))
    private static void overwriteRecipeHandler(CallbackInfo ci){
        Recipes.centrifuge=new OverrideCentrifugeRecipeHandler();
    }
}
