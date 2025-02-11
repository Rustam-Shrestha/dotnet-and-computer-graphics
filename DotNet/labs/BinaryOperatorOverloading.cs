using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
namespace BinaryOperatorOverloading
{
 class Test
 {
 int x, y, z;
 public Test() {
 
 }
 public Test(int a, int b, int c)
 {
 x = a;
 y = b;
 z = c;
 }
 public void display()
 {
 Console.WriteLine("The result is=" + x + "" + y + "" + z);
 }
 public static Test operator +(Test obj1, Test obj2)
 {
 Test obj3 = new Test();
 obj3.x = obj1.x + obj2.x;
 obj3.y = obj1.y + obj2.y;
 obj3.z = obj1.z + obj2.z;
 return obj3;
 }
 }
 internal class Program
 {
 
 
 static void Main(string[] args)
 {
 Test onj3 = new Test();
 Test obj1 = new Test(1, 2, 3);
 Test obj2 = new Test(4, 5, 6);
 Test obj3 = new Test();
 obj3 = obj1 + obj2;
 obj3.display();
 Console.ReadKey();
 }
 }
}
