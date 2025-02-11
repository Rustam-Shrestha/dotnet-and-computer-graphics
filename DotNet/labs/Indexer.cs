namespace Indexer
{
 internal class Program
 {
 class IndexerClass
 {
 private string[] names = new string[7];
 public string this[int i]
 {
 get
 {
 return names[i];
 }
 set
 {
 names[i] = value;
 }
 }
 }
 static void Main(string[] args)
 {
 IndexerClass week = new IndexerClass();
 week[0] = "SUNDAY";
 week[1] = "MONDAY";
 week[2] = "TUESDAY";
 week[3] = "WEDNESDAY";
 week[4] = "THURSDAY";
 week[5] = "FRIDAY";
 week[6] = "SATURDAY";
 Console.WriteLine("The name of week are:");
 for (int i = 0; i < 7; i++)
 {
 Console.WriteLine(week[i]);
 }
 Console.ReadKey();
 }
 }
}