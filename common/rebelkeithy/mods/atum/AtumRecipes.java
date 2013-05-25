package rebelkeithy.mods.atum;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class AtumRecipes 
{

	
	public static void addRecipes()
	{	
	    // Brick recipes
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AtumBlocks.largeBrick, 4), "XX", "XX", 'X', AtumBlocks.stone));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AtumBlocks.smallBrick, 4), "XX", "XX", 'X', AtumBlocks.cobble));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(AtumBlocks.carvedBrick, 1), AtumBlocks.stone));
		
		// Stair recipes
		GameRegistry.addRecipe(new ItemStack(AtumBlocks.smoothStairs, 6), "X  ", "XX ", "XXX", 'X', AtumBlocks.stone);
		GameRegistry.addRecipe(new ItemStack(AtumBlocks.cobbleStairs, 6), "X  ", "XX ", "XXX", 'X', AtumBlocks.cobble);
		GameRegistry.addRecipe(new ItemStack(AtumBlocks.largeStoneStairs, 6), "X  ", "XX ", "XXX", 'X', AtumBlocks.largeBrick);
		GameRegistry.addRecipe(new ItemStack(AtumBlocks.smallStoneStairs, 6), "X  ", "XX ", "XXX", 'X', AtumBlocks.smallBrick);
		
		// Slab recipes
		GameRegistry.addRecipe(new ItemStack(AtumBlocks.slabs, 6, 0), "XXX", 'X', AtumBlocks.stone);
		GameRegistry.addRecipe(new ItemStack(AtumBlocks.slabs, 6, 1), "XXX", 'X', AtumBlocks.cobble);
		GameRegistry.addRecipe(new ItemStack(AtumBlocks.slabs, 6, 2), "XXX", 'X', AtumBlocks.largeBrick);
		GameRegistry.addRecipe(new ItemStack(AtumBlocks.slabs, 6, 3), "XXX", 'X', AtumBlocks.smallBrick);
		
		// Wall recipes
		GameRegistry.addRecipe(new ItemStack(AtumBlocks.wall, 6, 0), "XXX", "XXX", 'X', AtumBlocks.stone);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.wall, 6, 1), "XXX", "XXX", 'X', AtumBlocks.cobble);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.wall, 6, 2), "XXX", "XXX", 'X', AtumBlocks.largeBrick);
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.wall, 6, 3), "XXX", "XXX", 'X', AtumBlocks.smallBrick);
        
        // Crystal glass to framed glass
        GameRegistry.addRecipe(new ItemStack(AtumBlocks.framedGlass), " X ", "XSX", " X ", 'X', Item.stick, 'S', AtumBlocks.crystalGlass);
        
        // Cracked large bricks recipe
		GameRegistry.addRecipe(new ItemStack(AtumBlocks.crackedLargeBrick, 4), "XX", "XX", 'X', AtumItems.stoneChunk);
		
		// Xp bottle recipe
		GameRegistry.addRecipe(new ItemStack(Item.expBottle), " X ", "XBX", " X ", 'X', AtumItems.ectoplasm, 'B', Item.potion);

		// Limestone tool recipes
		GameRegistry.addRecipe(new ItemStack(AtumItems.limestoneSword), "L", "L", "S", 'L', AtumBlocks.stone, 'S', Item.stick);
		GameRegistry.addRecipe(new ItemStack(AtumItems.limestoneShovel), "L", "S", "S", 'L', AtumBlocks.stone, 'S', Item.stick);
		GameRegistry.addRecipe(new ItemStack(AtumItems.limestonePickaxe), "LLL", " S ", " S ", 'L', AtumBlocks.stone, 'S', Item.stick);
		GameRegistry.addRecipe(new ItemStack(AtumItems.limestoneAxe), "LL", "LS", " S", 'L', AtumBlocks.stone, 'S', Item.stick);
		GameRegistry.addRecipe(new ItemStack(AtumItems.limestoneHoe), "LL", " S", " S", 'L', AtumBlocks.stone, 'S', Item.stick);
		
		// Mummy armor recipes
		GameRegistry.addRecipe(new ItemStack(AtumItems.mummyHelmet), "XXX", "X X", 'X', AtumItems.scrap);
		GameRegistry.addRecipe(new ItemStack(AtumItems.mummyChest), "X X", "XXX", "XXX", 'X', AtumItems.scrap);
		GameRegistry.addRecipe(new ItemStack(AtumItems.mummyLegs), "XXX", "X X", "X X", 'X', AtumItems.scrap);
		GameRegistry.addRecipe(new ItemStack(AtumItems.mummyBoots), "X X", "X X", 'X', AtumItems.scrap);
		
		// Wanderer's armor recipes
		GameRegistry.addRecipe(new ItemStack(AtumItems.wandererHelmet), "XXX", "X X", 'X', AtumItems.linen);
		GameRegistry.addRecipe(new ItemStack(AtumItems.wandererChest), "X X", "XXX", "XXX", 'X', AtumItems.linen);
		GameRegistry.addRecipe(new ItemStack(AtumItems.wandererLegs), "XXX", "X X", "X X", 'X', AtumItems.linen);
		GameRegistry.addRecipe(new ItemStack(AtumItems.wandererBoots), "X X", "X X", 'X', AtumItems.linen);
		
		// Linen from flax
		GameRegistry.addRecipe(new ItemStack(AtumItems.linen), "XXX", 'X', AtumItems.flax);
		
		// Bottles out of Crystal glass
		GameRegistry.addRecipe(new ItemStack(Item.glassBottle, 3), "X X", " X ", 'X', AtumBlocks.crystalGlass);
		
		// Thin Glass
		GameRegistry.addRecipe(new ItemStack(AtumBlocks.thinCrystalGlass, 16), "XXX", "XXX", 'X', AtumBlocks.crystalGlass);
		GameRegistry.addRecipe(new ItemStack(AtumBlocks.thinFramedGlass, 16), "XXX", "XXX", 'X', AtumBlocks.framedGlass);
		
		// Scroll from papyrus
		GameRegistry.addRecipe(new ItemStack(AtumItems.scroll), "XXX", "SXS", "XXX", 'X', AtumItems.papyrusPlant, 'S', Item.stick);
		
		// Scarab recipe
		GameRegistry.addRecipe(new ItemStack(AtumItems.scarab), " G ", "GDG", " G ", 'G', Item.ingotGold, 'D', Item.diamond);
		
		// Furnace recipe
		GameRegistry.addRecipe(new ItemStack(AtumBlocks.furnaceIdle), "XXX", "X X", "XXX", 'X', AtumBlocks.cobble);
	}
	
	public static void addSmeltingRecipes()
	{
	    // Ore smelting recipes
	    FurnaceRecipes.smelting().addSmelting(AtumBlocks.ironOre.blockID, 0, new ItemStack(Item.ingotIron), 0.7F);
        FurnaceRecipes.smelting().addSmelting(AtumBlocks.coalOre.blockID, new ItemStack(Item.coal), 0.1F);
        FurnaceRecipes.smelting().addSmelting(AtumBlocks.redstoneOre.blockID, new ItemStack(Item.redstone), 0.7F);
        FurnaceRecipes.smelting().addSmelting(AtumBlocks.lapisOre.blockID, new ItemStack(Item.dyePowder, 1, 4), 0.2F);
        FurnaceRecipes.smelting().addSmelting(AtumBlocks.goldOre.blockID, new ItemStack(Item.ingotGold), 1.0F);
        FurnaceRecipes.smelting().addSmelting(AtumBlocks.diamondOre.blockID, new ItemStack(Item.diamond), 1.0F);
        
        // Palm log to charcoal
        FurnaceRecipes.smelting().addSmelting(AtumBlocks.log.blockID, new ItemStack(Item.coal, 1, 1), 0.15F);
        
        // Atum cobble to Atum stone
        FurnaceRecipes.smelting().addSmelting(AtumBlocks.cobble.blockID, new ItemStack(AtumBlocks.stone), 0.1F);
        
        // Atum sand to crystal glass
        FurnaceRecipes.smelting().addSmelting(AtumBlocks.sand.blockID, new ItemStack(AtumBlocks.crystalGlass), 0.1F);
	}
	
	public static void addShapelessRecipes()
	{
	    // Palm planks from Palm logs
	    GameRegistry.addShapelessRecipe(new ItemStack(AtumBlocks.planks, 4), AtumBlocks.log);
	    
	    // Desert armor from iron armor
	    GameRegistry.addShapelessRecipe(new ItemStack(AtumItems.desertHelmet), AtumItems.wandererHelmet, Item.helmetIron);
        GameRegistry.addShapelessRecipe(new ItemStack(AtumItems.desertChest), AtumItems.wandererChest, Item.plateIron);
        GameRegistry.addShapelessRecipe(new ItemStack(AtumItems.desertLegs), AtumItems.wandererLegs, Item.legsIron);
        GameRegistry.addShapelessRecipe(new ItemStack(AtumItems.desertBoots), AtumItems.wandererBoots, Item.bootsIron);
        
        // Atum (Strange) sand to normal vanilla sand
        GameRegistry.addShapelessRecipe(new ItemStack(Block.sand), AtumBlocks.sand);
        
        // Linen cloth to string
        GameRegistry.addShapelessRecipe(new ItemStack(Item.silk, 3), AtumItems.flax);
	}
}
