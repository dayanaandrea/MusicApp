package com.example.musicapp

import android.media.AudioManager
import android.os.Bundle
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var seekBarTime: SeekBar
    private lateinit var seekBarAudio: SeekBar
    private lateinit var playPauseButton: ImageView
    private lateinit var textViewTiempo1: TextView
    private lateinit var textViewTiempo2: TextView
    private var isPlaying = false
    private val duracion = 235 // Duración de la canción en segundos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBarTime = findViewById(R.id.seekBarTime)
        seekBarAudio = findViewById(R.id.seekBarAudio)
        playPauseButton = findViewById(R.id.imageFilterButtonReproducir)
        textViewTiempo1 = findViewById(R.id.textViewTiempo1)
        textViewTiempo2 = findViewById(R.id.textViewTiempo2)


        seekBarTime.progress = 25 // 25% de la duración
        seekBarAudio.progress = 70 // 70% del volumen
        textViewTiempo1.text = "0:25" // Tiempo avanzado
        textViewTiempo2.text = "3:55" // Tiempo restante

        // Configurar SeekBar de tiempo
        //E cogido ideas del siguiente video https://www.youtube.com/watch?v=aZdcrU_QGzo
        seekBarTime.max = duracion
        seekBarTime.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val minutes = progress / 60
                val seconds = progress % 60             //%02d  formar el int en dos digitos en vez de aparecer 1 aparece 01
                textViewTiempo1.text = String.format("%d:%02d", minutes, seconds)
                textViewTiempo2.text = String.format("%d:%02d", (duracion - progress) / 60, (duracion - progress) % 60)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Botón Play/Pause
        playPauseButton.setOnClickListener {
            isPlaying = !isPlaying
            playPauseButton.setImageResource(if (isPlaying) android.R.drawable.ic_media_pause else android.R.drawable.ic_media_play)
            // Aquí puedes agregar lógica para reproducir o pausar la canción
        }
    }
}