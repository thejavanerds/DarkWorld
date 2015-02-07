package com.thejavanerds.zhscraft.blocks;

import com.thejavanerds.zhscraft.utils.Color4f;
import com.thejavanerds.zhscraft.utils.Spritesheet;

/**
 * Created by ZhsCraft on 2/6/2015.
 */
public class BlockCrackedStoen extends Block {
    @Override
    public int getBlockID() {
        return 3;
    }

    @Override
    public Color4f getColor() {
        return Color4f.GRAY;
    }

    @Override
    public float[] getTexCoords() {
        return new float[] { 2 * Spritesheet.tiles.uniformSize(), 0f };
    }
}
