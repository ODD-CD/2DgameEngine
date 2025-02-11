package scenes;


import components.GridLines;
import components.MouseControls;
import components.Sprite;
import components.Spritesheet;
import imgui.ImGui;
import imgui.ImVec2;
import jade.Camera;
import jade.GameObject;
import jade.Prefabs;
import jade.Transform;
import org.joml.Vector2f;
import org.joml.Vector3f;
import physics2D.PhysicsSystem2D;
import physics2D.primitives.Circle;
import physics2D.rigidbody.RigidBody2D;
import renderer.DebugDraw;
import util.AssetPool;

public class LevelEditorScene extends Scene {

    private Spritesheet sprites;

    GameObject levelEditorStuff = new GameObject("LevelEditor", new Transform(new Vector2f()), 0);
    PhysicsSystem2D physics = new PhysicsSystem2D(1.0f / 60.0f, new Vector2f(0, -10));
    Transform obj1, obj2;
    RigidBody2D rb1, rb2;

    public LevelEditorScene() {

    }

    @Override
    public void init() {

        levelEditorStuff.addComponent(new MouseControls());
        levelEditorStuff.addComponent(new GridLines());

        obj1 = new Transform(new Vector2f(100, 500));
        obj2 = new Transform(new Vector2f(100, 300));
        rb1 = new RigidBody2D();
        rb2 = new RigidBody2D();
        rb1.setRawTransform(obj1);
        rb2.setRawTransform(obj2);
        rb1.setMass(100.0f);
        rb2.setMass(200.0f);

        Circle c1 = new Circle();
        c1.setRadius(10.0f);
        c1.setRigidBody2D(rb1);
        Circle c2 = new Circle();
        c2.setRadius(20.0f);
        c2.setRigidBody2D(rb2);
        rb1.setCollider(c1);
        rb2.setCollider(c2);

        physics.addRigidBody(rb1, true);
        physics.addRigidBody(rb2, false);

        loadResources();

        this.camera = new Camera(new Vector2f(-250, 0));
        sprites = AssetPool.getSpritesheet("assets/images/spritesheets/decorationsAndBlocks.png");
        if (loadedLevel) {
            // Activating because if
            // loadedLevel == true objects will not be created, will be imported,
            // but obj1 will not be activated.
            // And if is not active it's not going to show color picker
            // So it needs to active here. For now
            if (gameObjects.size() > 0) {
                this.activeGameObject = gameObjects.get(0);
            }
            return;
        }
    }

    private void loadResources() {
        AssetPool.getShader("assets/shaders/default.glsl");
        AssetPool.addSpritesheet("assets/images/spritesheets/decorationsAndBlocks.png",
                new Spritesheet(AssetPool.getTexture("assets/images/spritesheets/decorationsAndBlocks.png")
                        , 16, 16, 81, 0));
        AssetPool.getTexture("assets/images/blendImage2.png");
    }

    @Override
    public void update(float dt) {
        //System.out.println("Aqui");
        levelEditorStuff.update(dt);

        for (GameObject go : this.gameObjects) {
            go.update(dt);
        }

        DebugDraw.addCircle(obj1.position, 10.0f, new Vector3f(1, 0, 0));
        DebugDraw.addCircle(obj2.position, 20.0f, new Vector3f(0.2f, 0.8f, 0.1f));
        //System.out.println("agora");
        physics.update(dt);
    }

    @Override
    public void render() {
        this.renderer.render();
    }

    @Override
    public void imgui() {
        System.out.println("imgui");
        ImGui.begin("Test Window");
        ImVec2 windowPos = new ImVec2();
        ImGui.getWindowPos(windowPos);
        ImVec2 windowSize = new ImVec2();
        ImGui.getWindowSize(windowSize);
        ImVec2 itemSpacing = new ImVec2();
        ImGui.getStyle().getItemSpacing(itemSpacing);

        float windowX2 = windowPos.x + windowSize.x;
        for (int i = 0; i < sprites.size(); i++) {
            Sprite sprite = sprites.getSprite(i);
            // Each sprite pixel has 4 channels (r,g,b,a), each channels is 1 byte
            // so sprite height and width is whatever many pixels they have
            // times 4 bytes
            float spriteWidth = sprite.getWidth() * 4;
            float spriteHeight = sprite.getHeight() * 4;
            int id = sprite.getTexId();
            Vector2f[] texCoords = sprite.getTexCoords();

            ImGui.pushID(i);
            //todo: Didn't understand yet.
            if (ImGui.imageButton(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y)) {
                GameObject object = Prefabs.generateSpriteObject(sprite, 32, 32);
                levelEditorStuff.getComponent(MouseControls.class).pickupObject(object);
            }
            ImGui.popID();

            ImVec2 lastButtonPos = new ImVec2();
            //todo: Didn't understand yet.
            ImGui.getItemRectMax(lastButtonPos);
            float lastButtonX2 = lastButtonPos.x;
            float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
            if (i + 1 < sprites.size() && nextButtonX2 < windowX2) {
                //todo: Didn't understand yet.
                ImGui.sameLine();
            }
        }
        ImGui.end();
    }
}
