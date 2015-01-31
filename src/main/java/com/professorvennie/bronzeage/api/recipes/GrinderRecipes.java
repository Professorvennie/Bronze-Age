package com.professorvennie.bronzeage.api.recipes;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ProfessorVennie on 1/23/2015 at 4:21 PM.
 */
public class GrinderRecipes {

    private static final GrinderRecipes INSTANCE = new GrinderRecipes();
    private Map grindingList = new HashMap();
    private Map experienceList = new HashMap();

    private GrinderRecipes() {
    }

    public static GrinderRecipes getInstance() {
        return INSTANCE;
    }

    public void addRecipe(Block input, ItemStack output, float xp) {
        this.addRecipe(Item.getItemFromBlock(input), output, xp);
    }

    public void addRecipe(Item input, ItemStack output, float xp) {
        this.addRecipe(new ItemStack(input, 1, 32767), output, xp);
    }

    public void addRecipe(ItemStack input, ItemStack output, float xp) {
        this.grindingList.put(input, output);
        this.experienceList.put(output, Float.valueOf(xp));
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

    public float getXp(ItemStack input) {
        float ret = input.getItem().getSmeltingExperience(input);
        if (ret != -1) return ret;

        Iterator iterator = this.experienceList.entrySet().iterator();
        Map.Entry entry;

        do {
            if (!iterator.hasNext()) {
                return 0.0F;
            }

            entry = (Map.Entry) iterator.next();
        }
        while (!this.matches(input, (ItemStack) entry.getKey()));

        return ((Float) entry.getValue()).floatValue();
    }
}
