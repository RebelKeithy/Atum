package rebelkeithy.mods.atum.artifacts;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAnubisMercy extends Item 
{

	public ItemAnubisMercy(int par1) 
	{
		super(par1);
		this.setMaxDamage(20);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@ForgeSubscribe
	public void onDamage(LivingHurtEvent event)
	{
		if(event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.entityLiving;
			ItemStack stack = null;
			
			for(ItemStack currStack : player.inventory.mainInventory)
			{
				if(currStack != null && currStack.itemID == this.itemID)
				{
					stack = currStack;
					break;
				}
			}
			
			if(stack == null)
			{
				return;
			}
			
			float damageAmount = event.ammount;
			
			if(!event.source.isUnblockable())
			{
				damageAmount = (event.ammount * (25 - player.getTotalArmorValue()) + player.carryoverDamage) / 25f;
			}
			
			if (player.isPotionActive(Potion.resistance))
		    {
		      int resistance = 25 - (player.getActivePotionEffect(Potion.resistance).getAmplifier() + 1) * 5;
		      damageAmount = damageAmount * resistance / 25f;
		    }
			
			if(Math.ceil(damageAmount) >= player.getHealth())
			{
				event.setCanceled(true);
				respawnPlayer(event.entityLiving.worldObj, player);
				player.setEntityHealth(player.getMaxHealth());
				player.getFoodStats().setFoodLevel(20);
				player.getFoodStats().setFoodSaturationLevel(20);
				player.spawnExplosionParticle();
				stack.damageItem(1, player);
			}
		}
	}

    public void respawnPlayer(World par3World, EntityPlayer par2EntityPlayer)
    {
		ChunkCoordinates spawn = par2EntityPlayer.getBedLocation();

		if(spawn == null)
		{
			spawn = par3World.getSpawnPoint();
		}
		
		if(spawn == null)
		{
			spawn = par3World.getSpawnPoint();
		}
		
		spawn = this.verifyRespawnCoordinates(par3World, spawn, false);
		
		if(spawn == null)
		{
			spawn = par3World.getSpawnPoint();
		}
		
		par2EntityPlayer.rotationPitch = 0.0F;
		par2EntityPlayer.rotationYaw = 0.0F;
		par2EntityPlayer.setPositionAndUpdate(spawn.posX + 0.5D, spawn.posY + 0.1D, spawn.posZ);
		
		while (!par3World.getCollidingBoundingBoxes(par2EntityPlayer, par2EntityPlayer.boundingBox).isEmpty())
		{
			par2EntityPlayer.setPosition(par2EntityPlayer.posX, par2EntityPlayer.posY + 1.0D, par2EntityPlayer.posZ);
		}
		
        return;
    }
    
    public static ChunkCoordinates verifyRespawnCoordinates(World par0World, ChunkCoordinates par1ChunkCoordinates, boolean par2)
    {
        if (!par0World.isRemote)
        {
	        IChunkProvider ichunkprovider = par0World.getChunkProvider();
	        ichunkprovider.loadChunk(par1ChunkCoordinates.posX - 3 >> 4, par1ChunkCoordinates.posZ - 3 >> 4);
	        ichunkprovider.loadChunk(par1ChunkCoordinates.posX + 3 >> 4, par1ChunkCoordinates.posZ - 3 >> 4);
	        ichunkprovider.loadChunk(par1ChunkCoordinates.posX - 3 >> 4, par1ChunkCoordinates.posZ + 3 >> 4);
	        ichunkprovider.loadChunk(par1ChunkCoordinates.posX + 3 >> 4, par1ChunkCoordinates.posZ + 3 >> 4);
        }
        
        ChunkCoordinates c = par1ChunkCoordinates;
        Block block = Block.blocksList[par0World.getBlockId(c.posX, c.posY, c.posZ)];

        if (block != null && block.isBed(par0World, c.posX, c.posY, c.posZ, null))
        {
            ChunkCoordinates chunkcoordinates1 = block.getBedSpawnPosition(par0World, c.posX, c.posY, c.posZ, null);
            return chunkcoordinates1;
        }
        else
        {
            Material material = par0World.getBlockMaterial(par1ChunkCoordinates.posX, par1ChunkCoordinates.posY, par1ChunkCoordinates.posZ);
            Material material1 = par0World.getBlockMaterial(par1ChunkCoordinates.posX, par1ChunkCoordinates.posY + 1, par1ChunkCoordinates.posZ);
            boolean flag1 = !material.isSolid() && !material.isLiquid();
            boolean flag2 = !material1.isSolid() && !material1.isLiquid();
            return par2 && flag1 && flag2 ? par1ChunkCoordinates : null;
        }
    }
    
    @SideOnly(Side.CLIENT)

    /**
     * Return an item rarity from EnumRarity
     */
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return EnumRarity.rare;
    }

    @SideOnly(Side.CLIENT)
    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) 
    {
    	if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
    	{
    		par3List.add(EnumChatFormatting.DARK_PURPLE + "Return I: Teleports you back");
    		par3List.add(EnumChatFormatting.DARK_PURPLE + "to your spawn point");
    	} else {
        	par3List.add("Return I " + EnumChatFormatting.DARK_GRAY + "[SHIFT]");
    	}
    	
    	par3List.add((int)(par1ItemStack.getMaxDamage()-par1ItemStack.getItemDamage()) + " Uses Remaining");
    }

}
