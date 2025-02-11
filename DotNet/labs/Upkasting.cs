using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
namespace Upcasting
{
 class A
 {
 }
 class B:A
 {
 }
 internal class Program
 {
 static void Main(string[] args)
 {
 A obj = new B();//Upcasting
 //B obj1 = new A();//Downcasting is not allowed in C#
 }
 }
}