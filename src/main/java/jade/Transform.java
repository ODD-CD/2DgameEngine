package jade;

import org.joml.Vector2f;

public class Transform {

    public Vector2f position;
    public Vector2f scale;

    public Transform() {
        init(new Vector2f(), new Vector2f());
    }

    public Transform(Vector2f position) {
        init(position, new Vector2f());
    }

    public Transform(Vector2f position, Vector2f scale) {
        init(position, scale);
    }

    public void init(Vector2f position, Vector2f scale) {
        this.position = position;
        this.scale = scale;
    }

    // copy current values of transform to new t and returns it
    public Transform copy() {
        Transform t = new Transform(new Vector2f(this.position), new Vector2f(this.scale));
        return t;
    }

    // copy current values of transform and pass to newTransform
    public void copy(Transform newTransform) {
        newTransform.position.set(this.position);
        newTransform.scale.set(this.scale);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) return false;
        if (!(object instanceof Transform)) return false;

        Transform transform = (Transform)object;
        return transform.position.equals(this.position) && transform.scale.equals(this.scale);
    }
}
