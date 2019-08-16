package com.thsai.jetpack.ui.fragment

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.thsai.jetpack.common.listener.SimpleAnimation
import com.thsai.jetpack.databinding.FragmentShoeBinding
import com.thsai.jetpack.utils.UiUtils

class ShoeFragment : Fragment() {

    private var animatorSet: AnimatorSet? = null

    private var radius: Int = 0

    private var size: Int = 0

    private lateinit var fab_shoe: FloatingActionButton
    private lateinit var fab_nike: FloatingActionButton
    private lateinit var fab_adidas: FloatingActionButton
    private lateinit var fab_other: FloatingActionButton

    private lateinit var gp_nike: Group
    private lateinit var gp_adi: Group
    private lateinit var gp_other: Group

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentShoeBinding.inflate(inflater, container, false)
        onSubscribeUI(binding)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        fab_shoe.post {
            size = fab_shoe.measuredWidth
        }
        radius = UiUtils.dp2px(requireContext(), 80f)
    }

    private fun onSubscribeUI(binding: FragmentShoeBinding) {
        fab_shoe = binding.fabShoe
        fab_nike = binding.fabNike
        fab_adidas = binding.fabAdidas
        fab_other = binding.fabOther

        gp_nike = binding.gpNike
        gp_adi = binding.gpAdi
        gp_other = binding.gpOther

        fab_shoe.setOnClickListener {
            shoeAnimation()
        }

        setViewVisible(false)
    }

    private fun shoeAnimation() {
        if (animatorSet != null && animatorSet!!.isRunning) {
            return
        }
        if (gp_nike.visibility != View.VISIBLE) {
            animatorSet = AnimatorSet()
            val nikeAnimator = getValueAnimator(fab_nike, false, gp_nike, 0)
            val adiAnimator = getValueAnimator(fab_adidas, false, gp_adi, 45)
            val otherAnimator = getValueAnimator(fab_other, false, gp_other, 90)
            animatorSet?.playSequentially(nikeAnimator, adiAnimator, otherAnimator)
            animatorSet?.start()
        } else {
            animatorSet = AnimatorSet()
            val nikeAnimator = getValueAnimator(fab_nike, true, gp_nike, 0)
            val adiAnimator = getValueAnimator(fab_adidas, true, gp_adi, 45)
            val otherAnimator = getValueAnimator(fab_other, true, gp_other, 90)
            animatorSet?.playSequentially(otherAnimator, adiAnimator, nikeAnimator)
            animatorSet?.start()
        }
    }

    private fun getValueAnimator(fab: FloatingActionButton, reverse: Boolean, group: Group, angle: Int): Animator {
        val animator: ValueAnimator
        if (reverse)
            animator = ValueAnimator.ofFloat(1f, 0f)
        else
            animator = ValueAnimator.ofFloat(0f, 1f)
        animator.addUpdateListener { animation ->
            val value = animation.animatedValue as Float
            val params = fab.layoutParams as ConstraintLayout.LayoutParams
            params.circleRadius = (radius.toFloat() * value).toInt()
            params.circleAngle = 270f + angle * value
            params.width = (size.toFloat() * value).toInt()
            params.height = (size.toFloat() * value).toInt()
            fab.layoutParams = params

            if (group == gp_nike) {

            }
        }
        animator.addListener(object : SimpleAnimation() {
            override fun onAnimationStart(animation: Animator?) {
                super.onAnimationStart(animation)
                group.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                if (group == gp_nike && reverse)
                    setViewVisible(false)
            }
        })
        animator.duration = 300
        animator.interpolator = DecelerateInterpolator()
        return animator
    }

    private fun setViewVisible(isShow: Boolean) {
        gp_nike.visibility = if (isShow) View.VISIBLE else View.GONE
        gp_adi.visibility = if (isShow) View.VISIBLE else View.GONE
        gp_other.visibility = if (isShow) View.VISIBLE else View.GONE
    }
}