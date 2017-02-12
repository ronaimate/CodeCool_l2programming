using System;
using System.Collections.Generic;
using System.Data;
using System.IO;
using System.Linq;
using System.Runtime.Serialization.Formatters.Binary;
using System.Runtime.Serialization.Formatters.Soap;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Serialization;
using Microsoft.SqlServer.Server;

namespace serialIzeAndDeserialize
{
    class Program
    {
        public void XMLSer(Person person, string path)
        {
            FileStream fs = new FileStream(path, FileMode.OpenOrCreate, FileAccess.ReadWrite);
            XmlSerializer ser = new XmlSerializer(typeof(Person));
            ser.Serialize(fs, person);
            fs.Close();
        }

        public void XMLDeSer(string path)
        {
            FileStream fs = new FileStream(path, FileMode.OpenOrCreate, FileAccess.ReadWrite);
            XmlSerializer ser = new XmlSerializer(typeof(Person));
            Person p = (Person) ser.Deserialize(fs);
            fs.Close();
            Console.WriteLine(p);
        }

        public void BinarySer(Person person, string path)
        {
            FileStream fs = new FileStream(path, FileMode.OpenOrCreate, FileAccess.ReadWrite);
            BinaryFormatter bf = new BinaryFormatter();
            bf.Serialize(fs, person);
            fs.Close();
        }

        public void BinaryDeSer(string path)
        {
            FileStream fs = new FileStream(path, FileMode.OpenOrCreate, FileAccess.ReadWrite);
            BinaryFormatter bf = new BinaryFormatter();
            Person p = (Person)bf.Deserialize(fs);
            fs.Close();
            Console.WriteLine(p);
        }

        public void SoapSer(Person person, string path)
        {
            FileStream fs = new FileStream(path, FileMode.OpenOrCreate, FileAccess.ReadWrite);
            SoapFormatter sf = new SoapFormatter();
            sf.Serialize(fs, person);
            fs.Close();
        }

        public void SoapDeSer(string path)
        {
            FileStream fs = new FileStream(path, FileMode.OpenOrCreate, FileAccess.ReadWrite);
            SoapFormatter sf = new SoapFormatter();
            Person p = (Person)sf.Deserialize(fs);
            fs.Close();
            Console.WriteLine(p);
        }

        static void Main(string[] args)
        {
            Person person = new Person("Mate", 22, "male");
            Program p = new Program();

            Console.WriteLine("\nxmlSerAndDeser");
            string xmlPath = "xml.xml";
            p.XMLSer(person, xmlPath);
            p.XMLDeSer(xmlPath);

            Console.WriteLine("\nBinarySerAndDeser");
            string binaryPath = "bin.bin";
            p.BinarySer(person, binaryPath);
            p.BinaryDeSer(binaryPath);

            Console.WriteLine("\nSoapSerAndDeser");
            string soapPath = "soap.soap";
            p.SoapSer(person, soapPath);
            p.SoapDeSer(soapPath);

            Console.ReadKey();
        }
    }
}
