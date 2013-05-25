package rebelkeithy.mods.atum.artifacts;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import rebelkeithy.mods.atum.artifacts.arrow.EntityNutsCall;
import rebelkeithy.mods.atum.entities.EntityStoneSoldier;
import rebelkeithy.mods.atum.entities.projectiles.EntityFireSpearCombined;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityCrit2FX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ItemNutsCall extends Item
{

	public ItemNutsCall(int par1) 
	{
		super(par1);
		this.setMaxDamage(650);
		this.setMaxStackSize(1);
	}

    /**
     * How long it takes to use or consume an item
     */
	@Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 7200;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.bow;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns true if this item should be rotated by 180 degrees around the Y axis when being held in an entities
     * hands.
     */
    public boolean shouldRotateAroundWhenRendering()
    {
        return false;
    }

    /**
     * called when the player releases the use item button. Args: itemstack, world, entityplayer, itemInUseCount
     */
    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World world, EntityPlayer player, int par4)
    {
        int j = this.getMaxItemUseDuration(par1ItemStack) - par4;
        System.out.println("charge strength: " + j);
        
        if(j > 21)
        	j = 21;
        
        System.out.println("firing " + (j/36.0 + 0.25F));
        EntityNutsCall spear = new EntityNutsCall(world, player, j/42.0F + 0.25F);
        spear.setDamage(spear.getDamage() * 1.5F);
        spear.setStack(par1ItemStack);
        
        if (!world.isRemote)
        {
            System.out.println("firing2");
        	world.spawnEntityInWorld(spear);
        	world.updateEntity(spear);
        }
        
        par1ItemStack.damageItem(4, player);
        player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
    }
    
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
    	System.out.println("check");
    	player.setItemInUse(stack, getMaxItemUseDuration(stack));
    	
    	return stack;
    }
}
