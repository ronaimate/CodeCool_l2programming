using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace fileMove
{
    class Move
    {
        private string[] paths;
        private string targetPath;

        public Move(string[] paths, string targetPath)
        {
            this.paths = paths;
            this.targetPath = targetPath;
        }

        public void PerformMove()
        {
            foreach (var path in paths)
            {
                if (File.GetAttributes(path) == FileAttributes.Directory)
                {
                    Copy copy = new Copy(new string[] { path }, targetPath);
                    copy.PerformCopy();
                    DirectoryInfo di = new DirectoryInfo(path);
                    di.Delete(true);

                }
                else
                {
                    string newFile = targetPath + @"\" + Path.GetFileName(path);
                    File.Move(@path, newFile);
                }
            }
        }
    }
}
