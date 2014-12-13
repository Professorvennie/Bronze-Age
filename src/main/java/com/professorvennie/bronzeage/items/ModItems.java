package com.professorvennie.bronzeage.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * Created by ProfessorVennie on 10/21/2014 at 5:20 PM.
 */
public class ModItems {

    public static Item manual;
    public static Item wrench;
    public static Item materials;

    public static void init() {
        manual = new ItemManual();
        wrench = new ItemWrench();
        materials = new ItemMaterials();

        registerItem(materials);
        registerItem(manual);
        registerItem(wrench);
    }

    private static void registerItem(Item item) {
        GameRegistry.registerItem(item, item.getUnlocalizedName());
    }
}
