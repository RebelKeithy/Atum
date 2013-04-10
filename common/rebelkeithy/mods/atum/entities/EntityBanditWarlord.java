package rebelkeithy.mods.atum.entities;

import rebelkeithy.mods.atum.Atum;
import rebelkeithy.mods.atum.ConfigAtum;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityBanditWarlord extends EntityMob {

	public EntityBanditWarlord(World par1World) 
	{
		super(par1World);
        this.experienceValue = 16;
	}

    public String getTexture()
    {
    	return "/mods/Atum/textures/mobs/BanditWarlord.png";
    }

	@Override
	public int getMaxHealth() 
	{
		return 80;
	}

	@Override
    protected void addRandomArmor() { }
    
    public float getSpeedModifier()
    {
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
    
    public void initCreature()
    {
    	this.setCurrentItemOrArmor(0, new ItemStack(Atum.itemScimitar));
        EnchantmentHelper.addRandomEnchantment(this.rand, this.getHeldItem(), 5 + this.worldObj.difficultySetting * this.rand.nextInt(6));
    	
        for (int i = 0; i < this.equipmentDropChances.length; ++i)
        {
            this.equipmentDropChances[i] = 0.05F;
        }
    }
    
    /**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEFINED;
    }

    /**
     * Returns the amount of damage a mob should deal.
     */
    public int getAttackStrength(Entity par1Entity)
    {
        return 4;
    }

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    protected void dropFewItems(boolean par1, int par2)
    {
    	 if(rand.nextInt(20) == 0)
    	 {
    		 int damage = (int) (Atum.itemScimitar.getMaxDamage() - rand.nextInt(Atum.itemScimitar.getMaxDamage()) * 0.5 + 20);
             this.entityDropItem(new ItemStack(ConfigAtum.scimitarID, 1, damage), 0.0F);
    	 }
    	 
    	 if(rand.nextInt(4) == 0)
    	 {
    		 int amount = rand.nextInt(3) + 3;
    		 this.dropItem(Item.goldNugget.itemID, amount);
    	 }
    	 
    	 if(rand.nextInt(4) == 0)
    	 {
    		 int choice = rand.nextInt(4);
        	 if(choice == 0)
        	 {
        		 this.dropItem(Atum.desertHelmet.itemID, 1);
        	 }
        	 else if(choice == 1)
        	 {
        		 this.dropItem(Atum.desertChest.itemID, 1);
        	 }
        	 else if(choice == 2)
        	 {
        		 this.dropItem(Atum.desertLegs.itemID, 1);
        	 }
        	 else if(choice == 3)
        	 {
        		 this.dropItem(Atum.desertBoots.itemID, 1);
        	 }
    	 }
    }
}
