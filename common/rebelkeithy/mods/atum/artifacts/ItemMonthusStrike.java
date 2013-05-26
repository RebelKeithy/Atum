package rebelkeithy.mods.atum.artifacts;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityCrit2FX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import rebelkeithy.mods.atum.entities.EntityStoneSoldier;
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
        //System.out.println("charge strength: " + j);
        
        if(j <= 21)
        	return;
        
        AxisAlignedBB bb = player.boundingBox.copy();
        bb = bb.expand(3, 3, 3);
        
        List<Entity> list = world.getEntitiesWithinAABB(EntityLiving.class, bb);
        
        for(Entity entity : list)
        {
        	if(entity != player && !(entity instanceof EntityStoneSoldier))
        	{
        		double dx = entity.posX - player.posX;
        		double dz = entity.posZ - player.posZ;
        		double magnitude = Math.sqrt(dx*dx + dz*dz);
        		dx = dx/magnitude;
        		dz = dz/magnitude;
        		
        		entity.isAirBorne = true;
        		entity.addVelocity(dx*2.5, 0.3D, dz*2.5);
        		//entity.motionY += 2;
        		
                if (entity.motionY > 0.4000000059604645D)
                {
                	entity.motionY = 0.4000000059604645D;
                }
                
                ((EntityLiving)entity).attackEntityFrom(DamageSource.generic, this.getDamageVsEntity(entity));
                
                if(world.isRemote)
                    spawnParticle(world, entity);
        	}
        }
        
        par1ItemStack.damageItem(4, player);
    }

    @SideOnly(Side.CLIENT)
    public void spawnParticle(World world, Entity entity)
    {
        Minecraft.getMinecraft().effectRenderer.addEffect(new EntityCrit2FX(world, entity));
    }
    
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
    	player.setItemInUse(stack, getMaxItemUseDuration(stack));
    	
    	return stack;
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
    		par3List.add(EnumChatFormatting.DARK_PURPLE + "Slam I: Increased damage,");
    		par3List.add(EnumChatFormatting.DARK_PURPLE + "charge for AOE knockback");
    	} else {
        	par3List.add("Slam I " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
    	}
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
