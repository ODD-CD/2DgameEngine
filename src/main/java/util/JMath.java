package util;

import org.joml.Vector2f;

// I don't understand anything in here (JMath is from 'ambrosiogabe');
public class JMath {

    public static void rotate(Vector2f vector, float angleDeg, Vector2f origin) {
        float x = vector.x - origin.x;
        float y = vector.y - origin.y;

        float cos = (float)Math.cos(Math.toRadians(angleDeg));
        float sin = (float)Math.sin(Math.toRadians(angleDeg));

        float xPrime = (x * cos) - (y * sin);
        float yPrime = (x * sin) + (y * cos);

        xPrime += origin.x;
        yPrime += origin.y;

        vector.x = xPrime;
        vector.y = yPrime;
    }

    public static boolean compare(float x, float y, float epsilon) {
        //    |compares floats by epsilon|
        //    |tolerance                 |-|prevents from broking with large numbers          |
        return Math.abs(x - y) <= epsilon * Math.max(1.0f, Math.max(Math.abs(x), Math.abs(y)));
    }

    public static boolean compare(Vector2f vector1, Vector2f vector2, float epsilon) {
        return compare(vector1.x, vector2.x, epsilon) && compare(vector1.y, vector2.y, epsilon);
    }

    public static boolean compare(float x, float x2) {
        return Math.abs(x - x2) <= Float.MIN_VALUE * Math.max(1.0f, Math.max(Math.abs(x), Math.abs(x2)));
    }

    public static boolean compare(Vector2f vector1, Vector2f vector2) {
        return compare(vector1.x, vector2.x) && compare(vector1.y, vector2.y);
    }
}
