package com.learning.app.commonlib.utils

import java.lang.reflect.ParameterizedType

object ReflectUtil {

    fun <T> getTargetTFromObj(o: Any?, topTarget: Class<*>?): Class<T>? {
        return getTargetTFromObj(
            o,
            null,
            topTarget
        )
    }
    /**
     * @param o  目标对象
     * @param topObj 目标对象的递归查找上界
     * @param topTarget 目标泛型的上界
     */
    fun <T> getTargetTFromObj(o: Any?, topObj: Class<*>?, topTarget: Class<*>?): Class<T>? {
        if (o == null || topTarget == null || (topObj != null && !topObj.isAssignableFrom(o.javaClass))) {
            return null
        }
        try {
            var temp: Class<Any>? = o.javaClass
            while (temp != null) {
                if ((temp.genericSuperclass is ParameterizedType)) {
                    //遍历当前类的泛型声明
                    (temp.genericSuperclass as ParameterizedType).actualTypeArguments.forEach {
                        if (topTarget.isAssignableFrom(it as Class<*>)) {
                            return it as Class<T>
                        }
                    }
                }
                // 向当前类父类递归
                temp = temp.superclass as Class<Any>
                //如果当前类的父类已经超出递归上界，跳出
                if (topObj != null && !topObj.isAssignableFrom(temp)) {
                    temp = null
                }
            }
        } catch (e: java.lang.ClassCastException) {
            e.printStackTrace()
        }
        return null
    }
    /**
     * @param o  目标对象
     * @param topObj 目标对象的递归查找上界
     * @param topTarget 目标泛型的上界
     */
    fun <T> getTargetTFromObjOp(o: Any?, topObj: Class<*>?, topTarget: Class<*>?): Class<out T>? {
        if (o == null || topTarget == null || (topObj != null && !topObj.isAssignableFrom(o.javaClass))) {
            return null
        }
        var temp: Class<*>? = o.javaClass
        while (temp != null) {
            if (temp.genericSuperclass is ParameterizedType) {
                //遍历当前类的泛型声明
                (temp.genericSuperclass as ParameterizedType).actualTypeArguments.forEach {
                    if (topTarget.isAssignableFrom(it as Class<*>)) {
                        return it as Class<out T>
                    }
                }
            }
            // 向当前类父类递归
            temp = temp.superclass as Class<*>
            //如果当前类的父类已经超出递归上界，跳出
            if (topObj != null && !topObj.isAssignableFrom(temp)) {
                temp = null
            }
        }
        return null
    }

}