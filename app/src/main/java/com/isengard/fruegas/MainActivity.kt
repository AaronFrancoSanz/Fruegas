package com.isengard.fruegas

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.util.Log

class MainActivity : AppCompatActivity() {
    private lateinit var editTextId: EditText
    private lateinit var spinnerTipo: Spinner
    private lateinit var radioGroup: RadioGroup
    private lateinit var rbArmadura: RadioButton
    private lateinit var rbEscudo: RadioButton
    private lateinit var checkAntorcha: CheckBox
    private lateinit var btnEnviar: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        editTextId = findViewById(R.id.editTextText)
        spinnerTipo = findViewById(R.id.spinner)
        radioGroup = findViewById(R.id.radioGroup)
        rbArmadura = findViewById(R.id.radioButton)
        rbEscudo = findViewById(R.id.radioButton2)
        checkAntorcha = findViewById(R.id.checkBox)
        btnEnviar = findViewById(R.id.imageButton)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.tipos_unidad,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTipo.adapter = adapter

        editTextId.requestFocus() //foco

        //Perdida del foco, aunque no se si en algún momento lo pierde
        editTextId.setOnFocusChangeListener { _, tieneFoco ->
            if (!tieneFoco && editTextId.text.toString().trim().isEmpty()) {
                editTextId.error = "El ejército no acepta soldados anónimos"
            }
        }
        btnEnviar.setOnClickListener {
            enviarTropa()
        }




    }
    //logs
    override fun onStart() {
        super.onStart()
        Log.d("FraguasIsengard", "onStart: Las fraguas se encienden")
    }

    override fun onResume() {
        super.onResume()
        Log.d("FraguasIsengard", "onResume: Los Uruk-hai marchan a pleno rendimiento")
    }

    override fun onPause() {
        super.onPause()
        Log.d("FraguasIsengard", "onPause: Saruman detiene la producción temporalmente")
    }

    override fun onStop() {
        super.onStop()
        Log.d("FraguasIsengard", "onStop: Las fraguas quedan en silencio")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("FraguasIsengard", "onDestroy: Isengard cae bajo las aguas de los Ents")
    }
    private fun enviarTropa() {
        val identificador = editTextId.text.toString().trim()
        if (identificador.isEmpty()) {
            editTextId.error = "El ejército no acepta soldados anónimos"
            editTextId.requestFocus()
            return
        }

        if (radioGroup.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Elige Armadura de Hierro o Escudo de Isengard", Toast.LENGTH_SHORT).show()
            return
        }

        val tipoUnidad = spinnerTipo.selectedItem.toString()
        val equipo = findViewById<RadioButton>(radioGroup.checkedRadioButtonId).text.toString()
        val antorcha = if (checkAntorcha.isChecked) "Sí" else "No"

        Toast.makeText(
            this,
            "¡Unidad $identificador enviada al Abismo de Helm!",
            Toast.LENGTH_LONG
        ).show()
    }
}