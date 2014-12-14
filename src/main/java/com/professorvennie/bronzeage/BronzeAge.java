package com.professorvennie.bronzeage;

import com.professorvennie.bronzeage.blocks.ModBlocks;
import com.professorvennie.bronzeage.client.handlers.HudHandler;
import com.professorvennie.bronzeage.core.creativetab.CreativeTab;
import com.professorvennie.bronzeage.core.handlers.GuiHandler;
import com.professorvennie.bronzeage.core.proxeys.CommonProxey;
import com.professorvennie.bronzeage.items.ModItems;
import com.professorvennie.bronzeage.lib.BookData;
import com.professorvennie.bronzeage.lib.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by ProfessorVennie on 10/21/2014 at 5:15 PM.
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class BronzeAge {

    @Mod.Instance
    public static BronzeAge INSTANSE;

    @SidedProxy(clientSide = Reference.CLIENT_PROXEY, serverSide = Reference.COMMON_PROXEY)
    public static CommonProxey proxey;

    public static CreativeTabs tabMain = new CreativeTab(Reference.MOD_ID);

    public static GuiHandler guiHandler = new GuiHandler();

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        ModBlocks.init();
        ModItems.init();
        BookData.initPages();

        proxey.registerRenders();
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(Reference.MOD_ID, guiHandler);
        MinecraftForge.EVENT_BUS.register(new HudHandler());
    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {

    }
}
