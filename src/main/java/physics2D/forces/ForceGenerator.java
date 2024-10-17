package physics2D.forces;

import physics2D.rigidbody.RigidBody2D;

public interface ForceGenerator {
    void updateForce(RigidBody2D body, float dt);
}
