using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CodingAndEncoding
{
    class Program
    {
        static void Main(string[] args)
        {
            String text = "lálálá";
            Encoding utf8 = Encoding.UTF8;
            Byte[] encodedBytes = utf8.GetBytes(text);
            Byte[] convertedBytes = Encoding.Convert(Encoding.UTF8, Encoding.ASCII, encodedBytes);
            Encoding ascii = System.Text.Encoding.ASCII;
            Console.WriteLine(ascii.GetString(convertedBytes));
            Console.ReadKey();
        }
    }
}
