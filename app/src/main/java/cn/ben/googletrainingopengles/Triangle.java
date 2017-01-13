package cn.ben.googletrainingopengles;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

class Triangle {

    private final FloatBuffer vertexBuffer;

    // number of coordinates per vertex in this array
    // TODO: 2017/1/13  
    static final int COORDS_PER_VERTEX = 3;
    private static final float[] triangleCoords = {   // in counterclockwise order:
            0.0f,  0.622008459f, 0.0f, // top
            -0.5f, -0.311004243f, 0.0f, // bottom left
            0.5f, -0.311004243f, 0.0f  // bottom right
    };

    // Set color with red, green, blue and alpha (opacity) values
    // TODO: 2017/1/13  
    float color[] = { 0.63671875f, 0.76953125f, 0.22265625f, 1.0f };

    // TODO: 2017/1/13  
    public Triangle() {
        // TODO: 2017/1/13 vertex byte buffer && shape coordinates
        // TODO: 2017/1/13 direct byte buffer
        // initialize vertex byte buffer for shape coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (number of coordinate values * 4 bytes per float)
                triangleCoords.length * 4);
        // TODO: 2017/1/13 why
        // use the device hardware's native byte order
        bb.order(ByteOrder.nativeOrder());

        // create a floating point buffer from the ByteBuffer
        vertexBuffer = bb.asFloatBuffer();
        // add the coordinates to the FloatBuffer
        vertexBuffer.put(triangleCoords);
        // set the buffer to read the first coordinate
        vertexBuffer.position(0);
    }
}
