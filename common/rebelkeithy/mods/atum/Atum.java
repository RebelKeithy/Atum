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
import rebelkeithy.mods.atum.artifacts.ItemGabsBlessing;
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
import rebelkeithy.mods.atum.blocks.BlockSandLayered;
import rebelkeithy.mods.atum.blocks.BlockShrub;
import rebelkeithy.mods.atum.blocks.ItemSandLayered;
import rebelkeithy.mods.atum.blocks.TileEntityArrowTrap;
import rebelkeithy.mods.atum.cursedchest.BlockChestSpawner;
import rebelkeithy.mods.atum.cursedchest.TileEntityChestSpawner;
import rebelkeithy.mods.atum.entities.EntityBanditArcher;
import rebelkeithy.mods.atum.entities.EntityBanditWarrior;
import rebelkeithy.mods.atum.entities.EntityDustySkeleton;
import rebelkeithy.mods.atum.entities.EntityGhost;
import rebelkeithy.mods.atum.entities.EntityMummy;
import rebelkeithy.mods.atum.entities.EntityPharoh;
import rebelkeithy.mods.atum.entities.EntityStoneSoldier;
import rebelkeithy.mods.atum.furnace.BlockLimeStoneFurnace;
import rebelkeithy.mods.atum.furnace.TileEntityLimestoneFurnace;
import rebelkeithy.mods.atum.items.ItemAtumBow;
import rebelkeithy.mods.atum.items.ItemScimitar;
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
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

// Start of post-modjam branch

@Mod(modid="Atum", name="Atum", version="0.0.0.1")
@NetworkMod(channels = {"Atum"}, clientSideRequired = true, serverSideRequired = false)
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
	public static Block atumSandLayered;
	
	public static Block atumShrub;
	public static Block atumWeed;
	
	public static Block atumLog;
	public static Block atumLeaves;
	public static Block atumPlanks;
	
	public static Block atumTrapArrow;
	
	public static Item itemScarab;
	public static Item itemScimitar;
	public static Item itemBow;
	
	public static Item ptahsPick;
	public static Item soteksRage;
	public static Item osirisWill;
	public static Item akersToil;
	public static Item gabsBlessing;
	public static Item rasGlory;
	public static Item sekhmetsWrath;
	public static Item nutsAgility;
	public static Item horusFlight;

	public static int dimensionID = 17;
	
	public static BiomeGenBase atumDesert;

	public static Block furnaceIdle;
	public static Block furnaceBurning;

	
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		portal = new BlockAtumPortal(ConfigAtum.portalBlockID);
		cursedChest = new BlockChestSpawner(ConfigAtum.cursedChestID).setCreativeTab(CreativeTabs.tabDecorations);
		atumSand = new BlockAtumSand(ConfigAtum.sandID).setUnlocalizedName("Atum:AtumSand").setStepSound(Block.soundSandFootstep).setHardness(0.5F).setCreativeTab(CreativeTabs.tabBlock);
		atumStone = new AtumStone(ConfigAtum.stoneID).setUnlocalizedName("Atum:AtumStone").setHardness(1.5F).setCreativeTab(CreativeTabs.tabBlock);
		atumCobble = new Block(ConfigAtum.cobbleID, Material.rock).setUnlocalizedName("Atum:AtumCobble").setHardness(2.0F).setCreativeTab(CreativeTabs.tabBlock);
		atumLargeBrick = new BlockAtumStone(ConfigAtum.largeBrickID, Material.rock).setUnlocalizedName("Atum:AtumBrickLarge").setHardness(2.0F).setCreativeTab(CreativeTabs.tabBlock);
		atumSmallBrick = new BlockAtumStone(ConfigAtum.smallBrickID, Material.rock).setUnlocalizedName("Atum:AtumBrickSmall").setHardness(2.0F).setCreativeTab(CreativeTabs.tabBlock);
		atumCarvedBrick = new BlockAtumStone(ConfigAtum.carvedBrickID, Material.rock).setUnlocalizedName("Atum:AtumBrickCarved").setHardness(2.0F).setCreativeTab(CreativeTabs.tabBlock);
		atumSlabs = (BlockAtumSlab) new BlockAtumSlab(ConfigAtum.slabID, false, Material.rock).setUnlocalizedName("Atum:AtumSlab").setHardness(2.0F).setCreativeTab(CreativeTabs.tabBlock);
		atumDoubleSlab = (BlockAtumSlab) new BlockAtumSlab(ConfigAtum.doubleSlabID, true, Material.rock).setUnlocalizedName("Atum:AtumDoubleSlab").setHardness(2.0F);
		atumSmoothStairs = (new BlockAtumStairs(ConfigAtum.smoothStairsID, atumStone, 0)).setUnlocalizedName("Atum:SmoothStair");
		atumCobbleStairs = (new BlockAtumStairs(ConfigAtum.cobbleStairsID, atumCobble, 0)).setUnlocalizedName("Atum:CobbleStair");
		atumLargeStoneStairs = (new BlockAtumStairs(ConfigAtum.largeStoneStairsID, atumLargeBrick, 0)).setUnlocalizedName("Atum:LargeStoneStair");
		atumSmallStoneStairs = (new BlockAtumStairs(ConfigAtum.smallStoneStairsID, atumSmallBrick, 0)).setUnlocalizedName("Atum:SmallStoneStair");
		atumShrub = (new BlockShrub(ConfigAtum.shrubID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("Atum:Shrub");
		atumWeed = (new BlockShrub(ConfigAtum.weedID)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("Atum:Weed");

	    atumSandLayered = (new BlockSandLayered(ConfigAtum.sandLayeredID)).setHardness(0.1F).setStepSound(Block.soundSnowFootstep).setUnlocalizedName("SandLayered").setLightOpacity(0);
	    
		atumLog = new BlockAtumLog(ConfigAtum.logID).setUnlocalizedName("AtumLogs").setHardness(1F).setStepSound(Block.soundWoodFootstep);
		atumLeaves = new BlockAtumLeaves(ConfigAtum.leavesID).setUnlocalizedName("AtumLeaves").setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName("AtumLeaves");
	    atumPlanks = (new Block(ConfigAtum.plankID, Material.wood)).setUnlocalizedName("AtumPlanks").setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("Atum:Planks").setCreativeTab(CreativeTabs.tabBlock);
		
		atumTrapArrow = new BlockArrowTrap(ConfigAtum.trapArrowID).setUnlocalizedName("FireTrap").setHardness(0.2F);
	    furnaceIdle = (new BlockLimeStoneFurnace(ConfigAtum.furnaceIdleID, false)).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("limestonefurnaceidle").setCreativeTab(CreativeTabs.tabDecorations);
	    furnaceBurning = (new BlockLimeStoneFurnace(ConfigAtum.furnaceBurningID, true)).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setLightValue(0.875F).setUnlocalizedName("limestonefurnaceactive");
		
		
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
		EntityRegistry.registerGlobalEntityID(EntityPharoh.class, "AtumPharaoh", entityID);
		EntityList.addMapping(EntityPharoh.class, "AtumPharaoh", entityID, 0xD4BC37, 0x3A4BE0);

		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityDustySkeleton.class, "AtumDustySkeleton", entityID);
		EntityList.addMapping(EntityDustySkeleton.class, "AtumDustySkeleton", entityID, 0xB59C7D, 0x6F5C43);

		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityGhost.class, "AtumDesertGhost", entityID);
		EntityList.addMapping(EntityGhost.class, "AtumDesertGhost", entityID, 0xE7DBC8, 0xAD9467);

		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityStoneSoldier.class, "AtumStoneSoldier", entityID);
		EntityList.addMapping(EntityStoneSoldier.class, "AtumStoneSoldier", entityID, 0x918354, 0x695D37);

		proxy.registerModelRenderers();
		
		LanguageRegistry.instance().addStringLocalization("entity.AtumMummy.name", "Mummy");
		LanguageRegistry.instance().addStringLocalization("entity.AtumBanditWarrior.name", "Bandit Warrior");
		LanguageRegistry.instance().addStringLocalization("entity.AtumBanditArcher.name", "Bandit Archer");
		LanguageRegistry.instance().addStringLocalization("entity.AtumPharaoh.name", "Pharaoh");
		LanguageRegistry.instance().addStringLocalization("entity.AtumDustySkeleton.name", "Dusty Skeleton");
		LanguageRegistry.instance().addStringLocalization("entity.AtumDesertGhost.name", "Desert Ghost");
		LanguageRegistry.instance().addStringLocalization("entity.AtumStoneSoldier.name", "Stone Soldier");
		
		TickRegistry.registerTickHandler(new TickHandler(), Side.CLIENT);		
		TickRegistry.registerTickHandler(new ServerTickHandler(), Side.SERVER);
		
		//EntityList.addMapping(EntityBandit.class, "AtumBanditArcher", ConfigAtum.banditArcherID, 0xC2C2C2, 0x070C0C);
		
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
		GameRegistry.registerBlock(atumPlanks, "AtumPlanks");
		GameRegistry.registerBlock(atumWeed, "AtumWeed");
		GameRegistry.registerBlock(atumTrapArrow, "AtumArmorTrap");
		GameRegistry.registerBlock(cursedChest, "BlockCursedChest");
		GameRegistry.registerBlock(atumSandLayered, ItemSandLayered.class, "BlockSandLayered");
		GameRegistry.registerBlock(furnaceIdle, "limestonefurnaceidle");
		GameRegistry.registerBlock(furnaceBurning, "limestonefurnaceburning");
		
		GameRegistry.registerTileEntity(TileEntityChestSpawner.class, "CursedChest");
		GameRegistry.registerTileEntity(TileEntityArrowTrap.class, "ArrowTrap");
		GameRegistry.registerTileEntity(TileEntityLimestoneFurnace.class, "LimestoneFurnace");
		
		Item.itemsList[ConfigAtum.slabID] = (new ItemSlab(atumSlabs.blockID - 256, atumSlabs, atumDoubleSlab, false)).setUnlocalizedName("woodSlab");
        Item.itemsList[atumDoubleSlab.blockID] = (new ItemSlab(atumDoubleSlab.blockID - 256, atumSlabs, atumDoubleSlab, true)).setUnlocalizedName("woodSlab");
        
		
		itemScarab = new ItemPortalSpawner(ConfigAtum.portalSpawnerID).setUnlocalizedName("Atum:Scarab").setCreativeTab(CreativeTabs.tabTools);
		atumDesert = (new BiomeGenAtumDesert(ConfigAtum.biomeAtumDesertID)).setColor(16421912).setBiomeName("AtumDesert").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, 0.2F);

		//EnumToolMaterial scimitarEnum = EnumHelper.addToolMaterial("Scimitar", 2, 250, 6.0F, 2, 14);
		itemScimitar = (new ItemScimitar(ConfigAtum.scimitarID, EnumToolMaterial.IRON)).setUnlocalizedName("Atum:Scimitar").setCreativeTab(CreativeTabs.tabCombat);
		itemBow = (new ItemAtumBow(ConfigAtum.bowID)).setUnlocalizedName("Atum:Bow").setCreativeTab(CreativeTabs.tabCombat);
		
		ptahsPick = new ItemPtahsDecadence(ConfigAtum.ptahsPickID, EnumToolMaterial.EMERALD).setUnlocalizedName("Atum:PtahsDecadence").setCreativeTab(CreativeTabs.tabTools);
		soteksRage = new ItemSoteksRage(ConfigAtum.soteksRageID, EnumToolMaterial.EMERALD).setUnlocalizedName("Atum:SoteksRage").setCreativeTab(CreativeTabs.tabTools);
		osirisWill = new ItemOsirisWill(ConfigAtum.osirisWillID, EnumToolMaterial.EMERALD).setUnlocalizedName("Atum:OsirisWill").setCreativeTab(CreativeTabs.tabTools);
		akersToil = new ItemAkersToil(ConfigAtum.akersToilID, EnumToolMaterial.EMERALD).setUnlocalizedName("Atum:AkersToil").setCreativeTab(CreativeTabs.tabTools);
		gabsBlessing = new ItemGabsBlessing(ConfigAtum.gabsBlessingID, EnumToolMaterial.EMERALD).setUnlocalizedName("Atum:GabsBlessing").setCreativeTab(CreativeTabs.tabTools);
		rasGlory = new ItemRasGlory(ConfigAtum.rasGloryID, EnumArmorMaterial.DIAMOND, 0, 0).setTextureFile("EgyptianArmor").setUnlocalizedName("Atum:RasGlory").setCreativeTab(CreativeTabs.tabCombat);
		sekhmetsWrath = new ItemSekhmetsWrath(ConfigAtum.sekhmetsWrathID, EnumArmorMaterial.DIAMOND, 1, 1).setTextureFile("EgyptianArmor").setUnlocalizedName("Atum:SekhmetsWrath").setCreativeTab(CreativeTabs.tabCombat);
		nutsAgility = new ItemNutsAgility(ConfigAtum.nutsAgilityID, EnumArmorMaterial.DIAMOND, 2, 2).setTextureFile("EgyptianArmor").setUnlocalizedName("Atum:NutsAgility").setCreativeTab(CreativeTabs.tabCombat);
		horusFlight = new HorusFlight(ConfigAtum.horusFlightID, EnumArmorMaterial.DIAMOND, 3, 3).setTextureFile("EgyptianArmor").setUnlocalizedName("Atum:HorusFlight").setCreativeTab(CreativeTabs.tabCombat);
		
		MinecraftForge.setToolClass(akersToil, "shovel", 4);

		LanguageRegistry.addName(atumSand, "Strange Sand");
		LanguageRegistry.addName(atumStone, "Limestone");
		LanguageRegistry.addName(atumCobble, "Cracked Limestone");
		LanguageRegistry.addName(atumLargeBrick, "Large Limestone Bricks");
		LanguageRegistry.addName(atumSmallBrick, "Small Limestone Bricks");
		LanguageRegistry.addName(atumCarvedBrick, "Carved Limestone");		
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
		LanguageRegistry.addName(atumSandLayered, "Strange Sand");
		LanguageRegistry.addName(furnaceIdle, "Limestone Furnace");
		LanguageRegistry.addName(new ItemStack(atumSlabs, 6, 0), "Limestone Slabs");
		LanguageRegistry.addName(new ItemStack(atumSlabs, 6, 1), "Cracked Limestone Slabs");
		LanguageRegistry.addName(new ItemStack(atumSlabs, 6, 2), "Large Limestone Brick Slabs");
		LanguageRegistry.addName(new ItemStack(atumSlabs, 6, 3), "Small Limestone Brick Slabs");
		
		LanguageRegistry.addName(itemScarab, "Golden Scarab");
		LanguageRegistry.addName(itemScimitar, "Scimitar");
		LanguageRegistry.addName(itemBow, "Desert Bow");
		
		LanguageRegistry.addName(ptahsPick, "Ptah's Decadence");
		LanguageRegistry.addName(soteksRage, "Sotek's Rage");
		LanguageRegistry.addName(osirisWill, "Osiris's Will");
		LanguageRegistry.addName(akersToil, "Aker's Toil");
		LanguageRegistry.addName(gabsBlessing, "Gab's Blessing");
		LanguageRegistry.addName(rasGlory, "Ra's Glory");
		LanguageRegistry.addName(sekhmetsWrath, "Sekhmet's Wrath");
		LanguageRegistry.addName(nutsAgility, "Nut's Agility");
		LanguageRegistry.addName(horusFlight, "Horus's Flight");
		
		proxy.registerParticles();
		
		MinecraftForge.EVENT_BUS.register(new FallDamageListener());
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
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(atumLargeBrick, 4), "XX", "XX", 'X', atumStone));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(atumSmallBrick, 4), "XX", "XX", 'X', atumCobble));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(atumCarvedBrick, 1), atumStone));
		GameRegistry.addRecipe(new ItemStack(atumSmoothStairs, 6), "X  ", "XX ", "XXX", 'X', atumStone);
		GameRegistry.addRecipe(new ItemStack(atumCobbleStairs, 6), "X  ", "XX ", "XXX", 'X', atumCobble);
		GameRegistry.addRecipe(new ItemStack(atumLargeStoneStairs, 6), "X  ", "XX ", "XXX", 'X', atumLargeBrick);
		GameRegistry.addRecipe(new ItemStack(atumSmallStoneStairs, 6), "X  ", "XX ", "XXX", 'X', atumSmallBrick);
		GameRegistry.addRecipe(new ItemStack(atumSlabs, 6, 0), "XXX", 'X', atumStone);
		GameRegistry.addRecipe(new ItemStack(atumSlabs, 6, 1), "XXX", 'X', atumCobble);
		GameRegistry.addRecipe(new ItemStack(atumSlabs, 6, 2), "XXX", 'X', atumLargeBrick);
		GameRegistry.addRecipe(new ItemStack(atumSlabs, 6, 3), "XXX", 'X', atumSmallBrick);
		
		GameRegistry.addRecipe(new ItemStack(furnaceIdle), "XXX", "X X", "XXX", 'X', atumStone);
		
		GameRegistry.addRecipe(new ItemStack(itemScarab), " G ", "GDG", " G ", 'G', Item.ingotGold, 'D', Item.diamond);
		
		GameRegistry.addShapelessRecipe(new ItemStack(atumPlanks, 4), atumLog);
		OreDictionary.registerOre("plankWood", atumPlanks);
		OreDictionary.registerOre("logWood", atumLog);
	}
}
