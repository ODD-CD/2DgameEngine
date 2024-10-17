package physics2D.primitives;

import org.joml.Vector2f;
import physics2D.rigidbody.RigidBody2D;

// Axis Aligned Bounding Box2D
public class AABB {
    private Vector2f size = new Vector2f();
    private RigidBody2D rigidBody = null;
    private Vector2f halfSize = new Vector2f();

    public AABB() {
        this.halfSize = new Vector2f(size).mul(0.5f);
    }

    public AABB(Vector2f min, Vector2f max) {
        this.size = new Vector2f(max).sub(min);
        this.halfSize = new Vector2f(size).mul(0.5f);
    }

    public Vector2f getMin() {
        return new Vector2f(this.rigidBody.getPosition()).sub(this.halfSize);
    }

    public Vector2f getMax() {
        return new Vector2f(this.rigidBody.getPosition()).add(this.halfSize);
    }

    public void setRigidBody(RigidBody2D rb) {
        this.rigidBody = rb;
    }

    public void setSize(Vector2f size) {
        this.size.set(size);
        this.halfSize.set(size.x / 2.0f, size.y / 2.0f);
    }
}
