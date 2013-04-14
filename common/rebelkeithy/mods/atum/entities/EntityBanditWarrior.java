package rebelkeithy.mods.atum.entities;

import rebelkeithy.mods.atum.Atum;
import rebelkeithy.mods.atum.ConfigAtum;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityBanditWarrior extends EntityMob implements IAtumDayMob
{

	public EntityBanditWarrior(World par1World) 
	{
		super(par1World);
        this.experienceValue = 8;
	}

    @Override
    public String getTexture()
    {
    	return "/mods/Atum/textures/mobs/BanditWarrior.png";
    }

	@Override
	public int getMaxHealth() 
	{
		return 30;
	}

	@Override
    protected void addRandomArmor() { }

    @Override
    public float getSpeedModifier()
    {
    	return super.getSpeedModifier() * 1.25F;
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    @Override
    public boolean getCanSpawnHere()
    {
    	//System.out.println("light level mummy " + this.isValidLightLevel() + " " + super.getCanSpawnHere());
    	return this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
        //return true || super.getCanSpawnHere();
    }
    
    /**
     * Checks to make sure the light is not too bright where the mob is spawning
     */
    @Override
    protected boolean isValidLightLevel()
    {
        return true;
    }

    @Override
    public void initCreature()
    {
    	this.setCurrentItemOrArmor(0, new ItemStack(Atum.itemScimitar));
        this.func_82162_bC();
    	
        for (int i = 0; i < this.equipmentDropChances.length; ++i)
        {
            this.equipmentDropChances[i] = 0F;
        }
    }
    
    /**
     * Get this Entity's EnumCreatureAttribute
     */
    @Override
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEFINED;
    }

    /**
     * Returns the amount of damage a mob should deal.
     */
    @Override
    public int getAttackStrength(Entity par1Entity)
    {
        return 3;
    }

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    @Override
    protected void dropFewItems(boolean par1, int par2)
    {
    	 if(rand.nextInt(20) == 0)
    	 {
    		 int damage = (int) (Atum.itemScimitar.getMaxDamage() - rand.nextInt(Atum.itemScimitar.getMaxDamage()) * 0.5 + 20);
             this.entityDropItem(new ItemStack(ConfigAtum.scimitarID, 1, damage), 0.0F);
    	 }
    	 
    	 if(rand.nextInt(10) == 0)
    	 {
    		 int amount = rand.nextInt(2) + 1;
    		 this.dropItem(Item.goldNugget.itemID, amount);
    	 }
    }
}
