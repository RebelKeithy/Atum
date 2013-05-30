package rebelkeithy.mods.atum;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

import net.minecraft.client.audio.SoundPoolEntry;
import net.minecraft.entity.EntityList;
import net.minecraft.potion.Potion;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import rebelkeithy.mods.atum.artifacts.arrow.EntityArrowDoubleShot;
import rebelkeithy.mods.atum.artifacts.arrow.EntityArrowExplosive;
import rebelkeithy.mods.atum.artifacts.arrow.EntityArrowFire;
import rebelkeithy.mods.atum.artifacts.arrow.EntityArrowPoison;
import rebelkeithy.mods.atum.artifacts.arrow.EntityArrowQuickdraw;
import rebelkeithy.mods.atum.artifacts.arrow.EntityArrowVelocity;
import rebelkeithy.mods.atum.artifacts.arrow.EntityAtumFishHook;
import rebelkeithy.mods.atum.artifacts.arrow.EntityNutsCall;
import rebelkeithy.mods.atum.entities.EntityBanditArcher;
import rebelkeithy.mods.atum.entities.EntityBanditWarlord;
import rebelkeithy.mods.atum.entities.EntityBanditWarrior;
import rebelkeithy.mods.atum.entities.EntityBarbarian;
import rebelkeithy.mods.atum.entities.EntityDesertWolf;
import rebelkeithy.mods.atum.entities.EntityDustySkeleton;
import rebelkeithy.mods.atum.entities.EntityGhost;
import rebelkeithy.mods.atum.entities.EntityMummy;
import rebelkeithy.mods.atum.entities.EntityPharaoh;
import rebelkeithy.mods.atum.entities.EntityStoneSoldier;
import rebelkeithy.mods.atum.entities.projectiles.EntityFireSpearCombined;
import rebelkeithy.mods.atum.entities.projectiles.EntityFireSpearSeperated;
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
	
	public static BiomeGenBase atumDesert;

	public static Potion stun;

    public static SoundPoolEntry music;

	
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		AtumConfig.initConfig();
		
		AtumBlocks.init();
		AtumBlocks.registerBlocks();
		
		AtumItems.init();
		
		try
        {
		    System.out.println("Does file exist, " + (new File("C:/Users/Keithy/Documents/Atum 1.5.1/source/resources/mods/Atum/music")).exists());
            music = new SoundPoolEntry("AtumMusic", (new File("C:/Users/Keithy/Documents/Atum 1.5.1/source/resources/mods/Atum/music")).toURI().toURL());
        } catch (MalformedURLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
		ArrayList<BiomeGenBase> biomeList = new ArrayList<BiomeGenBase>();
		for(int i = 0; i < BiomeGenBase.biomeList.length; i++)
		{
			if(BiomeGenBase.biomeList[i] != null && BiomeGenBase.biomeList[i].biomeID != AtumConfig.biomeAtumDesertID)
			{
				biomeList.add(BiomeGenBase.biomeList[i]);
			}
		}
		
		int entityID;
		entityID = EntityRegistry.findGlobalUniqueEntityId();
		//EntityRegistry.registerGlobalEntityID(EntityMummy.class, "AtumMummy", entityID);
		EntityRegistry.registerModEntity(EntityMummy.class, "AtumMummy", 20, this, 64, 1, true);
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

		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityDesertWolf.class, "AtumDesertWolf", entityID);
		EntityList.addMapping(EntityDesertWolf.class, "AtumDesertWolf", entityID, 0x918354, 0x695D37);

		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityBanditWarlord.class, "AtumBanditWarlord", entityID);
		EntityList.addMapping(EntityBanditWarlord.class, "AtumBanditWarlord", entityID, 0x918354, 0x695D37);
		
        EntityRegistry.registerModEntity(EntityBarbarian.class, "AtumBarbarian", 29, this, 64, 1, true);
        EntityList.addMapping(EntityBarbarian.class, "AtumBarbarian", entityID, 0x918354, 0x695D37);

		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityFireSpearCombined.class, "FireSpearCombined", entityID, this, 64, 1, true);
		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(EntityFireSpearSeperated.class, "FireSpearSeperated", entityID, this, 64, 1, true);
		
		EntityRegistry.registerModEntity(EntityArrowVelocity.class, "ArrowVeloctiy", 0, this, 64, 1, true);
		EntityRegistry.registerModEntity(EntityArrowExplosive.class, "ArrowExplosive", 1, this, 64, 1, true);
		EntityRegistry.registerModEntity(EntityArrowPoison.class, "ArrowPoison", 2, this, 64, 1, true);
		EntityRegistry.registerModEntity(EntityArrowFire.class, "ArrowFire", 3, this, 64, 1, true);
		EntityRegistry.registerModEntity(EntityArrowDoubleShot.class, "ArrowFire", 4, this, 64, 1, true);
		EntityRegistry.registerModEntity(EntityArrowQuickdraw.class, "ArrowFire", 5, this, 64, 1, true);
		EntityRegistry.registerModEntity(EntityNutsCall.class, "EntityNutsCall", 6, this, 64, 1, true);
		EntityRegistry.registerModEntity(EntityAtumFishHook.class, "EntityAtumFishHook", 7, this, 64, 1, false);
		
		//EntityRegistry.registerGlobalEntityID(EntityAtumFishHook.class, "AtunFishHook", 203);
		
		//EntityList.addMapping(EntityBandit.class, "AtumBanditArcher", ConfigAtum.banditArcherID, 0xC2C2C2, 0x070C0C);
		
		atumDesert = (new BiomeGenAtumDesert(AtumConfig.biomeAtumDesertID)).setColor(16421912).setBiomeName("AtumDesert").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, 0.2F);
		
		proxy.registerModelRenderers();
		proxy.registerTickHandlers();
		proxy.preloadImages();
		proxy.registerParticles();
		MinecraftForge.EVENT_BUS.register(new AtumEventListener());
		MinecraftForge.EVENT_BUS.register(new AtumMusicListener());
		//MinecraftForge.EVENT_BUS.register(new MobSpawnController());
		NetworkRegistry.instance().registerGuiHandler(this, new AtumGuiHandler());
	}
	
	@Init
	public void init(FMLInitializationEvent event)
	{
		DimensionManager.registerProviderType(AtumConfig.dimensionID, AtumWorldProvider.class, true);
		DimensionManager.registerDimension(AtumConfig.dimensionID , AtumConfig.dimensionID);

		stun = new PotionStun(21, true, 8171462).setPotionName("potion.stun").setIconIndex(0, 0);
		
		addNames();
		addOreDictionaryEntries();
		
		AtumRecipes.addRecipes();
		AtumRecipes.addShapelessRecipes();
		AtumRecipes.addSmeltingRecipes();
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
	
	public static void addOreDictionaryEntries()
	{
	    // Palm log to "logWood"
	    OreDictionary.registerOre("logWood", AtumBlocks.log);
	    
	    // Palm planks to "plankWood"
	    OreDictionary.registerOre("plankWood", AtumBlocks.planks);
	}
	
	public void addNames()
	{
		AtumBlocks.addNames();
		AtumItems.addNames();
		
		LanguageRegistry.instance().addStringLocalization("itemGroup.Atum", "Atum");
		
		LanguageRegistry.instance().addStringLocalization("entity.AtumMummy.name", "Mummy");
		LanguageRegistry.instance().addStringLocalization("entity.AtumBanditWarrior.name", "Brigand");
		LanguageRegistry.instance().addStringLocalization("entity.AtumBanditArcher.name", "Nomad");
		LanguageRegistry.instance().addStringLocalization("entity.AtumPharaoh.name", "Pharaoh");
		LanguageRegistry.instance().addStringLocalization("entity.AtumDustySkeleton.name", "Forsaken");
		LanguageRegistry.instance().addStringLocalization("entity.AtumDesertGhost.name", "Wraith");
		LanguageRegistry.instance().addStringLocalization("entity.AtumStoneSoldier.name", "Tombguard");
		LanguageRegistry.instance().addStringLocalization("entity.AtumDesertWolf.name", "Desert Wolf");
		LanguageRegistry.instance().addStringLocalization("entity.AtumBanditWarlord.name", "Warlord");
	}
}
