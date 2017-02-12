using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DrivesAndProcesses
{
    class ListDrives
    {
        public void GetDrives()
        {
            string[] drives = Directory.GetLogicalDrives();
            foreach (string str in drives)
            {
                Console.WriteLine(str);
            }
        }
    }
}
