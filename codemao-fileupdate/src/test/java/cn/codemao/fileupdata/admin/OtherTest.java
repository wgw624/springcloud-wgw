package cn.codemao.fileupdata.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: springcloud-wgw
 * @classname: OtherTest
 * @description: 测试类
 * @author: wgw  weiguangwei@codemao.cn
 * @date: 2020/12/3 6:19 下午
 * @Version 1.0
 **/
public class OtherTest {

    @Test
    public void test1()throws Exception{
        String classPath = ResourceUtils.getURL("classpath:").getPath();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","数学课程配置");
        jsonObject.put("id",1);

        JSONObject lesson = new JSONObject();
        lesson.put("name","课程环节配置");
        lesson.put("des","课程环节");
        JSONArray lessonArr = new JSONArray();
        for(int i=0;i<3;i++){
            JSONObject lessonObj = new JSONObject();
            JSONObject lessonChapter = new JSONObject();
//            lessonChapter.put("lessonChapter","环节-"+i);
            JSONArray jsonArray = new JSONArray();
            JSONObject genieObj = new JSONObject();
            genieObj.put("name","钩子精灵"+i);
            genieObj.put("id",10+i);
            jsonArray.add(genieObj);
            JSONObject genieObj1 = new JSONObject();
            genieObj1.put("name","钩子精灵");
            genieObj1.put("id",11+i);
            jsonArray.add(genieObj1);
            lessonChapter.put("genie",jsonArray);

            JSONArray barJsonArray = new JSONArray();
            JSONObject geniebarObj = new JSONObject();
            geniebarObj.put("name","精灵栏"+i);
            geniebarObj.put("id",100+i);
            barJsonArray.add(geniebarObj);

            JSONObject geniebarObj1 = new JSONObject();
            geniebarObj1.put("name","精灵栏"+i);
            geniebarObj1.put("id",101+i);
            barJsonArray.add(geniebarObj1);
            lessonChapter.put("geniebar",barJsonArray);

            lessonObj.put("lesson-"+i,lessonChapter);
            lessonArr.add(lessonObj);
        }
        lesson.put("data",lessonArr);
        jsonObject.put("data",lesson);

        String filePath = classPath+File.separator+"lesson.json";
        System.out.println(filePath);
        File file = new File(filePath);
        if(file.exists()){
            file.delete();
        }
        file.createNewFile();

        Writer writer = new OutputStreamWriter(new FileOutputStream(file),"utf-8");
        writer.write(jsonObject.toJSONString());
        writer.flush();
        writer.close();


    }
}
