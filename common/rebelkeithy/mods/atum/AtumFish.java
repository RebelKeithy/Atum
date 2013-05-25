package rebelkeithy.mods.atum;

import java.util.List;

import net.minecraft.item.ItemStack;

public class AtumFish 
{
	private static AtumWeightedLootSet fish;
	
	static
	{
		fish = new AtumWeightedLootSet();
		
		AtumFish.addFish(new ItemStack(AtumItems.fish, 1, 0), 3);
		AtumFish.addFish(new ItemStack(AtumItems.fish, 1, 1), 1);
		AtumFish.addFish(new ItemStack(AtumItems.fish, 1, 2), 5);
		AtumFish.addFish(new ItemStack(AtumItems.fish, 1, 3), 5);
	}
	
	public static void addFish(ItemStack fishStack, int probability)
	{
		fish.addLoot(fishStack, probability, 1, 1);
	}
	
	public static ItemStack getRandomFish()
	{
		return fish.getRandomLoot();
	}
}
