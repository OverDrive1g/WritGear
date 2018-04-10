package com.tnn_inc.writgear.di.modules;

import com.tnn_inc.writgear.model.Model;
import com.tnn_inc.writgear.model.ModelImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    @Singleton
    Model provideDataRepository(){
        return new ModelImpl();
    }
}
