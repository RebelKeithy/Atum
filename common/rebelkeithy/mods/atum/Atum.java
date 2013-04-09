package rebelkeithy.mods.atum;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import rebelkeithy.mods.atum.artifacts.HorusFlight;
import rebelkeithy.mods.atum.artifacts.ItemAkersToil;
import rebelkeithy.mods.atum.artifacts.ItemAtensFury;
import rebelkeithy.mods.atum.artifacts.ItemGebsBlessing;
import rebelkeithy.mods.atum.artifacts.ItemNutsAgility;
import rebelkeithy.mods.atum.artifacts.ItemOsirisWill;
import rebelkeithy.mods.atum.artifacts.ItemPtahsDecadence;
import rebelkeithy.mods.atum.artifacts.ItemRasGlory;
import rebelkeithy.mods.atum.artifacts.ItemSekhmetsWrath;
import rebelkeithy.mods.atum.artifacts.ItemSoteksRage;
import rebelkeithy.mods.atum.blocks.AtumStone;
import rebelkeithy.mods.atum.blocks.BlockArrowTrap;
import rebelkeithy.mods.atum.blocks.BlockAtumLeaves;
import rebelkeithy.mods.atum.blocks.BlockAtumLog;
import rebelkeithy.mods.atum.blocks.BlockAtumPortal;
import rebelkeithy.mods.atum.blocks.BlockAtumSand;
import rebelkeithy.mods.atum.blocks.BlockAtumSlab;
import rebelkeithy.mods.atum.blocks.BlockAtumStairs;
import rebelkeithy.mods.atum.blocks.BlockAtumStone;
import rebelkeithy.mods.atum.blocks.BlockAtumWall;
import rebelkeithy.mods.atum.blocks.BlockPapyrus;
import rebelkeithy.mods.atum.blocks.BlockSandLayered;
import rebelkeithy.mods.atum.blocks.BlockShrub;
import rebelkeithy.mods.atum.blocks.ItemBlockAtumWall;
import rebelkeithy.mods.atum.blocks.ItemPapyrusPlant;
import rebelkeithy.mods.atum.blocks.ItemSandLayered;
import rebelkeithy.mods.atum.blocks.TileEntityArrowTrap;
import rebelkeithy.mods.atum.blocks.ores.BlockAtumOre;
import rebelkeithy.mods.atum.blocks.ores.BlockAtumRedstone;
import rebelkeithy.mods.atum.cursedchest.BlockChestSpawner;
import rebelkeithy.mods.atum.cursedchest.PharaohChest;
import rebelkeithy.mods.atum.cursedchest.TileEntityChestSpawner;
import rebelkeithy.mods.atum.cursedchest.TileEntityPharaohChest;
import rebelkeithy.mods.atum.entities.EntityBanditArcher;
import rebelkeithy.mods.atum.entities.EntityBanditWarrior;
import rebelkeithy.mods.atum.entities.EntityDustySkeleton;
import rebelkeithy.mods.atum.entities.EntityGhost;
import rebelkeithy.mods.atum.entities.EntityMummy;
import rebelkeithy.mods.atum.entities.EntityPharaoh;
import rebelkeithy.mods.atum.entities.EntityStoneSoldier;
import rebelkeithy.mods.atum.furnace.BlockLimeStoneFurnace;
import rebelkeithy.mods.atum.furnace.TileEntityLimestoneFurnace;
import rebelkeithy.mods.atum.items.ItemAtumBow;
import rebelkeithy.mods.atum.items.ItemLoot;
import rebelkeithy.mods.atum.items.ItemScepter;
import rebelkeithy.mods.atum.items.ItemScimitar;
import rebelkeithy.mods.atum.items.ItemStoneSoldierSword;
import rebelkeithy.mods.atum.items.ItemTexturedArmor;
import rebelkeithy.mods.atum.tools.LimestoneAxe;
import rebelkeithy.mods.atum.tools.LimestoneHoe;
import rebelkeithy.mods.atum.tools.LimestonePickaxe;
import rebelkeithy.mods.atum.tools.LimestoneShovel;
import rebelkeithy.mods.atum.tools.LimestoneSword;
import rebelkeithy.mods.atum.world.AtumWorldProvider;
import rebelkeithy.mods.atum.world.biome.BiomeGenAtumDesert;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

// Start of post-modjam branch

@Mod(modid="Atum", name="Atum", version="0.0.0.1")
@NetworkMod(channels = {"Atum"}, clientSideRequired = true, serverSideRequired = false)
public class Atum 
{
	@Instance(value="Atum")
	public static Atum instance;
	
	@SidedProxy(clientSide = "rebelkeithy.mods.atum.ClientProxy", serverSide = "rebelkeithy.mods.atum.CommonProxy")
	public static CommonProxy proxy;
	
	public static AtumTab tabs = new AtumTab("Atum");
	
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
	public static Block atumSandLayered;
	public static Block atumCrackedLargeBrick;
	public static Block atumWall;
	
	public static Block atumShrub;
	public static Block atumWeed;
	public static Block atumPapyrus;
	
	public static Block atumLog;
	public static Block atumLeaves;
	public static Block atumPlanks;
	
	public static Block atumTrapArrow;
	public static Block atumPharaohChest;
	
	public static Block atumRedstoneOre;
	public static Block atumCoalOre;
	public static Block atumIronOre;
	public static Block atumGoldOre;
	public static Block atumLapisOre;
	public static Block atumDiamondOre;
	
	public static Item itemScarab;
	public static Item itemScimitar;
	public static Item itemScepter;
	public static Item itemStoneSoldierSword;
	public static Item itemBow;
	public static Item itemLoot;
	
	public static Item ptahsPick;
	public static Item soteksRage;
	public static Item osirisWill;
	public static Item akersToil;
	public static Item gebsBlessing;
	public static Item atensFury;
	public static Item rasGlory;
	public static Item sekhmetsWrath;
	public static Item nutsAgility;
	public static Item horusFlight;
	
	public static Item limestoneShovel;
	public static Item limestonePickaxe;
	public static Item limestoneAxe;
	public static Item limestoneSword;
	public static Item limestoneHoe;
	
	public static Item mummyHelmet;
	public static Item mummyChest;
	public static Item mummyLegs;
	public static Item mummyBoots;
	
	public static Item itemPapyrusPlant;
	public static Item itemEctoplasm;
	public static Item itemStoneChunk;
	public static Item itemClothScrap;
	
	public static int dimensionID = 17;
	
	public static BiomeGenBase atumDesert;

	public static Block furnaceIdle;
	public static Block furnaceBurning;


	
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		ConfigAtum.initConfig();
		
		portal = new BlockAtumPortal(ConfigAtum.portalBlockID);
		cursedChest = new BlockChestSpawner(ConfigAtum.cursedChestID).setUnlocalizedName("AtumCursedChest").setHardness(4.0F).setCreativeTab(tabs);
		atumPharaohChest = new PharaohChest(ConfigAtum.pharaohChestID).setUnlocalizedName("AtumPharaohChest").setHardness(4.0F).setCreativeTab(tabs);
		atumSand = new BlockAtumSand(ConfigAtum.sandID).setUnlocalizedName("Atum:AtumSand").setStepSound(Block.soundSandFootstep).setHardness(0.5F).setCreativeTab(tabs);
		atumStone = new AtumStone(ConfigAtum.stoneID).setUnlocalizedName("Atum:AtumStone").setHardness(1.5F).setCreativeTab(tabs);
		atumCobble = new Block(ConfigAtum.cobbleID, Material.rock).setUnlocalizedName("Atum:AtumCobble").setHardness(2.0F).setCreativeTab(tabs);
		atumCrackedLargeBrick = new Block(ConfigAtum.crackedLargeBrickID, Material.rock).setUnlocalizedName("Atum:AtumCrackedLargeBrick").setHardness(2.0F).setCreativeTab(tabs);
		atumLargeBrick = new BlockAtumStone(ConfigAtum.largeBrickID, Material.rock).setUnlocalizedName("Atum:AtumBrickLarge").setHardness(2.0F).setCreativeTab(tabs);
		atumSmallBrick = new BlockAtumStone(ConfigAtum.smallBrickID, Material.rock).setUnlocalizedName("Atum:AtumBrickSmall").setHardness(2.0F).setCreativeTab(tabs);
		atumCarvedBrick = new BlockAtumStone(ConfigAtum.carvedBrickID, Material.rock).setUnlocalizedName("Atum:AtumBrickCarved").setHardness(2.0F).setCreativeTab(tabs);
		atumSlabs = (BlockAtumSlab) new BlockAtumSlab(ConfigAtum.slabID, false, Material.rock).setUnlocalizedName("Atum:AtumSlab").setHardness(2.0F).setCreativeTab(tabs);
		atumDoubleSlab = (BlockAtumSlab) new BlockAtumSlab(ConfigAtum.doubleSlabID, true, Material.rock).setUnlocalizedName("Atum:AtumDoubleSlab").setHardness(2.0F);
		atumSmoothStairs = (new BlockAtumStairs(ConfigAtum.smoothStairsID, atumStone, 0)).setUnlocalizedName("Atum:SmoothStair");
		atumCobbleStairs = (new BlockAtumStairs(ConfigAtum.cobbleStairsID, atumCobble, 0)).setUnlocalizedName("Atum:CobbleStair");
		atumLargeStoneStairs = (new BlockAtumStairs(ConfigAtum.largeStoneStairsID, atumLargeBrick, 0)).setUnlocalizedName("Atum:LargeStoneStair");
		atumSmallStoneStairs = (new BlockAtumStairs(ConfigAtum.smallStoneStairsID, atumSmallBrick, 0)).setUnlocalizedName("Atum:SmallStoneStair");
		atumShrub = (new BlockShrub(ConfigAtum.shrubID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("Atum:Shrub");
		atumWeed = (new BlockShrub(ConfigAtum.weedID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("Atum:Weed");
		atumPapyrus = (new BlockPapyrus(ConfigAtum.papyrusBlockID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("Atum:AtumPapyrus").setCreativeTab(tabs);
		atumWall = (new BlockAtumWall(ConfigAtum.wallID, atumStone)).setUnlocalizedName("Atum:AtumStoneWall").setCreativeTab(CreativeTabs.tabBlock);
		
	    atumSandLayered = (new BlockSandLayered(ConfigAtum.sandLayeredID)).setHardness(0.1F).setStepSound(Block.soundSnowFootstep).setUnlocalizedName("SandLayered").setLightOpacity(0).setCreativeTab(tabs);
	    
		atumLog = new BlockAtumLog(ConfigAtum.logID).setUnlocalizedName("AtumLogs").setHardness(1F).setStepSound(Block.soundWoodFootstep);
		atumLeaves = new BlockAtumLeaves(ConfigAtum.leavesID).setUnlocalizedName("AtumLeaves").setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("AtumLeaves").setCreativeTab(tabs);
	    atumPlanks = (new Block(ConfigAtum.plankID, Material.wood)).setUnlocalizedName("AtumPlanks").setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("Atum:Planks").setCreativeTab(tabs);
		
		atumTrapArrow = new BlockArrowTrap(ConfigAtum.trapArrowID).setUnlocalizedName("FireTrap").setHardness(0.2F);
	    furnaceIdle = (new BlockLimeStoneFurnace(ConfigAtum.furnaceIdleID, false)).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("limestonefurnaceidle").setCreativeTab(tabs);
	    furnaceBurning = (new BlockLimeStoneFurnace(ConfigAtum.furnaceBurningID, true)).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setLightValue(0.875F).setUnlocalizedName("limestonefurnaceactive");
		
	    atumRedstoneOre = new BlockAtumRedstone(ConfigAtum.redstoneOreID).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("Atum:AtumRedstone").setCreativeTab(tabs);
	    atumGoldOre = (new BlockAtumOre(ConfigAtum.goldOreID)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("Atum:AtumGold").setCreativeTab(tabs);
	    atumIronOre = (new BlockAtumOre(ConfigAtum.ironOreID)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("Atum:AtumIron").setCreativeTab(tabs);
	    atumCoalOre = (new BlockAtumOre(ConfigAtum.coalOreID)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("Atum:AtumCoal").setCreativeTab(tabs);
	    atumLapisOre = (new BlockAtumOre(ConfigAtum.lapisOreID)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("Atum:AtumLapis").setCreativeTab(tabs);
	    atumDiamondOre = (new BlockAtumOre(ConfigAtum.diamondOreID)).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("Atum:AtumDiamond").setCreativeTab(tabs);
		
		ForgeHooks.canToolHarvestBlock(atumSand, 0, new ItemStack(Item.shovelSteel));
		MinecraftForge.setBlockHarvestLevel(atumSand, "shovel", 0);
		
		
		LanguageRegistry.addName(atumStone, "Limestone");
		LanguageRegistry.addName(atumSand, "Limestone sand");
		LanguageRegistry.addName(atumCobble, "Cracked Limestone");
		
		//EntityRegistry.registerModEntity(EntityMummy.class, "AtumMummy", ConfigAtum.mummyID, this, 16, 20, true);
		
		ArrayList<BiomeGenBase> biomeList = new ArrayList<BiomeGenBase>();
		for(int i = 0; i < BiomeGenBase.biomeList.length; i++)
		{
			if(BiomeGenBase.biomeList[i] != null && BiomeGenBase.biomeList[i].biomeID != ConfigAtum.biomeAtumDesertID)
			{
				biomeList.add(BiomeGenBase.biomeList[i]);
			}
		}
		
		int entityID;
		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityMummy.class, "AtumMummy", entityID);
		EntityList.addMapping(EntityMummy.class, "AtumMummy", entityID, 0x515838, 0x868F6B);

		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityBanditWarrior.class, "AtumBanditWarrior", entityID);
		EntityList.addMapping(EntityBanditWarrior.class, "AtumBanditWarrior", entityID, 0xC2C2C2, 0x040F85);

		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityBanditArcher.class, "AtumBanditArcher", entityID);
		EntityList.addMapping(EntityBanditArcher.class, "AtumBanditArcher", entityID, 0xC2C2C2, 0x7E0C0C);

		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityPharaoh.class, "AtumPharaoh", entityID);
		EntityList.addMapping(EntityPharaoh.class, "AtumPharaoh", entityID, 0xD4BC37, 0x3A4BE0);

		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityDustySkeleton.class, "AtumDustySkeleton", entityID);
		EntityList.addMapping(EntityDustySkeleton.class, "AtumDustySkeleton", entityID, 0xB59C7D, 0x6F5C43);

		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityGhost.class, "AtumDesertGhost", entityID);
		EntityList.addMapping(EntityGhost.class, "AtumDesertGhost", entityID, 0xE7DBC8, 0xAD9467);

		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityStoneSoldier.class, "AtumStoneSoldier", entityID);
		EntityList.addMapping(EntityStoneSoldier.class, "AtumStoneSoldier", entityID, 0x918354, 0x695D37);
		
		LanguageRegistry.instance().addStringLocalization("entity.AtumMummy.name", "Mummy");
		LanguageRegistry.instance().addStringLocalization("entity.AtumBanditWarrior.name", "Brigand");
		LanguageRegistry.instance().addStringLocalization("entity.AtumBanditArcher.name", "Nomad");
		LanguageRegistry.instance().addStringLocalization("entity.AtumPharaoh.name", "Pharaoh");
		LanguageRegistry.instance().addStringLocalization("entity.AtumDustySkeleton.name", "Forsaken");
		LanguageRegistry.instance().addStringLocalization("entity.AtumDesertGhost.name", "Wraith");
		LanguageRegistry.instance().addStringLocalization("entity.AtumStoneSoldier.name", "Tombguard");
		
		//EntityList.addMapping(EntityBandit.class, "AtumBanditArcher", ConfigAtum.banditArcherID, 0xC2C2C2, 0x070C0C);
		
		GameRegistry.registerBlock(atumSand, "AtumSand");
		GameRegistry.registerBlock(atumStone, "AtumStone");
		GameRegistry.registerBlock(atumCobble, "AtumCobble");
		GameRegistry.registerBlock(atumLargeBrick, "AtumBrickLarge");
		GameRegistry.registerBlock(atumSmallBrick, "AtumBrickSmall");
		GameRegistry.registerBlock(atumCarvedBrick, "AtumBrickCarved");
		GameRegistry.registerBlock(atumCrackedLargeBrick, "AtumCrackedLargeBrick");
		GameRegistry.registerBlock(atumSlabs, "AtumSlabs");
		GameRegistry.registerBlock(atumSmoothStairs, "AtumSmoothStairs");
		GameRegistry.registerBlock(atumCobbleStairs, "AtumCobbleStairs");
		GameRegistry.registerBlock(atumLargeStoneStairs, "AtumLargeStoneStairs");
		GameRegistry.registerBlock(atumSmallStoneStairs, "AtumSmallStoneStairs");
		GameRegistry.registerBlock(atumShrub, "AtumShrub");
		GameRegistry.registerBlock(atumLog, "AtumLog");
		GameRegistry.registerBlock(atumLeaves, "AtumLeaves");
		GameRegistry.registerBlock(atumPlanks, "AtumPlanks");
		GameRegistry.registerBlock(atumWeed, "AtumWeed");
		GameRegistry.registerBlock(atumTrapArrow, "AtumArmorTrap");
		GameRegistry.registerBlock(cursedChest, "BlockCursedChest");
		GameRegistry.registerBlock(atumPharaohChest, "BlockPharaohChest");
		GameRegistry.registerBlock(atumSandLayered, ItemSandLayered.class, "BlockSandLayered");
		GameRegistry.registerBlock(furnaceIdle, "limestonefurnaceidle");
		GameRegistry.registerBlock(furnaceBurning, "limestonefurnaceburning");
		GameRegistry.registerBlock(atumRedstoneOre, "atumRedstoneOre");
		GameRegistry.registerBlock(atumCoalOre, "atumCoalOre");
		GameRegistry.registerBlock(atumIronOre, "atumIronOre");
		GameRegistry.registerBlock(atumGoldOre, "atumGoldOre");
		GameRegistry.registerBlock(atumLapisOre, "atumLapisOre");
		GameRegistry.registerBlock(atumDiamondOre, "atumDiamondOre");
		GameRegistry.registerBlock(atumPapyrus, "atumPapyrusBlock");
		GameRegistry.registerBlock(atumWall, "AtumWalls");
		
		GameRegistry.registerTileEntity(TileEntityChestSpawner.class, "CursedChest");
		GameRegistry.registerTileEntity(TileEntityPharaohChest.class, "PharaohChest");
		GameRegistry.registerTileEntity(TileEntityArrowTrap.class, "ArrowTrap");
		GameRegistry.registerTileEntity(TileEntityLimestoneFurnace.class, "LimestoneFurnace");
		
		Item.itemsList[ConfigAtum.slabID] = (new ItemSlab(atumSlabs.blockID - 256, atumSlabs, atumDoubleSlab, false)).setUnlocalizedName("woodSlab");
        Item.itemsList[atumDoubleSlab.blockID] = (new ItemSlab(atumDoubleSlab.blockID - 256, atumSlabs, atumDoubleSlab, true)).setUnlocalizedName("woodSlab");
        Item.itemsList[ConfigAtum.wallID] = (new ItemBlockAtumWall(atumWall.blockID - 256)).setUnlocalizedName("atumWall");
        
		
		itemScarab = new ItemPortalSpawner(ConfigAtum.portalSpawnerID).setUnlocalizedName("Atum:Scarab").setCreativeTab(tabs);
		atumDesert = (new BiomeGenAtumDesert(ConfigAtum.biomeAtumDesertID)).setColor(16421912).setBiomeName("AtumDesert").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, 0.2F);
		itemLoot = new ItemLoot(ConfigAtum.lootID).setCreativeTab(CreativeTabs.tabMisc);
		
		//EnumToolMaterial scimitarEnum = EnumHelper.addToolMaterial("Scimitar", 2, 250, 6.0F, 2, 14);
		itemScimitar = (new ItemScimitar(ConfigAtum.scimitarID, EnumToolMaterial.IRON)).setUnlocalizedName("Atum:Scimitar").setCreativeTab(tabs);
		itemBow = (new ItemAtumBow(ConfigAtum.bowID)).setUnlocalizedName("Atum:Bow").setCreativeTab(tabs);
		itemStoneSoldierSword = new ItemStoneSoldierSword(ConfigAtum.stoneSwordID, EnumToolMaterial.IRON).setUnlocalizedName("Atum:StoneSoldierSword").setCreativeTab(tabs);
		itemScepter = new ItemScepter(ConfigAtum.scepterID, EnumToolMaterial.GOLD).setUnlocalizedName("Atum:Scepter").setCreativeTab(tabs);
		
		ptahsPick = new ItemPtahsDecadence(ConfigAtum.ptahsPickID, EnumToolMaterial.EMERALD).setUnlocalizedName("Atum:PtahsDecadence").setCreativeTab(tabs);
		soteksRage = new ItemSoteksRage(ConfigAtum.soteksRageID, EnumToolMaterial.EMERALD).setUnlocalizedName("Atum:SoteksRage").setCreativeTab(tabs);
		osirisWill = new ItemOsirisWill(ConfigAtum.osirisWillID, EnumToolMaterial.EMERALD).setUnlocalizedName("Atum:OsirisWill").setCreativeTab(tabs);
		akersToil = new ItemAkersToil(ConfigAtum.akersToilID, EnumToolMaterial.EMERALD).setUnlocalizedName("Atum:AkersToil").setCreativeTab(tabs);
		gebsBlessing = new ItemGebsBlessing(ConfigAtum.gebsBlessingID, EnumToolMaterial.EMERALD).setUnlocalizedName("Atum:GebsBlessing").setCreativeTab(tabs);
		atensFury = new ItemAtensFury(ConfigAtum.atensFuryID).setUnlocalizedName("Atum:AtensFury").setCreativeTab(tabs);
		rasGlory = new ItemRasGlory(ConfigAtum.rasGloryID, EnumArmorMaterial.DIAMOND, 0, 0).setTextureFile("EgyptianArmor_1").setUnlocalizedName("Atum:RasGlory").setCreativeTab(tabs);
		sekhmetsWrath = new ItemSekhmetsWrath(ConfigAtum.sekhmetsWrathID, EnumArmorMaterial.DIAMOND, 1, 1).setTextureFile("EgyptianArmor_1").setUnlocalizedName("Atum:SekhmetsWrath").setCreativeTab(tabs);
		nutsAgility = new ItemNutsAgility(ConfigAtum.nutsAgilityID, EnumArmorMaterial.DIAMOND, 2, 2).setTextureFile("EgyptianArmor_2").setUnlocalizedName("Atum:NutsAgility").setCreativeTab(tabs);
		horusFlight = new HorusFlight(ConfigAtum.horusFlightID, EnumArmorMaterial.DIAMOND, 3, 3).setTextureFile("EgyptianArmor_2").setUnlocalizedName("Atum:HorusFlight").setCreativeTab(tabs);
		
		limestoneShovel = new LimestoneShovel(ConfigAtum.limestoneShovelID, EnumToolMaterial.STONE).setUnlocalizedName("Atum:LimestoneShovel").setCreativeTab(tabs);
		limestonePickaxe = new LimestonePickaxe(ConfigAtum.limestonePickaxeID, EnumToolMaterial.STONE).setUnlocalizedName("Atum:LimestonePickaxe").setCreativeTab(tabs);
		limestoneAxe = new LimestoneAxe(ConfigAtum.limestoneAxeID, EnumToolMaterial.STONE).setUnlocalizedName("Atum:LimestoneAxe").setCreativeTab(tabs);
		limestoneSword = new LimestoneSword(ConfigAtum.limestoneSwordID, EnumToolMaterial.STONE).setUnlocalizedName("Atum:LimestoneSword").setCreativeTab(tabs);
		limestoneHoe = new LimestoneHoe(ConfigAtum.limestoneHoeID, EnumToolMaterial.STONE).setUnlocalizedName("Atum:LimestoneHoe").setCreativeTab(tabs);

	    mummyHelmet = (ItemTexturedArmor)(new ItemTexturedArmor(ConfigAtum.mummyHelmetID, EnumArmorMaterial.GOLD, 0, 0)).setTextureFile("MummyArmor_1").setUnlocalizedName("Atum:MummyHelmet");
	    mummyChest = (ItemTexturedArmor)(new ItemTexturedArmor(ConfigAtum.mummyChestID, EnumArmorMaterial.GOLD, 0, 1)).setTextureFile("MummyArmor_1").setUnlocalizedName("Atum:MummyChest");
	    mummyLegs = (ItemTexturedArmor)(new ItemTexturedArmor(ConfigAtum.mummyLegsID, EnumArmorMaterial.GOLD, 0, 2)).setTextureFile("MummyArmor_2").setUnlocalizedName("Atum:MummyLegs");
	    mummyBoots = (ItemTexturedArmor)(new ItemTexturedArmor(ConfigAtum.mummyBootsID, EnumArmorMaterial.GOLD, 0, 3)).setTextureFile("MummyArmor_2").setUnlocalizedName("Atum:MummyBoots");
	    
		itemPapyrusPlant = new ItemPapyrusPlant(ConfigAtum.itemPapyrusPlantID, atumPapyrus).setUnlocalizedName("Atum:PapyrusPlantItem").setCreativeTab(tabs);
		itemEctoplasm = new Item(ConfigAtum.ectoplasmID).setUnlocalizedName("Atum:Ectoplasm").setCreativeTab(tabs);
		itemStoneChunk = new Item(ConfigAtum.stoneChunkID).setUnlocalizedName("Atum:StoneChunk").setCreativeTab(tabs);
		itemClothScrap = new Item(ConfigAtum.clothScrapID).setUnlocalizedName("Atum:ClothScrap").setCreativeTab(tabs);
		
		MinecraftForge.setToolClass(akersToil, "shovel", 4);
		MinecraftForge.setToolClass(limestoneShovel, "shovel", 1);
		MinecraftForge.setToolClass(limestoneAxe, "axe", 1);
		
		MinecraftForge.setBlockHarvestLevel(atumCoalOre, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(atumIronOre, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(atumGoldOre, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(atumLapisOre, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(atumDiamondOre, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(atumRedstoneOre, "pickaxe", 2);

		LanguageRegistry.addName(atumSand, "Strange Sand");
		LanguageRegistry.addName(atumStone, "Limestone");
		LanguageRegistry.addName(atumCobble, "Cracked Limestone");
		LanguageRegistry.addName(atumLargeBrick, "Large Limestone Bricks");
		LanguageRegistry.addName(atumSmallBrick, "Small Limestone Bricks");
		LanguageRegistry.addName(atumCarvedBrick, "Carved Limestone");		
		LanguageRegistry.addName(atumCrackedLargeBrick, "Cracked Large Limestone Bricks");		
		LanguageRegistry.addName(atumSmoothStairs, "Limestone Stairs");
		LanguageRegistry.addName(atumCobbleStairs, "Cracked Limestone Stairs");
		LanguageRegistry.addName(atumLargeStoneStairs, "Large Limestone Brick Stairs");
		LanguageRegistry.addName(atumSmallStoneStairs, "Small Limestone Brick Stairs");
		LanguageRegistry.addName(atumShrub, "Desert Shrub");
		LanguageRegistry.addName(atumLog, "Palm Log");
		LanguageRegistry.addName(atumPlanks, "Palm Planks");
		LanguageRegistry.addName(atumLeaves, "Palm Leaves");
		LanguageRegistry.addName(atumWeed, "Desert Shrub");
		LanguageRegistry.addName(atumTrapArrow, "Fire Trap");
		LanguageRegistry.addName(cursedChest, "Cursed Chest");
		LanguageRegistry.addName(atumPharaohChest, "Pharaoh's Chest");
		LanguageRegistry.addName(atumSandLayered, "Strange Sand");
		LanguageRegistry.addName(furnaceIdle, "Limestone Furnace");
		LanguageRegistry.addName(atumRedstoneOre, "Redstone Ore");
		LanguageRegistry.addName(atumCoalOre, "Coal Ore");
		LanguageRegistry.addName(atumIronOre, "Iron Ore");
		LanguageRegistry.addName(atumGoldOre, "Gold Ore");
		LanguageRegistry.addName(atumLapisOre, "Lapis Ore");
		LanguageRegistry.addName(atumDiamondOre, "Diamond Ore");
		LanguageRegistry.addName(new ItemStack(atumSlabs, 6, 0), "Limestone Slabs");
		LanguageRegistry.addName(new ItemStack(atumSlabs, 6, 1), "Cracked Limestone Slabs");
		LanguageRegistry.addName(new ItemStack(atumSlabs, 6, 2), "Large Limestone Brick Slabs");
		LanguageRegistry.addName(new ItemStack(atumSlabs, 6, 3), "Small Limestone Brick Slabs");
		LanguageRegistry.addName(atumPapyrus, "Papyrus");
		LanguageRegistry.addName(new ItemStack(atumWall, 6, 0), "Limestone Wall");
        LanguageRegistry.addName(new ItemStack(atumWall, 6, 1), "Cracked Limestone Wall");
        LanguageRegistry.addName(new ItemStack(atumWall, 6, 2), "Large Limestone Brick Wall");
        LanguageRegistry.addName(new ItemStack(atumWall, 6, 3), "Small Limestone Brick Wall");
		
		LanguageRegistry.addName(itemScarab, "Golden Scarab");
		LanguageRegistry.addName(itemScimitar, "Scimitar");
		LanguageRegistry.addName(itemBow, "Shortbow");
		LanguageRegistry.addName(itemStoneSoldierSword, "Aged Stone Sword");
		LanguageRegistry.addName(itemEctoplasm, "Ectoplasm");
		LanguageRegistry.addName(itemStoneChunk, "Limestone Chunk");
		LanguageRegistry.addName(itemClothScrap, "Cloth Scrap");
		LanguageRegistry.addName(itemScepter, "Royal Scepter");
		LanguageRegistry.addName(itemPapyrusPlant, "Papyrus");
		
		LanguageRegistry.addName(ptahsPick, "Ptah's Decadence");
		LanguageRegistry.addName(soteksRage, "Sotek's Rage");
		LanguageRegistry.addName(osirisWill, "Osiris's Will");
		LanguageRegistry.addName(akersToil, "Aker's Toil");
		LanguageRegistry.addName(gebsBlessing, "Geb's Blessing");
		LanguageRegistry.addName(atensFury, "Aten's Fury");
		LanguageRegistry.addName(rasGlory, "Ra's Glory");
		LanguageRegistry.addName(sekhmetsWrath, "Sekhmet's Wrath");
		LanguageRegistry.addName(nutsAgility, "Nut's Agility");
		LanguageRegistry.addName(horusFlight, "Horus's Flight");
		
		LanguageRegistry.addName(limestoneShovel, "Limestone Shovel");
		LanguageRegistry.addName(limestonePickaxe, "Limestone Pickaxe");
		LanguageRegistry.addName(limestoneAxe, "Limestone Axe");
		LanguageRegistry.addName(limestoneSword, "Limestone Sword");
		LanguageRegistry.addName(limestoneHoe, "Limestone Hoe");
		
		LanguageRegistry.addName(mummyHelmet, "Head Wrap");
		LanguageRegistry.addName(mummyChest, "Chest Wrap");
		LanguageRegistry.addName(mummyLegs, "Leg Wrap");
		LanguageRegistry.addName(mummyBoots, "Feet Wrap");
		
		LanguageRegistry.instance().addStringLocalization("itemGroup.Atum", "Atum");

		proxy.registerModelRenderers();
		proxy.registerTickHandlers();
		proxy.preloadImages();
		proxy.registerParticles();
		MinecraftForge.EVENT_BUS.register(new FallDamageListener());
		MinecraftForge.EVENT_BUS.register(new LootTossListener());
		NetworkRegistry.instance().registerGuiHandler(this, new AtumGuiHandler());
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
		FurnaceRecipes.smelting().addSmelting(atumSand.blockID, new ItemStack(Block.glass), 0.1F);
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(atumLargeBrick, 4), "XX", "XX", 'X', atumStone));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(atumSmallBrick, 4), "XX", "XX", 'X', atumCobble));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(atumCarvedBrick, 1), atumStone));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(furnaceIdle, 1), "XXX", "X.X", "XXX", 'X', atumCobble));
		GameRegistry.addRecipe(new ItemStack(atumSmoothStairs, 6), "X  ", "XX ", "XXX", 'X', atumStone);
		GameRegistry.addRecipe(new ItemStack(atumCobbleStairs, 6), "X  ", "XX ", "XXX", 'X', atumCobble);
		GameRegistry.addRecipe(new ItemStack(atumLargeStoneStairs, 6), "X  ", "XX ", "XXX", 'X', atumLargeBrick);
		GameRegistry.addRecipe(new ItemStack(atumSmallStoneStairs, 6), "X  ", "XX ", "XXX", 'X', atumSmallBrick);
		GameRegistry.addRecipe(new ItemStack(atumSlabs, 6, 0), "XXX", 'X', atumStone);
		GameRegistry.addRecipe(new ItemStack(atumSlabs, 6, 1), "XXX", 'X', atumCobble);
		GameRegistry.addRecipe(new ItemStack(atumSlabs, 6, 2), "XXX", 'X', atumLargeBrick);
		GameRegistry.addRecipe(new ItemStack(atumSlabs, 6, 3), "XXX", 'X', atumSmallBrick);
		GameRegistry.addRecipe(new ItemStack(atumWall, 6, 0), "XXX", "XXX", 'X', atumStone);
        GameRegistry.addRecipe(new ItemStack(atumWall, 6, 1), "XXX", "XXX", 'X', atumCobble);
        GameRegistry.addRecipe(new ItemStack(atumWall, 6, 2), "XXX", "XXX", 'X', atumLargeBrick);
        GameRegistry.addRecipe(new ItemStack(atumWall, 6, 3), "XXX", "XXX", 'X', atumSmallBrick);
		
		GameRegistry.addRecipe(new ItemStack(atumSlabs, 6, 3), "XXX", 'X', atumSmallBrick);
		GameRegistry.addRecipe(new ItemStack(atumCrackedLargeBrick, 4), "XX", "XX", 'X', itemStoneChunk);
		GameRegistry.addRecipe(new ItemStack(Item.expBottle), " X ", "XBX", " X ", 'X', itemEctoplasm, 'B', Item.potion);
		
		GameRegistry.addRecipe(new ItemStack(limestoneShovel), " L ", " S ", " S ", 'L', atumStone, 'S', Item.stick);
		GameRegistry.addRecipe(new ItemStack(limestonePickaxe), "LLL", " S ", " S ", 'L', atumStone, 'S', Item.stick);
		GameRegistry.addRecipe(new ItemStack(limestoneAxe), "LL ", "LS ", " S ", 'L', atumStone, 'S', Item.stick);
		GameRegistry.addRecipe(new ItemStack(limestoneSword), " L ", " S ", " S ", 'L', atumStone, 'S', Item.stick);
		GameRegistry.addRecipe(new ItemStack(limestoneHoe), "LLL", " S ", " S ", 'L', atumStone, 'S', Item.stick);
		
		GameRegistry.addRecipe(new ItemStack(itemScarab), " G ", "GDG", " G ", 'G', Item.ingotGold, 'D', Item.diamond);
		
		GameRegistry.addShapelessRecipe(new ItemStack(atumPlanks, 4), atumLog);
		OreDictionary.registerOre("plankWood", atumPlanks);
		OreDictionary.registerOre("logWood", atumLog);
		
		GameRegistry.addRecipe(new ItemStack(this.furnaceIdle), "XXX", "X X", "XXX", 'X', atumCobble);
		
		FurnaceRecipes.smelting().addSmelting(this.atumIronOre.blockID, 0, new ItemStack(Item.ingotIron), 0.7F);
		FurnaceRecipes.smelting().addSmelting(this.atumCoalOre.blockID, new ItemStack(Item.coal), 0.1F);
		FurnaceRecipes.smelting().addSmelting(this.atumRedstoneOre.blockID, new ItemStack(Item.redstone), 0.7F);
		FurnaceRecipes.smelting().addSmelting(this.atumLapisOre.blockID, new ItemStack(Item.dyePowder, 1, 4), 0.2F);
		FurnaceRecipes.smelting().addSmelting(this.atumGoldOre.blockID, new ItemStack(Item.ingotGold), 1.0F);
        FurnaceRecipes.smelting().addSmelting(this.atumDiamondOre.blockID, new ItemStack(Item.diamond), 1.0F);
	}
}
