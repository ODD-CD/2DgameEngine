package physics2D.rigidbody;

import components.Component;
import org.joml.Vector2f;

public class RigidBody2D extends Component {
    private Vector2f position = new Vector2f();
    private float rotation = 0.0f;
    private float mass = 0.0f;
    private float inverseMass = 0.0f;
    
    private Vector2f forceAccum = new Vector2f();
    private Vector2f linearVelocity = new Vector2f();
    private float angularVelocity = 0.0f;
    private float linearDamping = 0.0f;
    private float angularDamping = 0.0f;

    private boolean fixedRotation = false;

    public Vector2f getPosition() {
        return position;
    }

    public void physicsUpdate(float dt) {
        
    }

    public void setTransform(Vector2f position, float rotation) {
        this.position.set(position);
        this.rotation = rotation;
    }

    public void setTransform(Vector2f position) {
        this.position.set(position);
    }

    public float getRotation() {
        return rotation;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
        if (this.mass != 0.0f) {
            this.inverseMass = 1.0f / this.mass;
        }
    }
}

