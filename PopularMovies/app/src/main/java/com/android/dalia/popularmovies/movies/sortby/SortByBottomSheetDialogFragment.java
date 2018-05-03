package com.android.dalia.popularmovies.movies.sortby;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.dalia.popularmovies.R;
import com.android.dalia.popularmovies.utils.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dalia on 11/9/2017.
 */

public class SortByBottomSheetDialogFragment extends BottomSheetDialogFragment {
    @BindView(R.id.list_view_sort_options)
    ListView listViewSortOptions;

    SortByListener listener;

    private BottomSheetBehavior.BottomSheetCallback bottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if(newState == BottomSheetBehavior.STATE_HIDDEN){
                dismiss();
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {

        }
    };

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        View view = View.inflate(getContext(), R.layout.dialog_modal_sort_by, null);

        ButterKnife.bind(this, view);

        dialog.setContentView(view);

        setupListViewInDialog();

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams)
                ((View) view.getParent()).getLayoutParams();

        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior != null && behavior instanceof BottomSheetBehavior){
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(bottomSheetBehaviorCallback);
        }
    }

    private void setupListViewInDialog(){
        Bundle bundle = getArguments();
        List<String> sortOptions = bundle.getStringArrayList(Constants.SORT_BY_KEY);

        Log.d("SORT", "sort: " + sortOptions.get(0) + ", " + sortOptions.get(1));

        listViewSortOptions.setAdapter(new ArrayAdapter<String>(
                                               getContext(), android.R.layout.simple_list_item_1, sortOptions){
                                           @NonNull
                                           @Override
                                           public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                                               View view = super.getView(position, convertView, parent);
                                               TextView text = view.findViewById(android.R.id.text1);
                                               text.setTextColor(Color.BLACK);
                                               return view;
                                           }
                                       }
        );

        listViewSortOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String value = (String) adapterView.getItemAtPosition(i);
                listener.sortByItemClicked(value);
                dismiss();
            }
        });
    }

    public void setListener(SortByListener listener) {
        this.listener = listener;
    }
}
