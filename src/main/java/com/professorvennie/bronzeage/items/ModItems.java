package com.professorvennie.bronzeage.items;

import com.professorvennie.bronzeage.api.BronzeAgeAPI;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * Created by ProfessorVennie on 10/21/2014 at 5:20 PM.
 */
public class ModItems {

    public static Item manual;
    public static Item stoneWrench;
    public static Item tinWrench;

    public static void init() {
        manual = new ItemManual();
        stoneWrench = new ItemWrench(BronzeAgeAPI.getInstance().stoneWrenchMaterial);
        tinWrench = new ItemWrench(BronzeAgeAPI.getInstance().tinWrenchMaterial);

        registerItem(manual);
        registerItem(stoneWrench);
        registerItem(tinWrench);
    }

    private static void registerItem(Item item) {
        GameRegistry.registerItem(item, item.getUnlocalizedName());
    }
}
