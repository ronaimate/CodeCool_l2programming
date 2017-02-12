using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DrivesAndProcesses
{
    class ListFolders
    {
        public void GetFolders()
        {
            string[] directorys = Directory.GetDirectories(@"c:\");
            foreach (var directory in directorys)
            {
                Console.WriteLine(directory);

                //ha csak a mappa neve kell elérés út nélkül.
                Console.WriteLine(directory.Remove(0, @"c:\".Length));

                //vagy ez is a nevet írja ki:
                Console.WriteLine(Path.GetFileName(directory));
            }
        }
    }
}
