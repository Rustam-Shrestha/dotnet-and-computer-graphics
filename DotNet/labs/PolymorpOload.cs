using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
namespace Useinterface
{
 class Addition
 {
 public void Sum(int a, int b)
 {
 int s = a + b;
 Console.WriteLine("The sum of two number is=" + s);
 }
 public void Sum(double a, double b)
 {
 double d = a + b;
 Console.WriteLine("The sum of two double number is=" + d);
 }
 public void Sum(int a, int b, int c)
 {
 int t = a + b + c;
 Console.WriteLine("The sum of three numer is=" + t);
 }
 }
 class Program
 {
 static void Main(string[] args)
 {
 Addition obj = new Addition();
 obj.Sum(20, 10);
 obj.Sum(10.25, 20.23);
 obj.Sum(10, 20, 30);
 Console.ReadKey();
 }
 }
 
}
