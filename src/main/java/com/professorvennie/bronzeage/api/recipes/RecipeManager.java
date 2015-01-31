package com.professorvennie.bronzeage.api.recipes;

import net.minecraft.item.ItemStack;

/**
 * Created by ProfessorVennie on 1/19/2015 at 6:21 PM.
 */
public class RecipeManager {

    private static GrinderRecipes grinderRecipes = GrinderRecipes.getInstance();

    public static void addGrinderRecipe(ItemStack input, ItemStack output, float xp) {
        grinderRecipes.addRecipe(input, output, xp);
    }
}
