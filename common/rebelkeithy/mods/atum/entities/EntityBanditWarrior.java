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
	
    public void initCreature()
    {
    	this.setCurrentItemOrArmor(0, new ItemStack(Atum.itemScimitar));
    }

}
