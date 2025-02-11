using Indexer;
using System;
namespace Indexer
{
    internal class Program
    {
        static class Person
        {
            public static int id;
            public static double price;
            public static void Display()
            {
                price += price * 0.13;
                Console.WriteLine("Your ID={0}\t and Name={1}", id, price);
            }
        }
        class TestProgram
        {
            static void Main(string[] args)
            {
                Person.id = 001;
                Person.price = 80;
                Person.Display();
            }
        }
    }
}
