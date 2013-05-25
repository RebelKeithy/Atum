package rebelkeithy.mods.atum.artifacts;

import java.util.List;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemMafdetsQuickness extends Item 
{

	public ItemMafdetsQuickness(int par1) 
	{
		super(par1);
		this.setMaxDamage(20*60*20);
	}
	

    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) 
    {
    	if(par3Entity instanceof EntityPlayer)
    	{
    		EntityPlayer player = (EntityPlayer) par3Entity;
    		if(player.onGround)
    		{
    			if(player.inventory.mainInventory[player.inventory.currentItem] != null)
    			{
    				if(player.inventory.mainInventory[player.inventory.currentItem].itemID == this.itemID)
    				{
    					doEffect(player, par1ItemStack);
    				}
    			}
    		}
    	}
    }
    
    public void doEffect(EntityPlayer player, ItemStack item)
    {
		player.landMovementFactor *= 1.4;
		
		if(player.motionX*player.motionX+player.motionZ*player.motionZ > 0.02)
		{
			if(!player.capabilities.isCreativeMode)
			{
				if(item.getItemDamage() == 1)
					item.damageItem(1, player);
				else
					item.setItemDamage(item.getItemDamage()+1);
			}
		}
    }

    @SideOnly(Side.CLIENT)

    /**
     * Return an item rarity from EnumRarity
     */
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
    		par3List.add(EnumChatFormatting.DARK_PURPLE + "Swiftness I: You run");
    		par3List.add(EnumChatFormatting.DARK_PURPLE + "faster when held");
    	} else {
        	par3List.add("Swiftness I " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
    	}
    	
    	par3List.add(((int)((par1ItemStack.getMaxDamage()-par1ItemStack.getItemDamage())/12))/100.0 + " Minutes Remaining");
    }

}
