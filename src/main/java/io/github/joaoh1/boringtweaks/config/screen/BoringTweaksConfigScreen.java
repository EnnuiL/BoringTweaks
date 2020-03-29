package io.github.joaoh1.boringtweaks.config.screen;

import io.github.joaoh1.boringtweaks.config.BoringTweaksConfig;
import me.shedaniel.fiber2cloth.api.Fiber2Cloth;
import net.minecraft.client.gui.screen.Screen;

public class BoringTweaksConfigScreen {
    public static Screen getConfigScreen(Screen currentScreen) {
        return Fiber2Cloth.create(currentScreen, "boringtweaks", BoringTweaksConfig.node, "title.boringtweaks.config").setSaveRunnable(() -> {
            BoringTweaksConfig.saveJanksonConfig();
        }).build().getScreen();
    }
}