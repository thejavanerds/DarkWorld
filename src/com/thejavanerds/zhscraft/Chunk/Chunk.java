package com.thejavanerds.zhscraft.Chunk;


import com.thejavanerds.zhscraft.blocks.Block;
import com.thejavanerds.zhscraft.utils.*;

import com.thejavanerds.zhscraft.world.World;
import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL11.*;

import java.util.Random;
public class Chunk {
    private Vector3f pos;
    private int[][][] blocks;
    private ShaderProgram shader;
    private int vcID, sizeX, sizeY, sizeZ, type;
    private boolean isActive;
    private Random rand;
    public Chunk(ShaderProgram shader, int type, float x, float y, float z) {
        this(shader, type, new Vector3f(x, y, z));
    }
    public Chunk(ShaderProgram shader, int type, Vector3f pos) {
        this.pos = pos;
        this.shader = shader;
        this.type = type;
        initGL();
        init();
    }
    public void initGL() {
        rand = new Random();
        sizeX = (int) pos.getX() + Constants.CHUNKSIZE;
        sizeY = (int) pos.getY() + Constants.CHUNKSIZE;
        sizeZ = (int) pos.getZ() + Constants.CHUNKSIZE;
        vcID = glGenLists(1);
        blocks = new int[sizeX][sizeY][sizeZ];
        createChunk();
        rebuild();
    }
    public void init() {
    }
    private void createChunk() {
        if (type == World.AIRCHUNK) {
            for (int x = (int) pos.getX(); x < sizeX; x++) {
                for (int y = (int) pos.getY(); y < sizeY; y++) {
                    for (int z = (int) pos.getZ(); z < sizeZ; z++) {
                        blocks[x][y][z] = Block.AIR.getBlockID();
                    }
                }
            }
        }
        if (type == World.MIXEDCHUNK) {
            for (int x = (int) pos.getX(); x < sizeX; x++) {
                for (int y = (int) pos.getY(); y < sizeY; y++) {
                    for (int z = (int) pos.getZ(); z < sizeZ; z++) {
                        blocks[x][y][z] = Block.GRASS.getBlockID();
                        if (rand.nextInt(2) == 0)
                            if(rand.nextBoolean()) blocks[x][y][z] = Block.AIR.getBlockID();
                            else blocks[x][y][z] = Block.CRACKEDSTONE.getBlockID();
                    }
                }
            }
        }
    }
    public void update() {
    }
    public void render() {
        if (type != World.AIRCHUNK) {
            glCallList(vcID);
        }
    }
    public void rebuild() {
        if (type != World.AIRCHUNK) {
            glNewList(vcID, GL_COMPILE);
            glBegin(GL_QUADS);
            for (int x = (int) pos.getX(); x < sizeX; x++) {
                for (int y = (int) pos.getY(); y < sizeY; y++) {
                    for (int z = (int) pos.getZ(); z < sizeZ; z++) {
                        if (blocks[x][y][z] != -1 && !checkTileNotInView(x, y, z)) {
                            Shape.createCube(x, y, z, Block.getBlockFromID(blocks[x][y][z]).getColor(), Block.getBlockFromID(blocks[x][y][z]).getTexCoords(), 1);
                        }
                    }
                }
            }
            glEnd();
            glEndList();
        }
    }
    private boolean checkTileNotInView(int x, int y, int z) {
        boolean facesHidden[] = new boolean[6];
        if(x > pos.getX()) {
            if(blocks[x - 1][y][z] != -1) facesHidden[0] = true;
            else facesHidden[0] = false;
        }else {
            facesHidden[0] = false;
        }
        if(x < sizeX - 1) {
            if(blocks[x + 1][y][z] != -1) facesHidden[1] = true;
            else facesHidden[1] = false;
        }else {
            facesHidden[1] = false;
        }
        if(y > pos.getY()) {
            if(blocks[x][y - 1][z] != -1) facesHidden[2] = true;
            else facesHidden[2] = false;
        }else {
            facesHidden[2] = false;
        }
        if(y < sizeY - 1) {
            if(blocks[x][y + 1][z] != -1) facesHidden[3] = true;
            else facesHidden[3] = false;
        }else {
            facesHidden[3] = false;
        }
        if(z > pos.getZ()) {
            if(blocks[x][y][z - 1] != -1) facesHidden[4] = true;
            else facesHidden[4] = false;
        }else {
            facesHidden[4] = false;
        }
        if(z < sizeZ - 1) {
            if(blocks[x][y][z + 1] != -1) facesHidden[5] = true;
            else facesHidden[5] = false;
        }else {
            facesHidden[5] = false;
        }
        return facesHidden[0] && facesHidden[1] && facesHidden[2] && facesHidden[3] && facesHidden[4] && facesHidden[5];
    }
    public void dispose() {
        shader.dispose();
        glDeleteLists(vcID, 1);
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
    public Vector3f getCenter() {
        return new Vector3f(pos.x - (Constants.CHUNKSIZE / 2), pos.y - (Constants.CHUNKSIZE / 2), pos.z - (Constants.CHUNKSIZE / 2));
    }
    public Vector3f getPos() {
        return pos;
    }
    public int getType() {
        return type;
    }
}