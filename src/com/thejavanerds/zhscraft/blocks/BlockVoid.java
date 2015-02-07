package com.thejavanerds.zhscraft.blocks;

import com.thejavanerds.zhscraft.utils.Color4f;

/**
 * Created by ZhsCraft on 2/6/2015.
 */
public class BlockVoid extends Block {
    @Override
    public int getBlockID() {
        return 1;
    }

    @Override
    public Color4f getColor() {
        return new Color4f(0.5f, 0.5f, 0.5f, 1);
    }
    @Override
    public float[] getTexCoords() {
        return new float[] { 0f, 0f };
    }
}
