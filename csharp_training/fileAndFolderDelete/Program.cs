using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace fileDelete
{
    class Program
    {
        static void Main(string[] args)
        {
            string[] files = new string[3];
            files[0] = @"C:\Users\Ronai Mate\Desktop\Gyakorlas1\asd.jnt";
            files[1] = @"C:\Users\Ronai Mate\Desktop\Gyakorlas1\bsd.rtf";
            files[2] = @"C:\Users\Ronai Mate\Desktop\Gyakorlas1\Új mappa";

            FileAndFolderDeleter fafd = new FileAndFolderDeleter(files);
            fafd.Deleter();

        }
    }
}
