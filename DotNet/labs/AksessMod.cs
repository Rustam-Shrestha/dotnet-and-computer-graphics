using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
namespace StudentDetails
{
    internal class Program
    {
        static void Main(string[] args)
            {
            Student st = new Student();
            st.name = "Ram Shrestha";
            st.address = "Kathmandu";
            st.age = 21;
            st.ShowDetails();
            }
    }
        class Student
            {
            internal string name;
            internal string address;
            internal int age;
            internal void ShowDetails()
            {
            Console.WriteLine("The name of student is=" + name);
            Console.WriteLine("The address of student is=" + address);
            Console.WriteLine("The age of student is=" + age);
            }
            
        }
}