package rebelkeithy.mods.atum.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import rebelkeithy.mods.atum.Atum;

public class EntityMummy extends EntityMob
{

    public EntityMummy(World par1World) 
    {
        super(par1World);
        this.experienceValue = 8;
    }

    @Override
    public int getMaxHealth() 
    {
        return 40;
    }

    public String getTexture()
    {
        return "/mods/Atum/textures/mobs/Mummy.png";
    }
    
    public float getSpeedModifier()
    {
        if(this.isBurning())
            return super.getSpeedModifier() * 1.4F;
        
        return super.getSpeedModifier();
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

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }
    
    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
    {
        if(par1DamageSource.isFireDamage())
        {
            par2 += 1;
        }
        if(this.isBurning())
        {
            par2 = (int) (par2 * 1.5);
        }

        return super.attackEntityFrom(par1DamageSource, par2);
    }

    public boolean attackEntityAsMob(Entity par1Entity)
    {
        boolean flag = super.attackEntityAsMob(par1Entity);

        if (flag && this.isBurning() && this.rand.nextFloat() < (float)this.worldObj.difficultySetting * 0.4F)
        {
            par1Entity.setFire(2 * this.worldObj.difficultySetting);
        }

        return flag;
    }

    /**
     * Returns the amount of damage a mob should deal.
     */
    public int getAttackStrength(Entity par1Entity)
    {
        return 2;
    }
    
    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    protected void dropFewItems(boolean par1, int par2)
    {
         if(rand.nextInt(4) == 0)
         {
             this.dropItem(Item.rottenFlesh.itemID, 1);
         }
         if(rand.nextInt(4) == 0)
         {
             int amount = rand.nextInt(2) + 1;
             this.dropItem(Atum.itemClothScrap.itemID, amount);
         }
    }
}
