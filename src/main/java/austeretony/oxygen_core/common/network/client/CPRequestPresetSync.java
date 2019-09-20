package austeretony.oxygen_core.common.network.client;

import austeretony.oxygen_core.common.api.CommonReference;
import austeretony.oxygen_core.common.main.OxygenMain;
import austeretony.oxygen_core.common.network.Packet;
import austeretony.oxygen_core.server.OxygenManagerServer;
import austeretony.oxygen_core.server.api.RequestsFilterHelper;
import austeretony.oxygen_core.server.api.OxygenHelperServer;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetHandler;

public class CPRequestPresetSync extends Packet {

    private int presetId;

    public CPRequestPresetSync() {}

    public CPRequestPresetSync(int presetId) {
        this.presetId = presetId;
    }

    @Override
    public void write(ByteBuf buffer, INetHandler netHandler) {
        buffer.writeByte(this.presetId);
    }

    @Override
    public void read(ByteBuf buffer, INetHandler netHandler) {
        final EntityPlayerMP playerMP = getEntityPlayerMP(netHandler);
        if (RequestsFilterHelper.getLock(CommonReference.getPersistentUUID(playerMP), OxygenMain.SYNC_PRESETS_REQUEST_ID)) {
            final int presetId = buffer.readByte();
            OxygenHelperServer.addRoutineTask(()->OxygenManagerServer.instance().getPresetsManager().syncPreset(playerMP, presetId));
        }
    }
}