package cn.ben.googletrainingopengles;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

// draw a black background in the GLSurfaceView
class MyGLRenderer implements GLSurfaceView.Renderer {
    private Triangle mTriangle;
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private Square mSquare;

    // mMVPMatrix is an abbreviation for "Model View Projection Matrix"
    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];

    private final float[] mRotationMatrix = new float[16];
    // TODO: 2017/1/16 volatile
    private volatile float mAngle;

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
        float[] scratch = new float[16];

        // Redraw background color
        // 清除屏幕颜色, 将屏幕上的所有像素点都还原为“底色”
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

//        mTriangle.draw();

        // Create a rotation transformation for the triangle
//        long time = SystemClock.uptimeMillis() % 4000L;
//        float angle = 0.090f * ((int) time);
//        // TODO: 2017/1/16 -1 .0f
//        Matrix.setRotateM(mRotationMatrix, 0, angle, 0, 0, -1.0f);
        Matrix.setRotateM(mRotationMatrix, 0, mAngle, 0, 0, -1.0f);

        // Set the camera position (View matrix)
        // TODO: 2017/1/14 Defines a viewing transformation in terms of an eye point, a center of view, and an up vector. 
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        // Calculate the projection and view transformation
        // TODO: 2017/1/14
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);

        // Combine the rotation matrix with the projection and camera view
        // Note that the mMVPMatrix factor *must be first* in order
        // for the matrix multiplication product to be correct.
        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, mRotationMatrix, 0);

        // Draw shape
//        mTriangle.draw(mMVPMatrix);
        mTriangle.draw(scratch);
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        // TODO: 2017/1/13
        GLES20.glViewport(0, 0, width, height);

        float ratio = (float) width / height;

        // this projection matrix is applied to object coordinates
        // in the onDrawFrame() method
        // TODO: 2017/1/14 six clip planes
        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
    }

    public static int loadShader(int type, String shaderCode) {

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }

    public void setAngle(float angle) {
        mAngle = angle;
    }

    public float getAngle() {
        return mAngle;
    }
}
