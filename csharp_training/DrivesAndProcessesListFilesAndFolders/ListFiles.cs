using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DrivesAndProcesses
{
    class ListFiles
    {
        public void GetFiles()
        {
            string[] files = Directory.GetFiles(@"c:\");
            foreach (var file in files)
            {
                Console.WriteLine(file);
            }
        }
    }
}
