using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DrivesAndProcesses
{
    class Program
    {
        static void Main(string[] args)
        {
            ListDrives ld = new ListDrives();
            //ld.GetDrives();

            ListProcesses lp = new ListProcesses();
            //lp.GetProcesses();

            ListFolders lf = new ListFolders();
            //lf.GetFolders();

            ListFiles lFiles = new ListFiles();
            lFiles.GetFiles();

            Console.ReadKey();
        }
    }
}
