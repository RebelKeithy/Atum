package rebelkeithy.mods.atum;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import rebelkeithy.mods.atum.blocks.AtumStone;
import rebelkeithy.mods.atum.blocks.BlockAtumLeaves;
import rebelkeithy.mods.atum.blocks.BlockAtumLog;
import rebelkeithy.mods.atum.blocks.BlockAtumPortal;
import rebelkeithy.mods.atum.blocks.BlockAtumSand;
import rebelkeithy.mods.atum.blocks.BlockAtumSlab;
import rebelkeithy.mods.atum.blocks.BlockAtumStairs;
import rebelkeithy.mods.atum.blocks.BlockShrub;
import rebelkeithy.mods.atum.cursedchest.BlockChestSpawner;
import rebelkeithy.mods.atum.cursedchest.TileEntityChestSpawner;
import rebelkeithy.mods.atum.entities.EntityMummy;
import rebelkeithy.mods.atum.world.AtumWorldProvider;
import rebelkeithy.mods.atum.world.biome.BiomeGenAtumDesert;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;


@Mod(modid="Atum", name="Atum", version="0.0.0.1")
public class Atum 
{
	@Instance(value="Atum")
	public static Atum instance;
	
	@SidedProxy(clientSide = "rebelkeithy.mods.atum.ClientProxy", serverSide = "rebelkeithy.mods.atum.CommonProxy")
	public static CommonProxy proxy;
	
	public static BlockAtumPortal portal;
	public static Block cursedChest;
	public static Block atumSand;
	public static Block atumStone;
	public static Block atumCobble;
	public static Block atumLargeBrick;
	public static Block atumSmallBrick;
	public static Block atumCarvedBrick;
	public static BlockAtumSlab atumSlabs;
	public static BlockAtumSlab atumDoubleSlab;
	public static Block atumSmoothStairs;
	public static Block atumCobbleStairs;
	public static Block atumLargeStoneStairs;
	public static Block atumSmallStoneStairs;
	
	public static Block atumFurnaceIdle;
	public static Block atumFurnaceActive;
	
	public static Block atumShrub;
	public static Block atumWeed;
	
	public static Block atumLog;
	public static Block atumLeaves;
	
	public static Item itemScarab;

	public static int dimensionID = 17;
	
	public static BiomeGenBase atumDesert;

	
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		portal = new BlockAtumPortal(ConfigAtum.portalBlockID);
		cursedChest = new BlockChestSpawner(ConfigAtum.cursedChestID).setCreativeTab(CreativeTabs.tabDecorations);
		atumSand = new BlockAtumSand(ConfigAtum.sandID).setUnlocalizedName("Atum:AtumSand").setHardness(0.5F).setCreativeTab(CreativeTabs.tabBlock);
		atumStone = new AtumStone(ConfigAtum.stoneID).setUnlocalizedName("Atum:AtumStone").setHardness(1.5F).setCreativeTab(CreativeTabs.tabBlock);
		atumCobble = new Block(ConfigAtum.cobbleID, Material.rock).setUnlocalizedName("Atum:AtumCobble").setHardness(2.0F).setCreativeTab(CreativeTabs.tabBlock);
		atumLargeBrick = new Block(ConfigAtum.largeBrickID, Material.rock).setUnlocalizedName("Atum:AtumBrickLarge").setHardness(2.0F).setCreativeTab(CreativeTabs.tabBlock);
		atumSmallBrick = new Block(ConfigAtum.smallBrickID, Material.rock).setUnlocalizedName("Atum:AtumBrickSmall").setHardness(2.0F).setCreativeTab(CreativeTabs.tabBlock);
		atumCarvedBrick = new Block(ConfigAtum.carvedBrickID, Material.rock).setUnlocalizedName("Atum:AtumBrickCarved").setHardness(2.0F).setCreativeTab(CreativeTabs.tabBlock);
		atumSlabs = (BlockAtumSlab) new BlockAtumSlab(ConfigAtum.slabID, false, Material.rock).setUnlocalizedName("Atum:AtumSlab").setHardness(2.0F).setCreativeTab(CreativeTabs.tabBlock);
		atumDoubleSlab = (BlockAtumSlab) new BlockAtumSlab(ConfigAtum.doubleSlabID, true, Material.rock).setUnlocalizedName("Atum:AtumDoubleSlab").setHardness(2.0F);
		atumSmoothStairs = (new BlockAtumStairs(ConfigAtum.smoothStairsID, atumStone, 0)).setUnlocalizedName("Atum:SmoothStair");
		atumCobbleStairs = (new BlockAtumStairs(ConfigAtum.cobbleStairsID, atumCobble, 0)).setUnlocalizedName("Atum:CobbleStair");
		atumLargeStoneStairs = (new BlockAtumStairs(ConfigAtum.largeStoneStairsID, atumLargeBrick, 0)).setUnlocalizedName("Atum:LargeStoneStair");
		atumSmallStoneStairs = (new BlockAtumStairs(ConfigAtum.smallStoneStairsID, atumSmallBrick, 0)).setUnlocalizedName("Atum:SmallStoneStair");
		atumShrub = (new BlockShrub(ConfigAtum.shrubID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("Atum:Shrub");
		atumWeed = (new BlockShrub(ConfigAtum.weedID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("Atum:Weed");
		
		atumLog = new BlockAtumLog(ConfigAtum.logID).setHardness(1F).setStepSound(Block.soundWoodFootstep);
		atumLeaves = new BlockAtumLeaves(ConfigAtum.leavesID).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("AtumLeaves");
		
		ForgeHooks.canToolHarvestBlock(atumSand, 0, new ItemStack(Item.shovelSteel));
		MinecraftForge.setBlockHarvestLevel(atumSand, "shovel", 0);
		
		RenderingRegistry.registerEntityRenderingHandler(EntityMummy.class, new RenderLiving(new ModelZombie(), 0.5F));
		
		LanguageRegistry.addName(atumStone, "Limestone");
		LanguageRegistry.addName(atumSand, "Limestone sand");
		LanguageRegistry.addName(atumCobble, "Cracked Limestone");
		
		//EntityRegistry.registerModEntity(EntityMummy.class, "AtumMummy", ConfigAtum.mummyID, this, 16, 20, true);
		EntityRegistry.registerGlobalEntityID(EntityMummy.class, "AtumMummy", ConfigAtum.mummyID);
		EntityList.addMapping(EntityMummy.class, "AtumMummy", ConfigAtum.mummyID, 0x770000, 0x220000);
		
		GameRegistry.registerBlock(atumSand, "AtumSand");
		GameRegistry.registerBlock(atumStone, "AtumStone");
		GameRegistry.registerBlock(atumCobble, "AtumCobble");
		GameRegistry.registerBlock(atumLargeBrick, "AtumBrickLarge");
		GameRegistry.registerBlock(atumSmallBrick, "AtumBrickSmall");
		GameRegistry.registerBlock(atumCarvedBrick, "AtumBrickCarved");
		GameRegistry.registerBlock(atumSlabs, "AtumSlabs");
		GameRegistry.registerBlock(atumSmoothStairs, "AtumSmoothStairs");
		GameRegistry.registerBlock(atumCobbleStairs, "AtumCobbleStairs");
		GameRegistry.registerBlock(atumLargeStoneStairs, "AtumLargeStoneStairs");
		GameRegistry.registerBlock(atumSmallStoneStairs, "AtumSmallStoneStairs");
		GameRegistry.registerBlock(atumShrub, "AtumShrub");
		GameRegistry.registerBlock(atumLog, "AtumLog");
		GameRegistry.registerBlock(atumLeaves, "AtumLeaves");
		GameRegistry.registerBlock(atumWeed, "AtumWeed");
		//GameRegistry.registerBlock(atumSlabs, ItemSlab.class);
		GameRegistry.registerBlock(cursedChest, "BlockCursedChest");
		GameRegistry.registerTileEntity(TileEntityChestSpawner.class, "CursedChest");
		
		Item.itemsList[ConfigAtum.slabID] = (new ItemSlab(atumSlabs.blockID - 256, atumSlabs, atumDoubleSlab, false)).setUnlocalizedName("woodSlab");
        Item.itemsList[atumDoubleSlab.blockID] = (new ItemSlab(atumDoubleSlab.blockID - 256, atumSlabs, atumDoubleSlab, true)).setUnlocalizedName("woodSlab");
        
		
		itemScarab = new ItemPortalSpawner(ConfigAtum.portalSpawnerID).setUnlocalizedName("Atum:Scarab").setCreativeTab(CreativeTabs.tabTools);
		atumDesert = (new BiomeGenAtumDesert(ConfigAtum.biomeAtumDesertID)).setColor(16421912).setBiomeName("AtumDesert").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, 0.2F);
	}
	
	@Init
	public void init(FMLInitializationEvent event)
	{
		DimensionManager.registerProviderType(Atum.dimensionID, AtumWorldProvider.class, true);
		DimensionManager.registerDimension(Atum.dimensionID , Atum.dimensionID);


		addRecipes();
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
	
	public void addRecipes()
	{
		FurnaceRecipes.smelting().addSmelting(atumCobble.blockID, new ItemStack(atumStone), 0.1F);
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(atumLargeBrick, 4), "XX", "XX", 'X', atumStone));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(atumSmallBrick, 4), "XX", "XX", 'X', atumCobble));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(atumCarvedBrick, 1), atumStone));
	}
}
