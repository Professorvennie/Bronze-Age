package com.professorvennie.bronzeage.core.config;

import com.professorvennie.bronzeage.lib.Reference;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ProfessorVennie on 12/19/2014 at 11:00 PM.
 */
public class ConfigGui extends GuiConfig {

    public ConfigGui(GuiScreen parentScreen) {
        super(parentScreen, getConfigElements(parentScreen), Reference.MOD_ID, false, false, "Bronze Age Configuration");
    }

    private static List<IConfigElement> getConfigElements(GuiScreen parentScreen) {
        List<IConfigElement> elements = new ArrayList<IConfigElement>();

        elements.add(new ConfigElement<ConfigCategory>(ConfigHandler.config.getCategory("blocks".toLowerCase())));
        elements.add(new ConfigElement<ConfigCategory>(ConfigHandler.config.getCategory("items".toLowerCase())));

        return elements;
    }
}
