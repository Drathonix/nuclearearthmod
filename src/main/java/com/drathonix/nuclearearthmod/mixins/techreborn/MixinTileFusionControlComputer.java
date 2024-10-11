package com.drathonix.nuclearearthmod.mixins.techreborn;

import com.drathonix.nuclearearthmod.utils.RecipeValidator;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import reborncore.common.util.Inventory;
import techreborn.api.reactor.FusionReactorRecipe;
import techreborn.tiles.fusionReactor.TileFusionControlComputer;

import java.util.ArrayList;
import java.util.List;

@Mixin(value = TileFusionControlComputer.class, remap = false)
public class MixinTileFusionControlComputer {
    @Shadow
    public Inventory inventory;
    @Shadow
    int outputStackSlot;
    @Shadow
    public boolean canFitStack(ItemStack stack, int slot, boolean oreDic) {
        return false;
    }

    @Shadow
    int topStackSlot;

    @Shadow
    int bottomStackSlot;

    @Shadow
    long lastTick;

    @Shadow
    FusionReactorRecipe currentRecipe;

    /**
     * @author Drathonix
     * @reason 2024 Drathonix here. I know techreborn definitely has an issue with accounting for the number of items stored in the fusion reactor. This really is the simplest way of handling this. Overwrite should be safe. This also fixes issues with item order.
     */
    @Overwrite
    private boolean validateReactorRecipeInputs(FusionReactorRecipe recipe, ItemStack slot1, ItemStack slot2) {
        List<ItemStack> in = new ArrayList<>();
        in.add(slot1);
        in.add(slot2);
        List<ItemStack> expected = new ArrayList<>();
        expected.add(recipe.getBottomInput());
        expected.add(recipe.getTopInput());
        if(RecipeValidator.validate(in, expected)){
            return canFitStack(recipe.getOutput(), outputStackSlot, true);
        }
        return false;
    }

    /**
     * The following 4 redirects correct the reactor recipe handling to be slot-irrelevant.
     * Unfortunately this does have some unnecessary overhead, but its cleaner than the insane overwrite I wrote 4 years ago.
     */
    @Redirect(method = "func_73660_a",at = @At(value = "INVOKE", target = "Ltechreborn/tiles/fusionReactor/TileFusionControlComputer;func_70298_a(II)Lnet/minecraft/item/ItemStack;",ordinal = 0))
    public ItemStack decrInputTop0(TileFusionControlComputer instance, int j, int i){
        nrm$decrStackSize(currentRecipe.getTopInput());
        return  inventory.func_70301_a(topStackSlot);
    }

    @Redirect(method = "func_73660_a",at = @At(value = "INVOKE", target = "Ltechreborn/tiles/fusionReactor/TileFusionControlComputer;func_70298_a(II)Lnet/minecraft/item/ItemStack;",ordinal = 3))
    public ItemStack decrInputTop1(TileFusionControlComputer instance, int j, int i){
        nrm$decrStackSize(currentRecipe.getTopInput());
        return inventory.func_70301_a(topStackSlot);
    }

    @Redirect(method = "func_73660_a",at = @At(value = "INVOKE", target = "Ltechreborn/tiles/fusionReactor/TileFusionControlComputer;func_70298_a(II)Lnet/minecraft/item/ItemStack;",ordinal = 1))
    public ItemStack decrInputBottom0(TileFusionControlComputer instance, int j, int i){
        nrm$decrStackSize(currentRecipe.getBottomInput());
        return inventory.func_70301_a(topStackSlot);
    }

    @Redirect(method = "func_73660_a",at = @At(value = "INVOKE", target = "Ltechreborn/tiles/fusionReactor/TileFusionControlComputer;func_70298_a(II)Lnet/minecraft/item/ItemStack;",ordinal = 4))
    public ItemStack decrInputBottom1(TileFusionControlComputer instance, int j, int i){
        nrm$decrStackSize(currentRecipe.getBottomInput());
        return inventory.func_70301_a(topStackSlot);
    }

    @Inject(method = "onLoad",at = @At("TAIL"))
    public void setLastTick(CallbackInfo ci){
        lastTick=-1;
    }

    @Unique
    public void nrm$decrStackSize(ItemStack stack){
        if(inventory.func_70301_a(topStackSlot).isItemEqual(stack)) inventory.func_70301_a(topStackSlot).shrink(stack.getCount());
        if(inventory.func_70301_a(bottomStackSlot).isItemEqual(stack)) inventory.func_70301_a(bottomStackSlot).shrink(stack.getCount());
    }
}
