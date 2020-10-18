package com.ceiba.ceiba.di.modules

import com.ceiba.ceiba.ui.posts.PostsFragment
import com.ceiba.ceiba.ui.users.UsersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract fun bindUsersFragment(): UsersFragment

    @ContributesAndroidInjector
    abstract fun bindPostsFragment(): PostsFragment

}