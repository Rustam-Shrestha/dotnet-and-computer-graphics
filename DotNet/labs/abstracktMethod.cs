using System;
namespace Useinterface
{
 abstract class A
 {
 public abstract int AddData(int a, int b); 
 }
 class B : A
 {
 public override int AddData(int a, int b)
 {
 return a + b;
 }
 }
 class Program
 {
 static void Main(string[] args)
 {
 B obj = new B();
 int res = obj.AddData(30, 20);
 Console.WriteLine("The sum is =" + res);
 
 }
 }
 
}