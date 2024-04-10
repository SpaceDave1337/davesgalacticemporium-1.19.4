package spacedave.davegalactic.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import spacedave.davegalactic.DavesGalacticEmporium;

public class ModBlocks {

    public static final Block ROCKET_WALL_BLOCK = registerBlock("rocket_wall_block",
            new Block((FabricBlockSettings.of(Material.METAL).strength(-1.0f,3600000.0f).dropsNothing().allowsSpawning(Blocks::never))));
    public static final Block ROCKET_GLASS_BLOCK = registerBlock("rocket_glass_block",
            new Block((FabricBlockSettings.of(Material.GLASS).strength(-1.0f,3600000.0f).dropsNothing().allowsSpawning(Blocks::never).nonOpaque())));
    public static final Block ROCKET_HATCH_TELEPORTER_BLOCK = registerBlock("rocket_hatch_teleporter_block",
            new RocketHatchTeleporterBlock(FabricBlockSettings.of(Material.METAL).strength(-1.0f,3600000.0f)));
    public static final Block CAPSULE_TELEPORTER_BLOCK = registerBlock("capsule_teleporter_block",
            new CapsuleTeleporterBlock((FabricBlockSettings.of(Material.METAL).strength(2.0f,1200.0f).requiresTool())));
    public static final Block GLASS_BLOCK = registerBlock("glass_block",
            new Block((FabricBlockSettings.of(Material.GLASS).strength(0.3f,0.3f).allowsSpawning(Blocks::never).nonOpaque())));
    public static final Block REGOLITH_BLOCK = registerBlock("regolith_block",
            new GravelBlock((FabricBlockSettings.of(Material.AGGREGATE).strength(0.5f,0.5f).sounds(BlockSoundGroup.SAND))));
    public static final Block MAFIC_ROCK_BLOCK = registerBlock("mafic_rock_block",
            new Block((FabricBlockSettings.of(Material.AGGREGATE).strength(1.5f,5.0f).sounds(BlockSoundGroup.GRAVEL))));

    private  static Block registerBlockWithoutItem(String name, Block block){
        return Registry.register(Registries.BLOCK, new Identifier(DavesGalacticEmporium.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(DavesGalacticEmporium.MOD_ID, name), block);
    }
    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, new Identifier(DavesGalacticEmporium.MOD_ID, name),
                new BlockItem(block,new FabricItemSettings()));
    }

    public static void registerModBlocks(){
        DavesGalacticEmporium.LOGGER.debug("Registering Mod Blocks for " + DavesGalacticEmporium.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register((entries -> entries.add(CAPSULE_TELEPORTER_BLOCK)));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register((entries -> entries.add(GLASS_BLOCK)));
    }
    //@see #getAir
    //@see Entity#setAir
}
