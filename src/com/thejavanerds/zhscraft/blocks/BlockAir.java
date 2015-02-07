package com.thejavanerds.zhscraft.blocks;

import com.thejavanerds.zhscraft.utils.Color4f;

/**
 * Created by ZhsCraft on 2/6/2015.
 */
public class BlockAir extends Block {

    @Override
    public int getBlockID() {
        return 0;
    }

    @Override
    public Color4f getColor() {
        return Color4f.WHITE;
    }

    @Override
    public float[] getTexCoords() {
        return new float[0];
    }
}
