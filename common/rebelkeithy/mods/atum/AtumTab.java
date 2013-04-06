package rebelkeithy.mods.atum;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class AtumTab extends CreativeTabs
{
	public AtumTab(String label)
	{
		super(label);
	}
	
	@Override
	public Item getTabIconItem()
	{
		return Atum.horusFlight;
	}	
}
