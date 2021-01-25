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


//        JSONObject lesson = new JSONObject();

        JSONObject lessonObj = new JSONObject();
        lessonObj.put("name","课程环节配置");
        lessonObj.put("des","课程环节");

        JSONArray lessonChapterArr = new JSONArray();
        for(int i=0;i<1;i++){ // 课程环节
            JSONObject lessonChapter = new JSONObject();
            lessonChapter.put("name","课程的第【"+i+"】个环节");
            lessonChapter.put("id",i);
            lessonChapter.put("description","ddddddd");

            JSONArray jsonArray = new JSONArray();
            for(int j=0;j<3;j++){//精灵
                JSONObject genieObj = new JSONObject();
                genieObj.put("name","钩子精灵"+j);
                genieObj.put("id",10+j);
                jsonArray.add(genieObj);
            }

            JSONArray barJsonArray = new JSONArray();
            for(int j=0;j<3;j++){//精灵栏
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("name","精灵栏"+j);
                jsonObj.put("id",100+j);
                barJsonArray.add(jsonObj);
            }

            JSONArray toolJsonArray = new JSONArray();
            for(int j=0;j<3;j++){//精灵栏
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("name","正方形"+j);
                jsonObj.put("id",1000+j);
                toolJsonArray.add(jsonObj);
            }
            JSONArray toolbarJsonArray = new JSONArray();
            for(int j=0;j<3;j++){//精灵栏
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("name","工具栏"+j);
                jsonObj.put("id",10000+j);
                toolbarJsonArray.add(jsonObj);
            }
            JSONObject configJson = new JSONObject();
            configJson.put("genie",jsonArray);
            configJson.put("geniebar",barJsonArray);
            configJson.put("tool",toolJsonArray);
            configJson.put("toolbar",toolbarJsonArray);
            lessonChapter.put("data",configJson);

            lessonChapterArr.add(lessonChapter);
        }
        lessonObj.put("data",lessonChapterArr);

        String filePath = classPath+File.separator+"lesson.json";
        System.out.println(filePath);
        File file = new File(filePath);
        if(file.exists()){
            file.delete();
        }
        file.createNewFile();

        Writer writer = new OutputStreamWriter(new FileOutputStream(file),"utf-8");
        writer.write(lessonObj.toJSONString());
        writer.flush();
        writer.close();


    }
}
