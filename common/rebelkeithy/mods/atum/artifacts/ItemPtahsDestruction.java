
package rebelkeithy.mods.atum.artifacts;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StringTranslate;
import rebelkeithy.mods.atum.entities.EntityStoneSoldier;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemPtahsDestruction extends ItemPickaxe
{

	
	public ItemPtahsDestruction(int par1, EnumToolMaterial par2EnumToolMaterial) 
	{
		super(par1, par2EnumToolMaterial);
		this.efficiencyOnProperMaterial = 11;
	}

	@Override
	public int getDamageVsEntity(Entity entity)
	{
        int dammage =  4 + toolMaterial.getDamageVsEntity();
		if(entity instanceof EntityStoneSoldier)
		{
			return (int) (dammage * (2 + Math.random()));
		}
		
		return dammage;
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
            par3List.add(EnumChatFormatting.DARK_PURPLE + "Stonecutter I: Incresed damage");
            par3List.add(EnumChatFormatting.DARK_PURPLE + "to stone enemies");
        } else {
            par3List.add("Stonecutter I " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
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
