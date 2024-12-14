package br.edu.ifsp.dmo.pedratesouraepapel.view

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo.pedratesouraepapel.R
import br.edu.ifsp.dmo.pedratesouraepapel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configToolBar()
        configSpinner()
        configListener()
    }

    private fun configToolBar() {
        supportActionBar?.hide()
    }

    private fun configSpinner() {
        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            resources.getStringArray(R.array.tipos_jogos)
        )

        binding.spinnerButtles.adapter = adapter
    }

    private fun configListener() {
        binding.buttonStart.setOnClickListener { startGame() }
    }

    private fun startGame() {
        val battles: Int = when (binding.spinnerButtles.selectedItemPosition) {
            0 -> 1
            1 -> 3
            else -> 5
        }

        val mIntent = Intent(this, WarActivity::class.java)
        mIntent.putExtra(Constants.KEY_PLAYER_1, binding.edittextPlayer1.text.toString())
        mIntent.putExtra(Constants.KEY_PLAYER_2, binding.edittextPlayer2.text.toString())
        mIntent.putExtra(Constants.KEY_IS_BOT_1, binding.checkboxPlayer1.isChecked)
        mIntent.putExtra(Constants.KEY_IS_BOT_2, binding.checkboxPlayer2.isChecked)
        mIntent.putExtra(Constants.KEY_ROUNDS, battles)
        startActivity(mIntent)
    }
}