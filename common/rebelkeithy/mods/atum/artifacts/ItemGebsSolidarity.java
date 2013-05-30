package rebelkeithy.mods.atum.artifacts;

import java.util.List;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.IArmorTextureProvider;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGebsSolidarity extends ItemArmor implements IArmorTextureProvider
{
	String texture;

	public ItemGebsSolidarity(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) 
	{
		super(par1, par2EnumArmorMaterial, par3, par4);
		//MinecraftForge.EVENT_BUS.register(this);
	}
	
	@ForgeSubscribe
	public void onLivingAttack(LivingHurtEvent event)
	{
		DamageSource par1DamageSource = event.source;
		if(par1DamageSource.getEntity() != null)
		{
			Entity par1Entity = par1DamageSource.getEntity();
			int j = 0;
	        if (par1Entity instanceof EntityLiving)
	        {
	            j += EnchantmentHelper.getKnockbackModifier((EntityLiving)par1Entity, event.entityLiving);
                System.out.println("undo knockback " + j);
	            if (j >= 0)
	            {
	                event.entityLiving.motionX /= 0.6D;
	                event.entityLiving.motionZ /= 0.6D;
	                event.entityLiving.addVelocity((double)(MathHelper.sin(par1Entity.rotationYaw * (float)Math.PI / 180.0F) * (float)j * 0.5F), -0.1D, (double)(-MathHelper.cos(par1Entity.rotationYaw * (float)Math.PI / 180.0F) * (float)j * 0.5F));
	            }
	        }
	        
	        EntityLiving player = event.entityLiving;
	        

            double d0 = par1DamageSource.getEntity().posX - player.posX;
            double d1;

            for (d1 = par1DamageSource.getEntity().posZ - player.posZ; d0 * d0 + d1 * d1 < 1.0E-4D; d1 = (Math.random() - Math.random()) * 0.01D)
            {
                d0 = (Math.random() - Math.random()) * 0.01D;
            }
	        
	        player.isAirBorne = true;
	        float f = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
	        float f1 = 0.2F;
            player.motionX += d0 / (double)f * (double)f1;
            //this.motionY += (double)f1;
            player.motionZ += d1 / (double)f * (double)f1;
	        player.motionX *= 2.0D;
	        player.motionY *= 2.0D;
	        player.motionZ *= 2.0D;

	        if (player.motionY > 0.4000000059604645D)
	        {
	            player.motionY = 0.4000000059604645D;
	        }

		}
	}
	
	
	
    /**
     * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
     * update it's contents.
     */
	@Override
    public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack)
    {
		double magnitude = Math.sqrt(player.motionX * player.motionX + player.motionZ * player.motionZ);
		player.landMovementFactor *= 0.5;
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
    		par3List.add(EnumChatFormatting.DARK_PURPLE + "Stalwart I: Decreased movement");
    		par3List.add(EnumChatFormatting.DARK_PURPLE + "speed, decreased knockback (WIP)");
    	} else {
        	par3List.add("Stalwart I " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
    	}
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
