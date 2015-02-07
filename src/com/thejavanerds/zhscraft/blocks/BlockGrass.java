package com.thejavanerds.zhscraft.blocks;

import com.thejavanerds.zhscraft.utils.*;

/**
 * Created by ZhsCraft on 2/6/2015.
 */
public class BlockGrass extends Block {

    @Override
    public int getBlockID() {
        return 2;
    }
    @Override
    public Color4f getColor() {
        return new Color4f(1f, 1f, 1f, 1);
    }
    @Override
    public float[] getTexCoords() {
        return new float[] { 4 * Spritesheet.tiles.uniformSize(), Spritesheet.tiles.uniformSize(),
                Spritesheet.tiles.uniformSize(), Spritesheet.tiles.uniformSize(),
                3 * Spritesheet.tiles.uniformSize(), Spritesheet.tiles.uniformSize(),
                3 * Spritesheet.tiles.uniformSize(), 0,
                3 * Spritesheet.tiles.uniformSize(), Spritesheet.tiles.uniformSize(),
                3 * Spritesheet.tiles.uniformSize(), Spritesheet.tiles.uniformSize() };
    }
}
