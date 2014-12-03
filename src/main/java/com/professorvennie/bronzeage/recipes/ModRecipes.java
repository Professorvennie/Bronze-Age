package com.professorvennie.bronzeage.recipes;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * Created by ProfessorVennie on 11/14/2014 at 7:40 PM.
 */
public class ModRecipes {

    public static void init() {
        registerRecipes();
        registerSmelting();
    }

    private static void registerRecipes() {

    }

    private static void registerSmelting() {

    }

    private static void addOreDictRecipe(ItemStack output, Object... recipe) {
        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(output, recipe));
        GameRegistry.addRecipe(output, recipe);
    }

    private static void addShapelessOreDictRecipe(ItemStack output, Object... recipe) {
        CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(output, recipe));
        GameRegistry.addShapelessRecipe(output, recipe);
    }

    private static void addSmeltingRecipe(ItemStack input, ItemStack output) {
        GameRegistry.addSmelting(input, output, 0.8F);
    }
}
