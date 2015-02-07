package com.thejavanerds.zhscraft.Entity.mobs;


import com.thejavanerds.zhscraft.Entity.Camera;

public class Player extends Mob {
    public Player(Camera camera, int id) {
        super(camera, camera.getX(), camera.getY(), camera.getZ(), camera.getPitch(), camera.getYaw(), camera.getRoll(), id, 0);
    }
    public void update() {

        move();
    }
    public void render() {
    }
    public void dispose() {
    }
}
