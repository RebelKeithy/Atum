package rebelkeithy.mods.atum.blocks;

import java.util.Random;

import rebelkeithy.mods.atum.Atum;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class AtumStone extends Block
{

	public AtumStone(int par1) 
	{
		super(par1, Material.rock);
	}

    public int idDropped(int par1, Random par2Random, int par3)
    {
        return Atum.atumCobble.blockID;
    }
}
