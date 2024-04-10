package spacedave.davegalactic;


import net.minecraft.nbt.NbtCompound;
import net.minecraft.stat.Stat;
import net.minecraft.world.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.PersistentState;
import net.fabricmc.fabric.api.util.NbtType;



public class StateSaverAndLoader extends PersistentState {
    public Integer totalCapsulesOpened = 0;
    @Override
    public NbtCompound writeNbt (NbtCompound nbt){
        nbt.putInt("totalCapsulesOpened", totalCapsulesOpened);
        return nbt;
    }
    public static StateSaverAndLoader createFromNbt(NbtCompound tag){
        StateSaverAndLoader state = new StateSaverAndLoader();
        state.totalCapsulesOpened = tag.getInt("totalCapsulesOpened");
        return state;
    }
    public static StateSaverAndLoader getServerState(MinecraftServer server){
        PersistentStateManager persistentStateManager = server.getWorld(World.OVERWORLD).getPersistentStateManager();

        StateSaverAndLoader state = persistentStateManager.getOrCreate(StateSaverAndLoader::createFromNbt, StateSaverAndLoader::new, DavesGalacticEmporium.MOD_ID);
        state.markDirty();

        return  state;
    }
}
