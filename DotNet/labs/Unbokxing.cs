using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
UNBOXIN
G
namespace BoxingUnboxing
{
 internal class Program
 {
 static void Main(string[] args)
 {
 int i = 10;
 Object obj = i;
 int j = (int)obj;//Unboxing the int
 Console.WriteLine(j);
 Console.ReadKey();
 }
 }
}