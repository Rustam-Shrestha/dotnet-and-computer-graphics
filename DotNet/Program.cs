using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AutoProps
{
    internal class Program
    {
        class IndexClass {
            private string[]names =new string[7];
                public string this[int i]
            {
                get
                {
                    return names[i];    
                }
                set
                {
                    names[i] = value;
                }
            }
        }
        static void Main(string[] args)
        {
            IndexClass myObj= new IndexClass();
            myObj[0] = "SUNDAY";
            myObj[1] = "MONDAY";
            myObj[2] = "TUESDAY";
            myObj[3] = "WEDNESDAY";
            myObj[4] = "THURSDAY";
            myObj[5] = "FRIDAY";
            myObj[6] = "SATURDAY";

            for (int i = 0; i < 7; i++)
                Console.WriteLine(myObj[i]);
            Console.ReadKey();

        }
    }
}
