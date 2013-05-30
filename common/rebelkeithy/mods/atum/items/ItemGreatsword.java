package rebelkeithy.mods.atum.items;

import rebelkeithy.mods.atum.entities.EntityPharaoh;
import rebelkeithy.mods.atum.entities.EntityStoneSoldier;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGreatsword extends ItemSword
{

    public ItemGreatsword(int par1, EnumToolMaterial par2EnumToolMaterial)
    {
        super(par1, par2EnumToolMaterial);
    }

    @Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLiving mob, EntityLiving player)
    {
        if(!(mob instanceof EntityStoneSoldier || mob instanceof EntityPharaoh))
        {
            float j = 1.2f;
            mob.addVelocity((double)(-MathHelper.sin(player.rotationYaw * (float)Math.PI / 180.0F) * (float)j * 0.5F), 0.1D, (double)(MathHelper.cos(player.rotationYaw * (float)Math.PI / 180.0F) * (float)j * 0.5F));
        }
        return super.hitEntity(par1ItemStack, mob, player);
    }
}
