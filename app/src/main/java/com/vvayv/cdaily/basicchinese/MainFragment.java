package com.vvayv.cdaily.basicchinese;

/**
 * Created by qingdi on 8/5/14.
 */

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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.vvayv.cdaily.basicchinese.data.PhraseData;

import java.io.IOException;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {

    private PhraseAdapter   mPhaseAdapter;

    private ActionListener  mActionListener = null;



    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mPhaseAdapter = new PhraseAdapter(getActivity(), PhraseData.RAW_DATA, R.layout.list_item_phrase,
                            PhraseData.VIEW_ENTRIES, PhraseData.VIEWS);
        mActionListener = new ActionListener(getActivity(), PhraseData.RAW_DATA);
        mPhaseAdapter.setActionListener(mActionListener);

        final ListView listView = (ListView) rootView.findViewById(R.id.listview_phrase);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                mPhaseAdapter.setCurrPos(position);
                mPhaseAdapter.notifyDataSetChanged();
                mActionListener.onPhraseItemClick(adapterView, view, position, l);
            }
        });

        listView.setAdapter(mPhaseAdapter);

        return rootView;
    }

    @Override
    public void onPause() {
        if (mActionListener != null) {
            mActionListener.resetMediaResources();
        }
        super.onPause();
    }


}