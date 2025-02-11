
 


using System;

namespace AccessModifiersDemo
{
    // Default access modifier for a class is 'internal' if no modifier is specified.
    class Program
    {
        static void Main(string[] args)
        {
            AccessModifiersExample example = new AccessModifiersExample();

            // Accessing public member - Accessible from anywhere
            Console.WriteLine("Public Member: " + example.PublicVariable);

            // Accessing internal member - Accessible within the same assembly
            Console.WriteLine("Internal Member: " + example.InternalVariable);

            // Accessing protected internal member - Accessible within the same assembly or a derived class
            //Console.WriteLine("Protected Internal Member: " + example.ProtectedInternalVariable); // Not accessible from here

            // Accessing private member - Not accessible
            //Console.WriteLine("Private Member: " + example.PrivateVariable); // Uncommenting this line will cause a compile-time error

            // Accessing protected member - Not accessible
            //Console.WriteLine("Protected Member: " + example.ProtectedVariable); // Uncommenting this line will cause a compile-time error

            // Accessing protected member via derived class
            DerivedExample derivedExample = new DerivedExample();
            derivedExample.AccessProtectedMember();
        }
    }

    public class AccessModifiersExample
    {
        // Public member - Accessible from anywhere
        public string PublicVariable = "I am public";

        // Internal member - Accessible within the same assembly
        internal string InternalVariable = "I am internal";

        // Protected member - Accessible within the class and derived classes
        protected string ProtectedVariable = "I am protected";

        // Private member - Accessible only within the class
        private string PrivateVariable = "I am private";

        // Protected internal member - Accessible within the same assembly or derived classes
        protected internal string ProtectedInternalVariable = "I am protected internal";
    }

    // Derived class to demonstrate access to protected members
    public class DerivedExample : AccessModifiersExample
    {
        public void AccessProtectedMember()
        {
            // Accessing protected member from derived class
            Console.WriteLine("Protected Member from Derived Class: " + ProtectedVariable);

            // Accessing protected internal member from derived class
            Console.WriteLine("Protected Internal Member from Derived Class: " + ProtectedInternalVariable);
        }
    }
}
