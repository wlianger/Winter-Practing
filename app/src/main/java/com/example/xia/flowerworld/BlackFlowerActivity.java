package com.example.xia.flowerworld;

import android.os.Bundle;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class BlackFlowerActivity extends Activity {

    private ListView lv;
    private ArrayList<String> list_string;
    private MyPagerAdater myPagerAdater;
    private String json = "{" +
            " \"_id\": \"悲之花\",      " +
            " \"planners\": [                               " +
            "    {                                           " +
            "        \"name\": \"荼蘼\",               " +
            "        \"speak\": \"        在春季末夏季初开花，凋谢后即表示花季结束，开到荼靡便没有了退路，也不能继续美丽了，那是多么的令人绝望与颓废荼蘼花开代表女子的青春已成过去，表示感情的终结，爱到荼靡，意蕴生命中最灿烂，最繁华或最刻骨铭心的爱即将失去 \"           " +
            "    },                                           " +
            "    {                                           " +
            "        \"name\": \"彼岸花：\",               " +
            "        \"speak\": \"        彼岸花又名曼珠沙华，被恶魔遣回自愿投入地狱的花朵，相传此花只开在冥界三途河边的黄泉路上，给离开人界的魂们一个指引与安慰，在长长的黄泉路上，花绚烂鲜红，犹如用血铺满的地毯，红得似火而被喻为“火照之路” 是冥界唯一的花，花香传说有魔力，能唤起死者生前的记忆，当灵魂渡过忘川，便忘却生前的种种，曾经的一切都留在了彼岸，死者就踏着这花的指引通向幽冥之狱 \"           " +
            "    },                                           " +
            "    {                                           " +
            "        \"name\": \"昙花：\",               " +
            "        \"speak\": \"        昙花又叫韦驮花。韦驮花很特别，总是在黎明时分朝露初凝的那一刻才绽放。传说昙花是一个花神，她每天的开花，四季都很灿烂，她爱上了一个每天为她锄草的小伙子，后来玉帝知道了这件事情，就大发雷霆，要拆散鸳鸯。玉帝把花神贬为一生只能开一瞬间的花，不让她再和情郎相见。还把那个小伙子送去灵柩山出家，赐名韦驮，让他忘记前尘，忘记花神。可是花神却忘不了那个年轻的小伙子，她知道每年暮春时分，韦驮尊者都会上山采春露，为佛祖煎茶。就选在那个时候开花！希望能见韦驮尊者一面，就一次，一次就够了！遗憾的是，春去春来，花开花谢，韦驮还是不认得她。\"            " +
            "    },                                           " +
            "    {                                           " +
            "        \"name\": \"月桂树：\",               " +
            "        \"speak\": \"        太阳神阿波罗被顽皮的小爱神丘比特用黄金利箭射中，心中顿时燃起热烈的爱恋，这时美丽的达芙妮仙女正巧来都到这里，又被调皮的丘比特用另一支铅制钝箭射中，达芙妮十分厌恶爱情，而阿波罗深深的爱上了达芙妮，当阿波罗向达芙妮倾诉衷情，手接触到她的身体时，达芙妮开始变为月桂树，阿波罗无可奈何，只好摘几片树叶编成桂冠戴在头上，自慰自怜，达芙妮深受感动，所以月桂树常年翠绿 \"            " +
            "    },                                           " +
            "    {                                           " +
            "        \"name\": \"向日葵：\",               " +
            "        \"speak\": \"        克丽泰是一位水泽仙女，一天，她在森林里遇见了正在狩猎的太阳神阿波罗，她深深地为这位俊美的神所迷，疯狂的爱上了他。可是，阿波罗连正眼也不瞧她一下就走了。克丽泰热切地盼望有一天阿波罗会对他说说话，但她却再也没有遇见过他。于是，她只能每天注视着天空，看着阿波罗驾着金碧辉煌的日车划过天空。她目不转睛地注视这阿波罗的行程，直到他下山。每天每天，她就这样呆坐着，头发散乱，众神怜悯她，把她变成了一朵金黄色的向日葵。她的脸儿变成了花盘，永远向着太阳。每日追随他，向他诉说她永远不变的恋情。阿波罗永远都不知道有个傻瓜曾给过他完完整整的爱。\"            " +
            "    },                                           " +
            "    {                                           " +
            "        \"name\": \"双生花：\",               " +
            "        \"speak\": \"        传说中黑暗里一种洁白美丽的花朵，味道潮湿芬芬，但是充满迷茫，在一枝梗子上互相爱着，但也互相争抢，斗争不止。用最深刻的伤害来表达最深刻的爱。直至死亡。甚至愿意杀死对方，因为任何一方死亡的时候，另一方也悄然腐烂。双生花，一株二艳，竟相绽放，最后一朵妖艳夺人，一朵枯败凋零。这是一种无奈，也是一种命运。或许它和它都不想，只是在那日日夜夜的缠绕间，不经意的一种结局，世间万物就是这么残酷\"            " +
            "    },                                           " +
            "    {                                           " +
            "        \"name\": \"蒲公英：\",               " +
            "        \"speak\": \"        蒲公英的茸毛像蚂蚁国的小不点儿的降落伞，在使劲吹的一阵人工暴雨里悬空飘舞一阵子，就四下里飞散开，不见了。请记得，忍耐的坚强和朴素的纯美。须知你们是从被践踏，被蹂躏里，勇敢的地生活下来的。今后再遭践踏，再遭蹂躏，还得勇敢的生活下去。却不要再尝那已经尝过的苦难吧\"            " +
            "    },                                           " +
            "    {                                           " +
            "        \"name\": \"薰衣草：\",               " +
            "        \"speak\": \"        相传很久以前，天使与一个叫薰衣的凡间女子相恋，为她留下了第一滴眼泪，翅膀为她而脱落。虽然天使每天都要忍着剧痛，但他们依然很快乐。可快乐很短暂，天使被抓回了天国，删除了那一段他与薰衣那段快乐的时光，被贬下凡间前他又流下一滴泪，泪化作一只蝴蝶，去陪伴着他最心爱的女孩。而还在薰衣傻傻的等着他回来，陪伴她的只有那只蝴蝶。日日夜夜的在天使离开的园地等着，最后，化作一株小草。每年会开出淡紫色的花，它们飞向各地。\"            " +
            "    },                                           " +
            "    {                                           " +
            "        \"name\": \"水仙：\",               " +
            "        \"speak\": \"        拿斯索斯（Narcissus）是希腊神话里的美少年。见过他的少女，无不深深地爱上他。然而，拿斯索斯性格高傲，没有一位女子能得到他的爱，湖，对拿斯索斯来说，是陌生的。拿斯索斯走过去，坐在湖边，正想伸手去摸一摸湖水，试试那是一种怎样的感觉，谁知，当他定睛在平滑如镜的湖面时，看见一张完美的面孔，不禁惊为天人。拿斯索斯心想，这美人是谁呢？真漂亮啊。凝望了一会儿，他发觉，当他向水中的美人挥手，水中的美人也在向他挥手；当他向水中的美人微笑，水中的美人也向他微笑；但当他伸手触摸那美人，那美人便立刻消失了；当他把手缩回来，不一会儿，那美人又再出现，并情深款款地看着他，拿斯索斯当然不知道浮现在湖面的其实就是自己的倒影。他竟然深深地爱上了了自己的倒影。为了不愿失去湖中的人儿，他日夜守护着在湖边。最后，拿斯索斯因为迷恋自己的倒影，枯坐死在湖边。爱神怜惜拿斯索斯，把他化成了水仙花，盛开在有水的地方，让他永远看着自己的倒影。\"            " +
            "    },                                           " +
            "    {                                           " +
            "        \"name\": \"紫藤：\",               " +
            "        \"speak\": \"        有个古老而美丽的传说，有个美丽的女孩，想要一段情缘，于是她每天祈求天上的红衣月老能成全。终于，红衣月老被女孩的虔诚感动了，在她梦中对她说：“在春天到来的时候，在后山的小树林里，她会遇到一个白衣男子，那就是她想要的情缘。”女孩在等待的时候不小心被草丛里的蛇咬伤了脚踝，心里害怕及了。这时，白衣男子出现了。他上前用嘴帮她吸出了脚踝上被蛇咬过的毒血。女孩从此便深深地爱上了他。可白衣男子家境贫寒，他们的婚事遭到了女方父母的反对。最终，两个相亲相爱的人双双跳崖徇情。在他们徇情的悬崖边上长出了一棵树，那树上居然缠着一根藤，并开出朵朵花坠，紫中带蓝，灿若云霞。后人称那藤上开出的花朵为紫藤花，紫藤花需缠树而生，独自不能成活。\"           " +
            "    }                                           " +
            "]                                               " +
            " }";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_flower);
        lv = (ListView) findViewById(R.id.lv);
        jsonTest();
        myPagerAdater = new MyPagerAdater();
        lv.setAdapter(myPagerAdater);
    }

    private void jsonTest() {
        try {
            JSONObject jsonObject = new JSONObject(json);
            String id = jsonObject.getString("_id");
            JSONArray jsonArray = jsonObject.getJSONArray("planners");


            list_string = new ArrayList<>();
            list_string.add(id);


            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject2 = (JSONObject) jsonArray.get(i);
                String coverage = jsonObject2.getString("name");
                String name = jsonObject2.getString("speak");
                list_string.add(coverage);
                list_string.add(name);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public class MyPagerAdater extends BaseAdapter {

        @Override
        public int getCount() {
            return list_string.size();
        }

        @Override
        public Object getItem(int position) {
            return list_string.get(position);

        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.item, null);
                viewHolder.textView = (TextView) convertView.findViewById(R.id.coverage);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.textView.setText(list_string.get(position));
            return convertView;
        }
    }


    final static class ViewHolder {
        TextView textView;
    }
}
