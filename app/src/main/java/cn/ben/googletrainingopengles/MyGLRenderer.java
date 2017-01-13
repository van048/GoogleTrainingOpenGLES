package cn.ben.googletrainingopengles;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

// draw a black background in the GLSurfaceView
class MyGLRenderer implements GLSurfaceView.Renderer {
    private Triangle mTriangle;
    @SuppressWarnings("FieldCanBeLocal")
    private Square mSquare;

    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        // Set the background frame color
        // 设置“底色”，即所谓的背景颜色
        // TODO: 2017/1/13 frame color
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        // initialize a triangle
        mTriangle = new Triangle();
        // initialize a square
        mSquare = new Square();
    }

    public void onDrawFrame(GL10 unused) {
        // Redraw background color
        // 清除屏幕颜色, 将屏幕上的所有像素点都还原为“底色”
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        mTriangle.draw();
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        // TODO: 2017/1/13
        GLES20.glViewport(0, 0, width, height);
    }

    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }
}
