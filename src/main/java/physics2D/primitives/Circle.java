package physics2D.primitives;

import org.joml.Vector2f;
import physics2D.rigidbody.RigidBody2D;

public class Circle extends Collider2D{
    private float radius = 1.0f;
    private RigidBody2D rigidbody = null;

    public float getRadius() {
        return this.radius;
    }

    public Vector2f getCenter() {
        return rigidbody.getPosition();
    }

    public void setRadius(float r) {
        this.radius = r;
    }

    public void setRigidBody2D(RigidBody2D rb) {
        this.rigidbody = rb;
    }
}
