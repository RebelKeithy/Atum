package rebelkeithy.mods.atum.artifacts;

import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemPtahsDecadence extends ItemPickaxe
{

	public ItemPtahsDecadence(int par1, EnumToolMaterial par2EnumToolMaterial) 
	{
		super(par1, par2EnumToolMaterial);
	}

    public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int blockID, int x, int y, int z, EntityLiving par7EntityLiving)
    {
        int dropID = Block.blocksList[blockID].idDropped(par2World.getBlockMetadata(x, y, z), new Random(), 0);
        if(dropID == Item.diamond.itemID)
        {
        	Block.oreDiamond.dropBlockAsItem(par2World, x, y, z, 0, 0);
        }
        
        return super.onBlockDestroyed(par1ItemStack, par2World, blockID, x, y, z, par7EntityLiving);
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
    		par3List.add(EnumChatFormatting.DARK_PURPLE + "Wealth I: Gain an extra");
    		par3List.add(EnumChatFormatting.DARK_PURPLE + "diamond from each ore");
    	} else {
        	par3List.add("Wealth I " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
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

    /**
     * Return whether this item is repairable in an anvil.
     */
	@Override
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return par2ItemStack.itemID == Item.diamond.itemID;
    }
}
