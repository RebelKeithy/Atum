package rebelkeithy.mods.atum.tools;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemPickaxe;

public class LimestonePickaxe extends ItemPickaxe 
{

	public LimestonePickaxe(int par1, EnumToolMaterial par2EnumToolMaterial) 
	{
		super(par1, par2EnumToolMaterial);
	}
	
	public void updateIcons(IconRegister par1IconRegister)
	{
		 iconIndex = par1IconRegister.registerIcon("Atum:LimestonePickaxe");
	}
}
