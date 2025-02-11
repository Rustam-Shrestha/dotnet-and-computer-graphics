using System;
namespace Sturcture
{
 struct book
 {
 public string book_title;
 public string author;
 public int page;
 public float price;
 };
 internal class Program
 {
 public class TestStructure
 {
 
 static void Main(string[] args )
 {
 book b1;
 b1.book_title = "Dot Net Technology";
 b1.author = "Krishna Prasad Acharya";
 b1.page = 200;
 b1.price = 425;
 Console.WriteLine("Name of book:{0}", b1.book_title);
 Console.WriteLine("Author Name:{0}", b1.author);
 Console.WriteLine("Total Page Number:{0}", b1.page);
 Console.WriteLine("Price of book:{0}", b1.price);
 Console.ReadKey();
 }
 }
 }
}