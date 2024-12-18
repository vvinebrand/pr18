package com.example.pr18

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter

// Главная активити
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherApp()
        }
    }
}

// Основное приложение
@Composable
fun WeatherApp(weatherViewModel: WeatherViewModel = viewModel()) {
    val weatherState = weatherViewModel.weatherState.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFBBDEFB) // Цвет фона
    ) {
        WeatherScreen(weatherState.value)
    }
}

// Экран с погодой
@Composable
fun WeatherScreen(weather: WeatherData) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = weather.city,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = rememberImagePainter(data = weather.iconUrl),
            contentDescription = "Weather Icon",
            modifier = Modifier.size(120.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "${weather.temperature}°C",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = weather.description,
            fontSize = 20.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

// Данные о погоде
data class WeatherData(
    val city: String,
    val temperature: Int,
    val description: String,
    val iconUrl: String
)

// ViewModel для погоды
class WeatherViewModel : ViewModel() {
    private val _weatherState = mutableStateOf(
        WeatherData(
            city = "Moscow",
            temperature = 15,
            description = "Partly Cloudy",
            iconUrl = "https://openweathermap.org/img/wn/02d@2x.png"
        )
    )
    val weatherState: State<WeatherData> = _weatherState
}
