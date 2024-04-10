package spacedave.davegalactic.block;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import spacedave.davegalactic.DavesGalacticEmporium;

public class ModBlocksClient {
    public static void registerModBlocksClient(){
        DavesGalacticEmporium.LOGGER.debug("Registering Mod Blocks on Client Side for " + DavesGalacticEmporium.MOD_ID);
        BlockRenderLayerMap.INSTANCE.putBlock((ModBlocks.ROCKET_GLASS_BLOCK), RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock((ModBlocks.GLASS_BLOCK), RenderLayer.getTranslucent());
    }
}
