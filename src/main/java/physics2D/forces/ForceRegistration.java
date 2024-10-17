package physics2D.forces;

import physics2D.rigidbody.RigidBody2D;

public class ForceRegistration {
    public ForceGenerator fg;
    public RigidBody2D rb;

    public ForceRegistration(ForceGenerator fg, RigidBody2D rb) {
        this.fg = fg;
        this.rb = rb;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other.getClass() != ForceRegistration.class) return false;

        ForceRegistration fr = (ForceRegistration)other;
        return fr.rb == this.rb && fr.fg == this.fg;
    }
}
