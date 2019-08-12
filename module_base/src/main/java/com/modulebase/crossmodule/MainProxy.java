package com.modulebase.crossmodule;

public class MainProxy extends Proxy<HotSprUI> {

    public static MainProxy g = new MainProxy();
    @Override
    public String getModuleClassName() {
        return "com.cmic.module_main.MainModule";
    }

    @Override
    public Module<HotSprUI> getDefaultModule() {
        return null;
    }

}
