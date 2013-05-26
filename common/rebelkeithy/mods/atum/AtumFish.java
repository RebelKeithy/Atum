package rebelkeithy.mods.atum;

import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AtumFish 
{
	private static AtumWeightedLootSet fish;
	
	static
	{
		fish = new AtumWeightedLootSet();
		
		AtumFish.addFish(new ItemStack(Item.fishRaw, 1, 0), 100);
		AtumFish.addFish(new ItemStack(AtumItems.fish, 1, 0), 30);
		AtumFish.addFish(new ItemStack(AtumItems.fish, 1, 1), 5);
		AtumFish.addFish(new ItemStack(AtumItems.fish, 1, 2), 50);
		AtumFish.addFish(new ItemStack(AtumItems.fish, 1, 3), 50);
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
