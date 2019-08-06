package austeretony.oxygen.common.api.config;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;

import austeretony.oxygen.common.config.IConfigHolder;
import austeretony.oxygen.common.config.IConfigValue;
import austeretony.oxygen.common.main.OxygenMain;
import net.minecraft.network.PacketBuffer;

public abstract class AbstractConfigHolder implements IConfigHolder {

    private final List<ConfigValue> values = new ArrayList<ConfigValue>();

    public AbstractConfigHolder() {
        this.getValues(this.values);
    }

    @Override
    public List<ConfigValue> values() {
        return this.values;
    }

    @Override
    public void init(JsonObject configObject) {
        for (IConfigValue value : this.values)
            value.init(configObject);
    }

    @Override
    public void write(PacketBuffer buffer) {
        if (this.sync())
            for (ConfigValue value : this.values)
                value.write(buffer);
    }

    @Override
    public void read(PacketBuffer buffer) {
        if (this.sync()) {
            OxygenMain.OXYGEN_LOGGER.info("Synchronized config for <{}>.", this.getModId());
            for (ConfigValue value : this.values)
                value.read(buffer);      
        }
    }
}
