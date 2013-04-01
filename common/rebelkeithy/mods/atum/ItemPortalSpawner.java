package rebelkeithy.mods.atum;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemPortalSpawner extends Item
{
	public ItemPortalSpawner(int id)
	{
		super(id);
		this.maxStackSize = 1;
	}
	

    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {

        int blockID = par3World.getBlockId(par4, par5, par6);
        if(blockID == Block.sandStone.blockID)
        {
        	if(!Atum.portal.tryToCreatePortal(par3World, par4, par5 + 1, par6))
        	{
        		if(par2EntityPlayer.capabilities.isCreativeMode)
        		{
	        		par3World.setBlock(par4+2, par5+1, par6, Block.sandStone.blockID);
	        		par3World.setBlock(par4+2, par5+2, par6, Block.sandStone.blockID);
	        		par3World.setBlock(par4-2, par5+1, par6, Block.sandStone.blockID);
	        		par3World.setBlock(par4-2, par5+2, par6, Block.sandStone.blockID);
	        		par3World.setBlock(par4, par5+1, par6+2, Block.sandStone.blockID);
	        		par3World.setBlock(par4, par5+2, par6+2, Block.sandStone.blockID);
	        		par3World.setBlock(par4, par5+1, par6-2, Block.sandStone.blockID);
	        		par3World.setBlock(par4, par5+2, par6-2, Block.sandStone.blockID);
	        		par3World.setBlock(par4, par5+3, par6+1, Block.sandStone.blockID);
	        		par3World.setBlock(par4, par5+3, par6-1, Block.sandStone.blockID);
	        		par3World.setBlock(par4-1, par5+3, par6, Block.sandStone.blockID);
	        		par3World.setBlock(par4+1, par5+3, par6, Block.sandStone.blockID);
        		}
        	} else {
        		par2EntityPlayer.getCurrentEquippedItem().stackSize--;
        	}
        }
        return true;
    }
}
