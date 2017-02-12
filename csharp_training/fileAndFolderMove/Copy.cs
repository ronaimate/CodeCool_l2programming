using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace fileMove
{
    class Copy
    {
        private string[] paths;
        private string targetPath;

        public Copy(string[] paths, string targetPath)
        {
            this.paths = paths;
            this.targetPath = targetPath;
        }

        public void PerformCopy()
        {
            foreach (var path in paths)
            {
                if (File.GetAttributes(path) == FileAttributes.Directory)
                {
                    Directory.CreateDirectory(targetPath + @"\" + Path.GetFileName(path));
                    targetPath += @"\" + Path.GetFileName(path);
                    CopyAll(new DirectoryInfo(path), new DirectoryInfo(targetPath));
                }
                else
                {
                    string newFile = targetPath + @"\" + Path.GetFileName(path);
                    File.Copy(@path, newFile);
                }
            }
        }

        private void CopyAll(DirectoryInfo sourceFolder, DirectoryInfo finalFolder)
        {
            foreach (DirectoryInfo actualFolder in sourceFolder.GetDirectories())
                this.CopyAll(actualFolder, finalFolder.CreateSubdirectory(actualFolder.Name));

            foreach (FileInfo actualFile in sourceFolder.GetFiles())
                actualFile.CopyTo(finalFolder.FullName + @"\" + actualFile.Name, true);
        }
    }
}
