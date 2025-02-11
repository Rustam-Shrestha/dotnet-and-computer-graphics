using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;
namespace Virtual_Method
{
 sealed class Test
 {
 static void Swap<T>(ref T a, ref T b)
 {
 T temp;
 temp = a;
 a = b;
 b = temp;
 }
 class GenericEx
 {
 static void Main(string[] args)
 {
 int a = 5, b = 6;
 Console.WriteLine("The value of a and b before swap is={0},\t{1}", a, b);
 Swap<int>(ref a, ref b);
 Console.WriteLine("The value of a and b after swap is ={0},\t{1}", a, b);
 Console.ReadKey();
 }
 }
 }
}