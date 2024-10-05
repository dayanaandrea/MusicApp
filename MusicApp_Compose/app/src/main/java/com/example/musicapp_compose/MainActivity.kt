package com.example.musicapp_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicapp_compose.ui.theme.MusicApp_ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicApp_ComposeTheme {
                // Llama a la función para mostrar por  pantalla  la calculadora
                MusicApp()
            }
        }
    }
}

@Composable
fun MusicApp() {
    var isPlaying by remember { mutableStateOf(false) }
    // Obtiene la orientacion de la pantalla
    val configuration = LocalConfiguration.current
    // Verifica si la orientación  es vertical
    val isPortrait =
        configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT


    val currentTime = 25 // en segundos
    val totalTime = 235 // en segundos (3 minutos y 55 segundos)
    val volume = 70 // en porcentaje

    if (isPortrait) {
        MusicAppVertical(isPlaying) { isPlaying = it }

    } else {
        MusicAppHorizontal(isPlaying) { isPlaying = it }
    }
}

@Composable
fun MusicAppHorizontal(isPlaying: Boolean, onPlayPauseClick: (Boolean) -> Unit) {
    val currentTime = 25 // en segundos
    val totalTime = 235 // en segundos (3 minutos y 55 segundos)
    val volume = 70 // en porcentaje

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), // Ajusta el padding si es necesario
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly // Espaciado uniforme entre los elementos
    ) {
        // Fila para alinear la imagen a la izquierda
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start // Alineación horizontal a la izquierda
        ) {
            // Imagen del disco
            Image(
                painter = painterResource(id = R.drawable.cancion),
                contentDescription = "Imagen del disco",
                modifier = Modifier
                    .size(200.dp) // Ajusta el tamaño si es necesario
                    .padding(16.dp)
            )

            Column {
                // Título de la canción
                Text(text = "20 de abril", fontSize = 20.sp, textAlign = TextAlign.Center)

                // Nombre del grupo
                Text(text = "Celtas Cortos", fontSize = 16.sp, textAlign = TextAlign.Center)

                // Barra deslizadora de volumen
                SliderVolumen(volume)
            }


        }


        // Botones de control
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(id = R.drawable.botonvolver),
                contentDescription = "Volver",
                modifier = Modifier.size(50.dp)
            )
            PlayPauseBoton(isPlaying) { onPlayPauseClick(!isPlaying) }
            Image(
                painter = painterResource(id = R.drawable.botonsiguiente),
                contentDescription = "Siguiente",
                modifier = Modifier.size(50.dp)
            )
        }


        // Barra deslizadora del tiempo
        SliderTiempo(currentTime, totalTime)

        // Botones adicionales
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(id = R.drawable.compartir),
                contentDescription = "Compartir",
                modifier = Modifier.size(50.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.letra),
                contentDescription = "Letra",
                modifier = Modifier.size(50.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.listado),
                contentDescription = "Listado",
                modifier = Modifier.size(50.dp)
            )
        }
    }
}

@Composable
fun MusicAppVertical(isPlaying: Boolean, onPlayPauseClick: (Boolean) -> Unit) {


    val currentTime = 25 // en segundos
    val totalTime = 235 // en segundos (3 minutos y 55 segundos)
    val volume = 70 // en porcentaje


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        // Imagen del disco
        Image(
            painter = painterResource(id = R.drawable.cancion), // Reemplaza con tu imagen
            contentDescription = "Imagen del disco",

            modifier = Modifier
                .size(200.dp)
                .padding(0.dp)

        )

        // Título de la canción
        Text(text = "20 de abril", fontSize = 20.sp, textAlign = TextAlign.Center)

        // Nombre del grupo
        Text(text = "Celtas Cortos", fontSize = 16.sp, textAlign = TextAlign.Center)

        // Barra deslizadora del tiempo
        SliderTiempo(currentTime, totalTime)

        // Botones de control
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(id = R.drawable.botonvolver),
                contentDescription = "Volver",
                modifier = Modifier.size(50.dp)
            )
            PlayPauseBoton(isPlaying) { onPlayPauseClick(!isPlaying) }
            Image(
                painter = painterResource(id = R.drawable.botonsiguiente),
                contentDescription = "Siguiente",
                modifier = Modifier.size(50.dp)
            )
        }

        // Barra deslizadora de volumen
        SliderVolumen(volume)

        // Botones adicionales
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(id = R.drawable.compartir),
                contentDescription = "Compartir",
                modifier = Modifier.size(50.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.letra),
                contentDescription = "Letra",
                modifier = Modifier.size(50.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.listado),
                contentDescription = "Listado",
                modifier = Modifier.size(50.dp)
            )

        }
    }
}


@Composable
fun SliderTiempo(currentTime: Int, totalTime: Int) {
    val progress = currentTime / totalTime.toFloat()

    // Función para formatear el tiempo en minutos:segundos
    fun formatTime(seconds: Int): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return String.format("%01d:%02d", minutes, remainingSeconds)
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = formatTime(currentTime), fontSize = 14.sp)
        Slider(value = progress, onValueChange = {}, valueRange = 0f..1f)
        Text(text = formatTime(totalTime - currentTime), fontSize = 14.sp)
    }
}

@Composable
fun SliderVolumen(volume: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Volumen: $volume%", fontSize = 14.sp)
        Slider(value = volume / 100f, onValueChange = {}, valueRange = 0f..1f)
    }
}

@Composable
fun ImagenBoton(painter: Painter, contentDescription: String, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            colorFilter = ColorFilter.tint(
                Color.Black
            )
        )
    }
}

@Composable
fun PlayPauseBoton(isPlaying: Boolean, onClick: (Boolean) -> Unit) {
    val icon =
        if (isPlaying) android.R.drawable.ic_media_pause else android.R.drawable.ic_media_play
    ImagenBoton(
        painterResource(id = icon),
        if (isPlaying) "Pausar" else "Reproducir",
        { onClick(isPlaying) }
    )
}

// Apaisado
@Preview(
    showBackground = true,
    heightDp = 412,
    widthDp = 873
)
// Vertical
@Preview(
    showBackground = true,
    widthDp = 412,
    heightDp = 873
)
@Composable
fun CalculatorScreenPreview() {
    MusicApp_ComposeTheme {
        MusicApp()
    }
}
