package me.camilo.ortiz.myapplicationdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.camilo.ortiz.myapplicationdemo.ui.theme.MyApplicationDemoTheme

class MainActivity : ComponentActivity() {

    // Instancia única del ViewModel
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationDemoTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    PantallaPrincipal(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun PantallaPrincipal(viewModel: MainViewModel) {
    // La vista se suscribe al estado expuesto por el ViewModel
    val contadorState by viewModel.contador.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Vista 1: Modifica el estado mediante eventos
        VistaContador(
            valor = contadorState,
            onIncrementar = { viewModel.incrementar() },
            onReiniciar = { viewModel.reiniciar() }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Vista 2: Lee el mismo estado y reacciona a sus cambios
        VistaResumen(valor = contadorState)
    }
}

@Composable
fun VistaContador(
    valor: Int,
    onIncrementar: () -> Unit,
    onReiniciar: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Vista 1: Control de Contador", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "$valor", fontSize = 36.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Button(onClick = onIncrementar) {
                    Text("Incrementar")
                }
                Spacer(modifier = Modifier.width(9.dp))
                OutlinedButton(onClick = onReiniciar) {
                    Text("Reiniciar")
                }
            }
        }
    }
}

@Composable
fun VistaResumen(valor: Int) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Vista 2: Estado derivado", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            val mensaje = if (valor % 2 == 0) "El número es PAR" else "El número es IMPAR"
            Text(text = mensaje, fontSize = 16.sp)
        }
    }
}