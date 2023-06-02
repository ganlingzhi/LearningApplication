package com.example.baselib.structure.base.io

import io.reactivex.Flowable
import io.reactivex.subscribers.DisposableSubscriber

interface IIOAbility {
    /**
     * RXJava提供的线程切换能力
     */
    fun <T> subscribe(
        observable: Flowable<T>,
        observer: DisposableSubscriber<T>
    ): DisposableSubscriber<T>
}