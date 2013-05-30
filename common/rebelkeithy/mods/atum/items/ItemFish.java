package rebelkeithy.mods.atum.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFish extends Item
{
	Icon[] icons;
	
	public ItemFish(int par1) 
	{
		super(par1);
		this.setHasSubtypes(true);
	}

    @SideOnly(Side.CLIENT)
    /**
     * Gets an icon index based on an item's damage value
     */
    public Icon getIconFromDamage(int par1)
    {
        return icons[par1];
    }

    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        return super.getUnlocalizedName() + "." + par1ItemStack.getItemDamage();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
    	for(int i = 0; i < 4; i++)
    	{
    		par3List.add(new ItemStack(this.itemID, 1, i));
    	}
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        icons = new Icon[4];
        
        for (int i = 0; i < 4; i++)
        {
    		icons[i] = par1IconRegister.registerIcon("Atum:Fish" + i);
        }
    }
}
