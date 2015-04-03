package com.professorvennie.bronzeage.api.recipes;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ProfessorVennie on 3/8/2015 at 7:34 PM.
 */
public class ExtractorRecipes {

    private static final ExtractorRecipes INSTANCE = new ExtractorRecipes();
    private Map grindingList = new HashMap(), grindingWater = new HashMap();

    private ExtractorRecipes() {
    }

    public static ExtractorRecipes getInstance() {
        return INSTANCE;
    }

    public void addRecipe(Block input, ItemStack output, int waterUsage) {
        this.addRecipe(Item.getItemFromBlock(input), output, waterUsage);
    }

    public void addRecipe(Item input, ItemStack output, int waterUsage) {
        this.addRecipe(new ItemStack(input, 1, 32767), output, waterUsage);
    }

    public void addRecipe(ItemStack input, ItemStack output, int waterUsage) {
        this.grindingList.put(input, output);
        this.grindingWater.put(input, waterUsage);
    }

    public ItemStack getResult(ItemStack input) {
        Iterator iterator = this.grindingList.entrySet().iterator();
        Map.Entry entry;

        do {
            if (!iterator.hasNext()) {
                return null;
            }

            entry = (Map.Entry) iterator.next();
        }
        while (!this.matches(input, (ItemStack) entry.getKey()));

        return (ItemStack) entry.getValue();
    }

    private boolean matches(ItemStack input, ItemStack output) {
        return output.getItem() == input.getItem() && (output.getItemDamage() == 32767 || output.getItemDamage() == input.getItemDamage());
    }

    public Map getGrindingList() {
        return this.grindingList;
    }
}