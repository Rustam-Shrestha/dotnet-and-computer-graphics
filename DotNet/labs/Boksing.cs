using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
namespace BoxingUnboxing
{
 internal class Program
BOXIN
G
 {
 static void Main(string[] args)
 {
 int x = 10;
 Object obj = x;//Boxing
 x=20;
 Console.WriteLine(x);
 Console.WriteLine(obj);
 Console.ReadKey();
 }
 }
}
