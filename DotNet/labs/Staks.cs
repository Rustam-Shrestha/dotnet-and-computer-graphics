using System.Collections.Generic;
using System.Linq;
using System.Text;
namespace StackApp
{
 class Program
 {
 static void Main(string[] args)
 {
 Stack st = new Stack();
 st.Push('I');
 st.Push('P');
 st.Push('U');
 st.Push('H');
 st.Push('B');
 Console.WriteLine("CURRENT STACK:");
 foreach (char c in st)
 {
 Console.Write(c+ " ");
 }
 Console.WriteLine();
 Console.WriteLine("the peak item in stack st:{0}", st.Peek());
 Console.WriteLine("the stack after popes some element:");
 st.Pop();
 st.Pop();
 foreach (char c in st)
 Console.Write( c + " ");
 Console.WriteLine();
 Console.ReadKey();
 }
 }
}