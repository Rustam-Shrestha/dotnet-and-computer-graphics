using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
namespace IncrementOperator
{
 class Counter
 {
 private int value;
 public Counter(int x)
 {
 value = x;
 }
 // Overloading the ++ operator
 public static Counter operator ++(Counter counter)
 {
 counter.value++;
 return counter;
 }
 public void Display()
 {
 Console.WriteLine("Counter value: " + value);
 }
 }
 internal class Program
 {
 static void Main(string[] args)
 {
 Counter obj= new Counter(20);
 Console.WriteLine("Your Entered Number is:");
 obj.Display();
 // Using the overloaded ++ operator
 obj++;
 Console.WriteLine("Counter after incrementing:");
 obj.Display();
 Console.ReadKey();
 }
 }
}
