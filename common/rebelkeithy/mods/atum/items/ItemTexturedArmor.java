package rebelkeithy.mods.atum.items;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;

public class ItemTexturedArmor extends ItemArmor implements IArmorTextureProvider
{

	private String textureFile;
	private int repairItemID = 0;

	public ItemTexturedArmor(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4)
	{
		super(par1, par2EnumArmorMaterial, par3, par4);
	}
	
	public ItemTexturedArmor setRepairItem(int id)
	{
		repairItemID = id;
		return this;
	}

    /**
     * Return whether this item is repairable in an anvil.
     */
	@Override
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return par2ItemStack.itemID == repairItemID;
    }
	
	public ItemTexturedArmor setTextureFile(String filename)
	{
		textureFile = filename;
		return this;
	}

	@Override
	public String getArmorTextureFile(ItemStack itemstack)
	{
		return "/armor/" + textureFile + ".png";
	}

}
