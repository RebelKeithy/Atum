package rebelkeithy.mods.atum.artifacts;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityCrit2FX;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;

import org.lwjgl.input.Keyboard;

import rebelkeithy.mods.atum.entities.EntityStoneSoldier;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemNusFlux extends ItemSword 
{

	public ItemNusFlux(int par1, EnumToolMaterial par2EnumToolMaterial) 
	{
		super(par1, par2EnumToolMaterial);
	}
	
	@Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLiving entity, EntityLiving player)
    {
		if(!player.worldObj.isRemote && Math.random() > 0.75 && !(entity instanceof EntityStoneSoldier))
		{
			double dx = entity.posX - player.posX;
			double dz = entity.posZ - player.posZ;
			double magnitude = Math.sqrt(dx*dx + dz*dz);
			dx = dx/magnitude;
			dz = dz/magnitude;
			
			entity.isAirBorne = true;
			entity.addVelocity(dx/2.0, 1D, dz/2.0);
			
	        if (entity.motionY > 1.0D)
	        {
	        	entity.motionY = 1.0D;
	        }
	        
	        ((EntityLiving)entity).attackEntityFrom(DamageSource.generic, this.getDamageVsEntity(entity));
	        
	        Minecraft.getMinecraft().effectRenderer.addEffect(new EntityCrit2FX(player.worldObj, entity));
		}
		return super.hitEntity(par1ItemStack, entity, player);
    }

    @SideOnly(Side.CLIENT)
    /**
     * Return an item rarity from EnumRarity
     */
    @Override
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return EnumRarity.rare;
    }

    @SideOnly(Side.CLIENT)
    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) 
    {
    	if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
    	{
    		par3List.add(EnumChatFormatting.DARK_PURPLE + "Sweep I: Chance to launch");
    		par3List.add(EnumChatFormatting.DARK_PURPLE + "foes into the air");
    	} else {
        	par3List.add("Sweep I " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
    	}
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
