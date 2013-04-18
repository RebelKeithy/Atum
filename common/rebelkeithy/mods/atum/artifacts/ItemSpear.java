package rebelkeithy.mods.atum.artifacts;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rebelkeithy.mods.atum.entities.projectiles.EntityFireSpearCombined;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSpear extends Item
{

	public ItemSpear(int par1) {
		super(par1);
		// TODO Auto-generated constructor stub
	}


    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
	@Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {

        EntityFireSpearCombined entityarrow = new EntityFireSpearCombined(par2World, par3EntityPlayer, 0.75F);
        entityarrow.setDamage(entityarrow.getDamage() * 1.5F);
        
        if (!par2World.isRemote)
        {
        	par2World.spawnEntityInWorld(entityarrow);
        	par2World.updateEntity(entityarrow);
        }
        
        return par1ItemStack;
    }
	

    @SideOnly(Side.CLIENT)
	@Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.registerIcons(par1IconRegister);
        this.itemIcon = par1IconRegister.registerIcon("Atum:Arrow");
    }

}
