package cn.six.espresso.swipe;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.six.aut.R;

class SimplePagerAdapter extends PagerAdapter {

    private static final int[] COLORS = {
            Color.BLUE,
            Color.RED,
            Color.YELLOW,
    };

    private static final int NUM_PAGES = COLORS.length;

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getItemPosition(Object object) {
        return ((ViewGroup) ((View) object).getParent()).indexOfChild((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View view = inflater.inflate(R.layout.item_pager, null);
        ((TextView) view.findViewById(R.id.pager_content)).setText("Position #" + position);
        view.setBackgroundColor(COLORS[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
