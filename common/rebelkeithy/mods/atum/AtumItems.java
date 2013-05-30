package rebelkeithy.mods.atum;

import net.minecraft.block.Block;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import rebelkeithy.mods.atum.artifacts.IsisEmbrace;
import rebelkeithy.mods.atum.artifacts.ItemAkersToil;
import rebelkeithy.mods.atum.artifacts.ItemAmunetsHomecoming;
import rebelkeithy.mods.atum.artifacts.ItemAnhursMight;
import rebelkeithy.mods.atum.artifacts.ItemAnubisMercy;
import rebelkeithy.mods.atum.artifacts.ItemAnuketsBounty;
import rebelkeithy.mods.atum.artifacts.ItemAtensFury;
import rebelkeithy.mods.atum.artifacts.ItemGebsBlessing;
import rebelkeithy.mods.atum.artifacts.ItemGebsSolidarity;
import rebelkeithy.mods.atum.artifacts.ItemHedetetsSting;
import rebelkeithy.mods.atum.artifacts.ItemHedetetsVenom;
import rebelkeithy.mods.atum.artifacts.ItemHorusFlight;
import rebelkeithy.mods.atum.artifacts.ItemHorusSoaring;
import rebelkeithy.mods.atum.artifacts.ItemIsisHealing;
import rebelkeithy.mods.atum.artifacts.ItemMaatsBalance;
import rebelkeithy.mods.atum.artifacts.ItemMafdetsQuickness;
import rebelkeithy.mods.atum.artifacts.ItemMnevisHorns;
import rebelkeithy.mods.atum.artifacts.ItemMonthusBlast;
import rebelkeithy.mods.atum.artifacts.ItemMonthusStrike;
import rebelkeithy.mods.atum.artifacts.ItemNeithsAudacity;
import rebelkeithy.mods.atum.artifacts.ItemNusFlux;
import rebelkeithy.mods.atum.artifacts.ItemNutsAgility;
import rebelkeithy.mods.atum.artifacts.ItemNutsCall;
import rebelkeithy.mods.atum.artifacts.ItemOsirisWill;
import rebelkeithy.mods.atum.artifacts.ItemPtahsDecadence;
import rebelkeithy.mods.atum.artifacts.ItemPtahsDestruction;
import rebelkeithy.mods.atum.artifacts.ItemRasGlory;
import rebelkeithy.mods.atum.artifacts.ItemSekhmetsWrath;
import rebelkeithy.mods.atum.artifacts.ItemShusBreath;
import rebelkeithy.mods.atum.artifacts.ItemSobeksRage;
import rebelkeithy.mods.atum.blocks.ItemPapyrusPlant;
import rebelkeithy.mods.atum.items.ItemAtumBow;
import rebelkeithy.mods.atum.items.ItemFish;
import rebelkeithy.mods.atum.items.ItemGreatsword;
import rebelkeithy.mods.atum.items.ItemLoot;
import rebelkeithy.mods.atum.items.ItemScarab;
import rebelkeithy.mods.atum.items.ItemScepter;
import rebelkeithy.mods.atum.items.ItemScimitar;
import rebelkeithy.mods.atum.items.ItemStoneSoldierSword;
import rebelkeithy.mods.atum.items.ItemTexturedArmor;
import rebelkeithy.mods.atum.tools.LimestoneAxe;
import rebelkeithy.mods.atum.tools.LimestoneHoe;
import rebelkeithy.mods.atum.tools.LimestonePickaxe;
import rebelkeithy.mods.atum.tools.LimestoneShovel;
import rebelkeithy.mods.atum.tools.LimestoneSword;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class AtumItems 
{
	public static Item scarab;
	public static Item scimitar;
	public static Item greatsword;
	public static Item scepter;
	public static Item stoneSoldierSword;
	public static Item bow;
	public static Item loot;
	
	public static Item ptahsPick;
	public static Item sobeksRage;
	public static Item osirisWill;
	public static Item akersToil;
	public static Item gebsBlessing;
	public static Item atensFury;
	public static Item rasGlory;
	public static Item sekhmetsWrath;
	public static Item nutsAgility;
	public static Item horusFlight;
	public static Item monthusStrike;
	public static Item anhursMight;
	public static Item hedetetsSting;
	public static Item horusSoaring;
	public static Item shusBreath;
	public static Item ptahsDestruction;
	public static Item monthusBlast;
	public static Item nusFlux;
	public static Item mnevisHorns;
	public static Item isisEmbrace;
	public static Item maatsBalance;
	public static Item hedetetsVenom;
	public static Item gebsSolidarity;
	public static Item nutsCall;
	public static Item anuketsBounty;
	public static Item mafdetsQuickness;
	public static Item isisHealing;
	public static Item amunetsHomecoming;
	public static Item anubisMercy;
	
	public static Item limestoneShovel;
	public static Item limestonePickaxe;
	public static Item limestoneAxe;
	public static Item limestoneSword;
	public static Item limestoneHoe;
	
	public static Item mummyHelmet;
	public static Item mummyChest;
	public static Item mummyLegs;
	public static Item mummyBoots;
	
	public static Item wandererHelmet;
	public static Item wandererChest;
	public static Item wandererLegs;
	public static Item wandererBoots;
	
	public static Item desertHelmet;
	public static Item desertChest;
	public static Item desertLegs;
	public static Item desertBoots;
	
	public static Item papyrusPlant;
	public static Item ectoplasm;
	public static Item stoneChunk;
	public static Item scrap;
	public static Item scroll;
	public static Item pelt;
	public static Item date;
	public static Item linen;
	public static Item flax;
	public static Item flaxSeeds;
	public static Item fish;
	
	public static Item neithsAudacity;
	
	public static Item spear;
	
	public static void init()
	{
		scarab = new ItemScarab(AtumConfig.portalSpawnerID).setUnlocalizedName("Atum:Scarab").setCreativeTab(Atum.tabs);
		loot = new ItemLoot(AtumConfig.lootID).setCreativeTab(Atum.tabs);
		date = (new ItemFood(AtumConfig.itemDateID, 5, 1.5F, false)).setUnlocalizedName("Atum:Date").setCreativeTab(Atum.tabs);
		
		scimitar = (new ItemScimitar(AtumConfig.scimitarID, EnumToolMaterial.IRON)).setUnlocalizedName("Atum:Scimitar").setCreativeTab(Atum.tabs);
		greatsword = new ItemGreatsword(AtumConfig.greatswordID, EnumToolMaterial.IRON).setUnlocalizedName("Atum:Greatsword").setCreativeTab(Atum.tabs);
		bow = (new ItemAtumBow(AtumConfig.bowID)).setUnlocalizedName("Atum:Bow").setCreativeTab(Atum.tabs);
		stoneSoldierSword = new ItemStoneSoldierSword(AtumConfig.stoneSwordID, EnumToolMaterial.IRON).setUnlocalizedName("Atum:StoneSoldierSword").setCreativeTab(Atum.tabs);
		scepter = new ItemScepter(AtumConfig.scepterID, EnumToolMaterial.GOLD).setUnlocalizedName("Atum:Scepter").setCreativeTab(Atum.tabs);
		
		ptahsPick = new ItemPtahsDecadence(AtumConfig.ptahsPickID, EnumToolMaterial.EMERALD).setUnlocalizedName("Atum:PtahsDecadence").setCreativeTab(Atum.tabs);
		sobeksRage = new ItemSobeksRage(AtumConfig.soteksRageID, EnumToolMaterial.EMERALD).setUnlocalizedName("Atum:SoteksRage").setCreativeTab(Atum.tabs);
		osirisWill = new ItemOsirisWill(AtumConfig.osirisWillID, EnumToolMaterial.EMERALD).setUnlocalizedName("Atum:OsirisWill").setCreativeTab(Atum.tabs);
		akersToil = new ItemAkersToil(AtumConfig.akersToilID, EnumToolMaterial.EMERALD).setUnlocalizedName("Atum:AkersToil").setCreativeTab(Atum.tabs);
		gebsBlessing = new ItemGebsBlessing(AtumConfig.gebsBlessingID, EnumToolMaterial.EMERALD).setUnlocalizedName("Atum:GebsBlessing").setCreativeTab(Atum.tabs);
		atensFury = new ItemAtensFury(AtumConfig.atensFuryID).setUnlocalizedName("Atum:AtensFury").setCreativeTab(Atum.tabs);
		rasGlory = new ItemRasGlory(AtumConfig.rasGloryID, EnumArmorMaterial.DIAMOND, 0, 0).setTextureFile("EgyptianArmor_1").setUnlocalizedName("Atum:RasGlory").setCreativeTab(Atum.tabs);
		sekhmetsWrath = new ItemSekhmetsWrath(AtumConfig.sekhmetsWrathID, EnumArmorMaterial.DIAMOND, 1, 1).setTextureFile("EgyptianArmor_1").setUnlocalizedName("Atum:SekhmetsWrath").setCreativeTab(Atum.tabs);
		nutsAgility = new ItemNutsAgility(AtumConfig.nutsAgilityID, EnumArmorMaterial.DIAMOND, 2, 2).setTextureFile("EgyptianArmor_2").setUnlocalizedName("Atum:NutsAgility").setCreativeTab(Atum.tabs);
		horusFlight = new ItemHorusFlight(AtumConfig.horusFlightID, EnumArmorMaterial.DIAMOND, 3, 3).setTextureFile("EgyptianArmor_1").setUnlocalizedName("Atum:HorusFlight").setCreativeTab(Atum.tabs);
		monthusStrike = new ItemMonthusStrike(AtumConfig.monthusStrikeID, EnumToolMaterial.EMERALD).setUnlocalizedName("Atum:MonthusStrike").setCreativeTab(Atum.tabs);
		anhursMight = new ItemAnhursMight(AtumConfig.anhursMightID, EnumToolMaterial.EMERALD).setUnlocalizedName("Atum:AnhursMight").setCreativeTab(Atum.tabs);
		hedetetsSting = new ItemHedetetsSting(AtumConfig.hedetetsStingID, EnumToolMaterial.EMERALD).setUnlocalizedName("Atum:HedetetsSting").setCreativeTab(Atum.tabs);
		horusSoaring = new ItemHorusSoaring(AtumConfig.horusSoaringID).setUnlocalizedName("Atum:HorusSoaring").setCreativeTab(Atum.tabs);
		shusBreath = new ItemShusBreath(AtumConfig.shusBreathID).setUnlocalizedName("Atum:ShusBreath").setCreativeTab(Atum.tabs);
		ptahsDestruction = new ItemPtahsDestruction(AtumConfig.ptahsDestructionID, EnumToolMaterial.EMERALD).setUnlocalizedName("Atum:PtahsDestruction").setCreativeTab(Atum.tabs);
		monthusBlast = new ItemMonthusBlast(AtumConfig.monthusBlastID).setUnlocalizedName("Atum:MonthusBlast").setCreativeTab(Atum.tabs);
		nusFlux = new ItemNusFlux(AtumConfig.nusFluxID, EnumToolMaterial.EMERALD).setUnlocalizedName("Atum:NusFlux").setCreativeTab(Atum.tabs);
		mnevisHorns = new ItemMnevisHorns(AtumConfig.mnevisHornsID, EnumArmorMaterial.DIAMOND, 0, 0).setTextureFile("RubyArtifactArmor_1").setUnlocalizedName("Atum:MnevisHorns").setCreativeTab(Atum.tabs);
		isisEmbrace = new IsisEmbrace(AtumConfig.isisEmbraceID, EnumArmorMaterial.DIAMOND, 1, 1).setTextureFile("RubyArtifactArmor_1").setUnlocalizedName("Atum:IsisEmbrace").setCreativeTab(Atum.tabs);
		maatsBalance = new ItemMaatsBalance(AtumConfig.maatsBalanceID, EnumArmorMaterial.DIAMOND, 2, 2).setTextureFile("RubyArtifactArmor_2").setUnlocalizedName("Atum:MaatsBalance").setCreativeTab(Atum.tabs);
		hedetetsVenom = new ItemHedetetsVenom(AtumConfig.hedetetsVenomID).setUnlocalizedName("Atum:HedetetsVenom").setCreativeTab(Atum.tabs);
		gebsSolidarity = new ItemGebsSolidarity(AtumConfig.gebsSolidarityID, EnumArmorMaterial.DIAMOND, 3, 3).setTextureFile("RubyArtifactArmor_1").setUnlocalizedName("Atum:GebsSolidarity").setCreativeTab(Atum.tabs);
		nutsCall = new ItemNutsCall(AtumConfig.nutsCallID).setUnlocalizedName("Atum:NutsCall").setCreativeTab(Atum.tabs);
		anuketsBounty = new ItemAnuketsBounty(AtumConfig.anuketsBountyID).setUnlocalizedName("Atum:AnuketsBounty").setCreativeTab(Atum.tabs);
		mafdetsQuickness = new ItemMafdetsQuickness(AtumConfig.mafdetsQuicknessID).setUnlocalizedName("Atum:MafdetsQuickness").setCreativeTab(Atum.tabs);
		isisHealing = new ItemIsisHealing(AtumConfig.isisHealingID).setUnlocalizedName("Atum:IsisHealing").setCreativeTab(Atum.tabs);
		amunetsHomecoming = new ItemAmunetsHomecoming(AtumConfig.amunetsHomecomingID).setUnlocalizedName("Atum:AmunetsHomecoming").setCreativeTab(Atum.tabs);
		anubisMercy = new ItemAnubisMercy(AtumConfig.anubisMercyID).setUnlocalizedName("Atum:AnubisMercy").setCreativeTab(Atum.tabs);
		
		limestoneShovel = new LimestoneShovel(AtumConfig.limestoneShovelID, EnumToolMaterial.STONE).setUnlocalizedName("Atum:LimestoneShovel").setCreativeTab(Atum.tabs);
		limestonePickaxe = new LimestonePickaxe(AtumConfig.limestonePickaxeID, EnumToolMaterial.STONE).setUnlocalizedName("Atum:LimestonePickaxe").setCreativeTab(Atum.tabs);
		limestoneAxe = new LimestoneAxe(AtumConfig.limestoneAxeID, EnumToolMaterial.STONE).setUnlocalizedName("Atum:LimestoneAxe").setCreativeTab(Atum.tabs);
		limestoneSword = new LimestoneSword(AtumConfig.limestoneSwordID, EnumToolMaterial.STONE).setUnlocalizedName("Atum:LimestoneSword").setCreativeTab(Atum.tabs);
		limestoneHoe = new LimestoneHoe(AtumConfig.limestoneHoeID, EnumToolMaterial.STONE).setUnlocalizedName("Atum:LimestoneHoe").setCreativeTab(Atum.tabs);
		
		EnumArmorMaterial mummyEnum = EnumHelper.addArmorMaterial("Mummy", 5, new int[] {1, 3, 2, 1}, 15);
	    mummyHelmet = (ItemTexturedArmor)(new ItemTexturedArmor(AtumConfig.mummyHelmetID, mummyEnum, 0, 0)).setRepairItem(AtumConfig.clothScrapID+256).setTextureFile("MummyArmor_1").setUnlocalizedName("Atum:MummyHelmet").setCreativeTab(Atum.tabs);
	    mummyChest = (ItemTexturedArmor)(new ItemTexturedArmor(AtumConfig.mummyChestID, mummyEnum, 0, 1)).setRepairItem(AtumConfig.clothScrapID+256).setTextureFile("MummyArmor_1").setUnlocalizedName("Atum:MummyChest").setCreativeTab(Atum.tabs);
	    mummyLegs = (ItemTexturedArmor)(new ItemTexturedArmor(AtumConfig.mummyLegsID, mummyEnum, 0, 2)).setRepairItem(AtumConfig.clothScrapID+256).setTextureFile("MummyArmor_2").setUnlocalizedName("Atum:MummyLegs").setCreativeTab(Atum.tabs);
	    mummyBoots = (ItemTexturedArmor)(new ItemTexturedArmor(AtumConfig.mummyBootsID, mummyEnum, 0, 3)).setRepairItem(AtumConfig.clothScrapID+256).setTextureFile("MummyArmor_1").setUnlocalizedName("Atum:MummyBoots").setCreativeTab(Atum.tabs);

		EnumArmorMaterial wandererEnum = EnumHelper.addArmorMaterial("Wanderer", 10, new int[] {2, 3, 3, 2}, 15);
	    wandererHelmet = (ItemTexturedArmor)(new ItemTexturedArmor(AtumConfig.wandererHelmetID, wandererEnum, 0, 0)).setRepairItem(AtumConfig.linenID+256).setTextureFile("WandererArmor_1").setUnlocalizedName("Atum:WandererHelmet").setCreativeTab(Atum.tabs);
	    wandererChest = (ItemTexturedArmor)(new ItemTexturedArmor(AtumConfig.wandererChestID, wandererEnum, 0, 1)).setRepairItem(AtumConfig.linenID+256).setTextureFile("WandererArmor_1").setUnlocalizedName("Atum:WandererChest").setCreativeTab(Atum.tabs);
	    wandererLegs = (ItemTexturedArmor)(new ItemTexturedArmor(AtumConfig.wandererLegsID, wandererEnum, 0, 2)).setRepairItem(AtumConfig.linenID+256).setTextureFile("WandererArmor_2").setUnlocalizedName("Atum:WandererLegs").setCreativeTab(Atum.tabs);
	    wandererBoots = (ItemTexturedArmor)(new ItemTexturedArmor(AtumConfig.wandererBootsID, wandererEnum, 0, 3)).setRepairItem(AtumConfig.linenID+256).setTextureFile("WandererArmor_1").setUnlocalizedName("Atum:WandererBoots").setCreativeTab(Atum.tabs);

		EnumArmorMaterial desertEnum = EnumHelper.addArmorMaterial("Desert", 20, new int[] {3, 6, 5, 3}, 15);
	    desertHelmet = (ItemTexturedArmor)(new ItemTexturedArmor(AtumConfig.desertHelmetID, desertEnum, 0, 0)).setRepairItem(Item.ingotIron.itemID).setTextureFile("DesertArmor_1").setUnlocalizedName("Atum:DesertHelmet").setCreativeTab(Atum.tabs);
	    desertChest = (ItemTexturedArmor)(new ItemTexturedArmor(AtumConfig.desertChestID, desertEnum, 0, 1)).setRepairItem(Item.ingotIron.itemID).setTextureFile("DesertArmor_1").setUnlocalizedName("Atum:DesertChest").setCreativeTab(Atum.tabs);
	    desertLegs = (ItemTexturedArmor)(new ItemTexturedArmor(AtumConfig.desertLegsID, desertEnum, 0, 2)).setRepairItem(Item.ingotIron.itemID).setTextureFile("DesertArmor_2").setUnlocalizedName("Atum:DesertLegs").setCreativeTab(Atum.tabs);
	    desertBoots = (ItemTexturedArmor)(new ItemTexturedArmor(AtumConfig.desertBootsID, desertEnum, 0, 3)).setRepairItem(Item.ingotIron.itemID).setTextureFile("DesertArmor_1").setUnlocalizedName("Atum:DesertBoots").setCreativeTab(Atum.tabs);
	    
		papyrusPlant = new ItemPapyrusPlant(AtumConfig.itemPapyrusPlantID, AtumBlocks.papyrus).setUnlocalizedName("Atum:PapyrusPlantItem").setCreativeTab(Atum.tabs);
		ectoplasm = new Item(AtumConfig.ectoplasmID).setUnlocalizedName("Atum:Ectoplasm").setCreativeTab(Atum.tabs);
		stoneChunk = new Item(AtumConfig.stoneChunkID).setUnlocalizedName("Atum:StoneChunk").setCreativeTab(Atum.tabs);
		scrap = new Item(AtumConfig.clothScrapID).setUnlocalizedName("Atum:ClothScrap").setCreativeTab(Atum.tabs);
		scroll = new Item(AtumConfig.scrollID).setUnlocalizedName("Atum:Scroll").setCreativeTab(Atum.tabs);
		pelt = new Item(AtumConfig.peltID).setUnlocalizedName("Atum:WolfPelt").setCreativeTab(Atum.tabs);
		linen = new Item(AtumConfig.linenID).setUnlocalizedName("Atum:Linen").setCreativeTab(Atum.tabs);
		flax = new Item(AtumConfig.itemFlaxID).setUnlocalizedName("Atum:FlaxItem").setCreativeTab(Atum.tabs);
		flaxSeeds = new ItemSeeds(AtumConfig.itemFlaxSeedsID, AtumBlocks.flax.blockID, Block.tilledField.blockID).setUnlocalizedName("Atum:FlaxSeeds").setCreativeTab(Atum.tabs);
		fish = new ItemFish(AtumConfig.itemFishID).setUnlocalizedName("Atum:Fish").setCreativeTab(Atum.tabs);
		
		neithsAudacity = new ItemNeithsAudacity(AtumConfig.neithsAudacityID).setUnlocalizedName("Atum:NeithsAudacity").setCreativeTab(Atum.tabs);
	
		Item.itemsList[AtumConfig.slabID] = (new ItemSlab(AtumBlocks.slabs.blockID - 256, AtumBlocks.slabs, AtumBlocks.doubleSlab, false)).setUnlocalizedName("woodSlab").setCreativeTab(Atum.tabs);
        Item.itemsList[AtumBlocks.doubleSlab.blockID] = (new ItemSlab(AtumBlocks.doubleSlab.blockID - 256, AtumBlocks.slabs, AtumBlocks.doubleSlab, true)).setUnlocalizedName("woodSlab");
        
		MinecraftForge.setToolClass(akersToil, "shovel", 4);
		MinecraftForge.setToolClass(limestoneShovel, "shovel", 1);
		MinecraftForge.setToolClass(limestoneAxe, "axe", 1);
		MinecraftForge.setToolClass(limestonePickaxe, "pickaxe", 1);
		MinecraftForge.setToolClass(ptahsDestruction, "pickaxe", 3);
	}
	
	public static void addNames()
	{
		LanguageRegistry.addName(scarab, "Golden Scarab");
		LanguageRegistry.addName(scimitar, "Scimitar");
		LanguageRegistry.addName(bow, "Shortbow");
		LanguageRegistry.addName(stoneSoldierSword, "Ancient Stone Sword");
		LanguageRegistry.addName(ectoplasm, "Ectoplasm");
		LanguageRegistry.addName(stoneChunk, "Limestone Chunk");
		LanguageRegistry.addName(scrap, "Cloth Scrap");
		LanguageRegistry.addName(scepter, "Royal Scepter");
		LanguageRegistry.addName(papyrusPlant, "Papyrus");
		
		LanguageRegistry.addName(ptahsPick, "Ptah's Decadence");
		LanguageRegistry.addName(sobeksRage, "Sobek's Rage");
		LanguageRegistry.addName(osirisWill, "Osiris's Will");
		LanguageRegistry.addName(akersToil, "Aker's Toil");
		LanguageRegistry.addName(gebsBlessing, "Geb's Blessing");
		LanguageRegistry.addName(atensFury, "Aten's Fury");
		LanguageRegistry.addName(rasGlory, "Ra's Glory");
		LanguageRegistry.addName(sekhmetsWrath, "Sekhmet's Wrath");
		LanguageRegistry.addName(nutsAgility, "Nut's Agility");
		LanguageRegistry.addName(horusFlight, "Horus's Flight");
		LanguageRegistry.addName(monthusStrike, "Monthu's Strike");
		LanguageRegistry.addName(anhursMight, "Anhur's Might");
		LanguageRegistry.addName(hedetetsSting, "Hetetet's Sting");
		LanguageRegistry.addName(horusSoaring, "Horus's Soaring");
		LanguageRegistry.addName(shusBreath, "Shu's Breath");
		LanguageRegistry.addName(ptahsDestruction, "Ptah's Destruction");
		LanguageRegistry.addName(monthusBlast, "Monthu's Blast");
		LanguageRegistry.addName(nusFlux, "Nu's Flux");
		LanguageRegistry.addName(mnevisHorns, "Mnevis's Horns");
		LanguageRegistry.addName(isisEmbrace, "Isis's Embrace");
		LanguageRegistry.addName(maatsBalance, "Ma'at's Balance");
		LanguageRegistry.addName(hedetetsVenom, "Hedetet's Venom");
		LanguageRegistry.addName(gebsSolidarity, "Geb's Solidairty");
		LanguageRegistry.addName(nutsCall, "Nut's Call");
		LanguageRegistry.addName(anuketsBounty, "Anuket's Bounty");
		LanguageRegistry.addName(mafdetsQuickness, "Mafdet's Quickness");
		LanguageRegistry.addName(isisHealing, "Isis's Healing");
		LanguageRegistry.addName(amunetsHomecoming, "Amunet's Homecoming");
		LanguageRegistry.addName(anubisMercy, "Anubis's Mercy");
		
		
		LanguageRegistry.addName(limestoneShovel, "Limestone Shovel");
		LanguageRegistry.addName(limestonePickaxe, "Limestone Pickaxe");
		LanguageRegistry.addName(limestoneAxe, "Limestone Axe");
		LanguageRegistry.addName(limestoneSword, "Limestone Sword");
		LanguageRegistry.addName(limestoneHoe, "Limestone Hoe");
		
		LanguageRegistry.addName(mummyHelmet, "Head Wrap");
		LanguageRegistry.addName(mummyChest, "Chest Wrap");
		LanguageRegistry.addName(mummyLegs, "Leg Wrap");
		LanguageRegistry.addName(mummyBoots, "Feet Wrap");
		
		LanguageRegistry.addName(wandererHelmet, "Wanderer Head");
		LanguageRegistry.addName(wandererChest, "Wanderer Chest");
		LanguageRegistry.addName(wandererLegs, "Wanderer Legs");
		LanguageRegistry.addName(wandererBoots, "Wanderer Sandels");
		
		LanguageRegistry.addName(desertHelmet, "Desert Head");
		LanguageRegistry.addName(desertChest, "Desert Chest");
		LanguageRegistry.addName(desertLegs, "Desert Legs");
		LanguageRegistry.addName(desertBoots, "Desert Sandels");

		LanguageRegistry.addName(neithsAudacity, "Neith's Audacity");
		LanguageRegistry.addName(scroll, "Scroll");
		LanguageRegistry.addName(pelt, "Wolf Pelt");
		LanguageRegistry.addName(linen, "Linen");
		LanguageRegistry.addName(flax, "Flax");
		LanguageRegistry.addName(flaxSeeds, "Flax Seeds");
		LanguageRegistry.addName(date, "Date");		
		LanguageRegistry.addName(new ItemStack(fish, 1, 0), "Wraith Fish");
        LanguageRegistry.addName(new ItemStack(fish, 1, 1), "Mummified Fish");
        LanguageRegistry.addName(new ItemStack(fish, 1, 2), "Golden Fish");
        LanguageRegistry.addName(new ItemStack(fish, 1, 3), "Forsaken Fish");
	}
}
