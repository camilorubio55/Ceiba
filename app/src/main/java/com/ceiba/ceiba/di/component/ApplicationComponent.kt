package com.ceiba.ceiba.di.component

import android.app.Application
import com.ceiba.ceiba.app.CeibaApplication
import com.ceiba.ceiba.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class, ApplicationModule::class, ActivityBindingModule::class,
        FragmentBindingModule::class, ViewModelModule::class, RepositoryModule::class, RoomModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<CeibaApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun create(application: Application): Builder

        fun build(): ApplicationComponent
    }

}