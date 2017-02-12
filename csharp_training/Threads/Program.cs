using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Threads
{
    class Program
    {
        static void Main(string[] args)
        {
            MultThreading multThreading = new MultThreading();
            multThreading.Demo();

            ThreadPoolClass threadPoolClass = new ThreadPoolClass();
            threadPoolClass.Demo();

            Console.ReadKey();
        }
    }
}
