package dev.bananamod.client;

import dev.bananamod.entity.BananaEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix4f;

public class BananaEntityRenderer extends EntityRenderer<BananaEntity> {

    private static final Identifier TEXTURE = new Identifier("minecraft", "textures/misc/white.png");

    public BananaEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.shadowRadius = 0.15f;
        this.shadowOpacity = 0.4f;
    }

    @Override
    public Identifier getTexture(BananaEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(BananaEntity entity, float yaw, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light) {

        matrices.push();
        float spin = (entity.age + tickDelta) * 18.0f;
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(spin));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(20.0f));

        VertexConsumer buffer = vertexConsumers.getBuffer(RenderLayer.getDebugQuads());
        MatrixStack.Entry entry = matrices.peek();

        box(entry, buffer, -0.09f, -0.09f, -0.32f, 0.09f, 0.09f, 0.32f, 1.00f, 0.85f, 0.10f, 1.0f);
        box(entry, buffer, -0.055f, -0.055f, 0.32f, 0.055f, 0.055f, 0.42f, 0.38f, 0.24f, 0.06f, 1.0f);

        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    private static void box(MatrixStack.Entry entry, VertexConsumer buffer,
                            float x1, float y1, float z1,
                            float x2, float y2, float z2,
                            float r, float g, float b, float a) {

        Matrix4f m = entry.getPositionMatrix();

        buffer.vertex(m, x1, y1, z1).color(r, g, b, a).next();
        buffer.vertex(m, x1, y2, z1).color(r, g, b, a).next();
        buffer.vertex(m, x2, y2, z1).color(r, g, b, a).next();
        buffer.vertex(m, x2, y1, z1).color(r, g, b, a).next();

        buffer.vertex(m, x2, y1, z2).color(r, g, b, a).next();
        buffer.vertex(m, x2, y2, z2).color(r, g, b, a).next();
        buffer.vertex(m, x1, y2, z2).color(r, g, b, a).next();
        buffer.vertex(m, x1, y1, z2).color(r, g, b, a).next();

        buffer.vertex(m, x1, y1, z2).color(r, g, b, a).next();
        buffer.vertex(m, x1, y2, z2).color(r, g, b, a).next();
        buffer.vertex(m, x1, y2, z1).color(r, g, b, a).next();
        buffer.vertex(m, x1, y1, z1).color(r, g, b, a).next();

        buffer.vertex(m, x2, y1, z1).color(r, g, b, a).next();
        buffer.vertex(m, x2, y2, z1).color(r, g, b, a).next();
        buffer.vertex(m, x2, y2, z2).color(r, g, b, a).next();
        buffer.vertex(m, x2, y1, z2).color(r, g, b, a).next();

        buffer.vertex(m, x1, y1, z1).color(r, g, b, a).next();
        buffer.vertex(m, x2, y1, z1).color(r, g, b, a).next();
        buffer.vertex(m, x2, y1, z2).color(r, g, b, a).next();
        buffer.vertex(m, x1, y1, z2).color(r, g, b, a).next();

        buffer.vertex(m, x1, y2, z2).color(r, g, b, a).next();
        buffer.vertex(m, x2, y2, z2).color(r, g, b, a).next();
        buffer.vertex(m, x2, y2, z1).color(r, g, b, a).next();
        buffer.vertex(m, x1, y2, z1).color(r, g, b, a).next();
    }
}
