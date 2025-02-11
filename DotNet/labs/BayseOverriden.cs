using System;
namespace Useinterface
{
 class Base
 {
 public virtual void BaseMethod()
 {
 Console.WriteLine("I am inside base class");
 }
 }
 class Derived:Base
 {
 public override void BaseMethod()//Overridden
 {
 base.BaseMethod();
 Console.WriteLine("I am inside derived class");
 }
 }
 
 class BaseEx
 {
 static void Main(string[] args)
 {
 Derived obj = new Derived();
 obj.BaseMethod();
 Console.ReadKey();
 }
 }
 
}