package spacedave.davegalactic;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.text.Text;
import spacedave.davegalactic.block.CapsuleTeleporterBlock;
import spacedave.davegalactic.block.ModBlocksClient;

public class DavesGalacticEmporiumClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		ModBlocksClient.registerModBlocksClient();

		ClientPlayNetworking.registerGlobalReceiver(CapsuleTeleporterBlock.CAPSULES_OPENED, ((client, handler, buf, responseSender) -> {
			int total_capsules_opened = buf.readInt();
			client.execute(()->{
				client.player.sendMessage(Text.literal(("Total Capsules Opened:" + total_capsules_opened)));
			});
		}));
	}
}