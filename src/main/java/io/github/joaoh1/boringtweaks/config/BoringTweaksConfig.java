package io.github.joaoh1.boringtweaks.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import me.zeroeightsix.fiber.exception.FiberException;
import me.zeroeightsix.fiber.serialization.JanksonSerializer;
import me.zeroeightsix.fiber.tree.ConfigNode;
import me.zeroeightsix.fiber.tree.ConfigValue;

public class BoringTweaksConfig {
	public static ConfigNode node = new ConfigNode();
	private static JanksonSerializer serializer = new JanksonSerializer();

	public static ConfigValue<Boolean> fixBabyBipedEntitiysHat = ConfigValue
			.builder("fix_baby_biped_entity_hat_layer", Boolean.class)
			.withComment("Fixes a bug where baby biped entities (ex. Baby Zombies) didn't scale up their hat layer.")
			.withParent(node)
			.withDefaultValue(true)
			.build();
	
	public static ConfigValue<Boolean> hideInvisibleEntityEyes = ConfigValue
			.builder("hide_invisible_entity_eyes", Boolean.class)
			.withComment("Hides entity eyes when they are invisible. The affected entities are determined by a list.")
			.withParent(node)
			.withDefaultValue(true)
			.build();

	public static ConfigValue<String[]> hiddenEyesEntityList = ConfigValue
			.builder("list_of_entities_with_hidden_eyes", String[].class)
			.withComment("The list of entities to be affected by the \"Hide Invisible Entity's Eyes\" tweak.")
			.withParent(node)
			.withDefaultValue(new String[]{"minecraft:entities/enderman", "minecraft:entities/phantom"})
			.build();

	public static ConfigValue<Boolean> changeSoundSliderBehavior = ConfigValue
			.builder("change_sound_slider_behavior", Boolean.class)
			.withComment("Changes the sliders in the \"Sound and Music Options\" in order help setting the volume.")
			.withParent(node)
			.withDefaultValue(false)
			.build();
	
	public static ConfigValue<Boolean> changeVolumeWhileAway = ConfigValue
			.builder("change_master_volume_while_away", Boolean.class)
			.withComment("When the Minecraft windows is focused away, the volume will be changed to a set value.")
			.withParent(node)
			.withDefaultValue(false)
			.build();

	public static ConfigValue<Integer> targetVolumeWhileAway = ConfigValue
			.builder("target_master_volume_while_away", Integer.class)
			.withComment("The set value used by the \"Change Volume While Away\" tweak for the master volume.")
			.withParent(node)
			.withDefaultValue(0)
			.constraints()
			.atLeast(0)
			.atMost(100)
			.finish()
			.build();

	public static void loadJanksonConfig() {
		if (Files.exists(Paths.get("./config/boringtweaks.json5"))) {
			try {
				serializer.deserialize(node, Files.newInputStream(Paths.get("./config/boringtweaks.json5")));
			} catch (IOException | FiberException e) {
				e.printStackTrace();
			}
		} else {
			saveJanksonConfig();
		}
	}

	public static void saveJanksonConfig() {
		try {
			serializer.serialize(node, Files.newOutputStream(Paths.get("./config/boringtweaks.json5")));
		} catch (FiberException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}