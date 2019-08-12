package com.modulebase.crossmodule;

import android.text.TextUtils;

public abstract class Proxy<U> implements IProxy<U> {

    private static final String TAG = "Proxy";
    private Module<U> proxy;

    @Override
    public U getUiInterface() {
        return getProxy().getUiInterface();
    }

    public abstract String getModuleClassName();

    public abstract Module<U> getDefaultModule();

    protected Module<U> getProxy(){
        if(proxy == null){
            String moduleName = getModuleClassName();
            if(!TextUtils.isEmpty(moduleName)){
                try {
                    proxy = (Module<U>) loadModule(moduleName, this.getClass().getClassLoader());
                }catch (Throwable e){
                    proxy = getDefaultModule();
                }
            }
        }

        return proxy;
    }

    private Object loadModule(String className,ClassLoader classLoader) throws ClassNotFoundException,InstantiationException,IllegalAccessException{
        Class<?> aClass = Class.forName(className,false,classLoader);
        Object result = aClass.newInstance();
        return result;
    }
}
