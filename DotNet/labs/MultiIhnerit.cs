using System;

interface IPrintable
{
    void Print();
}

interface IScannable
{
    void Scan();
}

class MultifunctionPrinter : IPrintable, IScannable
{
    public void Print()
    {
        Console.WriteLine("Printing document...");
    }

    public void Scan()
    {
        Console.WriteLine("Scanning document...");
    }
}

class Program
{
    static void Main(string[] args)
    {
        MultifunctionPrinter printer = new MultifunctionPrinter();
        printer.Print();
        printer.Scan();
    }
}
