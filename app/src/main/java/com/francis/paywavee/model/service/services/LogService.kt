package com.francis.paywavee.model.service.services

interface LogService {
    fun logNonFatalCrash(throwable: Throwable)
}