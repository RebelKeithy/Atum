package rebelkeithy.mods.atum.items;

import java.util.List;
import java.util.Random;

import rebelkeithy.mods.atum.Atum;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// Uses bit compression to store what type of loot this item is
// Format is TTTTQQQQD
// D: indicates weather or not it is dirty
// Q: Loot quality
// T: Loot type
public class ItemLoot extends Item 
{
	private static String[] typeArray = {"Idol", "Necklace", "Ring", "Broach", "Scepter"};
	private static String[] qualityArray = {"Dirty", "Silver", "Gold", "Sapphire", "Ruby", "Emerald", "Diamond"};
	Icon[] iconArray;
	
	public ItemLoot(int par1) 
	{
		super(par1);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
	}
	
	public static ItemStack getRandomLoot(Random rand, boolean isDirty)
	{
		int type = rand.nextInt(typeArray.length);
		int quality = rand.nextInt(qualityArray.length-1) + 1;
		return new ItemStack(Atum.itemLoot.itemID, 1, type << 5 | quality << 1 | (isDirty ? 1 : 0));
	}

    @Override
    public String getItemDisplayName(ItemStack par1ItemStack)
    {
        int dirty = par1ItemStack.getItemDamage() & 1;
        int quality = par1ItemStack.getItemDamage() >> 1 & 15;
        int type = par1ItemStack.getItemDamage() >> 5 & 15;
        if(dirty == 1)
        	return "Dirty " + typeArray[type];
        else
        	return qualityArray[quality] + " " + typeArray[type];
    }
    
    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        int dirty = par1ItemStack.getItemDamage() & 1;
        int quality = par1ItemStack.getItemDamage() >> 1 & 15;
        int type = par1ItemStack.getItemDamage() >> 5 & 15;
        if(dirty == 1)
        	return super.getUnlocalizedName() + ".Dirty" + typeArray[type];
        else
        	return super.getUnlocalizedName() + "." + qualityArray[quality] + typeArray[type];
        
        
    }

    /**
     * Called by the default implemetation of EntityItem's onUpdate method, allowing for cleaner 
     * control over the update of the item without having to write a subclass.
     * 
     * @param entityItem The entity Item
     * @return Return true to skip any further update code.
     */
    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem)
    {
		int id = entityItem.worldObj.getBlockId(MathHelper.floor_double(entityItem.posX), MathHelper.floor_double(entityItem.posY), MathHelper.floor_double(entityItem.posZ));
        if (id == Block.waterStill.blockID || id == Block.waterMoving.blockID)
        {
        	System.out.println("in water");
        	ItemStack item = entityItem.getEntityItem();
        	int damage = item.getItemDamage() >> 1;
        	int quality = damage & 15;
        	if(quality == 0)
        		damage = damage | ((int)(Math.random()*6))+1;
        	item.setItemDamage(damage << 1);
        	entityItem.setEntityItemStack(item);
        }
		return super.onEntityItemUpdate(entityItem);
    }

    @SideOnly(Side.CLIENT)

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @Override
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int type = 0; type < typeArray.length; type++)
        {
    		par3List.add(new ItemStack(par1, 1, type << 5 | 1));
        	for(int quality = 1; quality < qualityArray.length; quality++)
        	{
        		par3List.add(new ItemStack(par1, 1, type << 5 | quality << 1));
        	}
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * Gets an icon index based on an item's damage value
     */
    @Override
    public Icon getIconFromDamage(int par1)
    {

        int dirty = par1 & 1;
        int quality = (par1 >> 1) & 15;
        int type = (par1 >> 5) & 15;
        if(dirty == 1)
            return iconArray[type*7];
        else
        	return iconArray[type*7 + quality];
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        iconArray = new Icon[(typeArray.length)*(qualityArray.length+1)];
        
        for (int type = 0; type < 5; type++)
        {
    		iconArray[type*7] = par1IconRegister.registerIcon("Atum:Dirty" + typeArray[type]);
        	for(int quality = 1; quality < 7; quality++)
        	{
        		iconArray[((type*7) + quality)] = par1IconRegister.registerIcon("Atum:" + qualityArray[quality] + typeArray[type]);
        	}
        }
    }

	
}
