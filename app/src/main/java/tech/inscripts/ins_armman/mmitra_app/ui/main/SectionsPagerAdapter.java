package tech.inscripts.ins_armman.mmitra_app.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import tech.inscripts.ins_armman.mmitra_app.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return PlaceholderFragment.newInstance(position + 1);

            case 1:
                profile profile_object = new profile();
                return profile_object;

            case 2:
                add add_object = new add();
                return add_object;

            default:
                return null;
        }

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }


    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }
}