package com.example.trunghoang.materialviewpager.materialviewpager.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.trunghoang.materialviewpager.R;
import com.github.florent37.materialviewpager.MaterialViewPager;


public class MainActivity extends ActionBarActivity {

    MaterialViewPager materialViewPager;
    View headerLogo;
    ImageView headerLogoContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 4 tabs
        final int tabCount = 4;

        headerLogo = findViewById(R.id.headerLogo);
        headerLogoContent = (ImageView)findViewById(R.id.headerLogoContent);

        // The MaterialViewPager
        this.materialViewPager = (MaterialViewPager)findViewById(R.id.materialViewPager);
        // Fill the ViewPager
        this.materialViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int i) {
                return RecyclerViewFragment.newInstance();
            }

            @Override
            public int getCount() {
                return tabCount;
            }

            // The title to display for each page
            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return getResources().getString(R.string.divertissement);
                    case 1:
                        return getResources().getString(R.string.sports);
                    case 2:
                        return getResources().getString(R.string.technologie);
                    case 3:
                        return getResources().getString(R.string.international);
                    default:
                        return "Page" + position;
                }
            }

            int oldItemPosition = -1;
            @Override
            public void setPrimaryItem(ViewGroup container, int position, Object object) {
                super.setPrimaryItem(container, position, object);
                // Only if the page is different
                if (oldItemPosition != position) {
                    oldItemPosition = position;

                    // Set the new color and new images
                    String imageUrl = null;
                    int color = Color.BLACK;
                    Drawable newDrawable = null;

                    switch (position) {
                        case 0:
                            imageUrl = "http://www.skyscanner.fr/sites/default/files/image_import/fr/micro.jpg";
                            color = getResources().getColor(R.color.purple);
                            newDrawable = getResources().getDrawable(R.drawable.ticket);
                            break;
                        case 1:
                            imageUrl = "http://www.larousse.fr/encyclopedie/data/images/1311904-Balle_de_tennis_et_filet.jpg";
                            color = getResources().getColor(R.color.orange);
                            newDrawable = getResources().getDrawable(R.drawable.tennis);
                            break;
                        case 2:
                            imageUrl = "http://soocurious.com/fr/wp-content/uploads/2014/03/8-facettes-de-notre-cerveau-qui-ont-evolue-avec-la-technologie8.jpg";
                            color = getResources().getColor(R.color.cyan);
                            newDrawable = getResources().getDrawable(R.drawable.light);
                            break;
                        case 3:
                            imageUrl = "http://graduate.carleton.ca/wp-content/uploads/prog-banner-masters-international-affairs-juris-doctor.jpg";
                            color = getResources().getColor(R.color.green);
                            newDrawable = getResources().getDrawable(R.drawable.earth);
                            break;
                    }
                    // Then change the images / colors
                    int fadeDuration = 400 ;
                    materialViewPager.setColor(color, fadeDuration);
                    materialViewPager.setImageUrl(imageUrl, fadeDuration);
                    toggleLogo(newDrawable, color, fadeDuration);
                }
            }
        });

        // Allows viewPager keep 4 pages in memory (not to use more than 4 pages!)
        this.materialViewPager.getViewPager().setOffscreenPageLimit(tabCount);
        // Connects the tabs to viewpager
        this.materialViewPager.getPagerTitleStrip().setViewPager(this.materialViewPager.getViewPager());
    }

    // change our logo contained in the header, with an animated opening / closing
    private void toggleLogo(final Drawable newLogo, final int newColor, int duration) {
        // Animation disappearance
        final AnimatorSet animatorSetDisappear = new AnimatorSet();
        animatorSetDisappear.setDuration(duration);
        animatorSetDisappear.playTogether(
                ObjectAnimator.ofFloat(headerLogo, "scaleX", 0),
                ObjectAnimator.ofFloat(headerLogo, "scaleY", 0)
        );

        // Animation appearance
        final  AnimatorSet animatorSetAppear = new  AnimatorSet ();
        animatorSetAppear.setDuration (duration);
        animatorSetAppear.playTogether (
                ObjectAnimator.ofFloat (headerLogo, "scaleX" , 1 ),
                ObjectAnimator.ofFloat (headerLogo, "scaleY" , 1 )
        );

        // After the disappearance
        animatorSetDisappear.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                // Changes the color of the circle
                ((GradientDrawable) headerLogo.getBackground()).setColor(newColor);
                // Modify the image in the circle
                headerLogoContent.setImageDrawable(newLogo);

                // Starts the animation appearance
                animatorSetAppear.start();
            }
        });

        // Starts the animation of disappearance
        animatorSetDisappear.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
