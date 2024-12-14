package org.example.base;

public class DataTypesExample {
    public static void main(String[] args) {
        boolean flag = true;
        char grade = 'A';
        byte age = 25;
        short year = 2023;
        int salary = 50000;
        long distance = 9876543210L;
        float pi = 3.14f;
        double e = 2.718281828459045;

        System.out.println("Boolean: " + flag);
        System.out.println("Char: " + grade);
        System.out.println("Byte: " + age);
        System.out.println("Short: " + year);
        System.out.println("Int: " + salary);
        System.out.println("Long: " + distance);
        System.out.println("Float: " + pi);
        System.out.println("Double: " + e);
    }
}

//1. Kiểu dữ liệu nguyên thủy (Primitive Data Types)
//Boolean:
//boolean: Chỉ có hai giá trị là true và false.
//Character:
//char: Đại diện cho một ký tự đơn lẻ trong bảng mã Unicode. Kích thước là 16 bit.
//        Numeric:
//Integer Types:
//byte: Kích thước 8 bit. Giá trị từ -128 đến 127.
//short: Kích thước 16 bit. Giá trị từ -32,768 đến 32,767.
//int: Kích thước 32 bit. Giá trị từ -2^31 đến 2^31-1.
//long: Kích thước 64 bit. Giá trị từ -2^63 đến 2^63-1.
//Floating-Point Types:
//float: Kích thước 32 bit. Sử dụng để lưu trữ số thực.
//double: Kích thước 64 bit. Sử dụng để lưu trữ số thực có độ chính xác cao hơn.
