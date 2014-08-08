package com.vvayv.cdaily.basicchinese;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.vvayv.cdaily.basicchinese.data.PhraseData;
import com.vvayv.cdaily.basicchinese.data.PhraseData.PhraseEntry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qingdi on 8/5/14.
 */
public class PhraseAdapter extends SimpleAdapter{

    private final Context context;
    private final List<? extends Map<String, ?>> data;
    private final String[] from;
    private final int[] to;

    private static final int VIEW_TYPE_BASIC = 0;
    private static final int VIEW_TYPE_EXPANDED = 1;

    private int mCurrPos  = -1;
    private ActionListener mActionListener = null;

    public PhraseAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.context = context;
        this.data = data;
        this.from = from;
        this.to = to;
    }

    public int getCurrPos() {
        return mCurrPos;
    }

    public void setCurrPos(int mCurrPos) {
        this.mCurrPos = mCurrPos;
    }

    public ActionListener getActionListener() {
        return mActionListener;
    }

    public void setActionListener(ActionListener mActionListener) {
        this.mActionListener = mActionListener;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return (position==mCurrPos)?VIEW_TYPE_EXPANDED:VIEW_TYPE_BASIC;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        int viewType = getItemViewType(position);
        if (convertView == null) {

            int layoutId = (viewType == VIEW_TYPE_BASIC)?R.layout.list_item_phrase:R.layout.list_item_phrase_expanded;

            convertView =  LayoutInflater.from(context).inflate(layoutId, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        for (int i = 0; i < to.length; i ++) {
            if (viewHolder.getTextView(i) != null) {
                viewHolder.getTextView(i).setText((String)data.get(position).get(from[i]));
            }
        }

        viewHolder.toneImageView.setImageResource(getToneImageSrc(Integer.parseInt((String) data.get(position).get(PhraseEntry.COLUMN_TONE_ID))));

        String phaseId = (String)data.get(position).get(PhraseEntry.COLUMN_ID);
        boolean recordingExists = PhraseData.checkRecordFileExists(context, phaseId);

        if (viewHolder.recordExistsView != null) {
            if (recordingExists) {
                viewHolder.recordExistsView.setText("*");
            } else {
                viewHolder.recordExistsView.setText(" ");
            }
        }

        if (viewType == VIEW_TYPE_EXPANDED) {
            viewHolder.recorderView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mActionListener.onClickRecording(view);
                }
            });

            viewHolder.playerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mActionListener.onClickPlaying(view);
                }
            });


            viewHolder.playerView.setImageResource(recordingExists? R.drawable.play:R.drawable.play_disabled);
            mActionListener.setRecorderImageView(viewHolder.recorderView);
            mActionListener.setPlayerImageView(viewHolder.playerView);
        }
        return convertView;
    }

    private int getToneImageSrc(int toneId) {
        switch(toneId) {
            case 1 :
                return R.drawable.tone1;
            case 2 :
                return R.drawable.tone2;
            case 3 :
                return R.drawable.tone3;
            case 4:
                return R.drawable.tone4;
            default:
                return 0;
        }
    }


    /**
     * Cache of the children views for a forecast list item.
     */
    public class ViewHolder {
        private Map<Integer, TextView> textViews = new HashMap<Integer, TextView>();
        public ImageView toneImageView = null;
        public ImageView recorderView = null;
        public ImageView playerView = null;
        public TextView recordExistsView = null;
        public ViewHolder(View view) {
            for (int i = 0; i < to.length; i++) {
                textViews.put(i, (TextView) view.findViewById(to[i]));
            }

            toneImageView = (ImageView)view.findViewById(R.id.list_item_tone_imageview);
            playerView = (ImageView)view.findViewById(R.id.list_item_play_imageview);
            recorderView = (ImageView)view.findViewById(R.id.list_item_record_imageview);
            recordExistsView = (TextView)view.findViewById(R.id.list_item_recording_exists_textview);
        }

        public TextView getTextView(int index) {
            return textViews.get(index);
        }
    }

}
