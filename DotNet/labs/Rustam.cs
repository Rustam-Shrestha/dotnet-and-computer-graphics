using System;
using System.Security.Cryptography;

public class Class1
{
	public Class1()
	{
		public string name;
	public int age;
	public string address;
	public string city;
	public void student_details()
	{
		Console.WriteLine("name of the student=" + name);
		Console.WriteLine("age of the student =" +age);
		Console.WriteLine("address of the student =" +address);
	}
	public static void main (string args[])
	{
		Class1 c = new Class1();
		c.student_details();
		c.city = Console.ReadLine();
	}
	}
}
