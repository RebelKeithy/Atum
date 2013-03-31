package rebelkeithy.mods.atum.artifacts;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.IArmorTextureProvider;

public class ItemNutsAgility extends ItemArmor implements IArmorTextureProvider
{
	String texture;

	public ItemNutsAgility(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) 
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
	
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) 
    {
    	/*
    	if(par2World.isRemote)
    		return;
    	
    	System.out.println("update");
    	if(par3Entity instanceof EntityPlayer)
    	{
    		if(((EntityPlayer)par3Entity).getCurrentArmor(3) != null)
    		{
	    		if(((EntityPlayer)par3Entity).getCurrentArmor(3).itemID == this.itemID)
	    		{
	    			if(!((EntityPlayer) par3Entity).isPotionActive(16))
	    				((EntityPlayer) par3Entity).addPotionEffect(new PotionEffect(16, 20, 0, true));
	    		}
    		}
    	}*/
    }

}
