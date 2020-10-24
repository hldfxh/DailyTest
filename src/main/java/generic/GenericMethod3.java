package generic;

import java.lang.String;

/**
 * Author：Jay On 2019/5/10 16:22
 * <p>
 * Description: 泛型方法
 */
public class GenericMethod3 {

    static class Animal {
        @Override
        public String toString() {
            return "Animal";
        }
    }

    static class Dog extends Animal {
        @Override
        public String toString() {
            return "Dog";
        }
    }

    static class Fruit {
        @Override
        public String toString() {
            return "Fruit";
        }
    }

    static class GenericClass<T> {

        public void show01(T t) {
            System.out.println(t.toString());
        }

        public <T> void show02(T t) {
            System.out.println(t.toString());
        }

        public <K> void show03(K k) {
            System.out.println(k.toString());
        }
    }

    public static void main(String[] args) {
        Animal animal = new Animal();
        Dog dog = new Dog();
        Fruit fruit = new Fruit();
        GenericClass<Animal> genericClass = new GenericClass<>();
        //泛型类在初始化时限制了参数类型
        genericClass.show01(dog); //dog
   //     genericClass.show01(fruit);

        //泛型方法的参数类型在使用时指定
        genericClass.show02(dog); //dog
        genericClass.show02(fruit);//fruit

        //这俩是啥例子，觉得不太好
        genericClass.<Animal>show03(animal);
        genericClass.<Animal>show03(dog);
        genericClass.show03(fruit);
//        genericClass.<Dog>show03(animal);
    }
}