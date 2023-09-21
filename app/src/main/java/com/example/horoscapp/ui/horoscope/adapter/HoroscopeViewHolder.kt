package com.example.horoscapp.ui.horoscope.adapter

import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscapp.databinding.ItemHoroscopeBinding
import com.example.horoscapp.domain.model.HoroscopeInfo

class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemHoroscopeBinding.bind(view)
    fun render(horoscopeInfo: HoroscopeInfo, onItemSelected: (HoroscopeInfo) -> Unit) {
        binding.root.setOnClickListener {
            startRotationAnimation(
                binding.ivHoroscope,
                newAction = { onItemSelected(horoscopeInfo) })

        }
        binding.tvTitle.text = binding.tvTitle.context.getString(horoscopeInfo.name)
        binding.ivHoroscope.setImageResource(horoscopeInfo.image)
    }

    fun startRotationAnimation(view: View, newAction: () -> Unit) {
        view.animate().apply {
            duration = 500
            interpolator = LinearInterpolator()
            rotationYBy(360f)
            withEndAction { newAction() }
            start()
        }
    }
}