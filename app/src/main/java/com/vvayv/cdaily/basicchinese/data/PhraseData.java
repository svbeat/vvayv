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


    public static final class Categories {
        public static final String GREETING = "Greetings";
        public static final String ESSENTIAL = "Essentials";
        public static final String NUMBER = "Numbers";
        public static final String ASKHELP = "Asking for Help";
        public static final String HEALTH = "Feeling sick";

        public static List<String> data = new ArrayList<String>();
        static {
            data.add(GREETING);
            data.add(ESSENTIAL);
            data.add(NUMBER);
            data.add(ASKHELP);
            data.add(HEALTH);
        }
    }

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

    private static final String[][] rawArray_greeting = {
            {"greet1", "3", "Hello / Hi", "nĭ hăo", "你好", Integer.toString(R.raw.nihao)},
            {"greet2", "3", "Nice to meet you.", "hěn gāo xìng jiàn dào nĭ", "很高兴见到你.", Integer.toString(R.raw.gaojian)},
            {"greet3", "3", "I am ...", "wŏ shì ...", "我是 ...", Integer.toString(R.raw.woshi)},
            {"greet4", "3", "I am from United States.", "wŏ cóng měi gó lái.", "我从美国来.", Integer.toString(R.raw.congmei)},
            {"greet5", "3", "Are you ...?", "nĭ shì ...?", "你是 ...?", Integer.toString(R.raw.nishi)},
            {"greet6", "2", "Would you like a coffee?", "lái bēi kā fēi ma?", "来杯咖啡吗?", Integer.toString(R.raw.kafei)},
            {"greet7", "4", "Goodbye.", "zài jiàn.", "再见.", Integer.toString(R.raw.zaijian)},
            {"greet8", "2", "See you later.", "huí tóu jiàn", "回头见.", Integer.toString(R.raw.huitoujian)}
    };

    private static final String[][] rawArray_essential = {
            {"essential1", "4", "yes ... no", "shì ... bú shì", "是 ... 不是", Integer.toString(R.raw.shibushi)},
            {"essential2", "3", "good ... bad", "hăo ... huài", "好 ... 坏", Integer.toString(R.raw.haohuai)},
            {"essential3", "4", "up .. down ... left ... right", "shàng ... xià ... zuŏ ... yòu", "上 ... 下 ... 左 ... 右", Integer.toString(R.raw.shangxia)},
            {"essential4", "3", "you ... I ... she/he", "nĭ ... wŏ ... tā", "你 ... 我 ... 他/她", Integer.toString(R.raw.niwota)},
            {"essential5", "3", "morning ... afternoon ... evening", "zăo shàng ... zhōng wŭ ... wăn shàng", "早上 ... 中午 ... 晚上", Integer.toString(R.raw.zhaozhong)},
            {"essential6", "2", "food ... hungry", "shí wù ... è", "食物 ... 饿", Integer.toString(R.raw.shiwu)},
            {"essential7", "3", "water ... thirsty", "shuĭ ... kě", "水 ... 渴", Integer.toString(R.raw.shuike)}
    };

    private static final String[][] rawArray_number = {
            {"number1", "1", "one ... two ... three", "yī ... èr ... sān", "一 ... 二 ... 三", Integer.toString(R.raw.audio1)},
            {"number2", "4", "four ... five ... six", "sì ... wŭ ... liù", "四 ... 五 ... 六", Integer.toString(R.raw.audio4)},
            {"number3", "1", "seven ... eight ... nine", "qī ... bā ... jiŭ", "七 ... 八 ... 九", Integer.toString(R.raw.audio7)},
            {"number4", "2", "ten ... eleven ... twelve", "shí ... shí yī ... shí èr", "十 ... 十一 ... 十二", Integer.toString(R.raw.audio10)},
            {"number5", "4", "twenty ... thirty ... forty", "èr shí ... sān shí ... sì shí", "二十 ... 三十 ... 四十", Integer.toString(R.raw.audio20)},
            {"number6", "1", "three hundred", "sān băi", "三百", Integer.toString(R.raw.audio300)},
            {"number7", "3", "five thousand", "wŭ qīan", "五千", Integer.toString(R.raw.audio5000)},
            {"number8", "4", "sixty thousand", "liù wàn", "六万", Integer.toString(R.raw.audio60000)}
    };

    private static final String[][] rawArray_askhelp = {
            {"askhelp1", "2", "Could you do me a favor?", "néng qĭng nĭ bāng gè máng ma?", "能请你帮个忙吗?", Integer.toString(R.raw.bangmang)},
            {"askhelp2", "3", "Could you tell me where the bank is?", "qĭng wèn yín háng zài nă lĭ?", "请问银行在哪里?", Integer.toString(R.raw.yinhang)},
            {"askhelp3", "3", "How can I get to the train station?", "qĭng wèn huŏ chē zhàn zěn mo zŏu?", "请问火车站怎么走?", Integer.toString(R.raw.hoche)},
            {"askhelp4", "3", "I would like a ticket to Beijing.", "wŏ yào zhāng qù běi jīng de piào.", "我要张去北京的票.", Integer.toString(R.raw.beijing)},
            {"askhelp5", "4", "Is this seat taken?", "zhè zuò wèi yŏu rén ma?", "这座位有人吗?", Integer.toString(R.raw.zhuowei)},
            {"askhelp6", "3", "When is the next bus? ", "qĭng wèn xià yí tàng chē shén mo shí hòu dào?", "请问下一趟车什么时候到?", Integer.toString(R.raw.xiatangche)},
            {"askhelp7", "4", "Thank you.", "xiè xiè nĭ.", "谢谢你.", Integer.toString(R.raw.xiexie)}
    };

    private static final String[][] rawArray_health = {
            {"health1", "3", "I’m sick.", "wŏ bìng le.", "我病了.", Integer.toString(R.raw.wobing)},
            {"health2", "3", "I have a fever.", "wŏ fā shāo le.", "我发烧了.", Integer.toString(R.raw.fashao)},
            {"health3", "1", "He’s got a bad headache.", "tā tóu téng de lì hài.", "他头疼的厉害.", Integer.toString(R.raw.touteng)},
            {"health4", "3", "I’ve got a pain in my back.", "wŏ bèi téng.", "我背疼.", Integer.toString(R.raw.beiteng)},
            {"health5", "4", "It hurts right here.", "jiù zhè lĭ téng.", "就这里疼.", Integer.toString(R.raw.zheliteng)},
            {"health6", "4", "Call an ambulance!", "kuài jiào jiù hù chē!", "快叫救护车!", Integer.toString(R.raw.jiuhuche)},
            {"health7", "4", "Go to the hospital.", "qù yī yuàn.", "去医院.", Integer.toString(R.raw.yiyuan)}
    };


    public static List<Map<String,String>> getPhraseDataByCategory(String categoryKey) {
        List<Map<String, String>> phraseData = new ArrayList<Map<String, String>>();
        String[][] rawArray = null;

        if(Categories.GREETING.equals(categoryKey)) {
            rawArray = rawArray_greeting;
        } else if (Categories.ESSENTIAL.equals(categoryKey)) {
            rawArray = rawArray_essential;
        } else if (Categories.NUMBER.equals(categoryKey)) {
            rawArray = rawArray_number;
        } else if (Categories.ASKHELP.equals(categoryKey)) {
            rawArray = rawArray_askhelp;
        } else if (Categories.HEALTH.equals(categoryKey)) {
            rawArray = rawArray_health;
        } else {
            return phraseData;
        }

        for (int i = 0; i < rawArray.length; i++) {
            Map<String, String> dataMap = new HashMap<String, String>();
            for (int j = 0; j < MAP_ENTRIES.length; j++) {
                dataMap.put(MAP_ENTRIES[j], rawArray[i][j]);
            }
            phraseData.add(dataMap);
        }

        return phraseData;
    }





    public static String getRecordFileName(Context context, String phraseId) {
        return context.getFilesDir()+"/"+phraseId+RECORD_FILE_SUFFIX;
    }

    public static boolean checkRecordFileExists(Context context, String phraseId) {
        File file = new File(getRecordFileName(context, phraseId));
        return file.exists();
    }

}
