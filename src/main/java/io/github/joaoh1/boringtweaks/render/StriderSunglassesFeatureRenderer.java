package io.github.joaoh1.boringtweaks.render;

import io.github.joaoh1.boringtweaks.config.BoringTweaksConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.StriderEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.StriderEntity;
import net.minecraft.util.Identifier;

/*
If you understand the context of the following easter egg, you may have questioned yourself if the author of this mod is
obsessed with the source of the easter egg, well, i assure you that the developer is in fact not very obsessed about it,
and that this is as far as he go, and that you shouldn't worry that Boring Tweaks or any other mod made by the same creator
will contain a reference to the source of this easter egg. Anyway, you can proceed to the code behind the easter egg now.
*/

@Environment(EnvType.CLIENT)
public class StriderSunglassesFeatureRenderer extends FeatureRenderer<StriderEntity, StriderEntityModel<StriderEntity>> {
   private static final Identifier DAVE_SKIN = new Identifier("boringtweaks:textures/entity/strider/dave_sunglasses.png");
   private static final Identifier DIRK_SKIN = new Identifier("boringtweaks:textures/entity/strider/dirk_sunglasses.png");

   public StriderSunglassesFeatureRenderer(FeatureRendererContext<StriderEntity, StriderEntityModel<StriderEntity>> featureRendererContext) {
      super(featureRendererContext);
   }

   public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, StriderEntity striderEntity, float f, float g, float h, float j, float k, float l) {
      if (BoringTweaksConfig.striderEasterEgg.getValue() && !striderEntity.isInvisible()) {
         if (striderEntity.hasCustomName()) {
            String striderName = striderEntity.getCustomName().getString();
            if (striderName.equals("Dave")) {
               renderModel(this.getContextModel(), DAVE_SKIN, matrixStack, vertexConsumerProvider, i, striderEntity, 1.0F, 1.0F, 1.0F);
            } else if (striderName.equals("Dirk")) {
               renderModel(this.getContextModel(), DIRK_SKIN, matrixStack, vertexConsumerProvider, i, striderEntity, 1.0F, 1.0F, 1.0F);
            }
         }
      }
   }
}
