using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Delegates
{
    class Person
    {
        public string Name { get; set; }
        public int Age { get; set; }
        public string Gender { get; set; }
    }

    class Program
    {
        static void Main(string[] args)
        {
            Person jozsi = new Person() { Name = "Józsi", Age = 10, Gender = "male" };
            Person gabi = new Person() { Name = "Gabi", Age = 20, Gender = "male" };
            Person mate = new Person() { Name = "Máté", Age = 22, Gender = "male" };
            Person szodi = new Person() { Name = "Szodi", Age = 5, Gender = "male" };
            Person pakko = new Person() { Name = "Pakkó", Age = 60, Gender = "male" };
            Person atesz = new Person() { Name = "Atesz", Age = 80, Gender = "male" };
            Person pat = new Person() { Name = "Patti", Age = 77, Gender = "female" };
            Person orsi = new Person() { Name = "Orsi", Age = 17, Gender = "female" };
            List<Person> people = new List<Person> { jozsi, gabi, mate, szodi, pakko, atesz, pat, orsi };


            DisplayPeople(people, IsChild);
            DisplayPeople(people, IsAdult);
            DisplayPeople(people, IsRetired);

            Console.ReadKey();
        }

        public delegate bool FilterDelegate(Person person);

        public static void DisplayPeople(List<Person> people, FilterDelegate filter)
        {
            foreach (Person person in people)
            {
                if (filter(person))
                {
                    Console.WriteLine(person.Name);
                }
            }
        }

        public static bool IsChild(Person person)
        {
            return person.Age < 18;
        }

        public static bool IsAdult(Person person)
        {
            return person.Age >= 18;
        }

        public static bool IsRetired(Person person)
        {
            return (person.Age >= 62 && person.Gender == "female") || (person.Age >= 65 && person.Gender == "male");
        }

        public static bool IsWoman(Person person)
        {
            return person.Age >= 18 && person.Gender == "female";
        }

    }
}
