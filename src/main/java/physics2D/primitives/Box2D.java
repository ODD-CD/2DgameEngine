package physics2D.primitives;

import org.joml.Vector2f;
import physics2D.rigidbody.RigidBody2D;
import util.JMath;

public class Box2D {
    private Vector2f size = new Vector2f();
    private Vector2f halfSize = new Vector2f();
    private RigidBody2D rigidBody2D = null;

    public Box2D() {
        this.halfSize = new Vector2f(size).mul(0.5f);
    }

    public Box2D(Vector2f min, Vector2f max) {
        this.size = new Vector2f(max).sub(min);
        this.halfSize = new Vector2f(size).mul(0.5f);
    }

    public Vector2f getLocalMin() {
        return new Vector2f(this.rigidBody2D.getPosition()).sub(this.halfSize);
    }

    public Vector2f getLocalMax() {
        return new Vector2f(this.rigidBody2D.getPosition()).add(this.halfSize);
    }

    public Vector2f[] getVertices() {
        Vector2f min = getLocalMin();
        Vector2f max = getLocalMax();

        Vector2f[] vertices = {
                new Vector2f(min.x, min.y), new Vector2f(min.x, max.y),
                new Vector2f(max.x, min.y), new Vector2f(max.x, max.y)
        };

        if (rigidBody2D.getRotation() != 0.0f) {
            for (Vector2f vertice: vertices) {
                // Rotates vertices(Vector2f) around center(Vector2f) by rotation(float degrees)
                JMath.rotate(vertice, this.rigidBody2D.getRotation(), this.rigidBody2D.getPosition());
            }
        }

        return vertices;
    }

    public RigidBody2D getRigidBody2D() {
        return this.rigidBody2D;
    }

    public Vector2f getHalfSize() {
        return this.halfSize;
    }

    public void setRigidBody2D(RigidBody2D rb) {
        this.rigidBody2D = rb;
    }

    public void setSize(Vector2f size) {
        this.size.set(size);
        this.halfSize.set(size.x / 2.0f, size.y / 2.0f);
    }
}
