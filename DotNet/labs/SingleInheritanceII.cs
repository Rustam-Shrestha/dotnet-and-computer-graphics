using System;
namespace Inheritance
{
 internal class Program
 {
 class Employee
 {
 public int salary = 40000;
 }
 class Programmar : Employee
 {
 public int bonus = 10000;
 public void Display()
 {
 Console.WriteLine("The salary amount is=" + salary);
 Console.WriteLine("The bonus amount is=" + bonus);
 }
 }
 class Inherit
 {
 static void Main(string[] args)
 {
 Programmar obj = new Programmar();
 obj.Display();
 Console.ReadKey();
 }
 }
 }
}