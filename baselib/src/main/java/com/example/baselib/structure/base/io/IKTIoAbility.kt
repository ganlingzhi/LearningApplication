package com.example.baselib.structure.base.io

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow

interface IKTIoAbility : IIOAbility {
    fun <T> launch(
        block: suspend () -> T?,
        handler: ((T) -> Unit)? = null,
        error: ((Throwable) -> Unit)? = null
    ): Job

    fun <T> launchFlow(block: suspend () -> T): Flow<T>
    suspend fun <T> withMain(block: suspend () -> T): T
    suspend fun <T> withIO(block: suspend () -> T): T
}