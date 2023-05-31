package com.example.baselib.structure.mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel

abstract class BaseViewModel<M:IBaseModel>(app:Application) : AndroidViewModel(app),IBaseVM<M> {

}