using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SteamReaderAndStreamWriter
{
    class Program
    {
        static void Main(string[] args)
        {
            //fileba írás
            using (StreamWriter sw = new StreamWriter("valami.txt"))
            {
                sw.WriteLine("hello");
                sw.WriteLine("masodik sor");
            }


            //fileból olvasás soronként
            int counter = 0;
            string line;

            StreamReader file = new StreamReader("valami.txt");
            while ((line = file.ReadLine()) != null)
            {
                Console.WriteLine(line);
                counter++;
            }
            file.Close();


            //vagy van egy ilyen beolvasás is de itt már csak akkor tudjuk feldolgozni az adatokat ha beolvastuk..
            string[] lines = File.ReadAllLines("valami.txt");
            Console.WriteLine(lines.Length);


            Console.ReadKey();
        }
    }
}
