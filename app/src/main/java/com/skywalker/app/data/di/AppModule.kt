package com.skywalker.app.data.di

import com.skywalker.app.data.remoteDatasource.RetrofitServiceImpl
import com.skywalker.app.data.remoteDatasource.RetrofitServiceInterface
import com.skywalker.app.data.repository.RepositoryImpl
import com.skywalker.app.data.repository.RepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun retrofitDatasourceProvider():RetrofitServiceInterface=RetrofitServiceImpl()

    @Provides
    @Singleton
    fun repositoryProvider(retrofitService:RetrofitServiceInterface):RepositoryInterface=RepositoryImpl(retrofitService)
}
