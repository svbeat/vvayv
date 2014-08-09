package com.vvayv.cdaily.basicchinese;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.vvayv.cdaily.basicchinese.data.PhraseData;
import com.vvayv.cdaily.basicchinese.data.PhraseData.PhraseEntry;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by qingdi on 8/6/14.
 */
public class ActionListener {

    private static final int MAX_RECORDING_DURATION = 15000; // 15secs
    private Context                     mContext;
    private List<Map<String, String>>   mRawData;

    private MediaPlayer     mPlayer;
    private MediaRecorder   mRecorder;
    private Boolean         isRecording = false;
    private Boolean         isPlaying = false;

    private int             mCurrPos;
    private ImageView       mRecorderImageView;
    private ImageView       mPlayerImageView;



    public ActionListener(Context context, List<Map<String, String>> rawData) {
        this.mContext = context;
        this.mRawData = rawData;
    }

    public void setRecorderImageView(ImageView mRecorderImageView) {
        this.mRecorderImageView = mRecorderImageView;
    }

    public void setPlayerImageView(ImageView mPlayerImageView) {
        this.mPlayerImageView = mPlayerImageView;
    }


    private String getRecordFileName() {
        return PhraseData.getRecordFileName(mContext, (String) mRawData.get(mCurrPos).get(PhraseEntry.COLUMN_ID));
    }


    public void onPhraseItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        releaseRecorder();
        releasePlayer();
        mPlayer = MediaPlayer.create(mContext, Integer.parseInt(mRawData.get(position).get(PhraseEntry.COLUMN_RES_AUDIO_ID)));
        mCurrPos = position;
        startPlay();
    }

    public void onClickRecording(View view) {

        if (!isRecording) {
            if (mRecorder == null) {
                mRecorder = new MediaRecorder();
                mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            }
            mRecorder.setOutputFile(getRecordFileName());
            mRecorder.setMaxDuration(MAX_RECORDING_DURATION);
            try {
                mRecorder.prepare();
            } catch (IOException e) {
                Log.e("playImageView", "prepare() failed");
            }
            mRecorder.setOnInfoListener(new MediaRecorder.OnInfoListener() {
                @Override
                public void onInfo(MediaRecorder mr, int what, int extra) {
                    if (mr == mRecorder && what == MediaRecorder.MEDIA_RECORDER_INFO_MAX_DURATION_REACHED) {
                        releaseRecorder();
                    }
                }
            });
            mRecorder.start();
            isRecording = true;
            toggleRecorderImage();
        } else {
            releaseRecorder();
        }

    }

    public void onClickPlaying(View view) {
        if (!isPlaying) {
            if (!checkRecordFileExists()){
                return;
            }
            releaseRecorder();
            releasePlayer();
            mPlayer = new MediaPlayer();
            try {
                mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mPlayer.setDataSource(getRecordFileName());
                mPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            startPlay();
            isPlaying = true;
            togglePlayerImage();
        } else {
            releasePlayer();
        }

    }


    private void startPlay() {
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                isPlaying = false;
                releasePlayer();
            }

        });
        mPlayer.setVolume(1.0f, 1.0f);
        mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if (mp == mPlayer) {
                    mPlayer.start();
                }
            }
        });
    }


    public void resetMediaResources() {
        releasePlayer();
        releaseRecorder();
    }

    private void releasePlayer() {
        if (mPlayer != null) {
            if (mPlayer.isPlaying()) {
                mPlayer.stop();
            }
            mPlayer.release();
            mPlayer = null;
        }
        isPlaying = false;
        togglePlayerImage();
    }

    private void releaseRecorder() {
        if (mRecorder != null) {
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
        }
        isRecording = false;
        toggleRecorderImage();
        togglePlayerImage();
    }

    public void toggleRecorderImage() {
        if (mRecorderImageView == null) {
            return;
        }

        if(isRecording) {
            mRecorderImageView.setImageResource(R.drawable.stop);
        } else {
            mRecorderImageView.setImageResource(R.drawable.record);
        }
    }

    public void togglePlayerImage() {
        if (mPlayerImageView == null) {
            return;
        }

        if (isPlaying) {
            mPlayerImageView.setImageResource(R.drawable.stop);
        } else {
            mPlayerImageView.setImageResource(checkRecordFileExists()? R.drawable.play:R.drawable.play_disabled);
        }
    }

    private boolean checkRecordFileExists() {
        String phaseId = mRawData.get(mCurrPos).get(PhraseEntry.COLUMN_ID);
        return PhraseData.checkRecordFileExists(mContext, phaseId);
    }
}
