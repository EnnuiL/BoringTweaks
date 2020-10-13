package io.github.joaoh1.boringtweaks.render;

import io.github.joaoh1.boringtweaks.config.BoringTweaksConfigPojo;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.StriderEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.StriderEntity;
import net.minecraft.util.Identifier;

/*
If you understand the context of the following easter egg, you may have questioned yourself if the author of this mod is
obsessed with the source of the easter egg, well, I assure you that the developer is in fact not very obsessed about it,
and that this is as far as they will go, and that you shouldn't worry that this or any other mod made by the same creator
will contain a reference to the source of this easter egg. Anyway, you may proceed to the code behind the easter egg now.
*/

public class StriderSunglassesFeatureRenderer extends FeatureRenderer<StriderEntity, StriderEntityModel<StriderEntity>> {
    private static final Identifier DAVE_SUNGLASSES_SKIN = new Identifier("boringtweaks:textures/entity/strider/dave_sunglasses.png");
    private static final Identifier DIRK_SUNGLASSES_SKIN = new Identifier("boringtweaks:textures/entity/strider/dirk_sunglasses.png");

    public StriderSunglassesFeatureRenderer(FeatureRendererContext<StriderEntity, StriderEntityModel<StriderEntity>> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, StriderEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (BoringTweaksConfigPojo.featuresGroup.striderEasterEgg) {
            if (!entity.isInvisible() && entity.hasCustomName()) {
                String striderName = entity.getCustomName().getString();
                if (striderName.equals("Dave")) {
                    renderModel(this.getContextModel(), DAVE_SUNGLASSES_SKIN, matrices, vertexConsumers, light, entity, 1.0F, 1.0F, 1.0F);
                } else if (striderName.equals("Dirk")) {
                    renderModel(this.getContextModel(), DIRK_SUNGLASSES_SKIN, matrices, vertexConsumers, light, entity, 1.0F, 1.0F, 1.0F);
                }
            }
        }
    }
}