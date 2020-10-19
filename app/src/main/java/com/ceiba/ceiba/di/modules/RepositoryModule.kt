package com.ceiba.ceiba.di.modules

import com.ceiba.ceiba.db.dao.UserDao
import com.ceiba.ceiba.repository.PostsRepository
import com.ceiba.ceiba.repository.UsersRepository
import com.ceiba.ceiba.ui.posts.IContractPosts
import com.ceiba.ceiba.ui.users.IContractUsers
import com.ceiba.ceiba.webService.CeibaApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUsersRepository(
        ceibaApi: CeibaApi, userDao: UserDao
    ): IContractUsers.Repository {
        return UsersRepository(ceibaApi, userDao)
    }

    @Provides
    @Singleton
    fun providePostRepository(ceibaApi: CeibaApi): IContractPosts.Repository {
        return PostsRepository(ceibaApi)
    }
}