        // Abstract method (does not have a body)
    // yo vayo abstract class ko normal method 
    // abstract class tyo lai banaune jun actually use ma audaina
    // but program ma tesko role chai hunxa
    // like a calling may be class but not itself esist without
    // a phone middleware

/*
Abstract classes are the way to achieve abstraction in C#.  
 Abstraction in C# is the process to hide the internal details and showing 
functionality only.  
 Abstraction can be achieved by two ways: 
 Abstract class 
 Interface 
 Abstract class and interface both can have abstract methods which are 
necessary for abstraction.
*/

using System;

namespace AbstractClassExample
{
    // Abstract class
    abstract class Animal
    {
        public abstract void MakeSound();

        // Non-abstract method
        public void Sleep()
        {
            Console.WriteLine("Sleeping...");
        }
    }

    // Derived class (inheriting from Animal)
    class Dog : Animal
    {
        // Providing implementation for the abstract method
        public override void MakeSound()
        {
            Console.WriteLine("Bark Bark");
        }
    }

    class Program
    {
        static void Main(string[] args)
        {
            // Creating an object of the derived class
            Dog myDog = new Dog();

            // Calling the abstract method
            myDog.MakeSound();

            // Calling the non-abstract method accessing abstract  class method exending the abstyracgt class
            myDog.Sleep();
        }
    }
}
