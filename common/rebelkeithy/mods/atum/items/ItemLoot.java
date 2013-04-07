package rebelkeithy.mods.atum.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;

// Uses bit compression to store what type of loot this item is
// Format is TTTTQQQQD
// D: indicates weather or not it is dirty
// Q: Loot quality
// T: Loot type
public class ItemLoot extends Item 
{
	String[] typeArray = {"Statue", "Necklace", "Ring", "Broach", "Staff"};
	String[] qualityArray = {"Dirty", "Iron", "Gold", "Saphire", "Ruby", "Emerald", "Diamond"};
	Icon[] iconArray;
	
	public ItemLoot(int par1) 
	{
		super(par1);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
	}

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

    @SideOnly(Side.CLIENT)

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
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
    public void updateIcons(IconRegister par1IconRegister)
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
