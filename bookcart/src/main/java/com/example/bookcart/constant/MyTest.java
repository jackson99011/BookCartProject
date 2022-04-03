package com.example.bookcart.constant;

public class MyTest {
    public static void main(String[] args)
    {
        ProductCategory category = ProductCategory.FOOD;
        String s = category.name();
        System.out.println(s);

        String s2 = "CAR";
        ProductCategory category1 = ProductCategory.valueOf(s2);
    }
}
