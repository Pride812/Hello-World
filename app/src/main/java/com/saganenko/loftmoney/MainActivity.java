package com.saganenko.loftmoney;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        TabLayout tabLayout = findViewById(R.id.tabs);


        ViewPager2 viewPager = findViewById(R.id.viewpager);

        viewPager.setAdapter(new ViewPagerFragmentAdapter(this));


        final String[] fragmentsTitles = new String[] { getString(R.string.incomes), getString(R.string.expenses) };


        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(fragmentsTitles[position]);
            }
        }).attach();


    }


    private class ViewPagerFragmentAdapter extends FragmentStateAdapter {


        public ViewPagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }


        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return BudgetFragment.newInstance(R.color.income_color, getString(R.string.incomes));
                case 1:
                    return BudgetFragment.newInstance(R.color.expense_color, getString(R.string.expenses));
                case 2:

                default:
                    return null;
            }
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }

}