package com.vvayv.cdaily.basicchinese;

/**
 * Created by qingdi on 8/5/14.
 */

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.vvayv.cdaily.basicchinese.data.PhraseData;

import java.io.IOException;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {

    private ArrayAdapter   mCategoryAdapter;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);


        mCategoryAdapter = new ArrayAdapter(getActivity(), R.layout.list_item_category,
                                R.id.list_item_category_textview, PhraseData.Categories.data);

        final ListView listView = (ListView) rootView.findViewById(R.id.listview_category);
        listView.setAdapter(mCategoryAdapter);

        ColorDrawable lightGrey = new ColorDrawable(Color.TRANSPARENT);
        //listView.setDivider(this.getResources().getDrawable(Color.TRANSPARENT));
        listView.setDivider(lightGrey);
        listView.setDividerHeight(60);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View v, int pos, long l) {
                Intent intent = new Intent(getActivity(), PhraseActivity.class)
                        .putExtra(PhraseActivity.CATEGORY_KEY, PhraseData.Categories.data.get(pos));
                startActivity(intent);
            }
        });
        return rootView;
    }

}