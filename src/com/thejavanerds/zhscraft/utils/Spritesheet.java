package com.thejavanerds.zhscraft.utils;


public class Spritesheet {
    private Texture texture;
    private String path;
    private float size;
    public static Spritesheet tiles = new Spritesheet("terrain.png", 16);
    public Spritesheet(String path, float size) {
        this.path = path;
        this.size = 1 / size;
        load();
    }
    private void load() {
        texture = Texture.loadTexture(path);
    }
    public void bind() {
        texture.bind();
    }
    public void unbind() {
        texture.unbind();
    }
    public void delete() {
        texture.delete();
    }
    public float uniformSize() {
        return size;
    }
}