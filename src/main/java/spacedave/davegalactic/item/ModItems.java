package spacedave.davegalactic.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import spacedave.davegalactic.DavesGalacticEmporium;
import spacedave.davegalactic.block.ModBlocks;

public class ModItems {
    public static final Item STEEL_INGOT = registerItem("steel_ingot",
            new Item(new FabricItemSettings()));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(DavesGalacticEmporium.MOD_ID, name), item);
    }
    public static void registerModItems(){
        DavesGalacticEmporium.LOGGER.debug("Registering Mod Items for " + DavesGalacticEmporium.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register((entries -> entries.add(STEEL_INGOT)));
    }
    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, new Identifier(DavesGalacticEmporium.MOD_ID, name),
                new BlockItem(block,new FabricItemSettings()));
    }
}
