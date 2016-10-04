package com.professorvennie.bronzeage.blocks.fluids;

import com.professorvennie.bronzeage.lib.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

/**
 * Created by snows on 10/3/2016.
 */
public class ModFluids {

    public static Fluid steam;

    public static void init(){
        steam = new Fluid("steam", new ResourceLocation(Reference.MOD_ID, ""),  new ResourceLocation(Reference.MOD_ID, ""));
        FluidRegistry.registerFluid(steam);
    }
}
