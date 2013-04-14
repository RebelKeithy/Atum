package rebelkeithy.mods.atum.artifacts;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.World;
import net.minecraftforge.common.IArmorTextureProvider;

public class ItemSekhmetsWrath extends ItemArmor implements IArmorTextureProvider
{
	String texture;

	public ItemSekhmetsWrath(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) 
	{
		super(par1, par2EnumArmorMaterial, par3, par4);
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public String getItemDisplayName(ItemStack par1ItemStack)
    {
        return (EnumChatFormatting.AQUA + StringTranslate.getInstance().translateNamedKey(this.getLocalizedName(par1ItemStack))).trim();
    }

    @SideOnly(Side.CLIENT)

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) 
    {
    	par3List.add("Fire Resistance I");
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

    /**
     * Return whether this item is repairable in an anvil.
     */
	@Override
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return par2ItemStack.itemID == Item.diamond.itemID;
    }

}
