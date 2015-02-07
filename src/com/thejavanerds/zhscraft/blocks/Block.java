package com.thejavanerds.zhscraft.blocks;

import com.thejavanerds.zhscraft.utils.Color4f;

import java.util.HashMap;

/**
 * Created by ZhsCraft on 2/6/2015.
 */
public abstract  class Block {
    public static HashMap<Integer, Block> BlockMap = new HashMap<Integer, Block>();
    public static Block AIR = new BlockAir();
    public static Block VOID = new BlockVoid();
    public static Block GRASS = new BlockGrass();
    public static Block CRACKEDSTONE = new BlockCrackedStoen();
    public abstract int getBlockID();
    public abstract Color4f getColor();
    public abstract float[] getTexCoords();
    public static Block getBlockFromID(int id) {
        return BlockMap.get(id);
    }
    public static void createTileMap() {
        BlockMap.put(0, AIR);
        BlockMap.put(1, VOID);
        BlockMap.put(2, GRASS);
        BlockMap.put(3, CRACKEDSTONE);
    }
}
