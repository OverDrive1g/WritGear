package com.tnninc.writgear.di.modules;

import com.tnninc.writgear.model.Model;
import com.tnninc.writgear.model.ModelImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class PresenterModule {

    @Provides
    @Singleton
    Model provideDataRepository(){
        return new ModelImpl();
    }

    @Provides
    CompositeDisposable provideCompositeDisposable(){
        return new CompositeDisposable();
    }
}
