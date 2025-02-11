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
 public static Complex operator -(Complex c)
 {
 Complex temp = new Complex();
 temp.x = -c.x;
 temp.y = -c.y;
 return temp;
 }
 }
 class Drive
 {
 static void Main(string[] args)
 {
 Complex c1 = new Complex(10, 20);
 c1.ShowXY();
 Complex c2 = new Complex();
 c2.ShowXY();
 c2 = -c1;
 c2.ShowXY();
 }
 }
}