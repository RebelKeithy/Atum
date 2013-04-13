package rebelkeithy.mods.atum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.WeightedRandomChestContent;

public class AtumWeightedLootSet 
{
	public Map<Integer, ItemStack> loot;
	public Map<Integer, Integer> lootMin;
	public Map<Integer, Integer> lootMax;
	public int totalWeight;
	
	public AtumWeightedLootSet()
	{
		loot = new HashMap<Integer, ItemStack>();
		lootMin = new HashMap<Integer, Integer>();
		lootMax = new HashMap<Integer, Integer>();
		totalWeight = 0;
	}
	
	public void addLoot(ItemStack stack, int weight, int min, int max)
	{
		if(weight <= 0 || stack == null)
			return;
			
		loot.put(totalWeight + weight, stack);
		lootMin.put(totalWeight + weight, min);
		lootMax.put(totalWeight + weight, max);
		totalWeight += weight;
	}
	
	public ItemStack getRandomLoot()
	{
		Random rand = new Random();
		int weight = rand.nextInt(totalWeight);

		
		
		ItemStack stack = null;
		
		Set<Integer> keySet = loot.keySet();
		Integer[] keys = keySet.toArray(new Integer[keySet.size()]);
		Arrays.sort(keys);
		
		for(Integer key : keys)
		{
			System.out.println("key " + key);
			if(key >= weight)
			{
				System.out.println(weight + " " + totalWeight + " " + key);
				stack = loot.get(key).copy();
				int min = lootMin.get(key);
				int max = lootMax.get(key);
				int amount = rand.nextInt(max - min + 1) + min;
				stack.stackSize = amount;
				if(stack.itemID == Item.enchantedBook.itemID)
				{
			        Enchantment enchantment = Enchantment.field_92090_c[rand.nextInt(Enchantment.field_92090_c.length)];
			        int l = MathHelper.getRandomIntegerInRange(rand, enchantment.getMinLevel(), enchantment.getMaxLevel());
			        ((ItemEnchantedBook) stack.getItem()).func_92115_a(stack, new EnchantmentData(enchantment, l));
				}
				break;
			}
		}
		
		return stack;
	}
}
