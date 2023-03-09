package com.francis.paywavee.model.service.implimentation

import com.francis.paywavee.model.service.services.ConfigurationService
import com.google.firebase.ktx.BuildConfig
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ConfigurationServiceImpl @Inject constructor():
ConfigurationService{

    private val remoteConfig
        get() = Firebase.remoteConfig

    init {
        if (BuildConfig.DEBUG) {
            val configSettings = remoteConfigSettings { minimumFetchIntervalInSeconds = 0 }
            remoteConfig.setConfigSettingsAsync(configSettings)
        }
    }

    /**
     * The first function fetches the values from the server,
     * and it's being called as soon as the app starts
     */
    override suspend fun fetchConfiguration(): Boolean {
        return remoteConfig.fetchAndActivate().await()
    }
}