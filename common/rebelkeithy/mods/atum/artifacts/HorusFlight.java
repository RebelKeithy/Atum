package rebelkeithy.mods.atum.artifacts;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.IArmorTextureProvider;

public class HorusFlight extends ItemArmor implements IArmorTextureProvider
{
	String texture;

	public HorusFlight(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) 
	{
		super(par1, par2EnumArmorMaterial, par3, par4);
		// TODO Auto-generated constructor stub
	}

	public Item setTextureFile(String string) 
	{
		texture = string;
		return this;
	}

	@Override
	public String getArmorTextureFile(ItemStack itemstack) 
	{
		return "/armor/" + texture + ".png";
	}

	@SideOnly(Side.CLIENT)

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
	@Override
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
    	ItemStack stack = new ItemStack(par1, 1, 0);
    	stack.addEnchantment(Enchantment.featherFalling, 10);
        par3List.add(stack);
    }

}
