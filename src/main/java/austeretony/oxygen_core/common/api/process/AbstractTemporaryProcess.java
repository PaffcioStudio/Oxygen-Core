package austeretony.oxygen_core.common.api.process;

import austeretony.oxygen_core.common.process.TemporaryProcess;

public abstract class AbstractTemporaryProcess implements TemporaryProcess, Comparable<TemporaryProcess> {

    private final long id, expireTime;

    public AbstractTemporaryProcess() {
        this.id = System.nanoTime();
        this.expireTime = System.currentTimeMillis() + this.getExpireTimeSeconds() * 1000;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public long getExpirationTimeStamp() {
        return this.expireTime;
    }

    @Override
    public boolean isExpired() {
        this.process();
        if (System.currentTimeMillis() >= this.expireTime) {
            this.expired();
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(TemporaryProcess other) {        
        return (int) (this.id - other.getId());
    }
}