package rebelkeithy.mods.atum.entities;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityItemLoot extends EntityItem
{

	public EntityItemLoot(World par1World) 
	{
		super(par1World);
	}
	
	public EntityItemLoot(World worldObj, double posX, double posY, double posZ, ItemStack tossed) 
	{
		super(worldObj, posX, posY, posZ, tossed);
	}

	@Override
	public void onUpdate()
	{
		/*int id = worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
        if (id == Block.waterStill.blockID || id == Block.waterMoving.blockID)
        {
        	System.out.println("in water");
        	ItemStack item = this.getEntityItem();
        	int damage = item.getItemDamage() >> 1;
        	int quality = damage & 15;
        	if(quality == 0)
        		damage = damage | ((int)(Math.random()*5))+1;
        	item.setItemDamage(damage << 1);
        	this.setEntityItemStack(item);
        }*/
		super.onUpdate();
	}
}
