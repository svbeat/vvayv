package com.vvayv.cdaily.basicchinese.data;

import android.content.Context;

import com.vvayv.cdaily.basicchinese.R;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qingdi on 8/5/14.
 */
public class PhraseData {

    private static String RECORD_FILE_SUFFIX = ".3gp";

    public static final class PhraseEntry {
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TONE_ID = "tone_id";
        public static final String COLUMN_ENGLISH = "english";
        public static final String COLUMN_PINYIN = "pinyin";
        public static final String COLUMN_CHINESE = "chinese";
        public static final String COLUMN_RES_AUDIO_ID = "res_audio_id";
    }

    public static final String MAP_ENTRIES[] = { PhraseEntry.COLUMN_ID,
                                                 PhraseEntry.COLUMN_TONE_ID,
                                                 PhraseEntry.COLUMN_ENGLISH,
                                                 PhraseEntry.COLUMN_PINYIN,
                                                 PhraseEntry.COLUMN_CHINESE,
                                                 PhraseEntry.COLUMN_RES_AUDIO_ID};

    public static final String VIEW_ENTRIES[] = { PhraseEntry.COLUMN_ENGLISH,
                                                  PhraseEntry.COLUMN_PINYIN,
                                                  PhraseEntry.COLUMN_CHINESE};

    public static final int VIEWS[] = { R.id.list_item_english_textview, R.id.list_item_pinyin_textview, R.id.list_item_chinese_textview };

    private static final String[][] rawArray = {
            {"1", "1", "one ... two ... three", "yī ... èr ... sān", "一 ... 二 ... 三", Integer.toString(R.raw.audio1)},
            {"2", "4", "four ... five ... six", "sì ... wŭ ... liù", "四 ... 五 ... 六", Integer.toString(R.raw.audio4)},
            {"3", "1", "seven ... eight ... nine", "qī ... bā ... jiŭ", "七 ... 八 ... 九", Integer.toString(R.raw.audio7)},
            {"4", "2", "ten ... eleven ... twelve", "shí ... shí yī ... shí èr", "十 ... 十一 ... 十二", Integer.toString(R.raw.audio10)},
            {"5", "4", "twenty ... thirty ... forty", "èr shí ... sān shí ... sì shí", "二十 ... 三十 ... 四十", Integer.toString(R.raw.audio20)},
            {"6", "1", "three hundred", "sān băi", "三百", Integer.toString(R.raw.audio300)},
            {"7", "3", "five thousand", "wŭ qīan", "五千", Integer.toString(R.raw.audio5000)},
            {"8", "4", "sixty thousand", "liù wàn", "六万", Integer.toString(R.raw.audio60000)}
    };

    public static List<Map<String, String>> RAW_DATA = new ArrayList<Map<String, String>>();

    static {
        for (int i = 0; i < rawArray.length; i++) {
            Map<String, String> dataMap = new HashMap<String, String>();
            for (int j = 0; j < MAP_ENTRIES.length; j++) {
                dataMap.put(MAP_ENTRIES[j], rawArray[i][j]);
            }
            RAW_DATA.add(dataMap);
        }
    }

    public static String getRecordFileName(Context context, String phraseId) {
        return context.getFilesDir()+"/"+phraseId+RECORD_FILE_SUFFIX;
    }

    public static boolean checkRecordFileExists(Context context, String phraseId) {
        File file = new File(getRecordFileName(context, phraseId));
        return file.exists();
    }

}
