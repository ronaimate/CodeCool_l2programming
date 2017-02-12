using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Threads
{
    class MultThreading
    {
        public void Demo()
        {
            Thread first = new Thread(Counting);
            Thread second = new Thread(Counting);
            first.Start();
            second.Start();
            first.Join();
            second.Join();
        }
        void Counting()
        {
            for (int i = 1; i <= 10; i++)
            {
                Console.WriteLine("Count: {0} - Thread: {1}", i, Thread.CurrentThread.ManagedThreadId);
                Thread.Sleep(10);
            }
        }
    }
}
