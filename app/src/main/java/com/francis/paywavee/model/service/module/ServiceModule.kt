package com.francis.paywavee.model.service.module

import com.francis.paywavee.model.service.implimentation.AccountServiceImpl
import com.francis.paywavee.model.service.implimentation.ConfigurationServiceImpl
import com.francis.paywavee.model.service.implimentation.LogServiceImpl
import com.francis.paywavee.model.service.implimentation.StorageServiceImpl
import com.francis.paywavee.model.service.services.AccountService
import com.francis.paywavee.model.service.services.ConfigurationService
import com.francis.paywavee.model.service.services.LogService
import com.francis.paywavee.model.service.services.StorageService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService

    @Binds
    abstract fun provideLogService(impl: LogServiceImpl): LogService

    @Binds
    abstract fun provideStorageService(impl: StorageServiceImpl): StorageService

    @Binds
    abstract fun provideConfigurationService(impl: ConfigurationServiceImpl): ConfigurationService
}