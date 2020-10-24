package luban.jdk;

import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

//
//@RunWith(SpringRunner.class)
//@SpringBootTest

public class ArrayListTest {
    //static final Student i = new Student("1",1);

    ArrayList<Integer> al = new ArrayList();
    LinkedList<Integer> ll = new LinkedList();
    void init(int len){
        for(int i=0;i <len;i++){
            al.add(i);
            ll.add(i);
        }
    }
    /**
     * 查询性能对比
     */
    @Test
    public void queryEffect(){
        int j = 0;
        int len = 5000000;
        init(len);
        //下标查找对比
        long date1 = System.currentTimeMillis();
        for(int i=0;i <len;i++){
            int a = al.get(i);
            if(a == 50000) break;
        }
        long date2 = System.currentTimeMillis();
        System.out.println("arrayList下标查找耗时" + (date2-date1));

        for(int i=0;i <len;i++){
            int a = ll.get(i);
            if(a == 50000) break;
        }
        long date3 = System.currentTimeMillis();
        System.out.println("linkedList下标查找耗时" + (date3-date2));


        //增强for循环  底层是迭代器
        //long date3 = System.currentTimeMillis();
        for (int i : al){
            if(i == 500000) j = i;
        }
        long date4 = System.currentTimeMillis();
        System.out.println("arrayList增强for循环耗时" + (date4-date3));

        for (int i : ll){
            if(i == 500000)  j = i;
        }
        long date5 = System.currentTimeMillis();
        System.out.println("linkedList增强for循环耗时" + (date5-date4));


        //iterator
        Iterator alt = al.iterator();
        while(alt.hasNext()){
            if((int)alt.next() == 500000) j = 0;
        }
        long date6 = System.currentTimeMillis();
        System.out.println("arrayList迭代器耗时" +(date6-date5));

        Iterator llt = ll.iterator();
        while(llt.hasNext()){
            if((int)llt.next() == 500000) j = 0;
        }
        long date7 = System.currentTimeMillis();
        System.out.println("linkedList迭代器耗时" + (date7-date6));


        //foreach  底层增强for循环实现
        final int j1 = 0;
        al.forEach(p ->{
            if(p.equals(500000)) System.out.println();;
        });
        long date8 = System.currentTimeMillis();
        System.out.println("arrayList foreach耗时" +(date8-date7));

        ll.forEach(p ->{
            if(p.equals(500000)) System.out.println();
        });
        long date9 = System.currentTimeMillis();
        System.out.println("linkedList foreach耗时" + (date9-date8));
    }

    /**
     * 插入性能对比
     */
    @Test
    public void addEffect(){
        /*init(100000);
        long start3 = System.currentTimeMillis();
        al.add(1,11);//10000000
        long end3 = System.currentTimeMillis();
        System.out.println(end3-start3);

        long start4 = System.currentTimeMillis();
        ll.add(1,11);
        long end4 = System.currentTimeMillis();
        System.out.println(end4-start4);*/



        int length = 10000000;
        List al = new ArrayList(length);
        List ll = new LinkedList();
        long start5 = System.currentTimeMillis();
        for(int i=0;i <length;i++){
            al.add(i);
        }
        long end5 = System.currentTimeMillis();
        System.out.println(end5-start5);

        long start6 = System.currentTimeMillis();
        for(int i=0;i <length;i++){
            ll.add(i);
        }
        long end6 = System.currentTimeMillis();
        System.out.println(end6-start6);
    }

    /**
     * 插入、删除数据
     */
    @Test
    public void addOrDelete(){
        ArrayList<String> list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add(1,"4");
        System.out.println(list.size());

        //list.remove(2);

        list.remove("2");
    }

    /**
     * 深、浅拷贝
     * @throws CloneNotSupportedException
     */
    @Test
    public void cloneTest() throws CloneNotSupportedException {
        Student old = new Student("韩非",33);
        Study stOld = new Study();
        stOld.setMath("11");
        old.setStudy(stOld);

        Student new1 = (Student)old.clone();
        old.getStudy().setMath("22");
        old.setAge(20);

        System.out.println("old math String:"+old.getStudy().getMath());
        System.out.println("new1 math String:"+new1.getStudy().getMath());
        System.out.println("old age int:"+old.getAge());
        System.out.println("new1 age int :"+new1.getAge());
        System.out.println("new1 study :"+new1.getStudy());
        System.out.println("old study:"+old.getStudy());
    }

    /**
     * 在 subList 中，高度注意对原集合元素个数的修改，
     * 会导致子list的遍历、增加、 删除均会产生ConcurrentModificationException 异常。
     */
    @Test
    public void testSubList(){
        ArrayList<String> list = new ArrayList<>();
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        List<String> strings = list.subList(0, 3);
        list.set(2,"6666");
        System.out.println(strings.get(0));
        //fail-fast   错误检测机制
        /*list.add(3,"a");
        System.out.println(strings.get(2));*/
        list.remove(2);
        System.out.println(strings.get(2));
    }

    /**
     * 序列化
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        List<Student> list = new ArrayList<>(4);
        list.add(new Student("1",1));
        list.add(new Student("2",2));
        list.add(new Student("3",3));
        list.add(new Student("4",4));
        list.add(new Student("5",5));

        FileOutputStream fs=new FileOutputStream("D:\\foo");
        ObjectOutputStream os=new ObjectOutputStream(fs);
        os.writeObject(list);
        os.close();
        FileInputStream fi=new FileInputStream("D:\\foo");
        ObjectInputStream oi=new ObjectInputStream(fi);
        List<Student> box=(List<Student>)oi.readObject();
        oi.close();
        System.out.println(box.get(0).getName());

    }

    /**
     * 扩容
     */
    @Test
    public void testCopy() throws UnsupportedEncodingException {
        ArrayList<String> list1 = new ArrayList<>();
        for(int i=0;i<6;i++){
            list1.add(""+i);
        }
        list1.add(3,"aaaaaa");

        List list2 = new ArrayList(list1);
        list1.set(0,"ddd");
        System.out.println(list2.get(0));

        List list3 = (List)((ArrayList)list1).clone();
        list1.set(0,"gggg");
        System.out.println(list3.get(0));
        /*Iterator<String> it = list1.iterator();
        list1.add("bbb");
        list1.add("ccc");
        while(it.hasNext()){
            String str = it.next();
            System.out.println(str);
        }*/
       /*String [] str = new String[]{"a","b","c"};
       List l = Arrays.asList(str);
       l.set(0,"d");
        System.out.println(l);

        List li = Collections.unmodifiableList(l);
        li.set(0,"f");
        System.out.println(li);*/
    }

    /**
     * Arrays.ArrayList和ArrayList
     */

    public <T> void test1(T... obj){

    }
    @Test
    public void testArrays(){
        //test1(new String[]{"1"});
        long[] arr = new long[]{1l,4l,3l,3l};
                //自动拆装箱
        /*int i = 2;
        String[] str  = new String[]{"1","4","3","3"};*/
        List list = Arrays.asList(arr);//基本类型不支持泛型化  数组不支持向下转型
        System.out.println(list.get(0));



        /*String[] arr1 = new String[]{"1","4","3","3"};
        List list1 = Arrays.asList(arr1);
        //list.add(4);
        System.out.println(list1.size());*/





        /*list1.set(0,"11");
        System.out.println(Arrays.toString(arr1));

        List list2 = Arrays.asList(4,3,3,4,5,6);
        System.out.println(list2.size());

        List list3 = new ArrayList(Arrays.asList(4,3,3,4,5,6));
        System.out.println(list3.size());*/
    }

    /**
     * 不可变集合
     */
    @Test
    public void unmodifiable() {
        List list = new ArrayList(Arrays.asList(4,3,3,4,5,6));//缓存不可变配置
        List modilist = Collections.unmodifiableList(list);//只读
        modilist.set(0,1);
        //modilist.add(5,1);
        list.set(0,1);
        System.out.println(modilist.get(0));
    }

    public void testIterator(){

    }

}