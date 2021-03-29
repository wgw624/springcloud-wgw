package org.weiyada;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void test2(){
        List<String> list = new ArrayList();
        list.add("中国");
        list.add("美国");
        list.add("日本");
        list.add("新加坡");
        list.add("澳大利亚");
        list.stream().filter(x->!x.equals("美国")).anyMatch(x->{
            if(x.equals("日本")){
                System.out.println("相等 "+x);
                return true;
            }
            System.out.println("不等 "+x);
            return false;

        });
    }
}
