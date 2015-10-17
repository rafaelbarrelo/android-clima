package br.com.rbarrelo.clima.animations;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.graphics.drawable.ColorDrawable;
import android.view.View;

/**
 * Created by rafaelbarrelo on 10/17/15.
 */
public class ColorAnimation {

    @TargetApi(16)
    public static void animateBetweenColors(final View viewToAnimate, int corDestino, int duracaoEmMs)
    {
        ColorDrawable bg = (ColorDrawable) viewToAnimate.getBackground();
        final int corOrigem = bg.getColor();

        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), corOrigem, corDestino);

        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            ColorDrawable colorDrawable = new ColorDrawable(corOrigem);

            @Override
            public void onAnimationUpdate(final ValueAnimator animator) {
                colorDrawable.setColor((Integer) animator.getAnimatedValue());
                viewToAnimate.setBackground(colorDrawable);
            }
        });

        if (duracaoEmMs >= 0)
            colorAnimation.setDuration(duracaoEmMs);

        colorAnimation.start();
    }
}
