package com.example.spravochnik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.spravochnik.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()
        binding.searchButton.setOnClickListener {
            lifecycleScope.launch {
                try {
                    val country_name = binding.countryNameEditText.text.toString()
                    val countries = restCountriesAPI.getCountryByName(country_name)
                    val country = countries[0]
                    val cnt: Int

                    binding.countryNameTextView.text = country.name.common
                    binding.capitalTextView.text = country.capital[0]
                    binding.populationTextView.text = FormatNumber(country.population)
                    binding.areaTextView.text = FormatNumber(country.area)
                    loadSVG(binding.imageView, country.flags.svg)

                    binding.resultLayout.visibility = View.VISIBLE
                    binding.statusLayout.visibility = View.INVISIBLE
                }catch (e:Exception){
                    binding.statusTextView.text = "Страна не найдена"
                    binding.imageStatus.setImageResource(R.drawable.baseline_error_24)
                    binding.resultLayout.visibility = View.INVISIBLE
                    binding.statusLayout.visibility = View.VISIBLE
                }
            }
        }
    }
}

