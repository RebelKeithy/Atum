package rebelkeithy.mods.atum.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntitySheep;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDesertWolf extends RenderLiving
{
    public RenderDesertWolf(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3)
    {
        super(par1ModelBase, par3);
        this.setRenderPassModel(par2ModelBase);
    }

    protected float getTailRotation(EntityDesertWolf par1EntityDesertWolf, float par2)
    {
        return par1EntityDesertWolf.getTailRotation();
    }

    protected int func_82447_a(EntityDesertWolf par1EntityDesertWolf, int par2, float par3)
    {
        float f1;

        if (par2 == 0 && par1EntityDesertWolf.getWolfShaking())
        {
            f1 = par1EntityDesertWolf.getBrightness(par3) * par1EntityDesertWolf.getShadingWhileShaking(par3);
            this.loadTexture(par1EntityDesertWolf.getTexture());
            GL11.glColor3f(f1, f1, f1);
            return 1;
        }
        else if (par2 == 1 && par1EntityDesertWolf.isTamed())
        {
            this.loadTexture("/mob/wolf_collar.png");
            f1 = 1.0F;
            int j = par1EntityDesertWolf.getCollarColor();
            GL11.glColor3f(f1 * EntitySheep.fleeceColorTable[j][0], f1 * EntitySheep.fleeceColorTable[j][1], f1 * EntitySheep.fleeceColorTable[j][2]);
            return 1;
        }
        else
        {
            return -1;
        }
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    @Override
    protected int shouldRenderPass(EntityLiving par1EntityLiving, int par2, float par3)
    {
        return this.func_82447_a((EntityDesertWolf)par1EntityLiving, par2, par3);
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    @Override
    protected float handleRotationFloat(EntityLiving par1EntityLiving, float par2)
    {
        return this.getTailRotation((EntityDesertWolf)par1EntityLiving, par2);
    }
}
