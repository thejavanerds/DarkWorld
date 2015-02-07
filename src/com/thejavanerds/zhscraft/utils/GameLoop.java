package com.thejavanerds.zhscraft.utils;

public class GameLoop
{
    private Screen currentScreen;
    private double frameCap = 60.0D;
    private boolean running = false;
    private boolean debugMode = false;
    private int frames = 0;
    private static int currentFPS;

    public void setScreen(Screen screen)
    {
        if (this.currentScreen != null) {
            this.currentScreen.dispose();
        }
        this.currentScreen = screen;
        this.currentScreen.initGL();
        this.currentScreen.init();
    }

    public void start(int frameCap)
    {
        if (this.running) {
            return;
        }
        this.running = true;
        this.frameCap = frameCap;
        run();
    }

    private void run()
    {
        this.frames = 0;
        int frameCounter = 0;

        double frameTime = 1.0D / this.frameCap;

        long lastTime = Time.getTime();
        double unprocessed = 0.0D;
        while ((this.running) && (!Window.isWindowCloseRequested()))
        {
            boolean render = false;
            long startTime = Time.getTime();
            long passedTime = startTime - lastTime;
            lastTime = startTime;

            unprocessed += passedTime / 1000000000.0D;
            frameCounter = (int)(frameCounter + passedTime);
            while (unprocessed > frameTime)
            {
                render = true;
                unprocessed -= frameTime;
                if (Window.isWindowCloseRequested()) {
                    stop();
                }
                Time.setDelta(frameTime);
                update();
                if (frameCounter >= 1000000000L)
                {
                    if (this.debugMode) {
                        System.out.println("FPS: " + this.frames);
                    }
                    currentFPS = this.frames;
                    this.frames = 0;
                    frameCounter = 0;
                }
            }
            if (render)
            {
                render();
                this.frames += 1;
            }
            else
            {
                try
                {
                    Thread.sleep(1L);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
        stop();
    }

    private void update()
    {
        Window.update();
        this.currentScreen.update();
    }

    private void render()
    {
        this.currentScreen.render();
    }

    public void stop()
    {
        if (!this.running) {
            return;
        }
        this.running = false;
        this.currentScreen.dispose();
        Window.dispose();
    }

    public double getFrameCap()
    {
        return this.frameCap;
    }

    public void setFrameCap(double frameCap)
    {
        this.frameCap = frameCap;
    }

    public boolean isDebugMode()
    {
        return this.debugMode;
    }

    public void setDebugMode(boolean debugMode)
    {
        this.debugMode = debugMode;
    }

    public boolean isRunning()
    {
        return this.running;
    }

    public static int getFPS()
    {
        return currentFPS;
    }
}
