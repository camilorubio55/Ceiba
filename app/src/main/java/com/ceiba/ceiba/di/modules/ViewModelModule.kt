package com.ceiba.ceiba.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ceiba.ceiba.viewModels.PostsViewModel
import com.ceiba.ceiba.viewModels.UsersViewModel
import com.ceiba.ceiba.utility.viewModel.ViewModelFactory
import com.ceiba.ceiba.utility.viewModel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(UsersViewModel::class)
    internal abstract fun bindUsersViewModel(viewModel: UsersViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    internal abstract fun bindPostViewModel(viewModel: PostsViewModel) : ViewModel

}