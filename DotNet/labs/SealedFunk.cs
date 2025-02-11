using System;
namespace Virtual_Method
{
 sealed class Test
 {
 public void message()
 {
 Console.WriteLine("Running from siled class");
 }
 
 }
 class Drive
 {
 static void Main(string[] args)
 {
 Test obj = new Test();
 obj.message();
 }
 }
}
