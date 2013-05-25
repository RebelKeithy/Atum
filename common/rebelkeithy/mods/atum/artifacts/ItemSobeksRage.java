package rebelkeithy.mods.atum.artifacts;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;

import org.lwjgl.input.Keyboard;

import rebelkeithy.mods.atum.entities.EntityPharaoh;
import rebelkeithy.mods.atum.entities.EntityStoneSoldier;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSobeksRage extends ItemAxe
{

	public ItemSobeksRage(int par1, EnumToolMaterial par2EnumToolMaterial) 
	{
		super(par1, par2EnumToolMaterial);
	}

    /**
     * Returns the damage against a given entity.
     */
	@Override
    public int getDamageVsEntity(Entity par1Entity)
    {
        return 4 + toolMaterial.getDamageVsEntity();
    }
	
	@Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLiving mob, EntityLiving player)
    {
		if(!(mob instanceof EntityStoneSoldier || mob instanceof EntityPharaoh))
		{
			float j = 4;
			mob.addVelocity((double)(-MathHelper.sin(player.rotationYaw * (float)Math.PI / 180.0F) * (float)j * 0.5F), 0.1D, (double)(MathHelper.cos(player.rotationYaw * (float)Math.PI / 180.0F) * (float)j * 0.5F));
		}
		return super.hitEntity(par1ItemStack, mob, player);
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
    		par3List.add(EnumChatFormatting.DARK_PURPLE + "Exile I: Knocks foes ");
    		par3List.add(EnumChatFormatting.DARK_PURPLE + "back a large amount");
    	} else {
        	par3List.add("Exile I " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
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
