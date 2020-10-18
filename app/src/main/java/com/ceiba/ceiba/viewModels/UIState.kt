package com.ceiba.ceiba.viewModels

sealed class UIState {
    class Success(val data: Any) : UIState()
    class Error(val message: String) : UIState()
}