package rebelkeithy.mods.atum;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import rebelkeithy.mods.atum.blocks.AtumStone;
import rebelkeithy.mods.atum.blocks.BlockAtumPortal;
import rebelkeithy.mods.atum.blocks.BlockAtumSand;
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
	
	public static Item portalSpawner;

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
		
		ForgeHooks.canToolHarvestBlock(atumSand, 0, new ItemStack(Item.shovelSteel));
		MinecraftForge.setBlockHarvestLevel(atumSand, "shovel", 0);
		
		RenderingRegistry.registerEntityRenderingHandler(EntityMummy.class, new RenderLiving(new ModelBiped(), 0.5F));
		
		LanguageRegistry.addName(atumStone, "Limestone");
		LanguageRegistry.addName(atumSand, "Limestone sand");
		LanguageRegistry.addName(atumCobble, "Cracked Limestone");
		
		//EntityRegistry.registerModEntity(EntityMummy.class, "AtumMummy", ConfigAtum.mummyID, this, 16, 20, true);
		EntityRegistry.registerGlobalEntityID(EntityMummy.class, "AtumMummy", ConfigAtum.mummyID);
		EntityList.addMapping(EntityMummy.class, "AtumMummy", ConfigAtum.mummyID, 0x770000, 0x220000);
		
		GameRegistry.registerBlock(atumSand, "AtumSand");
		GameRegistry.registerBlock(atumStone, "AtumStone");
		GameRegistry.registerBlock(atumCobble, "AtumCobble");
		GameRegistry.registerBlock(cursedChest, "BlockCursedChest");
		GameRegistry.registerTileEntity(TileEntityChestSpawner.class, "CursedChest");
		
		portalSpawner = new ItemPortalSpawner(ConfigAtum.portalSpawnerID).setUnlocalizedName("stick").setCreativeTab(CreativeTabs.tabTools);
		atumDesert = (new BiomeGenAtumDesert(ConfigAtum.biomeAtumDesertID)).setColor(16421912).setBiomeName("AtumDesert").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, 0.2F);
	}
	
	@Init
	public void init(FMLInitializationEvent event)
	{
		DimensionManager.registerProviderType(Atum.dimensionID, AtumWorldProvider.class, true);
		DimensionManager.registerDimension(Atum.dimensionID , Atum.dimensionID);
		//DimensionManager.setWorld(Atum.dimensionID, new AtumWorld)
		//DimensionManager.
		/*
		WorldProvider atumProvider = DimensionManager.createProviderFor(Atum.dimensionID);
        MinecraftServer minecraftserver = MinecraftServer.getServer();
        WorldServer worldserver1 = minecraftserver.worldServerForDimension(Atum.dimensionID);
        worldserver1.field_85177_Q = new AtumTeleporter(worldserver1);
        */
		//DimensionManager.registerProviderType(Atum.dimensionID, atumProvider, true);
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
