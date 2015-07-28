package anjithsasindran.materialsharingcard;

import android.animation.Animator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class MainActivity extends Activity {

    ImageView imageView;
    ImageButton imageButton;
    LinearLayout revealView, layoutButtons;
    Animation alphaAnimation;
    int pixelDensity;
    boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pixelDensity = (int) getResources().getDisplayMetrics().density;

        imageView = (ImageView) findViewById(R.id.imageView);
        imageButton = (ImageButton) findViewById(R.id.launchTwitterAnimation);
        revealView = (LinearLayout) findViewById(R.id.linearView);
        layoutButtons = (LinearLayout) findViewById(R.id.layoutButtons);

        alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
    }

    public void launchTwitter(View view) {

        /*
         MARGIN_RIGHT = 16;
         FAB_BUTTON_RADIUS = 28;
         */
        int x = imageView.getRight();
        int y = imageView.getBottom();
        x -= ((28 * pixelDensity) + (16 * pixelDensity));

        int hypotenuse = (int) Math.hypot(imageView.getWidth(), imageView.getHeight());

        if (flag) {

            imageButton.setBackgroundResource(R.drawable.rounded_cancel_button);
            imageButton.setImageResource(R.mipmap.image_cancel);

            FrameLayout.LayoutParams parameters = (FrameLayout.LayoutParams)
                    revealView.getLayoutParams();
            parameters.height = imageView.getHeight();
            revealView.setLayoutParams(parameters);

            Animator anim = ViewAnimationUtils.createCircularReveal(revealView, x, y, 0, hypotenuse);
            anim.setDuration(700);

            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    layoutButtons.setVisibility(View.VISIBLE);
                    layoutButtons.startAnimation(alphaAnimation);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });

            revealView.setVisibility(View.VISIBLE);
            anim.start();

            flag = false;
        } else {

            imageButton.setBackgroundResource(R.drawable.rounded_button);
            imageButton.setImageResource(R.mipmap.twitter_logo);

            Animator anim = ViewAnimationUtils.createCircularReveal(revealView, x, y, hypotenuse, 0);
            anim.setDuration(400);

            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    revealView.setVisibility(View.GONE);
                    layoutButtons.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });

            anim.start();
            flag = true;
        }
    }

}