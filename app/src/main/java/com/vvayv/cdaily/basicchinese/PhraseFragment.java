package com.vvayv.cdaily.basicchinese;

/**
 * Created by qingdi on 8/5/14.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.vvayv.cdaily.basicchinese.data.PhraseData;

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