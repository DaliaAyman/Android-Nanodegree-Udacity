package com.example.xyzreader.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v13.app.ActivityCompat;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.xyzreader.R;
import com.example.xyzreader.data.ArticleLoader;
import com.example.xyzreader.data.ItemsContract;

import java.util.List;
import java.util.Map;

/**
 * An activity representing a single Article detail screen, letting you swipe between articles.
 */
public class ArticleDetailActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {

    private Cursor mCursor;
    private long mStartId;

    private long mSelectedItemId;

    private ViewPager mPager;
    private MyPagerAdapter mPagerAdapter;

    public static final String EXTRA_POSITION = "imagePosition";
    public static int selectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityCompat.postponeEnterTransition(this);
        ActivityCompat.setEnterSharedElementCallback(this, enterTransitionCallback);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        getLoaderManager().initLoader(0, null, this);

        mPagerAdapter = new MyPagerAdapter(getFragmentManager());
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mPagerAdapter);

        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
//                mUpButton.animate()
//                        .alpha((state == ViewPager.SCROLL_STATE_IDLE) ? 1f : 0f)
//                        .setDuration(300);
            }

            @Override
            public void onPageSelected(int position) {
                if (mCursor != null) {
                    mCursor.moveToPosition(position);
                }
                mSelectedItemId = mCursor.getLong(ArticleLoader.Query._ID);
                selectedIndex = position;
            }
        });

        mPager.getViewTreeObserver().addOnGlobalLayoutListener(pagerLayoutListener);

        if (savedInstanceState == null) {
            if (getIntent() != null && getIntent().getData() != null) {
                mStartId = ItemsContract.Items.getItemId(getIntent().getData());
                mSelectedItemId = mStartId;
            }
        }
    }

    private ViewTreeObserver.OnGlobalLayoutListener pagerLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            mPager.getViewTreeObserver().removeOnGlobalLayoutListener(pagerLayoutListener);
            ActivityCompat.startPostponedEnterTransition(ArticleDetailActivity.this);
        }
    };

    SharedElementCallback enterTransitionCallback = new SharedElementCallback() {
        @Override
        public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
            View view = null;

            if (mPager.getChildCount() > 0) {
                view = mPagerAdapter.getCurrentView(mPager);
                view = view.findViewById(R.id.thumbnail);
            }

            if (view != null) {
                sharedElements.put(names.get(0), view);
            }
        }
    };

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return ArticleLoader.newAllArticlesInstance(this);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        mCursor = cursor;
        mPagerAdapter.notifyDataSetChanged();
//
//        // Select the start ID
//        if (mStartId > 0) {
//            mCursor.moveToFirst();
//            // TODO: optimize
//            while (!mCursor.isAfterLast()) {
//                if (mCursor.getLong(ArticleLoader.Query._ID) == mStartId) {
//                    final int position = mCursor.getPosition();
//                    mPager.setCurrentItem(position, false);
//                    break;
//                }
//                mCursor.moveToNext();
//            }
//            mStartId = 0;
//        }


        mPager.setCurrentItem(getIntent().getExtras().getInt(EXTRA_POSITION), false);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        mCursor = null;
        mPagerAdapter.notifyDataSetChanged();
    }

    private class MyPagerAdapter extends FragmentStatePagerAdapter {

        int lastPosition = -1;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);

            if(lastPosition != position){
                lastPosition = position;
                ArticleDetailFragment fragment = (ArticleDetailFragment) object;
                fragment.setPosition(position);
            }

        }

        @Override
        public Fragment getItem(int position) {
            mCursor.moveToPosition(position);
            Log.d("TAG__", "fragment get item position: " + position);
            ArticleDetailFragment fragment = ArticleDetailFragment
                    .newInstance(mCursor.getLong(ArticleLoader.Query._ID));

            //fragment
            return fragment;
        }

        @Override
        public int getCount() {
            return (mCursor != null) ? mCursor.getCount() : 0;
        }


        public View getCurrentView(ViewPager pager) {
            for (int i=0; i<pager.getChildCount(); i++) {
                Log.d("TAG__current_item",String.valueOf( pager.getCurrentItem()));
                Log.d("TAG__child_at",String.valueOf( pager.getChildAt(i)));
                Log.d("TAG__tag",String.valueOf(  pager.getChildAt(i).getTag(R.id.index)));

                if ( (int)pager.getChildAt(i).getTag(R.id.index) == pager.getCurrentItem()) {
                    return pager.getChildAt(i);
                }
            }

            return null;
        }
    }
}
