using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
namespace Inheritance
{
 internal class Program
 {
 class A
 {
A
B
 public int a = 20, b = 30;
 }
 class B : A
 {
 public void Test()
 {
 Console.WriteLine("The value of a is=" + a);
 Console.WriteLine("The value of b is=" + b);
 }
 }
 class Inherit //derived class
 {
 static void Main(string[] args)
 {
 B obj = new B();
 obj.Test();
 Console.ReadKey();
 }
 }
 }
}