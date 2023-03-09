package com.francis.paywavee.model.service.services

interface ConfigurationService {
    suspend fun fetchConfiguration(): Boolean
}