namespace Virtual_Method
{
 internal class Program
 {
 public virtual void message()
 {
 Console.WriteLine("This is test message");
 }
 static void Main(string[] args)
 {
 Program obj = new Program();
 obj.message();
 Console.ReadKey();
 }
 }
}
