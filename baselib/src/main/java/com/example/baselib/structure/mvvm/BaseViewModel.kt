package com.example.baselib.structure.mvvm

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.CallSuper
import androidx.annotation.MainThread
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.example.baselib.structure.base.io.IKTIoAbility
import com.example.baselib.structure.mvvm.entity.LaunchActivityResultInfo
import com.example.baselib.structure.mvvm.entity.RegisterActivityResultInfo
import com.learning.app.commonlib.utils.ReflectUtil
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseViewModel<M : IBaseModel>(app: Application) : AndroidViewModel(app),
    IBaseVM<M>, IArgumentsSetter, IArgumentsGetter, IKTIoAbility, DefaultLifecycleObserver {
    @JvmField
    val TAG = javaClass.simpleName

    private var mBundle: Bundle? = null
    private var mIntent: Intent? = null

    /**
     * 业务Model
     */
    protected val mModel: M by lazy { createModel() }

    /**
     * RX注册器
     */
    private lateinit var mCompositeDisposable: CompositeDisposable

    /**
     * 声明周期持有者
     */
    protected var lifecycleOwner: LifecycleOwner? = null

    /**
     * 公共View变化监听
     */
    val mUiChangeLiveData: BaseUIChangerLiveData by lazy { initCommonUIChangeLiveData() }

    /**
     * 收敛初始化，封装视图初始化和业务初始化
     */
    open fun onInit() {
    }

    override fun setArgumentsIntent(intent: Intent?) {
        mIntent = intent
    }

    override fun setArgumentsBundle(bundle: Bundle?) {
        mBundle = bundle
    }

    override fun getArgumentsIntent(): Intent? {
        return mIntent
    }

    override fun getArgumentsBundle(): Bundle? {
        return mBundle
    }

    override fun <T> launch(
        block: suspend () -> T?,
        handler: ((T?) -> Unit)?,
        error: ((Throwable) -> Unit)?
    ): Job {
        return viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                block.invoke()
            }.onSuccess {
                withContext(Dispatchers.Main) {
                    handler?.invoke(it)
                }
            }.onFailure {
                withContext(Dispatchers.Main) {
                    error?.invoke(it)
                }
            }
        }
    }

    override fun <T> launchFlow(block: suspend () -> T): Flow<T> {
        return flow {
            emit(block())
        }
    }

    override suspend fun <T> withMain(block: suspend () -> T): T {
        return withContext(Dispatchers.Main) {
            block.invoke()
        }
    }

    override suspend fun <T> withIO(block: suspend () -> T): T {
        return withContext(Dispatchers.IO) {
            block.invoke()
        }
    }

    /**
     * RX异步任务
     */
    override fun <T> subscribe(
        observable: Flowable<T>,
        observer: DisposableSubscriber<T>
    ): DisposableSubscriber<T> {
        initDisposable()
        val disposable: DisposableSubscriber<T> = observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(observer)
        mCompositeDisposable.add(disposable)
        return disposable
    }

    override fun createModel(): M {
        var clz: Class<M> = (ReflectUtil.getTargetTFromObj(this, IBaseModel::class.java)
            ?: IBaseModel::class.java) as Class<M>
        if (clz.isInterface) {
            throw RuntimeException("can not declare <Model> with interface(like:IBaseModel), use BaseModel or other instead")
        }
        return clz.newInstance()
    }

    override fun initCommonUIChangeLiveData(): BaseUIChangerLiveData {
        return BaseUIChangerLiveData()
    }

    @CallSuper
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        this.lifecycleOwner = owner
        setEvent()
    }

    @CallSuper
    override fun onCleared() {
        mModel.onCleared()
        cancelConsumingTask()
    }

    /**
     * 取消耗时任务，比如在界面销毁时，或者在对话框消失时
     */
    fun cancelConsumingTask() {
        // ViewModel销毁时会执行，同时取消所有异步任务
        if (this::mCompositeDisposable.isInitialized) {
            mCompositeDisposable.clear()
        }
    }

    // 以下是界面开启和结束相关的 =========================================================

    @MainThread
    fun finish() {
        mUiChangeLiveData.finishPageLiveData?.call()
    }

    @MainThread
    protected fun finishWithResult(resultCode: Int, data: Intent?) {
        mUiChangeLiveData.finishPageWithResultLiveData?.value = Pair(resultCode, data)
    }

    /**
     * 延时退出，并toast提示
     */
    @MainThread
    protected fun finishDelay(text: String? = null, delay: Long? = 0) {
        mUiChangeLiveData.finishPageDelayLiveData?.value = Pair(delay, text)
    }

    @MainThread
    fun startActivity(clazz: Class<out Activity>) {
        startActivity(clazz, false)
    }

    @MainThread
    fun startActivity(clazz: Class<out Activity>, clearTask: Boolean) {
        if (clearTask) {
            mUiChangeLiveData.starActivityClearTaskLiveData?.value = clazz
        } else {
            mUiChangeLiveData.startActivityLiveData?.value = clazz
        }
    }

    @MainThread
    protected fun startActivity(clazz: Class<out Activity>, map: Map<String, *>) {
        mUiChangeLiveData.startActivityWithMapLiveData?.value = Pair(clazz, map)
    }

    @MainThread
    fun startActivity(clazz: Class<out Activity>, bundle: Bundle?) {
        mUiChangeLiveData.startActivityWithBundleLiveData?.value = Pair(clazz, bundle)
    }

    @MainThread
    protected fun registerActivityForResult(
        registerInfo: RegisterActivityResultInfo
    ) {
        mUiChangeLiveData.registerForResultLiveData?.value = registerInfo
    }

    @MainThread
    protected fun startActivityForResult(
        launcher: ActivityResultLauncher<Intent>,
        clazz: Class<out Activity>
    ) {
        mUiChangeLiveData.startActivityForResultLiveData?.value =
            LaunchActivityResultInfo(launcher, clazz, null, null)
    }

    @MainThread
    protected fun startActivityForResult(
        launcher: ActivityResultLauncher<Intent>,
        clazz: Class<out Activity>,
        bundle: Bundle?
    ) {
        mUiChangeLiveData.startActivityForResultWithBundleLiveData?.value =
            LaunchActivityResultInfo(launcher, clazz, bundle, null)
    }

    @MainThread
    protected fun startActivityForResult(
        launcher: ActivityResultLauncher<Intent>,
        clazz: Class<out Activity>,
        map: Map<String, *>
    ) {
        mUiChangeLiveData.startActivityForResultWithMapLiveData?.value =
            LaunchActivityResultInfo(launcher, clazz, null, map)
    }

    @MainThread
    protected fun startActivityByName(name: String, bundle: Bundle?) {
        mUiChangeLiveData.startActivityByNameLiveData?.value = Pair(name, bundle)
    }

    // ===================================================================================

    fun getCompositeDisposable(): CompositeDisposable? {
        return mCompositeDisposable
    }

    private fun initDisposable() {
        if (!this::mCompositeDisposable.isInitialized) {
            mCompositeDisposable = CompositeDisposable()
        }
    }
}