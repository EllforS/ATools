package com.ellfors.atools;


import android.os.Bundle;
import android.view.View;

import com.ellfors.extools.base.ExBaseActivity;
import com.ellfors.extools.utils.L;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class TestLoggerActivity extends ExBaseActivity implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_logger);

        initView();
    }

    private void initView()
    {
        setClick(R.id.logger_test_string_v);
        setClick(R.id.logger_test_string_i);
        setClick(R.id.logger_test_string_w);
        setClick(R.id.logger_test_string_d);
        setClick(R.id.logger_test_string_e);
        setClick(R.id.logger_test_json);
        setClick(R.id.logger_test_xml);
        setClick(R.id.logger_test_list);
        setClick(R.id.logger_test_map);
        setClick(R.id.logger_test_set);
        setClick(R.id.logger_exception);
        setClick(R.id.logger_new_tag);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.logger_test_string_v:
                L.v("Test Log.v");
                break;
            case R.id.logger_test_string_i:
                L.i("Test Log.i");
                break;
            case R.id.logger_test_string_w:
                L.w("Test Log.w");
                break;
            case R.id.logger_test_string_d:
                L.d("Test Log.d");
                break;
            case R.id.logger_test_string_e:
                L.e("Test Log.e");
                break;
            case R.id.logger_test_json:
                String json = "{\"list\":[{\"img_url\":\"api/img/1.png\",\"cat_id\":\"20\",\"cat_name\":\"头条\",\"is_default\":\"1\",\"cat_type\":\"0\"},{\"img_url\":\"api/img/2.png\",\"cat_id\":\"24\",\"cat_name\":\"展会\",\"is_default\":\"1\",\"cat_type\":\"0\"},{\"img_url\":\"api/img/3.png\",\"cat_id\":\"23\",\"cat_name\":\"新品\",\"is_default\":\"1\",\"cat_type\":\"0\"},{\"img_url\":\"api/img/4.png\",\"cat_id\":\"37\",\"cat_name\":\"养生\",\"is_default\":\"1\",\"cat_type\":\"0\"},{\"img_url\":\"api/img/5.png\",\"cat_id\":\"27\",\"cat_name\":\"美容\",\"is_default\":\"1\",\"cat_type\":\"0\"},{\"img_url\":\"api/img/6.png\",\"cat_id\":\"43\",\"cat_name\":\"减肥\",\"is_default\":\"1\",\"cat_type\":\"0\"},{\"img_url\":\"api/img/7.png\",\"cat_id\":\"156\",\"cat_name\":\"温泉SPA\",\"is_default\":\"1\",\"cat_type\":\"0\"},{\"img_url\":\"api/img/8.png\",\"cat_id\":\"157\",\"cat_name\":\"休闲水疗\",\"is_default\":\"1\",\"cat_type\":\"0\"},{\"img_url\":\"api/img/9.png\",\"cat_id\":\"29\",\"cat_name\":\"美发\",\"is_default\":\"2\",\"cat_type\":\"2\"},{\"img_url\":\"api/img/10.png\",\"cat_id\":\"30\",\"cat_name\":\"美甲\",\"is_default\":\"2\",\"cat_type\":\"2\"},{\"img_url\":\"api/img/11.png\",\"cat_id\":\"158\",\"cat_name\":\"纹绣\",\"is_default\":\"2\",\"cat_type\":\"2\"},{\"img_url\":\"api/img/12.png\",\"cat_id\":\"26\",\"cat_name\":\"教育\",\"is_default\":\"2\",\"cat_type\":\"1\"},{\"img_url\":\"api/img/13.png\",\"cat_id\":\"52\",\"cat_name\":\"营销\",\"is_default\":\"2\",\"cat_type\":\"1\"},{\"img_url\":\"api/img/14.png\",\"cat_id\":\"159\",\"cat_name\":\"大健康\",\"is_default\":\"2\",\"cat_type\":\"1\"},{\"img_url\":\"api/img/15.png\",\"cat_id\":\"160\",\"cat_name\":\"社会\",\"is_default\":\"2\",\"cat_type\":\"1\"},{\"img_url\":\"api/img/16.png\",\"cat_id\":\"161\",\"cat_name\":\"时尚\",\"is_default\":\"2\",\"cat_type\":\"1\"},{\"img_url\":\"api/img/16.png\",\"cat_id\":\"172\",\"cat_name\":\"测试\",\"is_default\":\"2\",\"cat_type\":\"1\"}]}";
                L.json(json);
                break;
            case R.id.logger_test_xml:
                String xml = "<update>\n" +
                        "<androidversion>10</androidversion>\n" +
                        "<androidcontent>新增消息推送、修改软件的漏洞、更新软件卡机现象等。</androidcontent>\n" +
                        "<ioscontent>新增消息推送、修改软件的漏洞、更新软件卡机现象等。</ioscontent>\n" +
                        "<androidurl>http://www.skytcm.com/mobile2/MeiyeAndroid.apk</androidurl>\n" +
                        "</update>";
                L.xml(xml);
                break;
            case R.id.logger_test_list:
                List<String> list = new ArrayList<>();
                for(int i = 0 ; i < 5 ; i++)
                {
                    list.add("第"+i+"条");
                }
                L.list(list);
                break;
            case R.id.logger_test_map:
                HashMap<String,Integer> map = new HashMap<>();
                for(int i = 0 ; i < 5 ; i++)
                {
                    map.put("My Key",i);
                }
                L.map(map);
                break;
            case R.id.logger_test_set:
                HashSet<Integer> set = new HashSet<>();
                for(int i = 0 ; i < 5 ; i++)
                {
                    set.add(i);
                }
                L.set(set);
                break;
            case R.id.logger_exception:
                List<Integer> ex_list = new ArrayList<>();
                ex_list.add(1);
                ex_list.add(2);
                ex_list.add(3);
                try
                {
                    showToast(ex_list.get(10));
                }
                catch(IndexOutOfBoundsException e)
                {
                    L.exception(e,"My Message");
                }
                break;
            case R.id.logger_new_tag:
                L.t("AAA").d("This is new Tag");
                break;
            default:
                break;
        }
    }

    private void setClick(int id)
    {
        $(id).setOnClickListener(this);
    }

}
