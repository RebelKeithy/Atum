package rebelkeithy.mods.atum.tools;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSpade;

public class LimestoneShovel extends ItemSpade 
{

	public LimestoneShovel(int par1, EnumToolMaterial par2EnumToolMaterial)
	{
		super(par1, par2EnumToolMaterial);
	}
	
	public void updateIcons(IconRegister par1IconRegister)
	{
		 iconIndex = par1IconRegister.registerIcon("Atum:LimestoneShovel");
	}
}
