package me.camilo.ortiz.myapplicationdemo

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {

    // Estado privado (Mutable)
    private val _contador = MutableStateFlow(0)

    // Estado público (Inmutable para las Vistas)
    val contador: StateFlow<Int> = _contador.asStateFlow()

    // Eventos/Acciones para modificar el estado
    fun incrementar() {
        _contador.value += 1
    }

    fun reiniciar() {
        _contador.value = 0
    }
}

