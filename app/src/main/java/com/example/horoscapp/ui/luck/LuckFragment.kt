package com.example.horoscapp.ui.luck

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment
import com.example.horoscapp.R
import com.example.horoscapp.databinding.FragmentLuckBinding
import com.example.horoscapp.ui.core.listeners.OnSwipeTouchListener
import com.example.horoscapp.ui.providers.RandomCardProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LuckFragment : Fragment() {

    private var _binding: FragmentLuckBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var randomCardProvider: RandomCardProvider

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLuckBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()

    }

    private fun initUI() {
        preparePrediction()
        initListeners()
    }

    private fun preparePrediction() {
        val luckyCard = randomCardProvider.getLuckyCard()
        luckyCard?.let { luck ->
            binding.ivLuckyCard.setImageResource(luck.image)
            binding.tvLucky.text = getString(luck.text)
            binding.btnShare.setOnClickListener { sharePrediction(getString(luck.text)) }
        }
    }

    private fun sharePrediction(prediction: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, prediction)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, "Comparte tu predicción")
        startActivity(shareIntent)
    }

    private fun initListeners() {
        //binding.ivRoulette.setOnClickListener { spinRoulette() }
        binding.ivRoulette.setOnTouchListener(object : OnSwipeTouchListener(requireContext()) {
            override fun onSwipeRight() {
                spinRoulette()
            }

            override fun onSwipeLeft() {
                spinRoulette()
            }
        })
    }

    private fun spinRoulette() {
        val random = (360..1440).random()
        val animator =
            ObjectAnimator.ofFloat(binding.ivRoulette, View.ROTATION, 0f, random.toFloat())
        animator.duration = 1000
        animator.interpolator = DecelerateInterpolator()
        animator.doOnEnd { slideAndSpinCard() }
        animator.start()
    }

    private fun slideAndSpinCard() {
        val slideAndSpinAnimation =
            AnimationUtils.loadAnimation(requireContext(), R.anim.slide_and_spin)
        slideAndSpinAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                binding.ivReverse.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animation?) {
                growUpCard()

            }

            override fun onAnimationRepeat(animation: Animation?) {
            }

        })

        binding.ivReverse.startAnimation(slideAndSpinAnimation)

    }

    private fun growUpCard() {
        val growUpAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.grow_up)
        growUpAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                binding.ivReverse.visibility = View.GONE
                showPremonitionView()
// cositas
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }

        })
        binding.ivReverse.startAnimation(growUpAnimation)
    }

    private fun showPremonitionView() {
        val disappearAnimation = AlphaAnimation(1f, 0f)
        disappearAnimation.duration = 500

        val appearAnimation = AlphaAnimation(0f, 1f)
        appearAnimation.duration = 1000

        disappearAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                binding.clPreview.visibility = View.GONE
                binding.clPrediction.visibility = View.VISIBLE
            }

            override fun onAnimationRepeat(animation: Animation?) {}

        })
        binding.clPreview.startAnimation(disappearAnimation)
        binding.clPrediction.startAnimation(appearAnimation)
    }

}