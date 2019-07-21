package austeretony.oxygen.common.main;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OxygenSoundEffects {

    private static final Map<Integer, SoundEventContainer> REGISTRY = new HashMap<Integer, SoundEventContainer>();

    public static final SoundEventContainer 
    INVENTORY = new SoundEventContainer(OxygenMain.MODID, "inventory"),
    SELL = new SoundEventContainer(OxygenMain.MODID, "sell"),
    REQUEST_RECEIVED = new SoundEventContainer(OxygenMain.MODID, "request_recieved"),
    BUTTON_CLICK = new SoundEventContainer(OxygenMain.MODID, "button_click"),
    CONTEXT_OPEN = new SoundEventContainer(OxygenMain.MODID, "context_open"),
    CONTEXT_CLOSE = new SoundEventContainer(OxygenMain.MODID, "context_close"),
    DROP_DOWN_LIST_OPEN = new SoundEventContainer(OxygenMain.MODID, "drop_down_list_open");

    @SubscribeEvent
    public void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        event.getRegistry().registerAll(
                INVENTORY.soundEvent,
                SELL.soundEvent,
                REQUEST_RECEIVED.soundEvent,
                BUTTON_CLICK.soundEvent,
                CONTEXT_OPEN.soundEvent,
                CONTEXT_CLOSE.soundEvent,
                DROP_DOWN_LIST_OPEN.soundEvent
                );
    }

    public static SoundEvent getSoundEvent(int id) {
        return REGISTRY.get(id).soundEvent;
    }

    public static class SoundEventContainer {

        public final SoundEvent soundEvent;

        public final int id;

        private static int count;

        public SoundEventContainer(String modId, String name) {
            ResourceLocation location = new ResourceLocation(modId, name);
            this.soundEvent = new SoundEvent(location).setRegistryName(location);
            this.id = createId();
            REGISTRY.put(this.id, this);
        }

        public static int createId() {
            return count++;
        }
    }
}