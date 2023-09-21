package com.example.horoscapp.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.example.horoscapp.R
import com.example.horoscapp.databinding.ActivityHoroscopeDetailBinding
import com.example.horoscapp.domain.model.HoroscopeModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHoroscopeDetailBinding
    private val horoscopeDetailViewModel: HoroscopeDetailViewModel by viewModels()

    private val args: HoroscopeDetailActivityArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHoroscopeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
        horoscopeDetailViewModel.getHoroscope(args.type)

    }

    private fun initUI() {
        initListeners()
        initUIState()
    }

    private fun initListeners() {
        binding.ivBack.setOnClickListener { onBackPressed() }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopeDetailViewModel.state.collect { state ->
                    when (state) {
                        is HoroscopeDetailState.Loading -> {
                            binding.pbLoading.isVisible = true

                        }

                        is HoroscopeDetailState.Success -> {
                            binding.pbLoading.isVisible = false
                            binding.tvTitle.text = state.sign
                            binding.tvBody.text = state.prediction

                            val image = when (state.horoscopeModel) {
                                HoroscopeModel.Aries -> R.drawable.detail_aries
                                HoroscopeModel.Taurus -> R.drawable.detail_taurus
                                HoroscopeModel.Gemini -> R.drawable.detail_gemini
                                HoroscopeModel.Cancer -> R.drawable.detail_cancer
                                HoroscopeModel.Leo -> R.drawable.detail_leo
                                HoroscopeModel.Virgo -> R.drawable.detail_virgo
                                HoroscopeModel.Libra -> R.drawable.detail_libra
                                HoroscopeModel.Scorpio -> R.drawable.detail_scorpio
                                HoroscopeModel.Sagittarius -> R.drawable.detail_sagittarius
                                HoroscopeModel.Capricorn -> R.drawable.detail_capricorn
                                HoroscopeModel.Aquarius -> R.drawable.detail_aquarius
                                HoroscopeModel.Pisces -> R.drawable.detail_pisces
                            }
                            binding.ivDetail.setImageResource(image)
                        }

                        is HoroscopeDetailState.Error -> {
                            binding.pbLoading.isVisible = false
                        }

                        else -> {}
                    }
                }
            }
        }
    }
}
