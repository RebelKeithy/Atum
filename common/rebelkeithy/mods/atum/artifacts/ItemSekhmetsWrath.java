package rebelkeithy.mods.atum.artifacts;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.IArmorTextureProvider;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSekhmetsWrath extends ItemArmor implements IArmorTextureProvider
{
	String texture;

	public ItemSekhmetsWrath(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) 
	{
		super(par1, par2EnumArmorMaterial, par3, par4);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@ForgeSubscribe
	public void onLivingAttack(LivingAttackEvent event)
	{
		if(event.entityLiving.getCurrentArmor(2) != null)
		{
			if(event.entityLiving.getCurrentArmor(2).itemID == this.itemID)
			{
				if(event.source instanceof EntityDamageSource)
				{
					EntityDamageSource source = (EntityDamageSource) event.source;
					if(source.getEntity() != null)
					{
						if(Math.random() > 0.5)
						{
							source.getEntity().setFire(10);
						}
					}
				}
			}
		}
	}
	
	@ForgeSubscribe
	public void onLivingAttack(LivingHurtEvent event)
	{
		if(event.entityLiving.getCurrentArmor(2) != null)
		{
			if(event.entityLiving.getCurrentArmor(2).itemID == this.itemID)
			{
				if(event.source.isFireDamage())
				{
					event.ammount = event.ammount / 2;
					if(event.ammount == 0 && Math.random() > 0.5)
					{
						event.ammount = 1;
					}
				}
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
    		par3List.add(EnumChatFormatting.DARK_PURPLE + "Immolation I: Protection from fire,");
    		par3List.add(EnumChatFormatting.DARK_PURPLE + "chance to ignite attackers");
    	} else {
        	par3List.add("Immolation I " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
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
