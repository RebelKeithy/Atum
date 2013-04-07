package rebelkeithy.mods.atum;

import java.io.File;

import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy 
{
	public void registerParticles(){}

	public File getMinecraftDir() 
	{
		return new File(".");
	}
	
	public void registerTickHandlers()
	{
		TickRegistry.registerTickHandler(new ServerTickHandler(), Side.SERVER);	
	}
	
	public void preloadImages() {}
	
	public void registerModelRenderers() {}
}
