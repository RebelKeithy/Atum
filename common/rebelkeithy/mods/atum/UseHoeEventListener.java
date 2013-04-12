package rebelkeithy.mods.atum;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.UseHoeEvent;

public class UseHoeEventListener 
{
	@ForgeSubscribe
	public boolean onHoeEvent(UseHoeEvent event)
	{
		int id = event.world.getBlockId(event.x, event.y, event.z);
		
		if(id == Atum.atumFertileSoil.blockID)
		{
			event.world.setBlock(event.x, event.y, event.z, Atum.atumFertileSoilTilled.blockID);
			return false;
		}
		
		return false;
	}
}
