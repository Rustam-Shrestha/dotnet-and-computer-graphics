using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
namespace Generic 
{
 class Example<T>
 {
 T box;
 public Example(T b)
 {
 this.box = b;
 }
 public T getbox()
 {
 return this.box;
 }
 }
 internal class Program
 {
 static void Main(string[] args)
 {
 Example<int> e = new Example<int>(50);
 Example<string> e1 = new Example<string>("Ram Shrestha");
 Example<char> e2 = new Example<char>('A');
 Console.WriteLine(e.getbox());
 Console.WriteLine(e1.getbox());
 Console.WriteLine(e2.getbox());
 Console.ReadKey();
 }
 }
}