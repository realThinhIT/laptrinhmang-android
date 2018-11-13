package livestream.adapter;

import android.support.annotation.IntDef;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.lang.annotation.Retention;

import livestream.BlankFragment;
import livestream.ProfileFragment;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by nttungg on 4/20/18.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    @Retention(SOURCE)
    @IntDef({FRAGMENT_HOME, FRAGMENT_DIRECTION, FRAGMENT_STORAGE, FRAGMENT_PROFILE})
    public @interface FrangmentMode {
    }

    public static final int FRAGMENT_HOME = 0;
    public static final int FRAGMENT_DIRECTION = 1;
    public static final int FRAGMENT_STORAGE = 2;
    public static final int FRAGMENT_PROFILE = 3;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case FRAGMENT_HOME:
                return new BlankFragment();
            case FRAGMENT_DIRECTION:
                return new BlankFragment();
            case FRAGMENT_STORAGE:
                return new BlankFragment();
            case FRAGMENT_PROFILE:
                return new ProfileFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
