package test.launcher.mummu.androidscreenrecordsecond.glutils;

/**
 * Created by muhammed on 7/12/2016.
 */
public class OffScreenSurface extends EglSurfaceBase {
    public OffScreenSurface(final EglCore eglBase, final int width, final int height) {
        super(eglBase);
        createOffscreenSurface(width, height);
        makeCurrent();
    }

    public void release() {
        releaseEglSurface();
    }
}
