using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
namespace Generic
{
 class Example
 {
 public static void ShowArray<T>(T[] arr)
 {
 for (int i=0;i<arr.Length;i++)
 {
 Console.WriteLine(arr[i]);
 }
 }
 /*
 public static void ShowArray(String[] arr)
 {
 for (int i = 0; i < arr.Length; i++)
 {
 Console.WriteLine(arr[i]);
 }
 }
 */
 }
 internal class Program
 {
 static void Main(string[] args)
 {
 int[] numbers = new int[3];
 numbers[0] = 10;
 numbers[1] = 20;
 numbers[2] = 30;
 String[] name = new string[3];
 name[0] = "Ram";
 name[1] = "Hari";
 name[2] = "Sita";
 Example.ShowArray(numbers);
 Example.ShowArray(name);
 Console.ReadKey();
 }
 }
}