package com.richzjc.scrollview;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;

/**
 * Created by wscn on 17/4/12.
 */

public class AnimateUtil {

    public static void showAnim(View view) {
        TranslateAnimation translateAnimation =
                new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF, 0f,
                        Animation.RELATIVE_TO_SELF, 0f,
                        Animation.RELATIVE_TO_SELF, 1f,
                        Animation.RELATIVE_TO_SELF, 0f);
        translateAnimation.setDuration(300);
        view.startAnimation(translateAnimation);
    }

    public static void dismissAnim(View view) {
        dismissAnim(view, 300);
    }

    public static void dismissAnim(View view, long time) {
        TranslateAnimation translateAnimation =
                new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF, 0f,
                        Animation.RELATIVE_TO_SELF, 0f,
                        Animation.RELATIVE_TO_SELF, 0f,
                        Animation.RELATIVE_TO_SELF, 1f);
        translateAnimation.setDuration(time);
        view.startAnimation(translateAnimation);
    }


    public static void showAnimUpToBottom(View view) {
        TranslateAnimation translate =
                new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, -1.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f);
        translate.setDuration(500);
        translate.setFillAfter(true);
        view.startAnimation(translate);
    }

    public static void dismissAnimBottomToUp(View view) {
        TranslateAnimation translate =
                new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, -1.0f);
        translate.setDuration(300);
        view.startAnimation(translate);
    }

    public static void dismissAnimUpToBottom(View view) {
        TranslateAnimation translate =
                new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 1.0f);
        translate.setDuration(300);
        view.startAnimation(translate);
    }

    public static void showAnimRightToLeft(View view) {
        TranslateAnimation translate =
                new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF, 1.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f);
        translate.setDuration(500);
        translate.setFillAfter(true);
        translate.setInterpolator(new AccelerateDecelerateInterpolator());
        view.startAnimation(translate);
    }

    public static void dismissAnimLeftToRight(View view) {
        TranslateAnimation translate =
                new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF, 0f,
                        Animation.RELATIVE_TO_SELF, 1.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f);
        translate.setDuration(200);
        translate.setFillAfter(true);
        translate.setInterpolator(new AccelerateDecelerateInterpolator());
        view.startAnimation(translate);
    }

    public static void showAlphaAnimation(View view) {
        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(view, "alpha", 0f, 0.5f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setInterpolator(new LinearInterpolator());
        alphaAnimation.start();
    }

    public static void dismissAlphaAnimation(final View view) {
        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(view, "alpha", 0.5f, 0);
        alphaAnimation.setDuration(300);
        alphaAnimation.setInterpolator(new LinearInterpolator());
        alphaAnimation.start();
    }

    public static void showAllAlphaAnimation(View view) {
        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setInterpolator(new LinearInterpolator());
        alphaAnimation.start();
    }

    public static void dismissAllAlphaAnimation(final View view) {
        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(view, "alpha", 1f, 0);
        alphaAnimation.setDuration(300);
        alphaAnimation.setInterpolator(new LinearInterpolator());
        alphaAnimation.start();
    }
}
