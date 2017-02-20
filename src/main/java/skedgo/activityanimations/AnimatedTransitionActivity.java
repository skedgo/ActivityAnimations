package skedgo.activityanimations;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;

import skedgo.rxlifecyclecomponents.RxAppCompatActivity;

public abstract class AnimatedTransitionActivity extends RxAppCompatActivity {
  private static final String KEY_HAS_CUSTOM_ANIMATION =
      AnimatedTransitionActivity.class.getSimpleName() + ".hasCustomAnimation";

  @Nullable private Pair<Integer, Integer> enteringAnimation;
  @Nullable private Pair<Integer, Integer> exitingAnimation;

  public static Intent setHasCustomAnimation(Intent intent, boolean value) {
    return intent.putExtra(KEY_HAS_CUSTOM_ANIMATION, value);
  }

  public static boolean hasCustomAnimation(Intent intent) {
    return intent.getBooleanExtra(KEY_HAS_CUSTOM_ANIMATION, true);
  }

  protected void setEnteringAnimation(@NonNull Pair<Integer, Integer> enteringAnimation) {
    this.enteringAnimation = enteringAnimation;
  }

  protected void setExitingAnimation(@NonNull Pair<Integer, Integer> exitingAnimation) {
    this.exitingAnimation = exitingAnimation;
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (hasCustomAnimation(getIntent())) {
      if (enteringAnimation == null) {
        overridePendingTransition(
            R.anim.entering_slide_left,
            R.anim.exiting_scale_down_fade_out
        );
      } else {
        overridePendingTransition(enteringAnimation.first, enteringAnimation.second);
      }
    }
  }

  @Override protected void onPause() {
    super.onPause();

    // Check isFinishing() to avoid weird sliding animation
    // when pressing Home button or navigating to other apps.
    if (hasCustomAnimation(getIntent()) && isFinishing()) {
      if (exitingAnimation == null) {
        overridePendingTransition(
            R.anim.entering_scale_up_fade_in,
            R.anim.exiting_slide_right
        );
      } else {
        overridePendingTransition(exitingAnimation.first, exitingAnimation.second);
      }
    }
  }
}