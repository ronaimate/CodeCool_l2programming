from helper import Functions

class Employee:
    def persist(self):
        sql = "INSERT INTO `asd`.`employees` VALUES \
('" + self.EmployeeID + "', '" + self.LastName + "', '" + self.FirstName + "', '" + self.Title + "', '" + self.TitleOfCourtesy\
              + "', '" + self.BirthDate + "', '" + self.HireDate + "', '" + self.Address + "', '" + self.City + "', '" \
              + self.Region + "', '" + self.PostalCode +  "', '" + self.Country +  "', '" + self.HomePhone\
              + "', '" + self.Extension +  "', '" + self.Photo +  "', '" + self.Notes + "', '" \
              + self.ReportsTo +  "', '" + self.PhotoPath +  "', '" + self.Salary + "');"

        print(sql)
        Functions.sql_executer(sql)


    @staticmethod
    def parse(row):
        parsed_row = row.split(";")
        employee = Employee()
        employee.EmployeeID = parsed_row[0]
        employee.LastName = parsed_row[1]
        employee.FirstName = parsed_row[2]
        employee.Title = parsed_row[3]
        employee.TitleOfCourtesy = parsed_row[4]
        employee.BirthDate = parsed_row[5]
        employee.HireDate = parsed_row[6]
        employee.Address = parsed_row[7]
        employee.City = parsed_row[8]
        employee.Region = parsed_row[9]
        employee.PostalCode = parsed_row[10]
        employee.Country  = parsed_row[11]
        employee.HomePhone = parsed_row[12]
        employee.Extension = parsed_row[13]
        employee.Photo = parsed_row[14]
        employee.Notes = parsed_row[15]
        employee.ReportsTo = parsed_row[16]
        employee.PhotoPath = parsed_row[17]
        employee.Salary = parsed_row[18]
        return employee


    def caller(self):
        datas = Functions.data_reader("employees.csv")
        for i in range(1, len(datas)):
            employee = Employee.parse(datas[i])
            employee.persist()


test = Employee()
test.caller()