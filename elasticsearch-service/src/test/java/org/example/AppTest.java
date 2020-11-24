package org.example;

import static org.junit.Assert.assertTrue;

import com.oracle.tools.packager.Log;
import com.wgw.entity.Shop;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.junit.Test;

import java.util.Optional;

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
    public void test1(){
//       TermQueryBuilder builder =  QueryBuilders.termQuery("name",123);
//       System.out.println(builder.getWriteableName());
//
//       builder.getWriteableName();
        Shop shop = new Shop();
        Shop shop1 = null;
        shop.setShopName("tttt");

//        orelse 和orElseGet

        Shop result = Optional.ofNullable(shop).orElse(createShop());
        Shop result1 = Optional.ofNullable(shop).orElseGet(()->createShop());

        System.out.println(result.getShopName()+"--->"+result1.getShopName());

//        map 应用
        String shopName = Optional.ofNullable(shop).map(u->u.getShopName()).orElse("gggg");
        String shopName1 = Optional.ofNullable(shop).map(u->u.getShopName()).orElseGet(()->"gggg");
        System.out.println(shopName+"-->"+shopName1);

        Shop shop2 = new Shop();
        shop2.setShopName("1111");
        shop2.setReducePrice(456L);
        shop2.setId("33");

        String str = Optional.ofNullable(shop2).map(Shop::getShopName).orElse("default");
        System.out.println("打印---->"+str);

    }
    public Shop createShop(){
        Log.info("create new User");
        System.out.println("create new user");
        Shop shop = new Shop();
        shop.setShopName("gggg");
        return shop;
    }
}
