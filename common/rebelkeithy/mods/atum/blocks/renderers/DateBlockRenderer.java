package rebelkeithy.mods.atum.blocks.renderers;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockDirectional;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import rebelkeithy.mods.atum.Atum;
import rebelkeithy.mods.atum.blocks.BlockDate;
import rebelkeithy.mods.atum.blocks.models.ModelDate;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class DateBlockRenderer implements ISimpleBlockRenderingHandler
{
	ModelDate modelDate = new ModelDate();
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		//renderWorldBlock(null, 0, 0, 0, block, modelID, renderer);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		//BlockDate block = (BlockDate) Atum.atumDateBlock;
        Tessellator tessellator = Tessellator.instance;
        if(world != null)
        	tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        else
        	tessellator.setBrightness(1);
        	
        tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        Icon icon = block.getIcon(0, 0);
        
        double sideu1 = icon.getInterpolatedU(0);
        double sideu2 = icon.getInterpolatedU(6);
        double sidev1 = icon.getInterpolatedV(14);
        double sidev2 = icon.getInterpolatedV(6);

        double topu1 = icon.getInterpolatedU(0);
        double topu2 = icon.getInterpolatedU(6);
        double topv1 = icon.getInterpolatedV(6);
        double topv2 = icon.getInterpolatedV(0);
        
        double fx1 = 5/16.0D;
        double fx2 = 11/16.0D;
        double fz = 11/16.0D;
        double y1 = 2/16.0D;
        double y2 = 10/16.0D;
        
        
        double bz = 5/16.0D;

        tessellator.addVertexWithUV(x+fx1, y+y1, z+fz, sideu1, sidev1);
        tessellator.addVertexWithUV(x+fx2, y+y1, z+fz, sideu2, sidev1);
        tessellator.addVertexWithUV(x+fx2, y+y2, z+fz, sideu2, sidev2);
        tessellator.addVertexWithUV(x+fx1, y+y2, z+fz, sideu1, sidev2);

        tessellator.addVertexWithUV(x+fx1, y+y1, z+bz, sideu1, sidev1);
        tessellator.addVertexWithUV(x+fx1, y+y2, z+bz, sideu1, sidev2);
        tessellator.addVertexWithUV(x+fx2, y+y2, z+bz, sideu2, sidev2);
        tessellator.addVertexWithUV(x+fx2, y+y1, z+bz, sideu2, sidev1);
        
        tessellator.addVertexWithUV(x+fz, y+y1, z+fx1, sideu1, sidev1);
        tessellator.addVertexWithUV(x+fz, y+y2, z+fx1, sideu1, sidev2);
        tessellator.addVertexWithUV(x+fz, y+y2, z+fx2, sideu2, sidev2);
        tessellator.addVertexWithUV(x+fz, y+y1, z+fx2, sideu2, sidev1);
        
        tessellator.addVertexWithUV(x+bz, y+y1, z+fx1, sideu1, sidev1);
        tessellator.addVertexWithUV(x+bz, y+y1, z+fx2, sideu2, sidev1);
        tessellator.addVertexWithUV(x+bz, y+y2, z+fx2, sideu2, sidev2);
        tessellator.addVertexWithUV(x+bz, y+y2, z+fx1, sideu1, sidev2);

        tessellator.addVertexWithUV(x+bz, y+y2, z+fx1, topu1, topv1);
        tessellator.addVertexWithUV(x+bz, y+y2, z+fx2, topu1, topv2);
        tessellator.addVertexWithUV(x+fz, y+y2, z+fx2, topu2, topv2);
        tessellator.addVertexWithUV(x+fz, y+y2, z+fx1, topu2, topv1);

        tessellator.addVertexWithUV(x+bz, y+y1, z+fx1, topu1, topv1);
        tessellator.addVertexWithUV(x+fz, y+y1, z+fx1, topu2, topv1);
        tessellator.addVertexWithUV(x+fz, y+y1, z+fx2, topu2, topv2);
        tessellator.addVertexWithUV(x+bz, y+y1, z+fx2, topu1, topv2);
        

        sideu1 = icon.getInterpolatedU(6);
        sideu2 = icon.getInterpolatedU(8);
        sidev1 = icon.getInterpolatedV(12);
        sidev2 = icon.getInterpolatedV(6);

        topu1 = icon.getInterpolatedU(6);
        topu2 = icon.getInterpolatedU(8);
        topv1 = icon.getInterpolatedV(2);
        topv2 = icon.getInterpolatedV(0);
        
        fx1 = 7/16.0D;
        fx2 = 9/16.0D;
        bz = 7/16.0D;
        fz = 9/16.0D;
        y1 = 10/16.0D;
        y2 = 16/16.0D;

        tessellator.addVertexWithUV(x+fx1, y+y1, z+fz, sideu1, sidev1);
        tessellator.addVertexWithUV(x+fx2, y+y1, z+fz, sideu2, sidev1);
        tessellator.addVertexWithUV(x+fx2, y+y2, z+fz, sideu2, sidev2);
        tessellator.addVertexWithUV(x+fx1, y+y2, z+fz, sideu1, sidev2);

        tessellator.addVertexWithUV(x+fx1, y+y1, z+bz, sideu1, sidev1);
        tessellator.addVertexWithUV(x+fx1, y+y2, z+bz, sideu1, sidev2);
        tessellator.addVertexWithUV(x+fx2, y+y2, z+bz, sideu2, sidev2);
        tessellator.addVertexWithUV(x+fx2, y+y1, z+bz, sideu2, sidev1);
        
        tessellator.addVertexWithUV(x+fz, y+y1, z+fx1, sideu1, sidev1);
        tessellator.addVertexWithUV(x+fz, y+y2, z+fx1, sideu1, sidev2);
        tessellator.addVertexWithUV(x+fz, y+y2, z+fx2, sideu2, sidev2);
        tessellator.addVertexWithUV(x+fz, y+y1, z+fx2, sideu2, sidev1);
        
        tessellator.addVertexWithUV(x+bz, y+y1, z+fx1, sideu1, sidev1);
        tessellator.addVertexWithUV(x+bz, y+y1, z+fx2, sideu2, sidev1);
        tessellator.addVertexWithUV(x+bz, y+y2, z+fx2, sideu2, sidev2);
        tessellator.addVertexWithUV(x+bz, y+y2, z+fx1, sideu1, sidev2);

        tessellator.addVertexWithUV(x+bz, y+y2, z+fx1, topu1, topv1);
        tessellator.addVertexWithUV(x+bz, y+y2, z+fx2, topu1, topv2);
        tessellator.addVertexWithUV(x+fz, y+y2, z+fx2, topu2, topv2);
        tessellator.addVertexWithUV(x+fz, y+y2, z+fx1, topu2, topv1);

        tessellator.addVertexWithUV(x+bz, y+y1, z+fx1, topu1, topv1);
        tessellator.addVertexWithUV(x+fz, y+y1, z+fx1, topu2, topv1);
        tessellator.addVertexWithUV(x+fz, y+y1, z+fx2, topu2, topv2);
        tessellator.addVertexWithUV(x+bz, y+y1, z+fx2, topu1, topv2);
        
        
        /*
        int l = world.getBlockMetadata(x, y, z);
        int i1 = BlockDirectional.getDirection(l);
        int j1 = BlockCocoa.func_72219_c(l);
        Icon icon = block.getBlockTextureFromSide(j1);
        int k1 = 4 + j1 * 2;
        int l1 = 5 + j1 * 2;
        double d0 = 15.0D - (double)k1;
        double d1 = 15.0D;
        double d2 = 4.0D;
        double d3 = 4.0D + (double)l1;
        double d4 = (double)icon.getInterpolatedU(d0);
        double d5 = (double)icon.getInterpolatedU(d1);
        double d6 = (double)icon.getInterpolatedV(d2);
        double d7 = (double)icon.getInterpolatedV(d3);
        double d8 = 0.0D;
        double d9 = 0.0D;

        switch (i1)
        {
            case 0:
                d8 = 8.0D - (double)(k1 / 2);
                d9 = 15.0D - (double)k1;
                break;
            case 1:
                d8 = 1.0D;
                d9 = 8.0D - (double)(k1 / 2);
                break;
            case 2:
                d8 = 8.0D - (double)(k1 / 2);
                d9 = 1.0D;
                break;
            case 3:
                d8 = 15.0D - (double)k1;
                d9 = 8.0D - (double)(k1 / 2);
        }

        double d10 = (double)x + d8 / 16.0D;
        double d11 = (double)x + (d8 + (double)k1) / 16.0D;
        double d12 = (double)y + (12.0D - (double)l1) / 16.0D;
        double d13 = (double)y + 0.75D;
        double d14 = (double)z + d9 / 16.0D;
        double d15 = (double)z + (d9 + (double)k1) / 16.0D;
        tessellator.addVertexWithUV(d10, d12, d14, d4, d7);
        tessellator.addVertexWithUV(d10, d12, d15, d5, d7);
        tessellator.addVertexWithUV(d10, d13, d15, d5, d6);
        tessellator.addVertexWithUV(d10, d13, d14, d4, d6);
        tessellator.addVertexWithUV(d11, d12, d15, d4, d7);
        tessellator.addVertexWithUV(d11, d12, d14, d5, d7);
        tessellator.addVertexWithUV(d11, d13, d14, d5, d6);
        tessellator.addVertexWithUV(d11, d13, d15, d4, d6);
        tessellator.addVertexWithUV(d11, d12, d14, d4, d7);
        tessellator.addVertexWithUV(d10, d12, d14, d5, d7);
        tessellator.addVertexWithUV(d10, d13, d14, d5, d6);
        tessellator.addVertexWithUV(d11, d13, d14, d4, d6);
        tessellator.addVertexWithUV(d10, d12, d15, d4, d7);
        tessellator.addVertexWithUV(d11, d12, d15, d5, d7);
        tessellator.addVertexWithUV(d11, d13, d15, d5, d6);
        tessellator.addVertexWithUV(d10, d13, d15, d4, d6);
        int i2 = k1;

        if (j1 >= 2)
        {
            i2 = k1 - 1;
        }

        d4 = (double)icon.getMinU();
        d5 = (double)icon.getInterpolatedU((double)i2);
        d6 = (double)icon.getMinV();
        d7 = (double)icon.getInterpolatedV((double)i2);
        tessellator.addVertexWithUV(d10, d13, d15, d4, d7);
        tessellator.addVertexWithUV(d11, d13, d15, d5, d7);
        tessellator.addVertexWithUV(d11, d13, d14, d5, d6);
        tessellator.addVertexWithUV(d10, d13, d14, d4, d6);
        tessellator.addVertexWithUV(d10, d12, d14, d4, d6);
        tessellator.addVertexWithUV(d11, d12, d14, d5, d6);
        tessellator.addVertexWithUV(d11, d12, d15, d5, d7);
        tessellator.addVertexWithUV(d10, d12, d15, d4, d7);
        d4 = (double)icon.getInterpolatedU(12.0D);
        d5 = (double)icon.getMaxU();
        d6 = (double)icon.getMinV();
        d7 = (double)icon.getInterpolatedV(4.0D);
        d8 = 8.0D;
        d9 = 0.0D;
        double d16;

        switch (i1)
        {
            case 0:
                d8 = 8.0D;
                d9 = 12.0D;
                d16 = d4;
                d4 = d5;
                d5 = d16;
                break;
            case 1:
                d8 = 0.0D;
                d9 = 8.0D;
                break;
            case 2:
                d8 = 8.0D;
                d9 = 0.0D;
                break;
            case 3:
                d8 = 12.0D;
                d9 = 8.0D;
                d16 = d4;
                d4 = d5;
                d5 = d16;
        }

        d10 = (double)x + d8 / 16.0D;
        d11 = (double)x + (d8 + 4.0D) / 16.0D;
        d12 = (double)y + 0.75D;
        d13 = (double)y + 1.0D;
        d14 = (double)z + d9 / 16.0D;
        d15 = (double)z + (d9 + 4.0D) / 16.0D;

        if (i1 != 2 && i1 != 0)
        {
            if (i1 == 1 || i1 == 3)
            {
                tessellator.addVertexWithUV(d11, d12, d14, d4, d7);
                tessellator.addVertexWithUV(d10, d12, d14, d5, d7);
                tessellator.addVertexWithUV(d10, d13, d14, d5, d6);
                tessellator.addVertexWithUV(d11, d13, d14, d4, d6);
                tessellator.addVertexWithUV(d10, d12, d14, d5, d7);
                tessellator.addVertexWithUV(d11, d12, d14, d4, d7);
                tessellator.addVertexWithUV(d11, d13, d14, d4, d6);
                tessellator.addVertexWithUV(d10, d13, d14, d5, d6);
            }
        }
        else
        {
            tessellator.addVertexWithUV(d10, d12, d14, d5, d7);
            tessellator.addVertexWithUV(d10, d12, d15, d4, d7);
            tessellator.addVertexWithUV(d10, d13, d15, d4, d6);
            tessellator.addVertexWithUV(d10, d13, d14, d5, d6);
            tessellator.addVertexWithUV(d10, d12, d15, d4, d7);
            tessellator.addVertexWithUV(d10, d12, d14, d5, d7);
            tessellator.addVertexWithUV(d10, d13, d14, d5, d6);
            tessellator.addVertexWithUV(d10, d13, d15, d4, d6);
        }
		*/
        return true;
	}

	@Override
	public boolean shouldRender3DInInventory()
	{
		return false;
	}

	@Override
	public int getRenderId()
	{
		return ((BlockDate)(Atum.atumDateBlock)).renderID;
	}

}
