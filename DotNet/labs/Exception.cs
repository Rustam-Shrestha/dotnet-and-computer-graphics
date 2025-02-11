using System;

namespace Exception
{

    class Exception
    {
        public static void Main(string[] args)
        {
            Console.WriteLine("give me numerator");
            int numerator = int.Parse(Console.ReadLine());
            Console.WriteLine("give me denomnator");
            int denominator = int.Parse(Console.ReadLine());
            try
            {
                int result = numerator / denominator;
            }
            catch (DivideByZeroException ex)
            {
                Console.WriteLine("Error: The denominator must be greater or less than 0");
            }
            finally
            {
                Console.WriteLine("we have reached finally block");
            }
        }
    }
}
