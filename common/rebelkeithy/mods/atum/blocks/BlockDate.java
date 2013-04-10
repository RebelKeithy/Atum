package rebelkeithy.mods.atum.blocks;

import java.util.Random;

import rebelkeithy.mods.atum.Atum;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockDate extends Block
{
    public BlockDate(int id, Material material)
    {
        super(id, material);
    }
    
    public int idDropped(int par1, Random rand, int par3)
    {
        return Atum.itemDate.itemID;
    }
    
    public int quantityDropped(Random rand)
    {
        return rand.nextInt(3) + 1;
    }
}
