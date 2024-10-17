package physics2D;

import org.joml.Vector2f;
import physics2D.forces.ForceRegistry;
import physics2D.rigidbody.RigidBody2D;

import java.util.ArrayList;
import java.util.List;

public class PhysicsSystem2D {
    private ForceRegistry forceRegistry;
    private List<RigidBody2D> rigidbodies;
    private Gravity2D gravity;
    private float fixedUpdate;

    public PhysicsSystem2D(float fixedUpdateDt, Vector2f gravity) {
        forceRegistry = new ForceRegistry();
        rigidbodies = new ArrayList<>();

        fixedUpdate = fixedUpdateDt;
    }

    public void update(float dt) {
        fixedUpdate();
    }

    public void fixedUpdate() {
        forceRegistry.updateForces(fixedUpdate);

        // Update the velocities of all rigidbodies
    }

    public void addRigidBody(RigidBody2D body) {
        this.rigidbodies.add(body);
        // Register gravity
    }
}
