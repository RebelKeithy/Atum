package rebelkeithy.mods.atum.artifacts;

import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityCrit2FX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMonthusStrike extends ItemAxe 
{

	public ItemMonthusStrike(int par1, EnumToolMaterial par2EnumToolMaterial) 
	{
		super(par1, par2EnumToolMaterial);
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

    /**
     * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
     * update it's contents.
     */
    public void onUpdate(ItemStack stack, World world, Entity player, int par4, boolean par5) 
    {
    	/*
    	if(world.isRemote)
    	{
    		Random rand = new Random();
    		
    		int angle = 40;
    		double x = -MathHelper.sin((player.rotationYaw + angle) * (float)Math.PI / 180.0F) * 0.4;
    		double z = (double)(MathHelper.cos((player.rotationYaw + angle) * (float)Math.PI / 180.0F)) * 0.4;
    		world.spawnParticle("flame", player.posX+x, player.posY, player.posZ+z, 0.0, 0.0D, 0.0);
    	}
    	*/
    }

    /**
     * called when the player releases the use item button. Args: itemstack, world, entityplayer, itemInUseCount
     */
    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World world, EntityPlayer player, int par4)
    {
        int j = this.getMaxItemUseDuration(par1ItemStack) - par4;
        System.out.println("charge strength: " + j);
        
        if(j <= 21)
        	return;
        
        AxisAlignedBB bb = player.boundingBox.copy();
        bb = bb.expand(2.5, 2.5, 2.5);
        
        List<Entity> list = world.getEntitiesWithinAABB(EntityLiving.class, bb);
        
        for(Entity entity : list)
        {
        	if(entity != player)
        	{
        		double dx = entity.posX - player.posX;
        		double dz = entity.posZ - player.posZ;
        		double magnitude = Math.sqrt(dx*dx + dz*dz);
        		dx = dx/magnitude;
        		dz = dz/magnitude;
        		
        		entity.isAirBorne = true;
        		entity.addVelocity(dx*2, 0.3D, dz*2);
        		//entity.motionY += 2;
        		
                if (entity.motionY > 0.4000000059604645D)
                {
                	entity.motionY = 0.4000000059604645D;
                }
                
                ((EntityLiving)entity).attackEntityFrom(DamageSource.generic, this.getDamageVsEntity(entity));
                
                Minecraft.getMinecraft().effectRenderer.addEffect(new EntityCrit2FX(world, entity));
        	}
        }
        
        par1ItemStack.damageItem(4, player);
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
    	par3List.add("Slam I");
    }	

    /**
     * Returns the damage against a given entity.
     */
	@Override
    public int getDamageVsEntity(Entity par1Entity)
    {
        return 4 + toolMaterial.getDamageVsEntity();
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
