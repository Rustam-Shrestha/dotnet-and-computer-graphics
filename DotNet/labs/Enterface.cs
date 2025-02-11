
/*
 
 It is used to achieve multiple inheritance which can't be achieved by class. 
  The class or struct which implements the interface, must provide the implementation of all the 
methods declared inside the interface. 
 Interface cannot include private, protected, or internal members. All the 
members are public by default. 
 Interface cannot contain fields, and auto-implemented properties. 
 Use public modifier when implementing interface implicitly, 
whereas don't use it in case of explicit implementation. 
 Implement interface explicitly using InterfaceName.MemberName. 
 
 
 An interface can inherit one or more interfaces. 
*/

using System;

namespace InterfaceExample
{
    // Base interface
    interface IAnimal
    {
        void Eat();
    }

    // Derived interface
    interface IDog : IAnimal
    {
        void Bark();
    }

    // Class implementing the derived interface
    class Labrador : IDog
    {
        public void Eat()
        {
            Console.WriteLine("Eating...");
        }

        public void Bark()
        {
            Console.WriteLine("Barking...");
        }
    }

    class Program
    {
        static void Main(string[] args)
        {
            Labrador myDog = new Labrador();
            myDog.Eat();
            myDog.Bark();
        }
    }
}
