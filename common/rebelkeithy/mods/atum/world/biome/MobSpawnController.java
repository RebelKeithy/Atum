package rebelkeithy.mods.atum.world.biome;

import java.util.List;

import net.minecraft.world.biome.SpawnListEntry;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent.PotentialSpawns;
import rebelkeithy.mods.atum.entities.IAtumDayMob;
import rebelkeithy.mods.atum.entities.IAtumNightMob;

public class MobSpawnController 
{
	@ForgeSubscribe
	public void getPotentialSpawns(PotentialSpawns event)
	{
		List<SpawnListEntry> list = event.list;
		int time = (int) (event.world.getWorldTime() % 48000);
		System.out.println(time);
		for(int i = 0; i < list.size(); i++)
		{
			System.out.println(time);
			if(IAtumDayMob.class.isAssignableFrom(list.get(i).entityClass) && time > 0 && time < 24000) {} 
			else if(IAtumNightMob.class.isAssignableFrom(list.get(i).entityClass) && time > 24000 && time < 48000) {}
			else
			{
				list.remove(i);
				i--;
			}
		}
	}
}
