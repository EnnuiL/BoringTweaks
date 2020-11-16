package io.github.joaoh1.boringtweaks.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import io.github.fablabsmc.fablabs.api.fiber.v1.annotation.AnnotatedSettings;
import io.github.fablabsmc.fablabs.api.fiber.v1.annotation.SettingNamingConvention;
import io.github.fablabsmc.fablabs.api.fiber.v1.exception.FiberException;
import io.github.fablabsmc.fablabs.api.fiber.v1.serialization.FiberSerialization;
import io.github.fablabsmc.fablabs.api.fiber.v1.serialization.JanksonValueSerializer;
import io.github.fablabsmc.fablabs.api.fiber.v1.tree.ConfigTree;
import net.fabricmc.loader.api.FabricLoader;

//The class responsible for loading and saving the config. Obviously not stolen from Ok Zoomer. *whistles*
public class BoringTweaksConfig {
	public static boolean isConfigLoaded = false;
	public static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("boringtweaks.json5");
	private static final AnnotatedSettings ANNOTATED_SETTINGS = AnnotatedSettings.builder()
		.useNamingConvention(SettingNamingConvention.SNAKE_CASE)
		.build();
	private static final BoringTweaksConfigPojo POJO = new BoringTweaksConfigPojo();
	public static final ConfigTree TREE = ConfigTree.builder()
		.applyFromPojo(POJO, ANNOTATED_SETTINGS)
		.build();
	
	private static final JanksonValueSerializer serializer = new JanksonValueSerializer(false);

	public static void loadModConfig() {
		if (Files.exists(CONFIG_PATH)) {
			try {
				ANNOTATED_SETTINGS.applyToNode(TREE, POJO);
				FiberSerialization.deserialize(TREE, Files.newInputStream(CONFIG_PATH), serializer);
				isConfigLoaded = true;
			} catch (IOException | FiberException e) {
				e.printStackTrace();
			}
		} else {
			saveModConfig();
			isConfigLoaded = true;
		}
	}

	public static void saveModConfig() {
		try {
			ANNOTATED_SETTINGS.applyToNode(TREE, POJO);
			FiberSerialization.serialize(TREE, Files.newOutputStream(CONFIG_PATH), serializer);
		} catch (IOException | FiberException e) {
			e.printStackTrace();
		}
	}
}