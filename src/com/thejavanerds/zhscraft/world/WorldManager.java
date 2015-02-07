package com.thejavanerds.zhscraft.world;


import java.util.ArrayList;

import com.thejavanerds.zhscraft.Chunk.Chunk;
import com.thejavanerds.zhscraft.Entity.MobManager;
import com.thejavanerds.zhscraft.utils.*;

public class WorldManager {
    private MobManager mobManager;

    private ArrayList<Chunk> loadedChunks;
    private ArrayList<Chunk> activeChunks;
    private ShaderProgram shader;
    public WorldManager() {
        initGL();
        init();
        createWorld();
    }
    private void initGL() {
        Shader temp = new Shader("shaders/chunk.vert", "shaders/chunk.frag");
        shader = new ShaderProgram(temp.getvShader(), temp.getfShader());
    }
    private void init() {
        mobManager = new MobManager();
        loadedChunks = new ArrayList<Chunk>();
        activeChunks = new ArrayList<Chunk>();
    }
    private void createWorld() {
        for (int x = 0; x < Constants.viewDistance; x++) {
            for (int y = 0; y < Constants.viewDistance; y++) {
                for (int z = 0; z < Constants.viewDistance; z++) {
                    activeChunks.add(new Chunk(shader, 1, x * Constants.CHUNKSIZE, y * Constants.CHUNKSIZE, z * Constants.CHUNKSIZE));
                    Constants.chunksLoaded++;
                }
            }
        }
    }
    public void update() {
        mobManager.update();
    }
    public void render() {
        Constants.chunksFrustum = 0;
        Spritesheet.tiles.bind();
        getMobManager().getPlayer().getCamera().applyTranslations();
        for (int i = 0; i < activeChunks.size(); i++) {
            if (Frustum.getFrustum().cubeInFrustum(activeChunks.get(i).getPos().x, activeChunks.get(i).getPos().y, activeChunks.get(i).getPos().z, activeChunks.get(i).getPos().x + Constants.CHUNKSIZE, activeChunks.get(i).getPos().y + Constants.CHUNKSIZE, activeChunks.get(i).getPos().z + Constants.CHUNKSIZE)) {
                if (Math.abs(activeChunks.get(i).getCenter().x - (int) mobManager.getPlayer().getX()) < 64 && Math.abs(activeChunks.get(i).getCenter().z - mobManager.getPlayer().getZ()) < 64 && activeChunks.get(i).getCenter().y - mobManager.getPlayer().getY() < 32) {
                    Constants.chunksFrustum++;
                    activeChunks.get(i).render();
                }
            }
        }
        mobManager.render();
    }
    public MobManager getMobManager() {
        return mobManager;
    }
}
