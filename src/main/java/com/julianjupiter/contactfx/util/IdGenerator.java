package com.julianjupiter.contactfx.util;

/**
 * @author Julian Jupiter
 */
public final class IdGenerator {
    private IdGenerator() {
    }

    public static Long generate() {
        return TSID.Factory.getTsid().toLong();
    }
}
