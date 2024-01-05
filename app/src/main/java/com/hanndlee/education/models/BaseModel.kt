package com.hanndlee.education.models

sealed class BaseModel<out T> {
    data class Success<out T>(val data: T): BaseModel<T>()
    data class Error(val error: String) : BaseModel<Nothing>()
    object Loading: BaseModel<Nothing>()
}