package net.soufiane.imc_android_app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.ui.semantics.text


class MainActivity : ComponentActivity() {

    var poidsEditText: EditText? = null
    var tailleEditText: EditText? = null
    var calculerButton: Button? = null
    var imcTextView: TextView? = null
    var categorieTextView: android.widget.TextView? = null
    var categorieImageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main);

        poidsEditText = findViewById(R.id.poidsEditText);
        tailleEditText = findViewById(R.id.tailleEditText);
        calculerButton = findViewById(R.id.calculerButton);
        imcTextView = findViewById(R.id.imcTextView);
        categorieTextView = findViewById(R.id.categorieTextView);
        categorieImageView = findViewById(R.id.categorieImageView);

        calculerButton?.setOnClickListener {
            val poids = poidsEditText?.text.toString().toDoubleOrNull()
            val tailleCM = tailleEditText?.text.toString().toDoubleOrNull()

            if (poids != null && tailleCM != null) {
                val tailleM = tailleCM / 100
                val imc = poids / (tailleM * tailleM)
                imcTextView?.text = String.format("Votre IMC est: %.2f", imc);



                val (categorieText, imageResId) = when {
                    imc < 18.5 -> "Maigreur" to R.drawable.maigre
                    imc < 25   -> "Normal" to R.drawable.normal
                    imc < 30   -> "Surpoids" to R.drawable.surpoids
                    imc < 40   -> "Obésité modérée" to R.drawable.obese
                    else       -> "Obésité sévère" to R.drawable.t_obese
                }
                categorieTextView?.text = categorieText
                categorieImageView?.setImageResource(imageResId)

            }else {
                Toast.makeText(this,"Veuillez entrer des valeurs valides",Toast.LENGTH_SHORT).show();

            }


        }
    }
}