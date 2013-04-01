package rebelkeithy.mods.atum.entities;

import rebelkeithy.mods.atum.Atum;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityBanditWarrior extends EntityMob {

	public EntityBanditWarrior(World par1World) 
	{
		super(par1World);
	}

    public String getTexture()
    {
    	return "/mods/Atum/textures/mobs/BanditWarrior.png";
    }

	@Override
	public int getMaxHealth() 
	{
		return 20;
	}


    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
    	//System.out.println("light level mummy " + this.isValidLightLevel() + " " + super.getCanSpawnHere());
    	return this.worldObj.checkIfAABBIsClear(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
        //return true || super.getCanSpawnHere();
    }
    /**
     * Checks to make sure the light is not too bright where the mob is spawning
     */
    protected boolean isValidLightLevel()
    {
        return true;
    }
    
    public void initCreature()
    {
    	this.setCurrentItemOrArmor(0, new ItemStack(Atum.itemScimitar));
    	
        for (int i = 0; i < this.equipmentDropChances.length; ++i)
        {
            this.equipmentDropChances[i] = 0F;
        }
    }

}
