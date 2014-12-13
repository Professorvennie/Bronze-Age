package com.professorvennie.bronzeage.items;

import com.professorvennie.bronzeage.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

/**
 * Created by ProfessorVennie on 12/6/2014 at 11:16 AM.
 */
public class ItemMaterials extends ItemBase {

    private final String[] NAMES = new String[]{"ingotCopper", "ingotTin", "ingotSilver", "ingotLead", "ingotBronze", "dirtyChunkCopper", "dirtyChunkTin", "dirtyChunkIron", "dirtyChunkGold", "dirtyChunkSilver", "dirtyChunkLead", "cleanChunkCopper", "cleanChunkTin", "cleanChunkIron", "cleanChunkGold", "cleanChunkSilver", "cleanChunkLead", "dustCopper", "dustTin", "dustIron", "dustGold", "dustSilver", "dustLead", "dustBronze"};
    private IIcon[] icons;

    public ItemMaterials() {
        super("material");
        setHasSubtypes(true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < NAMES.length; i++)
            list.add(new ItemStack(item, 1, i));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return super.getUnlocalizedName(itemStack) + "." + NAMES[itemStack.getItemDamage()];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        icons = new IIcon[NAMES.length];
        for (int i = 0; i < NAMES.length; i++)
            icons[i] = iconRegister.registerIcon(Reference.MOD_ID + ":materials/" + NAMES[i]);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int i) {
        return i < icons.length ? icons[i] : icons[0];
    }
}
