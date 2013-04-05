package rebelkeithy.mods.atum.tools;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemTool;

public class LimestonePaxel extends ItemSpade
{

	public LimestonePaxel(int par1, EnumToolMaterial par3EnumToolMaterial) 
	{
		super(par1, par3EnumToolMaterial);
	}

	public void updateIcons(IconRegister par1IconRegister)
	{
		 iconIndex = par1IconRegister.registerIcon("Atum:LimestonePaxel");
	}
	
	public boolean canHarvestBlock(Block par1Block)
	{
	        return par1Block == Block.obsidian ? this.toolMaterial.getHarvestLevel() == 3 : (par1Block != Block.blockDiamond && par1Block != Block.oreDiamond ? (par1Block == Block.oreEmerald ? this.toolMaterial.getHarvestLevel() >= 2 : (par1Block != Block.blockGold && par1Block != Block.oreGold ? (par1Block != Block.blockSteel && par1Block != Block.oreIron ? (par1Block != Block.blockLapis && par1Block != Block.oreLapis ? (par1Block != Block.oreRedstone && par1Block != Block.oreRedstoneGlowing ? (par1Block.blockMaterial == Material.rock ? true : par1Block.blockMaterial == Material.iron) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 2)) : this.toolMaterial.getHarvestLevel() >= 2);
	}
}
