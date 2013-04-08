
package rebelkeithy.mods.atum;

import java.util.EnumSet;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import rebelkeithy.mods.particleregistry.ParticleRegistry;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class TickHandler implements ITickHandler
{
	private boolean raining;
	private boolean overlay;
	public static int defaultFog;
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) 
	{		

		if(type.equals(EnumSet.of(TickType.RENDER)))
		{
		}
		

		if(type.equals(EnumSet.of(TickType.PLAYER)))
		{
			if(Minecraft.getMinecraft().theWorld != null && Minecraft.getMinecraft().theWorld.loadedEntityList.size() > 0)
			{
				List<EntityPlayer> players = Minecraft.getMinecraft().theWorld.playerEntities;
				for(EntityPlayer player : players)
				{
					if(player != null && player.getEntityName() == "RebelKeithy")
					{
						String cloakURL = "http://images.mccapes.com/capes/standardmc/" + player.username + ".png";
						if(player.cloakUrl != cloakURL)
							player.cloakUrl = cloakURL;
						
						Minecraft.getMinecraft().renderEngine.obtainImageData(player.cloakUrl, new ImageBufferDownload());
					}
				}
			}
			
			EntityPlayer player = (EntityPlayer) tickData[0];
			
			boolean nightvision = false;
			
			if(player.getCurrentArmor(3) != null)
			{
				if(player.getCurrentArmor(3).itemID == Atum.rasGlory.itemID)
				{
					nightvision = true;
				}
		       
			}
			
			if(player.dimension == Atum.dimensionID)
			{
				if(Minecraft.getMinecraft().gameSettings.renderDistance < (nightvision ? 1 : 2))
				{
					defaultFog = Minecraft.getMinecraft().gameSettings.renderDistance;
					Minecraft.getMinecraft().gameSettings.renderDistance = nightvision ? 1 : 2;
				}
				
				if(player.worldObj.isRaining())
				{
					raining = true;
					if(Minecraft.getMinecraft().gameSettings.renderDistance < (nightvision ? 2 : 3))
					{
						Minecraft.getMinecraft().gameSettings.renderDistance = nightvision ? 2 : 3;
					}
					
					Random random = new Random();
					int particlesPerTick = (3 - Minecraft.getMinecraft().gameSettings.particleSetting) * 6;
					for(int i = 0; i < particlesPerTick; i++)
					{
						float x = random.nextInt(4)-2;
						float z = random.nextInt(4)-2;
						float y = (random.nextFloat() - 0.7F)*2F;
						
						float vx = 0.1F + random.nextFloat() * 0.1F;
						float vz = 0.1F + random.nextFloat() * 0.1F;
	
						ParticleRegistry.spawnParticle("sand", player.worldObj, player.posX + x, player.posY+y, player.posZ+z, vx + player.motionX, 0.0D, vz + player.motionZ);
					}
				}
				else
				{
					if(raining == true && defaultFog < (nightvision ? 1 : 2))
					{
						raining = false;
						Minecraft.getMinecraft().gameSettings.renderDistance = nightvision ? 1 : 2;
								
					}
				}
			}
			
		}
		/*if(type.equals(EnumSet.of(TickType.WORLD)))
		{

			World world = (World) tickData[0];
			for(Object o : world.playerEntities)
			{
				EntityPlayer player = (EntityPlayer) o;
				world.spawnParticle("Flame", player.posX+1, player.posY, player.posZ, 0.0D, 0.0D, 0.0D);
			}
		}*/
		
	}


    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {

        Minecraft minecraft = FMLClientHandler.instance().getClient();
        EntityPlayer player = minecraft.thePlayer;
        ItemStack currentItemStack = null;

        if (type.contains(TickType.RENDER)) 
        {
			if(minecraft.inGameHasFocus && player != null && player.getCurrentArmor(3) != null)
			{
				if(player.getCurrentArmor(3).itemID == Atum.mummyHelmet.itemID)
				{
			        ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft().gameSettings, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
			        int par1 = scaledresolution.getScaledWidth();
			        int par2 = scaledresolution.getScaledHeight();
			        
			        //Minecraft.getMinecraft().entityRenderer.setupOverlayRendering();
					
			        GL11.glDisable(GL11.GL_DEPTH_TEST);
			        GL11.glDepthMask(false);
			        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			        GL11.glDisable(GL11.GL_ALPHA_TEST);
			        Minecraft.getMinecraft().renderEngine.bindTexture("/mods/Atum/textures/hud/mummyblur.png");
			        Tessellator tessellator = Tessellator.instance;
			        tessellator.startDrawingQuads();
			        tessellator.addVertexWithUV(0.0D, (double)par2, -90.0D, 0.0D, 1.0D);
			        tessellator.addVertexWithUV((double)par1, (double)par2, -90.0D, 1.0D, 1.0D);
			        tessellator.addVertexWithUV((double)par1, 0.0D, -90.0D, 1.0D, 0.0D);
			        tessellator.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
			        tessellator.draw();
			        GL11.glDepthMask(true);
			        GL11.glEnable(GL11.GL_DEPTH_TEST);
			        GL11.glEnable(GL11.GL_ALPHA_TEST);
			        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				}
		       
			}
        }
    }

	@Override
	public EnumSet<TickType> ticks() {
		// TODO Auto-generated method stub
		return EnumSet.of(TickType.PLAYER, TickType.RENDER);
	}

	@Override
	public String getLabel() {
		return "Atum.TickHandler.Player";
	}

}
