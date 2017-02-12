using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Threading.Tasks;

namespace serialIzeAndDeserialize
{
    [Serializable]
    public class Person //:ISerializable csak azt szerializalja ki amit megadunk nek addvalue / getvalueval
    //:IDeserializationCallback ez deszerializálásnál futási időbe ha kész azt csinálja amit bele írunk h csinálja
    {
        [NonSerialized]
        private string name;
        private int age;
        private string gender;

        public Person(string name, int age, string gender)
        {
            this.name = name;
            this.age = age;
            this.gender = gender;
        }

        public string Name
        {
            get { return name; }
            set { name = value; }
        }

        public int Age
        {
            get { return age; }
            set { age = value; }
        }

        public string Gender
        {
            get { return gender; }
            set { gender = value; }
        }

        public Person()
        {
            
        }

        [OnSerialized]
        public void Metodus(StreamingContext context)
        {
            Console.WriteLine("after ser");
        }

        [OnSerializing]
        public void Metodus2(StreamingContext context)
        {
            Console.WriteLine("before ser");
        }

        [OnDeserialized]
        public void Metodus3(StreamingContext context)
        {
            Console.WriteLine("after deser");
        }

        [OnDeserializing]
        public void Metodu4(StreamingContext context)
        {
            Console.WriteLine("before deser");
        }

        //public Person(SerializationInfo info, StreamingContext context)
        //{
        //    name = (string)info.GetValue("nev", typeof(string));
        //    age = (int)info.GetValue("ev", typeof(int));
        //}

        public override string ToString()
        {
            return name + age + gender;
        }

        //public void GetObjectData(SerializationInfo info, StreamingContext context)
        //{
        //    info.AddValue("nev", name);
        //    info.AddValue("ev", age);
        //}
    }
}
