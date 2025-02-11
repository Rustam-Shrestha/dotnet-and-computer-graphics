using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
namespace LinkedlistApp
{
 class Program
 {
 static void Main(string[] args)
 {
 LinkedList<int> ld = new LinkedList<int>();
 ld.AddFirst(90);
 ld.AddAfter(ld.Find(90),80);
 ld.AddBefore(ld.Find(90),60);
 ld.AddLast(55);    
 Console.WriteLine("the total number element in linked list is:{0}", ld.Count);
 Console.WriteLine("print the value of linked list:");
 foreach (int i in ld)
 {
 Console.WriteLine(i);
 }
 ld.Remove(55);
 ld.RemoveFirst();
 ld.RemoveLast();
 Console.WriteLine("print the value of linked list:");
 foreach (int i in ld)
 {
 Console.WriteLine(i);
 }
 Console.ReadKey();
 }
 }
}