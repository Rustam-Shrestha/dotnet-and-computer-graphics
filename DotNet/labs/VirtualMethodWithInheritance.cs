using System;
using System.Collections.Generic;

namespace VirtualMethodExample
{
    // Base class
    class Animal
    {
        // Virtual method
        public virtual void MakeSound()
        {
            Console.WriteLine("Animal makes a sound");
        }
    }

    // Derived class
    class Dog : Animal
    {
        // Overriding the virtual method
        public override void MakeSound()
        {
            Console.WriteLine("Dog barks");
        }
    }

    // Another derived class
    class Cat : Animal
    {
        // Overriding the virtual method
        public override void MakeSound()
        {
            Console.WriteLine("Cat meows");
        }
    }

    class Program
    {
        static void Main(string[] args)
        {
            // Creating a list of Animal objects
            List<Animal> animals = new List<Animal>
            {
                new Dog(),
                new Cat(),
                new Dog()
            };

            // Calling the MakeSound method on each Animal object
            foreach (Animal animal in animals)
            {
                animal.MakeSound();
            }
        }
    }
}
