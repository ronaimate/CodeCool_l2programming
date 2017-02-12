using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DrivesAndProcesses
{
    class ListProcesses
    {
        public void GetProcesses()
        {
            var allProcceses = Process.GetProcesses();
            foreach (var process in allProcceses)
            {
                Console.WriteLine(process.ProcessName);
                //itt ProcessName helyett mást is lehet hívni rá.
            }
        }
    }
}
