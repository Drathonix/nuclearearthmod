package com.drathonix.nuclearearthmod.techreborn;

import net.minecraft.item.ItemStack;
import reborncore.api.praescriptum.ingredients.input.InputIngredient;
import reborncore.api.praescriptum.ingredients.input.ItemStackInputIngredient;
import reborncore.api.praescriptum.recipes.Recipe;
import reborncore.api.praescriptum.recipes.RecipeHandler;
import reborncore.common.util.ItemUtils;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

/**
 * Overridden variant of the centrifuge RecipeHandler. Actually cares about NBT, preventing bugs when storing cells.
 */
public class OverrideCentrifugeRecipeHandler extends RecipeHandler {
    public OverrideCentrifugeRecipeHandler() {
        super("centrifuge_overridden");
    }

    public Recipe findAndApply(Collection<ItemStack> itemStacks, boolean simulate) {
        Queue<InputIngredient<?>> ingredients = new ArrayDeque<>();
        for (ItemStack stack : itemStacks) {
            if (!ItemUtils.isEmpty(stack)) {
                ingredients.add(ItemStackInputIngredient.of(stack));
            }
        }

        if (ingredients.isEmpty()) {
            return null;
        } else {
            Recipe recipe = this.cachedRecipes.get(ingredients);
            if (recipe == null) {
                return null;
            } else if (ingredients.size() != recipe.getInputIngredients().size()) {
                return null;
            } else {
                Queue<InputIngredient<?>> queueA = new ArrayDeque<>(recipe.getInputIngredients());
                for (InputIngredient<?> entry : ingredients) {
                    queueA.removeIf((temp) -> temp.matchesStrict(entry.ingredient) && entry.getCount() >= temp.getCount());
                }
                if (!queueA.isEmpty()) {
                    return null;
                } else {
                    if (!simulate) {
                        Queue<InputIngredient<?>> queueB = new ArrayDeque<>(recipe.getInputIngredients());

                        for (InputIngredient<?> entry : ingredients) {
                            queueB.removeIf((temp) -> {
                                if (temp.matchesStrict(entry.ingredient) && entry.getCount() >= temp.getCount()) {
                                    entry.shrink(temp.getCount());
                                    return true;
                                } else {
                                    return false;
                                }
                            });
                        }
                    }

                    return recipe;
                }
            }
        }
    }
}