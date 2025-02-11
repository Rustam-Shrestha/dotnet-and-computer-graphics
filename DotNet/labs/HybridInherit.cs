using System;

class Vehicle
{
    public void Start()
    {
        Console.WriteLine("Vehicle started");
    }
}

interface IFlyable
{
    void Fly();
}

interface ISailable
{
    void Sail();
}

class AmphibiousAircraft : Vehicle, IFlyable, ISailable
{
    public void Fly()
    {
        Console.WriteLine("Aircraft is flying");
    }

    public void Sail()
    {
        Console.WriteLine("Aircraft is sailing");
    }
}

class Program
{
    static void Main(string[] args)
    {
        AmphibiousAircraft aircraft = new AmphibiousAircraft();
        aircraft.Start();
        aircraft.Fly();
        aircraft.Sail();
    }
}
