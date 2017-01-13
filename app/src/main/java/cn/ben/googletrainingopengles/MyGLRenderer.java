package cn.ben.googletrainingopengles;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

// draw a black background in the GLSurfaceView
class MyGLRenderer implements GLSurfaceView.Renderer {

    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        // Set the background frame color
        // 设置“底色”，即所谓的背景颜色
        // TODO: 2017/1/13 frame color
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    }

    public void onDrawFrame(GL10 unused) {
        // Redraw background color
        // 清除屏幕颜色, 将屏幕上的所有像素点都还原为“底色”
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        // TODO: 2017/1/13
        GLES20.glViewport(0, 0, width, height);
    }
}
