package com.example.available

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

val BgDark = Color(0xFF111625)
val CardDark = Color(0xFF1A2232)
val AccentOrange = Color(0xFFF17322)
val PrimaryBlue = Color(0xFF2F62ED)
val StatusGreen = Color(0xFF4CAF50)
val StatusRed = Color(0xFFE53935)
val LightGray = Color(0xFFE0E0E0)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize(), color = BgDark) {
                val menuViewModel: LaboratorioViewModel = viewModel()
                var telaAtual by remember { mutableStateOf("lista") }
                var labSelecionado by remember { mutableStateOf<Laboratorio?>(null) }

                when (telaAtual) {
                    "login" -> TelaLogin(onLoginSuccess = { telaAtual = "lista" })
                    "lista" -> TelaListaLaboratorios(
                        viewModel = menuViewModel,
                        onLabClick = { lab ->
                            labSelecionado = lab
                            telaAtual = "detalhes"
                        }
                    )
                    "detalhes" -> labSelecionado?.let { lab ->
                        TelaDetalhesLaboratorio(
                            lab = lab,
                            onAgendarClick = { telaAtual = "calendario" }
                        )
                    }
                    "calendario" -> labSelecionado?.let { lab ->
                        TelaCalendarioAgendamento(
                            labId = lab.id,
                            viewModel = menuViewModel,
                            onVoltarClick = { telaAtual = "lista" }
                        )
                    }
                }
            }
        }
    }
}

class LaboratorioViewModel : ViewModel() {
    private val _laboratorios = mutableStateOf<List<Laboratorio>>(emptyList())
    val laboratorios: State<List<Laboratorio>> = _laboratorios

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun buscarLaboratorios() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val resposta = RetrofitClient.apiService.getLaboratorios()
                _laboratorios.value = resposta
            } catch (e: Exception) {
                // Tratado internamente
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun agendarLaboratorio(labId: Int, data: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.cadastrarReserva(ReservaRequest(labId, data))
                if (response.isSuccessful) {
                    onSuccess()
                }
            } catch (e: Exception) {
                // Erro de conexão
            }
        }
    }
}

@Composable
fun TelaLogin(onLoginSuccess: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().background(BgDark).padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("AvaiLaBle", color = AccentOrange, fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(40.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text("Login", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(value = senha, onValueChange = { senha = it }, label = { Text("Password") }, modifier = Modifier.fillMaxWidth(), visualTransformation = PasswordVisualTransformation())
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = onLoginSuccess, modifier = Modifier.fillMaxWidth().height(48.dp), colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue), shape = RoundedCornerShape(8.dp)) {
                    Text("Log In", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun TelaListaLaboratorios(viewModel: LaboratorioViewModel, onLabClick: (Laboratorio) -> Unit) {
    LaunchedEffect(Unit) { viewModel.buscarLaboratorios() }
    val laboratorios by viewModel.laboratorios
    val carregando by viewModel.isLoading

    Column(modifier = Modifier.fillMaxSize().background(BgDark).padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text("AvaiLaBle", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text("⚙️", color = Color.White, fontSize = 24.sp)
        }
        Spacer(modifier = Modifier.height(20.dp))
        if (carregando) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator(color = AccentOrange) }
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(laboratorios) { lab -> CardLaboratorio(lab, onLabClick) }
            }
        }
    }
}

@Composable
fun CardLaboratorio(lab: Laboratorio, onLabClick: (Laboratorio) -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().clickable { onLabClick(lab) }, colors = CardDefaults.cardColors(containerColor = CardDark), shape = RoundedCornerShape(12.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(lab.nome, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = lab.status, color = if (lab.status.contains("Disponível", ignoreCase = true)) StatusGreen else StatusRed)
        }
    }
}

@Composable
fun TelaDetalhesLaboratorio(lab: Laboratorio, onAgendarClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(BgDark).padding(16.dp)) {
        Text(lab.nome, color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(text = lab.status, color = if (lab.status.contains("Disponível", ignoreCase = true)) StatusGreen else StatusRed, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(24.dp))
        ItemRecurso("🖥️ Computadores", "${lab.computadores}")
        ItemRecurso("📹 Projetor", lab.projetor)
        ItemRecurso("⚡ Tomadas", lab.tomadas)
        ItemRecurso("❄️ A/C", lab.arCondicionado)
        ItemRecurso("✏️ Lousa", lab.lousa)
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = onAgendarClick, modifier = Modifier.fillMaxWidth().height(50.dp), colors = ButtonDefaults.buttonColors(containerColor = AccentOrange), shape = RoundedCornerShape(8.dp)) {
            Text("AGENDAR", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ItemRecurso(nome: String, status: String) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp).background(CardDark, RoundedCornerShape(8.dp)).padding(14.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(nome, color = LightGray)
        Text(text = status, color = if(status.contains("Indisponível", ignoreCase = true)) StatusRed else Color.White, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun TelaCalendarioAgendamento(labId: Int, viewModel: LaboratorioViewModel, onVoltarClick: () -> Unit) {
    var dataSelecionada by remember { mutableStateOf("2026-06-02") }
    Column(modifier = Modifier.fillMaxSize().background(BgDark).padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Junho 2026", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(20.dp))
        Card(modifier = Modifier.fillMaxWidth().height(250.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("[ Calendário Interativo: Dia 02 ]", color = Color.Black) }
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { viewModel.agendarLaboratorio(labId, dataSelecionada, onSuccess = onVoltarClick) }, modifier = Modifier.fillMaxWidth().height(50.dp), colors = ButtonDefaults.buttonColors(containerColor = AccentOrange), shape = RoundedCornerShape(8.dp)) {
            Text("CONFIRMAR AGENDAMENTO", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}