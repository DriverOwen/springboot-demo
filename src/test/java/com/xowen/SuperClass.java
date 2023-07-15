package com.xowen;

import org.junit.jupiter.api.Test;

class SuperClass {
    private int n;
    SuperClass(){
        System.out.println("SuperClass()");
    }
    SuperClass(int n) {
        System.out.println("SuperClass(int "+n+")");
        this.n = n;
    }
}
// SubClass 类继承
class SubClass extends SuperClass{
    private int n;

    SubClass(){ // 自动调用父类的无参数构造器
        System.out.println("SubClass");
    }

    public SubClass(int n){
        super(300);  // 调用父类中带有参数的构造器
        System.out.println("SubClass(int n):"+n);
        this.n = n;
    }
}
// SubClass2 类继承
class SubClass2 extends SuperClass{
    private int n;

    SubClass2(){
        super(300);  // 调用父类中带有参数的构造器
        System.out.println("SubClass2");
    }

    public SubClass2(int n){ // 自动调用父类的无参数构造器
        System.out.println("SubClass2(int n):"+n);
        this.n = n;
    }
}
