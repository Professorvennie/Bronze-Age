package com.professorvennie.bronzeage.items;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by ProfessorVennie on 10/21/2014 at 5:20 PM.
 */
public class ModItems {

    public static Item manual;
    public static ItemBase wrench;
    public static Item materials;

    public static void init() {
        manual = register(new ItemManual());
        wrench = new ItemWrench();
        GameRegistry.register(wrench);
        wrench.registerItemModel();
        materials = register(new ItemMaterials());
    }

    private static <T extends Item> T register(T item) {
        GameRegistry.register(item);

        if (item instanceof ItemBase) {
            ((ItemBase)item).registerItemModel();
        }
        return item;
    }
}
