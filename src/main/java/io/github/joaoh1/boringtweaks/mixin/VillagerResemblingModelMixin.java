package io.github.joaoh1.boringtweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import io.github.joaoh1.boringtweaks.config.BoringTweaksConfigPojo;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.minecraft.client.render.entity.model.ModelWithHat;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.client.render.entity.model.VillagerResemblingModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

@Mixin(VillagerResemblingModel.class)
public class VillagerResemblingModelMixin<T extends Entity> extends CompositeEntityModel<T> implements ModelWithHead, ModelWithHat {
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        this.getParts().forEach((modelPart) -> {
            if (this.child && BoringTweaksConfigPojo.featuresGroup.biggerBabyVillagerHeads) {
                matrices.push();
                float g = BoringTweaksConfigPojo.valuesGroup.babyVillagerHeadScale;
                matrices.scale(g, g, g);
                if (modelPart.equals(this.getHead())) {
                    modelPart.render(matrices, vertices, light, overlay, red, green, blue, alpha);
                }
                matrices.pop();
                matrices.push();
                g = 1F;
                matrices.scale(g, g, g);
                if (!modelPart.equals(this.getHead())) {
                    modelPart.render(matrices, vertices, light, overlay, red, green, blue, alpha);
                }
                matrices.pop();
            } else {
                modelPart.render(matrices, vertices, light, overlay, red, green, blue, alpha);
            }
        });
    }

	@Shadow
	public void setHatVisible(boolean visible) {}

	@Shadow
	public ModelPart getHead() {
		return null;
	}

	@Shadow
	public Iterable<ModelPart> getParts() {
		return null;
	}

	@Shadow
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw,float headPitch) {}
}
