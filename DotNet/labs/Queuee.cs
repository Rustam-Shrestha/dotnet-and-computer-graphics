using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
namespace QueueApp
{
 class Program
 {
 static void Main(string[] args)
 {
 Queue q = new Queue();
 q.Enqueue('B');
 q.Enqueue('U');
 q.Enqueue('D');
 q.Enqueue('I');
 Console.WriteLine("content of queue :");
 foreach(char c in q)
 {
 Console.Write(c + " ");
 }
 Console.WriteLine();
 Console.WriteLine("Removing some values:");
 char ch = (char)q.Dequeue();
 Console.WriteLine("the removed value is :{0}", ch);
 Console.WriteLine("after removing q element the content of queue is:");
 foreach (char c in q)
 {
 Console.Write(c + " ");
 }
 Console.WriteLine();
 Console.ReadKey();
 
 }
 }
}
