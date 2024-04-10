package spacedave.davegalactic;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spacedave.davegalactic.block.CapsuleTeleporterBlock;
import spacedave.davegalactic.block.ModBlocks;
import spacedave.davegalactic.block_entity.CapsuleTeleporterBlockEntity;
import spacedave.davegalactic.block_entity.RocketHatchTeleporterBlockEntity;
import spacedave.davegalactic.item.ModItems;

import static spacedave.davegalactic.block.ModBlocks.CAPSULE_TELEPORTER_BLOCK;
import static spacedave.davegalactic.block.ModBlocks.ROCKET_HATCH_TELEPORTER_BLOCK;

public class DavesGalacticEmporium implements ModInitializer {
	public static final String MOD_ID = "davespacemod";
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ModItems.registerModItems();
        ModBlocks.registerModBlocks();
		LOGGER.info("DGE loading");
	}

	//Block Entity Registration
	public static final BlockEntityType<CapsuleTeleporterBlockEntity> CAPSULE_TELEPORTER_BLOCK_ENTITY = Registry.register(
			Registries.BLOCK_ENTITY_TYPE,
			new Identifier("davespacemod","capsule_teleporter_block_entity"),
			FabricBlockEntityTypeBuilder.create(CapsuleTeleporterBlockEntity::new, CAPSULE_TELEPORTER_BLOCK).build()
	);
	public static final BlockEntityType<RocketHatchTeleporterBlockEntity> ROCKET_HATCH_TELEPORTER_BLOCK_ENTITY = Registry.register(
			Registries.BLOCK_ENTITY_TYPE,
			new Identifier("davespacemod","rocket_hatch_teleporter_block_entity"),
			FabricBlockEntityTypeBuilder.create(RocketHatchTeleporterBlockEntity::new, ROCKET_HATCH_TELEPORTER_BLOCK).build()
	);
}