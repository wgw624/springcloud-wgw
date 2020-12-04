package org.example;

import static org.junit.Assert.assertTrue;

import com.google.common.collect.Lists;
import com.oracle.tools.packager.Log;
import com.wgw.ElasticSearch;
import com.wgw.dao.EsDao;
import com.wgw.entity.Shop;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticSearch.class)
public class AppTest{
    /**
     * Rigorous Test :-)
     */
    @Autowired
    private EsDao esDao;

    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void test1() {
//       TermQueryBuilder builder =  QueryBuilders.termQuery("name",123);
//       System.out.println(builder.getWriteableName());
//
//       builder.getWriteableName();
        Shop shop = new Shop();
        Shop shop1 = null;
        shop.setShopName("tttt");

//        orelse 和orElseGet

        Shop result = Optional.ofNullable(shop).orElse(createShop());
        Shop result1 = Optional.ofNullable(shop).orElseGet(() -> createShop());

        System.out.println(result.getShopName() + "--->" + result1.getShopName());

//        map 应用
        String shopName = Optional.ofNullable(shop).map(u -> u.getShopName()).orElse("gggg");
        String shopName1 = Optional.ofNullable(shop).map(u -> u.getShopName()).orElseGet(() -> "gggg");
        System.out.println(shopName + "-->" + shopName1);

        Shop shop2 = new Shop();
        shop2.setShopName("1111");
        shop2.setReducePrice(456L);
        shop2.setId("33");

        String str = Optional.ofNullable(shop2).map(Shop::getShopName).orElse("default");
        System.out.println("打印---->" + str);

        System.out.println(LocalDate.now().minusDays(7L) + "--->" + LocalDate.now() + "--->" + LocalDate.now().minusDays(-7L));

    }

    public Shop createShop() {
        Log.info("create new User");
        System.out.println("create new user");
        Shop shop = new Shop();
        shop.setShopName("gggg");
        return shop;
    }

    public void PP(int n) {
        System.out.println("打印：" + n);
    }

    @Test
    public void streamTest() {
        List<Integer> nums = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
//        int s = nums.stream().filter(num -> num != null).distinct().mapToInt(num -> num * 2).peek(this::PP).skip(2).limit(5).sum();
//        System.out.println("result = " + s);

        List<Integer> intList = nums.stream().filter(num->num!=null).collect(Collectors.toList());
        System.out.println(intList);
        ArrayList<Integer> list4 = nums.stream().filter(num->num!=null)
                .collect(()->new ArrayList<Integer>(),(list,item)->list.add(item),(list1,list2)->list1.addAll(list2));
        System.out.println(list4);

        List<Integer> ints = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10,10,10);
        List<Integer>ints1 = Collections.emptyList();
        System.out.println(ints.stream().reduce((sum,item)->sum+item).get());

        System.out.println(ints1.stream().reduce(10,(sum,item)->sum+item));
        ints.stream().max((o1,o2)->o1.compareTo(o2)).ifPresent(System.out::println);
        System.out.println(ints.stream().allMatch(item->item<100));
        AtomicInteger k = new AtomicInteger();
        Map<Integer,Integer> map =ints.stream().map(item->item).collect(Collectors.toMap(Integer::valueOf, v->k.getAndIncrement(),(v1, v2)->v2));
        System.out.println(map);
    }

    @Test
    public void addTest(){
        Shop shop = new Shop("2","河南烩面",16L);
        Shop shop1 = new Shop("3","牛肉板面",18L);
        Shop shop2 = new Shop("4","羊肉汤",19L);

        List<Shop> list = new ArrayList<>();
        list.add(shop);
        list.add(shop1);
        list.add(shop2);
        esDao.saveAll(list);
    }
    @Test
    public void testquery1(){
        Optional<Shop> str = esDao.findById("1");
        Shop shop = str.get();
        System.out.println(shop);
        List<Shop> list= (List<Shop>) esDao.findAll();
        list.forEach(sp->{
            System.out.println(sp.getShopName()+"-->"+sp.getReducePrice());
        });
    }
}