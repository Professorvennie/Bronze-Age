package com.professorvennie.bronzeage.recipes;

import com.professorvennie.bronzeage.blocks.ModBlocks;
import com.professorvennie.bronzeage.items.ItemMaterials;
import com.professorvennie.bronzeage.items.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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
        addOreDictRecipe(new ItemStack(ModItems.materials, 2, ItemMaterials.BRONZE), "CC ", "CT ", "   ", 'C', new ItemStack(ModItems.materials, 1, ItemMaterials.COPPER), 'T', new ItemStack(ModItems.materials, 1, ItemMaterials.TIN));
        addOreDictRecipe(new ItemStack(ModBlocks.steamBoilerIdle), "BBB", "bFb", "BBB", 'B', new ItemStack(ModItems.materials, 1, ItemMaterials.BRONZE), 'b', new ItemStack(Items.bucket), 'F', new ItemStack(Blocks.furnace));
    }

    private static void registerSmelting() {
        addSmeltingRecipe(new ItemStack(ModBlocks.ore, 1, 0), new ItemStack(ModItems.materials, 1, ItemMaterials.COPPER));
        addSmeltingRecipe(new ItemStack(ModBlocks.ore, 1, 1), new ItemStack(ModItems.materials, 1, ItemMaterials.TIN));

        addSmeltingRecipe(new ItemStack(ModItems.materials, 1, ItemMaterials.CLEAN_CHUNK_COPPER), new ItemStack(ModItems.materials, 1, ItemMaterials.COPPER));
        addSmeltingRecipe(new ItemStack(ModItems.materials, 1, ItemMaterials.CLEAN_CHUNK_TIN), new ItemStack(ModItems.materials, 1, ItemMaterials.TIN));
        addSmeltingRecipe(new ItemStack(ModItems.materials, 1, ItemMaterials.CLEAN_CHUNK_IRON), new ItemStack(Items.iron_ingot));
        addSmeltingRecipe(new ItemStack(ModItems.materials, 1, ItemMaterials.CLEAN_CHUNK_GOLD), new ItemStack(Items.gold_ingot));
        addSmeltingRecipe(new ItemStack(ModItems.materials, 1, ItemMaterials.CLEAN_CHUNK_SILVER), new ItemStack(ModItems.materials, 1, ItemMaterials.SILVER));
        addSmeltingRecipe(new ItemStack(ModItems.materials, 1, ItemMaterials.CLEAN_CHUNK_LEAD), new ItemStack(ModItems.materials, 1, ItemMaterials.LEAD));

        addSmeltingRecipe(new ItemStack(ModItems.materials, 1, ItemMaterials.DUST_COPPER), new ItemStack(ModItems.materials, 1, ItemMaterials.COPPER));
        addSmeltingRecipe(new ItemStack(ModItems.materials, 1, ItemMaterials.DUST_TIN), new ItemStack(ModItems.materials, 1, ItemMaterials.TIN));
        addSmeltingRecipe(new ItemStack(ModItems.materials, 1, ItemMaterials.DUST_IRON), new ItemStack(Items.iron_ingot));
        addSmeltingRecipe(new ItemStack(ModItems.materials, 1, ItemMaterials.DUST_GOLD), new ItemStack(Items.gold_ingot));
        addSmeltingRecipe(new ItemStack(ModItems.materials, 1, ItemMaterials.DUST_SILVER), new ItemStack(ModItems.materials, 1, ItemMaterials.SILVER));
        addSmeltingRecipe(new ItemStack(ModItems.materials, 1, ItemMaterials.DUST_LEAD), new ItemStack(ModItems.materials, 1, ItemMaterials.LEAD));
    }

    private static void addOreDictRecipe(ItemStack output, Object... recipe) {
        GameRegistry.addRecipe(new ShapedOreRecipe(output, true, recipe));
    }

    private static void addShapelessOreDictRecipe(ItemStack output, Object... recipe) {
        CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(output, recipe));
        GameRegistry.addShapelessRecipe(output, recipe);
    }

    private static void addSmeltingRecipe(ItemStack input, ItemStack output) {
        GameRegistry.addSmelting(input, output, 0.8F);
    }
}