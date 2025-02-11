using System;
namespace Useinterface
{
 class Base
 {
 public Base(int a, int b)
 {
 Console.WriteLine("The value of a={0} and b={1}", a, b);
 }
 }
 class Derived:Base
 {
 public Derived(int x, int y): base(x,y)
 {
 Console.WriteLine("The value of x={0} and y={1}", x, y);
 }
 }
 
 class BaseEx
 {
 
 static void Main(string[] args)
 {
 new Derived(10, 20);
 Console.ReadKey();
 
 }
 }
 
}
