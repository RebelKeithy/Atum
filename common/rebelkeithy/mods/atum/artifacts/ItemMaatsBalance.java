package rebelkeithy.mods.atum.artifacts;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.IArmorTextureProvider;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import org.lwjgl.input.Keyboard;

import rebelkeithy.mods.atum.Atum;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMaatsBalance extends ItemArmor
{

	public String texture;
	public ItemMaatsBalance(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) 
	{
		super(par1, par2EnumArmorMaterial, par3, par4);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@ForgeSubscribe
	public void onLivingAttack(LivingHurtEvent event)
	{
		if(event.entityLiving.getCurrentArmor(3) != null)
		{
			if(event.entityLiving.getCurrentArmor(3).itemID == this.itemID)
			{
				event.ammount = (int) ((event.ammount + 1)/1.5F) - 1;
			}
		}
		
		if(event.source instanceof EntityDamageSource)
		{
			EntityDamageSource source = (EntityDamageSource) event.source;
			if(source.getEntity() != null && source.getEntity() instanceof EntityLiving)
			{
				EntityLiving entity = (EntityLiving) source.getEntity();
				if(entity.getCurrentArmor(3) != null)
				{
					if(entity.getCurrentArmor(3).itemID == this.itemID)
					{
						event.ammount = (int) ((event.ammount + 1)/1.5F) - 1;
					}
				}
			}
		}
	}

	
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) 
    {
    	//System.out.println("tick");
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
    		par3List.add(EnumChatFormatting.DARK_PURPLE + "Balance I: Decreases damage");
    		par3List.add(EnumChatFormatting.DARK_PURPLE + "dealt, Decreases damage taken");
    	} else {
        	par3List.add("Balance I " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
    	}
    }


	public Item setTextureFile(String string) 
	{
		texture = string;
		return this;
	}
	
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer)
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
