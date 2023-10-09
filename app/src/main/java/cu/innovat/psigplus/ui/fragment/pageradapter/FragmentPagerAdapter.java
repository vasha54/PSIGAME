package cu.innovat.psigplus.ui.fragment.pageradapter;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;
import java.util.*;
/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 2/10/23
 */
public class FragmentPagerAdapter extends androidx.fragment.app.FragmentPagerAdapter {
    private  List<Fragment> mFragmentList ;
    private  List<String> mFragmentTitleList ;

    public FragmentPagerAdapter(FragmentManager manager) {
        super(manager);
        mFragmentList = new ArrayList<>();
        mFragmentTitleList = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}
