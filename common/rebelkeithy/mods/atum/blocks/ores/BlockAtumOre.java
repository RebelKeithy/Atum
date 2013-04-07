package rebelkeithy.mods.atum.blocks.ores;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import rebelkeithy.mods.atum.Atum;

public class BlockAtumOre extends Block
{
    public BlockAtumOre(int par1)
    {
        super(par1, Material.rock);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
    	if(this.blockID == Atum.atumCoalOre.blockID)
    		return Item.coal.itemID;
    	else if(this.blockID == Atum.atumDiamondOre.blockID)
    		return Item.diamond.itemID;
    	else if(this.blockID == Atum.atumLapisOre.blockID)
    		return Item.dyePowder.itemID;
    	else 
    		return this.blockID;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return this.blockID == Atum.atumLapisOre.blockID ? 4 + par1Random.nextInt(5) : 1;
    }

    /**
     * Returns the usual quantity dropped by the block plus a bonus of 1 to 'i' (inclusive).
     */
    public int quantityDroppedWithBonus(int par1, Random par2Random)
    {
        if (par1 > 0 && this.blockID != this.idDropped(0, par2Random, par1))
        {
            int j = par2Random.nextInt(par1 + 2) - 1;

            if (j < 0)
            {
                j = 0;
            }

            return this.quantityDropped(par2Random) * (j + 1);
        }
        else
        {
            return this.quantityDropped(par2Random);
        }
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);

        if (this.idDropped(par5, par1World.rand, par7) != this.blockID)
        {
            int j1 = 0;

            if (this.blockID == Atum.atumCoalOre.blockID)
            {
                j1 = MathHelper.getRandomIntegerInRange(par1World.rand, 0, 2);
            }
            else if (this.blockID == Atum.atumDiamondOre.blockID)
            {
                j1 = MathHelper.getRandomIntegerInRange(par1World.rand, 3, 7);
            }
            else if (this.blockID == Atum.atumLapisOre.blockID)
            {
                j1 = MathHelper.getRandomIntegerInRange(par1World.rand, 2, 5);
            }

            this.dropXpOnBlockBreak(par1World, par2, par3, par4, j1);
        }
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return this.blockID == Atum.atumLapisOre.blockID ? 4 : 0;
    }
}
