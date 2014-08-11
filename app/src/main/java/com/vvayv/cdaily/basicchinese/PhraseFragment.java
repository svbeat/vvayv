package com.vvayv.cdaily.basicchinese;

/**
 * Created by qingdi on 8/5/14.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.vvayv.cdaily.basicchinese.data.PhraseData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A placeholder fragment containing a simple view.
 */
public class PhraseFragment extends Fragment {

    private PhraseAdapter   mPhaseAdapter;

    private ActionListener  mActionListener = null;



    public PhraseFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_phrase, container, false);

        Intent intent = getActivity().getIntent();
        String categoryKey = null;
        if (intent != null && intent.hasExtra(PhraseActivity.CATEGORY_KEY)) {
            categoryKey = intent.getStringExtra(PhraseActivity.CATEGORY_KEY);
        }

        getActivity().setTitle(categoryKey);
        List<Map<String, String>> phraseData = PhraseData.getPhraseDataByCategory(categoryKey);

        mPhaseAdapter = new PhraseAdapter(getActivity(), phraseData, R.layout.list_item_phrase,
                            PhraseData.VIEW_ENTRIES, PhraseData.VIEWS);

        mActionListener = new ActionListener(getActivity(), phraseData);
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