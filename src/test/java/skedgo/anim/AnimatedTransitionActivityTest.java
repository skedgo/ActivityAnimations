package skedgo.anim;

import android.content.Intent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import skedgo.activityanimations.BuildConfig;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static skedgo.anim.AnimatedTransitionActivity.hasCustomAnimation;
import static skedgo.anim.AnimatedTransitionActivity.setHasCustomAnimation;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class AnimatedTransitionActivityTest {
  @Test public void shouldHaveCustomAnimation() {
    final Intent intent = new Intent();
    setHasCustomAnimation(intent, true);
    assertTrue(hasCustomAnimation(intent));
  }

  @Test public void shouldNotHaveCustomAnimation() {
    final Intent intent = new Intent();
    setHasCustomAnimation(intent, false);
    assertFalse(hasCustomAnimation(intent));
  }
}