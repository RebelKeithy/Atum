package rebelkeithy.mods.atum.artifacts.arrow;

import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderNutsCall extends Render
{
    public void renderArrow(CustomArrow par1EntityFireSpearCombined, double par2, double par4, double par6, float par8, float par9)
    {
        this.loadTexture("/mods/Atum/textures/projectiles/nutscall.png");
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par2, (float)par4, (float)par6);
        GL11.glRotatef(par1EntityFireSpearCombined.prevRotationYaw + (par1EntityFireSpearCombined.rotationYaw - par1EntityFireSpearCombined.prevRotationYaw) * par9 - 90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(par1EntityFireSpearCombined.prevRotationPitch + (par1EntityFireSpearCombined.rotationPitch - par1EntityFireSpearCombined.prevRotationPitch) * par9, 0.0F, 0.0F, 1.0F);
        Tessellator tessellator = Tessellator.instance;
        byte b0 = 0;
        float f2 = 0.0F;
        float f3 = 0.5F;
        float f4 = (float)(0 + b0 * 10) / 32.0F;
        float f5 = (float)(5 + b0 * 10) / 32.0F;
        float f6 = 0.0F;
        float f7 = 0.15625F;
        float f8 = (float)(5 + b0 * 10) / 32.0F;
        float f9 = (float)(10 + b0 * 10) / 32.0F;
        float f10 = 0.15625F;
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        float f11 = (float)par1EntityFireSpearCombined.arrowShake - par9;

        if (f11 > 0.0F)
        {
            float f12 = -MathHelper.sin(f11 * 3.0F) * f11;
            GL11.glRotatef(f12, 0.0F, 0.0F, 1.0F);
        }

        //GL11.glTranslatef(0.0F, 0.5F, 0.5F);
        //GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
        GL11.glScalef(2f, 1.5f, 1.5f);
        GL11.glTranslatef(-0.85F, 0.0F, 0.0F);
        GL11.glNormal3f(f10, 0.0F, 0.0F);
        /*
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(-7.0D, -2.0D, -2.0D, (double)f6, (double)f8);
        tessellator.addVertexWithUV(-7.0D, -2.0D, 2.0D, (double)f7, (double)f8);
        tessellator.addVertexWithUV(-7.0D, 2.0D, 2.0D, (double)f7, (double)f9);
        tessellator.addVertexWithUV(-7.0D, 2.0D, -2.0D, (double)f6, (double)f9);
        tessellator.draw();
        GL11.glNormal3f(-f10, 0.0F, 0.0F);
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(-7.0D, 2.0D, -2.0D, (double)f6, (double)f8);
        tessellator.addVertexWithUV(-7.0D, 2.0D, 2.0D, (double)f7, (double)f8);
        tessellator.addVertexWithUV(-7.0D, -2.0D, 2.0D, (double)f7, (double)f9);
        tessellator.addVertexWithUV(-7.0D, -2.0D, -2.0D, (double)f6, (double)f9);
        tessellator.draw();
        */

        ItemRenderer.renderItemIn2D(tessellator, -1f, -10/32.0f, -5/32.0f, 12/32.0f, 32, 32*32, 0.0625F);
        //ItemRenderer.renderItemIn2D(tessellator, -1f, 0.0f, 0.0f, 1f, 32, 32, 0.0625F);
        /*
        for (int i = 0; i < 2; ++i)
        {
            //GL11.glDepthFunc(GL11.GL_EQUAL);
            //GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
            GL11.glNormal3f(0.0F, 0.0F, f10);
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(-27.0D, -9.0D, 0.0D, (double)0, (double)0);
            tessellator.addVertexWithUV(27.0D, -9.0D, 0.0D, (double)27/32.0d, (double)0);
            tessellator.addVertexWithUV(27.0D, 9.0D, 0.0D, (double)27/32.0d, (double)9/32.0);
            tessellator.addVertexWithUV(-27.0D, 9.0D, 0.0D, (double)0, (double)9/32.0);
            tessellator.draw();

            //GL11.glEnable(GL11.GL_LIGHTING);
        }*/

        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderArrow((CustomArrow)par1Entity, par2, par4, par6, par8, par9);
    }
}