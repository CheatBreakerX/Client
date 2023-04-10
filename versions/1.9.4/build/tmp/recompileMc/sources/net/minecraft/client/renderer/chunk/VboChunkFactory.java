package net.minecraft.client.renderer.chunk;

import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class VboChunkFactory implements IRenderChunkFactory
{
    public RenderChunk create(World p_189565_1_, RenderGlobal p_189565_2_, int p_189565_3_)
    {
        return new RenderChunk(p_189565_1_, p_189565_2_, p_189565_3_);
    }
}