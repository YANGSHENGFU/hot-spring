package com.modulebase.crossmodule;

public abstract class Module<U> implements IProxy<U> {
    public abstract String getName();
    public abstract int getVersion();
}
