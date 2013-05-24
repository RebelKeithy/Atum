
package rebelkeithy.mods.atum.artifacts;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumChatFormatting;

import org.lwjgl.input.Keyboard;

import rebelkeithy.mods.atum.AtumItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemOsirisWill extends ItemSword
{

	public ItemOsirisWill(int par1, EnumToolMaterial par2EnumToolMaterial) 
	{
		super(par1, par2EnumToolMaterial);
	}

    /**
     * Returns the damage against a given entity.
     */
    public int getDamageVsEntity(Entity par1Entity)
    {
    	int damage = 0;
    	for(int i = 0; i < 4; i++)
    	{
    		damage += Math.random() * 4 + 1;
    	}
        return super.getDamageVsEntity(par1Entity) + damage;
    }

	@Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLiving mob, EntityLiving player)
    {
		if(!mob.isEntityAlive())
		{
			if(Math.random() < 0.5 && mob.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD)
			{
				mob.dropItem(AtumItems.ectoplasm.itemID, 1);
			}
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
    		par3List.add(EnumChatFormatting.DARK_PURPLE + "Banish I: Bonus damage to undead,");
    		par3List.add(EnumChatFormatting.DARK_PURPLE + "chance to drop ectoplasm");
    	} else {
        	par3List.add("Banish I " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
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
