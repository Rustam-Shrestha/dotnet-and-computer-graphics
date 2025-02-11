using System;
using System.Collections.Generic;

public class Dict
{
    static void Main(string[] args)
    {
        Dictonary<String, int> ages = new Dictionary<String, int>();
        ages["Santosh"] = 21;
        ages["Rustam"] = 22;
        ages["Lucky"] = 19;
        Console.WriteLine("Lucky's age : " + ages["Lucky"]);
    }
}
