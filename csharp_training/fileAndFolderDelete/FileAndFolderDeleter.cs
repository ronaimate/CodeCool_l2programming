using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace fileDelete
{
    class FileAndFolderDeleter
    {
        private string[] paths;

        public FileAndFolderDeleter(string[] paths)
        {
            this.paths = paths;
        }

        public void Deleter()
        {
            foreach (var path in paths)
            {
                if (File.GetAttributes(path) == FileAttributes.Directory)
                {
                    DirectoryInfo di = new DirectoryInfo(path);
                    di.Delete(true);

                }
                else
                {
                    File.Delete(path);
                }
            }
        }
    }
}
