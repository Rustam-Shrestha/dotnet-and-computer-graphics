using System;

namespace MyApplication
{
    class Student
    {
        public string name = "Sheeran"; // Public field
        public void Print() // Public method
        {
            Console.WriteLine("Hello from Student class");
        }
    }

    class Program
    {
        static void Main(string[] args)
        {
            Student student1 = new Student();
            Console.WriteLine("Name: " + student1.name);
            student1.Print();
            Console.ReadLine();
        }
    }
}
