package rebelkeithy.mods.atum;

import net.minecraft.entity.player.EnumStatus;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingFallEvent;

public class FallDamageListener 
{
	 @ForgeSubscribe
	 public void onSleepyTime(LivingFallEvent event)
	 {
		 if(event.entityLiving.getCurrentArmor(0) != null)
		 {
			 if(event.entityLiving.getCurrentArmor(0).itemID == Atum.horusFlight.itemID)
			 {
				 event.distance = 0;
			 }
		 }
	 }
}
