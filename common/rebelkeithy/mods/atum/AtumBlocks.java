package rebelkeithy.mods.atum;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import rebelkeithy.mods.atum.blocks.AtumStone;
import rebelkeithy.mods.atum.blocks.BlockArrowTrap;
import rebelkeithy.mods.atum.blocks.BlockAtumGlass;
import rebelkeithy.mods.atum.blocks.BlockAtumLeaves;
import rebelkeithy.mods.atum.blocks.BlockPalmLog;
import rebelkeithy.mods.atum.blocks.BlockAtumPane;
import rebelkeithy.mods.atum.blocks.BlockAtumPortal;
import rebelkeithy.mods.atum.blocks.BlockAtumSand;
import rebelkeithy.mods.atum.blocks.BlockPalmSapling;
import rebelkeithy.mods.atum.blocks.BlockAtumSlab;
import rebelkeithy.mods.atum.blocks.BlockAtumStairs;
import rebelkeithy.mods.atum.blocks.BlockAtumStone;
import rebelkeithy.mods.atum.blocks.BlockAtumWall;
import rebelkeithy.mods.atum.blocks.BlockDate;
import rebelkeithy.mods.atum.blocks.BlockFertileSoil;
import rebelkeithy.mods.atum.blocks.BlockFertileSoilTilled;
import rebelkeithy.mods.atum.blocks.BlockFlax;
import rebelkeithy.mods.atum.blocks.BlockPapyrus;
import rebelkeithy.mods.atum.blocks.BlockSandLayered;
import rebelkeithy.mods.atum.blocks.BlockShrub;
import rebelkeithy.mods.atum.blocks.ItemBlockAtumWall;
import rebelkeithy.mods.atum.blocks.ItemSandLayered;
import rebelkeithy.mods.atum.blocks.TileEntityArrowTrap;
import rebelkeithy.mods.atum.blocks.ores.BlockAtumOre;
import rebelkeithy.mods.atum.blocks.ores.BlockAtumRedstone;
import rebelkeithy.mods.atum.cursedchest.BlockChestSpawner;
import rebelkeithy.mods.atum.cursedchest.PharaohChest;
import rebelkeithy.mods.atum.cursedchest.TileEntityChestSpawner;
import rebelkeithy.mods.atum.cursedchest.TileEntityPharaohChest;
import rebelkeithy.mods.atum.furnace.BlockLimeStoneFurnace;
import rebelkeithy.mods.atum.furnace.TileEntityLimestoneFurnace;

public class AtumBlocks 
{
	public static BlockAtumPortal portal;
	public static Block cursedChest;
	public static Block sand;
	public static Block stone;
	public static Block cobble;
	public static Block largeBrick;
	public static Block smallBrick;
	public static Block carvedBrick;
	public static BlockAtumSlab slabs;
	public static BlockAtumSlab doubleSlab;
	public static Block smoothStairs;
	public static Block cobbleStairs;
	public static Block largeStoneStairs;
	public static Block smallStoneStairs;
	public static Block sandLayered;
	public static Block crackedLargeBrick;
	public static Block wall;
	public static Block crystalGlass;
	public static Block framedGlass;
	public static Block palmSapling;
	public static Block dateBlock;
	
	public static Block shrub;
	public static Block weed;
	public static Block papyrus;
	public static Block flax;
	
	public static BlockFertileSoil fertileSoil;
	public static Block fertileSoilTilled;
	public static Block log;
	public static Block leaves;
	public static Block planks;
	public static Block thinCrystalGlass;
	public static Block thinFramedGlass;
	
	public static Block trapArrow;
	public static Block pharaohChest;
	
	public static Block redstoneOre;
	public static Block coalOre;
	public static Block ironOre;
	public static Block goldOre;
	public static Block lapisOre;
	public static Block diamondOre;

	public static Block furnaceIdle;
	public static Block furnaceBurning;
	
	public static void init()
	{
		
		portal = new BlockAtumPortal(ConfigAtum.portalBlockID);
		cursedChest = new BlockChestSpawner(ConfigAtum.cursedChestID).setUnlocalizedName("AtumCursedChest").setHardness(4.0F).setCreativeTab(Atum.tabs);
		pharaohChest = new PharaohChest(ConfigAtum.pharaohChestID).setUnlocalizedName("AtumPharaohChest").setHardness(4.0F).setCreativeTab(Atum.tabs);
		sand = new BlockAtumSand(ConfigAtum.sandID).setUnlocalizedName("Atum:AtumSand").setStepSound(Block.soundSandFootstep).setHardness(0.5F).setCreativeTab(Atum.tabs);
		stone = new AtumStone(ConfigAtum.stoneID).setUnlocalizedName("Atum:AtumStone").setHardness(1.5F).setCreativeTab(Atum.tabs);
		cobble = new Block(ConfigAtum.cobbleID, Material.rock).setUnlocalizedName("Atum:AtumCobble").setHardness(2.0F).setCreativeTab(Atum.tabs);
		crackedLargeBrick = new Block(ConfigAtum.crackedLargeBrickID, Material.rock).setUnlocalizedName("Atum:AtumCrackedLargeBrick").setHardness(2.0F).setCreativeTab(Atum.tabs);
		largeBrick = new BlockAtumStone(ConfigAtum.largeBrickID, Material.rock).setUnlocalizedName("Atum:AtumBrickLarge").setHardness(2.0F).setCreativeTab(Atum.tabs);
		smallBrick = new BlockAtumStone(ConfigAtum.smallBrickID, Material.rock).setUnlocalizedName("Atum:AtumBrickSmall").setHardness(2.0F).setCreativeTab(Atum.tabs);
		carvedBrick = new BlockAtumStone(ConfigAtum.carvedBrickID, Material.rock).setUnlocalizedName("Atum:AtumBrickCarved").setHardness(2.0F).setCreativeTab(Atum.tabs);
		slabs = (BlockAtumSlab) new BlockAtumSlab(ConfigAtum.slabID, false, Material.rock).setUnlocalizedName("Atum:AtumSlab").setHardness(2.0F).setCreativeTab(Atum.tabs);
		doubleSlab = (BlockAtumSlab) new BlockAtumSlab(ConfigAtum.doubleSlabID, true, Material.rock).setUnlocalizedName("Atum:AtumDoubleSlab").setHardness(2.0F);
		smoothStairs = (new BlockAtumStairs(ConfigAtum.smoothStairsID, stone, 0)).setUnlocalizedName("Atum:SmoothStair").setCreativeTab(Atum.tabs);
		cobbleStairs = (new BlockAtumStairs(ConfigAtum.cobbleStairsID, cobble, 0)).setUnlocalizedName("Atum:CobbleStair").setCreativeTab(Atum.tabs);
		largeStoneStairs = (new BlockAtumStairs(ConfigAtum.largeStoneStairsID, largeBrick, 0)).setUnlocalizedName("Atum:LargeStoneStair").setCreativeTab(Atum.tabs);
		smallStoneStairs = (new BlockAtumStairs(ConfigAtum.smallStoneStairsID, smallBrick, 0)).setUnlocalizedName("Atum:SmallStoneStair").setCreativeTab(Atum.tabs);
		shrub = (new BlockShrub(ConfigAtum.shrubID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("Atum:Shrub").setCreativeTab(Atum.tabs);
		weed = (new BlockShrub(ConfigAtum.weedID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("Atum:Weed").setCreativeTab(Atum.tabs);
		papyrus = (new BlockPapyrus(ConfigAtum.papyrusBlockID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("Atum:AtumPapyrus");
		wall = (new BlockAtumWall(ConfigAtum.wallID, stone)).setUnlocalizedName("Atum:AtumStoneWall").setHardness(0.3F).setCreativeTab(Atum.tabs);
		crystalGlass = (new BlockAtumGlass(ConfigAtum.crystalGlassID, "Atum:AtumCrystalGlass", Material.glass, false)).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("Atum:AtumCrystalGlass").setHardness(0.3F).setCreativeTab(Atum.tabs);
		framedGlass = (new BlockAtumGlass(ConfigAtum.framedGlassID, "Atum:AtumFramedGlass", Material.glass, false)).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("Atum:AtumFramedGlass").setCreativeTab(Atum.tabs);
		palmSapling = (new BlockPalmSapling(ConfigAtum.palmSaplingID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("Atum:AtumPalmSapling").setCreativeTab(Atum.tabs);
		dateBlock = (new BlockDate(ConfigAtum.blockDateID, Material.plants)).setHardness(0.0F).setUnlocalizedName("Atum:AtumDate");
		flax = (new BlockFlax(ConfigAtum.flaxBlockID)).setUnlocalizedName("Atum:FlaxBlock").setCreativeTab(Atum.tabs);

	    thinCrystalGlass = (new BlockAtumPane(ConfigAtum.thinCrystalGlassID, "Atum:AtumCrystalGlass", "thinglass_top", Material.glass, false)).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("thinCrystalGlass").setCreativeTab(Atum.tabs);
	    thinFramedGlass = (new BlockAtumPane(ConfigAtum.thinFramedGlassID, "Atum:AtumFramedGlass", "Atum:thinglass_top", Material.glass, false)).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("thinFramedGlass").setCreativeTab(Atum.tabs);
		
	    sandLayered = (new BlockSandLayered(ConfigAtum.sandLayeredID)).setHardness(0.1F).setStepSound(Block.soundSnowFootstep).setUnlocalizedName("SandLayered").setLightOpacity(0).setCreativeTab(Atum.tabs);
	    
	    fertileSoil = (BlockFertileSoil) new BlockFertileSoil(ConfigAtum.fertileSoilID).setUnlocalizedName("Atum:FertileSoil").setHardness(0.5F).setStepSound(Block.soundGrassFootstep).setCreativeTab(Atum.tabs);
		fertileSoilTilled = new BlockFertileSoilTilled(ConfigAtum.fertileSoilTillID).setUnlocalizedName("Atum:FertileSoilTilled").setHardness(0.5F).setStepSound(Block.soundGrassFootstep).setCreativeTab(Atum.tabs);
	    log = new BlockPalmLog(ConfigAtum.logID).setUnlocalizedName("AtumLogs").setHardness(2F).setStepSound(Block.soundWoodFootstep).setCreativeTab(Atum.tabs);
		leaves = new BlockAtumLeaves(ConfigAtum.leavesID).setUnlocalizedName("AtumLeaves").setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("AtumLeaves").setCreativeTab(Atum.tabs);
	    planks = (new Block(ConfigAtum.plankID, Material.wood)).setUnlocalizedName("AtumPlanks").setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("Atum:Planks").setCreativeTab(Atum.tabs);
		
		trapArrow = new BlockArrowTrap(ConfigAtum.trapArrowID).setUnlocalizedName("FireTrap").setHardness(0.2F).setCreativeTab(Atum.tabs);
	    furnaceIdle = (new BlockLimeStoneFurnace(ConfigAtum.furnaceIdleID, false)).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("limestonefurnaceidle").setCreativeTab(Atum.tabs);
	    furnaceBurning = (new BlockLimeStoneFurnace(ConfigAtum.furnaceBurningID, true)).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setLightValue(0.875F).setUnlocalizedName("limestonefurnaceactive");
		
	    redstoneOre = new BlockAtumRedstone(ConfigAtum.redstoneOreID).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("Atum:AtumRedstone").setCreativeTab(Atum.tabs);
	    goldOre = (new BlockAtumOre(ConfigAtum.goldOreID)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("Atum:AtumGold").setCreativeTab(Atum.tabs);
	    ironOre = (new BlockAtumOre(ConfigAtum.ironOreID)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("Atum:AtumIron").setCreativeTab(Atum.tabs);
	    coalOre = (new BlockAtumOre(ConfigAtum.coalOreID)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("Atum:AtumCoal").setCreativeTab(Atum.tabs);
	    lapisOre = (new BlockAtumOre(ConfigAtum.lapisOreID)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("Atum:AtumLapis").setCreativeTab(Atum.tabs);
	    diamondOre = (new BlockAtumOre(ConfigAtum.diamondOreID)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("Atum:AtumDiamond").setCreativeTab(Atum.tabs);

		ForgeHooks.canToolHarvestBlock(sand, 0, new ItemStack(Item.shovelIron));
		MinecraftForge.setBlockHarvestLevel(sand, "shovel", 0);
		
		MinecraftForge.setBlockHarvestLevel(AtumBlocks.coalOre, "pickaxe", 0);
		MinecraftForge.setBlockHarvestLevel(AtumBlocks.ironOre, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(AtumBlocks.goldOre, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(AtumBlocks.lapisOre, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(AtumBlocks.diamondOre, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(AtumBlocks.redstoneOre, "pickaxe", 2);
		
		Block.setBurnProperties(ConfigAtum.plankID, 5, 20);
		Block.setBurnProperties(ConfigAtum.leavesID, 30, 60);
	}
	
	public static void registerBlocks()
	{
		GameRegistry.registerBlock(sand, "AtumSand");
		GameRegistry.registerBlock(stone, "AtumStone");
		GameRegistry.registerBlock(cobble, "AtumCobble");
		GameRegistry.registerBlock(largeBrick, "AtumBrickLarge");
		GameRegistry.registerBlock(smallBrick, "AtumBrickSmall");
		GameRegistry.registerBlock(carvedBrick, "AtumBrickCarved");
		GameRegistry.registerBlock(crackedLargeBrick, "AtumCrackedLargeBrick");
		GameRegistry.registerBlock(slabs, "AtumSlabs");
		GameRegistry.registerBlock(smoothStairs, "AtumSmoothStairs");
		GameRegistry.registerBlock(cobbleStairs, "AtumCobbleStairs");
		GameRegistry.registerBlock(largeStoneStairs, "AtumLargeStoneStairs");
		GameRegistry.registerBlock(smallStoneStairs, "AtumSmallStoneStairs");
		GameRegistry.registerBlock(shrub, "AtumShrub");
		GameRegistry.registerBlock(log, "AtumLog");
		GameRegistry.registerBlock(leaves, "AtumLeaves");
		GameRegistry.registerBlock(planks, "AtumPlanks");
		GameRegistry.registerBlock(weed, "AtumWeed");
		GameRegistry.registerBlock(trapArrow, "AtumArmorTrap");
		GameRegistry.registerBlock(cursedChest, "BlockCursedChest");
		GameRegistry.registerBlock(pharaohChest, "BlockPharaohChest");
		GameRegistry.registerBlock(sandLayered, ItemSandLayered.class, "BlockSandLayered");
		GameRegistry.registerBlock(furnaceIdle, "limestonefurnaceidle");
		GameRegistry.registerBlock(furnaceBurning, "limestonefurnaceburning");
		GameRegistry.registerBlock(redstoneOre, "atumRedstoneOre");
		GameRegistry.registerBlock(coalOre, "atumCoalOre");
		GameRegistry.registerBlock(ironOre, "atumIronOre");
		GameRegistry.registerBlock(goldOre, "atumGoldOre");
		GameRegistry.registerBlock(lapisOre, "atumLapisOre");
		GameRegistry.registerBlock(diamondOre, "atumDiamondOre");
		GameRegistry.registerBlock(papyrus, "atumPapyrusBlock");
		GameRegistry.registerBlock(wall, ItemBlockAtumWall.class, "AtumWalls");
		GameRegistry.registerBlock(crystalGlass, "AtumCrystalGlass");
		GameRegistry.registerBlock(framedGlass, "AtumFramedGlass");
		GameRegistry.registerBlock(palmSapling, "AtumPalmSapling");
		GameRegistry.registerBlock(dateBlock, "AtumDateBlock");
		GameRegistry.registerBlock(flax, "Flax");
		GameRegistry.registerBlock(fertileSoil, "FertileSoil");
		GameRegistry.registerBlock(fertileSoilTilled, "FertileSoilTilled");
		GameRegistry.registerBlock(thinCrystalGlass, "ThinCrystalGlass");
		GameRegistry.registerBlock(thinFramedGlass, "ThinFramedGlass");

		GameRegistry.registerTileEntity(TileEntityChestSpawner.class, "CursedChest");
		GameRegistry.registerTileEntity(TileEntityPharaohChest.class, "PharaohChest");
		GameRegistry.registerTileEntity(TileEntityArrowTrap.class, "ArrowTrap");
		GameRegistry.registerTileEntity(TileEntityLimestoneFurnace.class, "LimestoneFurnace");
	}
	
	public static  void addNames()
	{
		LanguageRegistry.addName(stone, "Limestone");
		LanguageRegistry.addName(sand, "Limestone sand");
		LanguageRegistry.addName(cobble, "Cracked Limestone");
		LanguageRegistry.addName(sand, "Strange Sand");
		LanguageRegistry.addName(stone, "Limestone");
		LanguageRegistry.addName(cobble, "Cracked Limestone");
		LanguageRegistry.addName(largeBrick, "Large Limestone Bricks");
		LanguageRegistry.addName(smallBrick, "Small Limestone Bricks");
		LanguageRegistry.addName(carvedBrick, "Carved Limestone");		
		LanguageRegistry.addName(crackedLargeBrick, "Cracked Large Limestone Bricks");		
		LanguageRegistry.addName(smoothStairs, "Limestone Stairs");
		LanguageRegistry.addName(cobbleStairs, "Cracked Limestone Stairs");
		LanguageRegistry.addName(largeStoneStairs, "Large Limestone Brick Stairs");
		LanguageRegistry.addName(smallStoneStairs, "Small Limestone Brick Stairs");
		LanguageRegistry.addName(shrub, "Desert Shrub");
		LanguageRegistry.addName(log, "Palm Wood");
		LanguageRegistry.addName(planks, "Palm Wood Planks");
		LanguageRegistry.addName(leaves, "Palm Leaves");
		LanguageRegistry.addName(weed, "Desert Shrub");
		LanguageRegistry.addName(trapArrow, "Fire Trap");
		LanguageRegistry.addName(cursedChest, "Cursed Chest");
		LanguageRegistry.addName(pharaohChest, "Pharaoh's Chest");
		LanguageRegistry.addName(sandLayered, "Strange Sand");
		LanguageRegistry.addName(furnaceIdle, "Limestone Furnace");
		LanguageRegistry.addName(redstoneOre, "Redstone Ore");
		LanguageRegistry.addName(coalOre, "Coal Ore");
		LanguageRegistry.addName(ironOre, "Iron Ore");
		LanguageRegistry.addName(goldOre, "Gold Ore");
		LanguageRegistry.addName(lapisOre, "Lapis Ore");
		LanguageRegistry.addName(diamondOre, "Diamond Ore");
		LanguageRegistry.addName(new ItemStack(slabs, 6, 0), "Limestone Slabs");
		LanguageRegistry.addName(new ItemStack(slabs, 6, 1), "Cracked Limestone Slabs");
		LanguageRegistry.addName(new ItemStack(slabs, 6, 2), "Large Limestone Brick Slabs");
		LanguageRegistry.addName(new ItemStack(slabs, 6, 3), "Small Limestone Brick Slabs");
		LanguageRegistry.addName(papyrus, "Papyrus");
		LanguageRegistry.addName(new ItemStack(wall, 6, 0), "Limestone Wall");
        LanguageRegistry.addName(new ItemStack(wall, 6, 1), "Cracked Limestone Wall");
        LanguageRegistry.addName(new ItemStack(wall, 6, 2), "Large Limestone Brick Wall");
        LanguageRegistry.addName(new ItemStack(wall, 6, 3), "Small Limestone Brick Wall");
        LanguageRegistry.addName(crystalGlass, "Crystal Glass");
        LanguageRegistry.addName(framedGlass, "Framed Crystal Glass");
        LanguageRegistry.addName(palmSapling, "Palm Sapling");
        LanguageRegistry.addName(dateBlock, "Date Block");
        LanguageRegistry.addName(fertileSoil, "Fertile Soil");
        LanguageRegistry.addName(fertileSoilTilled, "Fertile Soil Tilled");
        LanguageRegistry.addName(thinCrystalGlass, "Crystal Glass Panes");
        LanguageRegistry.addName(thinFramedGlass, "Framed Crystal Glass Panes");
	}
}
