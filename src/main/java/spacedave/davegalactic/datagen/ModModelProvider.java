package spacedave.davegalactic.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import spacedave.davegalactic.block.ModBlocks;
import spacedave.davegalactic.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CAPSULE_TELEPORTER_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ROCKET_HATCH_TELEPORTER_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ROCKET_GLASS_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GLASS_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.REGOLITH_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.MAFIC_ROCK_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.STEEL_INGOT, Models.GENERATED);
    }
}
