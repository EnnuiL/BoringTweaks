package io.github.joaoh1.boringtweaks.config.modmenu;

import java.util.function.Function;

import io.github.joaoh1.boringtweaks.config.screen.BoringTweaksConfigScreen;
import io.github.prospector.modmenu.api.ModMenuApi;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;

public class BoringTweaksConfigModMenuImpl implements ModMenuApi {
    private final MinecraftClient minecraft = MinecraftClient.getInstance();

    @Override
    public String getModId() {
        return "boringtweaks";
    }

    @Override
    public Function<Screen, ? extends Screen> getConfigScreenFactory() {
        return screen -> BoringTweaksConfigScreen.getConfigScreen(minecraft.currentScreen);
    }
}