using System;
namespace Useinterface
{
 public class Account
 {
 public virtual int balance()
 {
 return 10;
 }
 } 
 public class Amount : Account
 {
 public override int balance()
 {
 return 500;
 }
 }
 class Program
 {
 static void Main(string[] args)
 {
 Amount obj = new Amount();
 Console.WriteLine("The Balance is=" + obj.balance());
 Console.ReadKey();
 }
 }
 
}
