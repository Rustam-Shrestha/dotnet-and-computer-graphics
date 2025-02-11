using System;
namespace Virtual_Method
{
 internal class Complex
 {
 private int x;
 private int y;
 public Complex()
 {
 }
 public Complex(int i, int j)
 {
 x = i;
 y = j;
 }
 public void ShowXY()
 {
 Console.WriteLine("The value of x is={0}and the value of y is={1}", x, 
y);
 
 }
 public static Complex operator + (Complex c1, Complex c2)
 { 
 
 
 Complex temp = new Complex();
 temp.x = c1.x + c2.x;
 temp.y = c1.y+c2.y;
 return temp;
 }
 }
 class Drive
 {
 static void Main(string[] args)
 {
 Complex c1 = new Complex(10, 20);
 c1.ShowXY();
 Complex c2 = new Complex(20,30);
 Complex c3 = c1 + c2;
 c2.ShowXY();
 
 c3.ShowXY();
 }
 }
}