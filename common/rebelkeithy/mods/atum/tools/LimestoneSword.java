package rebelkeithy.mods.atum.tools;

import rebelkeithy.mods.atum.Atum;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class LimestoneSword extends ItemSword 
{

	public LimestoneSword(int par1, EnumToolMaterial par2EnumToolMaterial) 
	{
		super(par1, par2EnumToolMaterial);
	}

    /**
     * Return whether this item is repairable in an anvil.
     */
	@Override
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return par2ItemStack.itemID == Atum.atumCobble.blockID;
    }

    @Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		 itemIcon = par1IconRegister.registerIcon("Atum:LimestoneSword");
	}
}
