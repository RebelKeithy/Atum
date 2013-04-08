package rebelkeithy.mods.atum.items;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;

public class ItemTexturedArmor extends ItemArmor implements IArmorTextureProvider
{

	private String textureFile;

	public ItemTexturedArmor(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4)
	{
		super(par1, par2EnumArmorMaterial, par3, par4);
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
