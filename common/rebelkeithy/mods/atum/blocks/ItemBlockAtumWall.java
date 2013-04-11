package rebelkeithy.mods.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemBlockAtumWall extends ItemBlock
{
    public ItemBlockAtumWall(int par1)
    {
        super(par1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }
    
    @Override
    public int getMetadata(int meta)
    {
        return meta;
    }
    
    @Override
    public Icon getIconFromDamage(int meta)
    {
        return Block.blocksList[this.itemID].getIcon(2, meta);
    }
    
    @Override
    public String getUnlocalizedName(ItemStack itemstack)
    {
        switch(itemstack.getItemDamage())
        {
            case 0:
                return "Atum:AtumStoneWall";
            case 1:
                return "Atum:AtumCobble";
            case 2:
                return "Atum:AtumBrickLarge";
            case 3:
                return "Atum:AtumBrickSmall";
            default:
                return "Atum:Default";
        }
    }
}
