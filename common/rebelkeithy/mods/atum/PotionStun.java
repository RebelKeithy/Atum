package rebelkeithy.mods.atum;

import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;

public class PotionStun extends Potion
{

	protected PotionStun(int par1, boolean par2, int par3) 
	{
		super(par1, par2, par3);
		MinecraftForge.EVENT_BUS.register(this);
	}
	


	@ForgeSubscribe
	public void entityAquireTarge(LivingSetAttackTargetEvent event)
	{
		if(event.entityLiving != null)
		{
			if(event.entityLiving.isPotionActive(this))
			{
				ReflectionHelper.setPrivateValue(EntityLiving.class, event.entityLiving, null, "attackTarget", "field_70696_bz", "b0");
				//event.entityLiving.attackTarget = null;
			}
		}
	}

	@Override
    public void performEffect(EntityLiving par1EntityLiving, int par2)
    {
    	//par1EntityLiving.landMovementFactor = 0;
    	ReflectionHelper.setPrivateValue(EntityLiving.class, par1EntityLiving, null, "currentTarget", "field_70776_bF", "bY");
    }
	
	@Override
	public boolean isReady(int par1, int par2)
	{
		return true;
	}
}
