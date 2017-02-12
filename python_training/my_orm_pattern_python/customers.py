from helper import Functions

class Customer:
    def persist(self):
        sql = "INSERT INTO `asd`.`customers` (`CustomerID`, `CompanyName`, `ContactName`, `ContactTitle`, `Address`, `City`, `Region`, `PostalCode`, `Country`, `Phone`, `Fax`) VALUES \
('" + self.customerid + "', '" + self.companyname + "', '" + self.contactname + "', '" + self.contacttitle + "', '" + self.address + "', '" + self.city + "', '" + self.region + "', '" + self.postalcode + "', '" + self.country + "', '" + self.phone + "', '" + self.fax + "');"
        Functions.sql_executer(sql)


    @staticmethod
    def parse(row):
        parsed_row = row.split(";")
        customer = Customer()
        customer.customerid = parsed_row[0]
        customer.companyname = parsed_row[1]
        customer.contactname = parsed_row[2]
        customer.contacttitle = parsed_row[3]
        customer.address = parsed_row[4]
        customer.city = parsed_row[5]
        customer.region = parsed_row[6]
        customer.postalcode = parsed_row[7]
        customer.country = parsed_row[8]
        customer.phone = parsed_row[9]
        customer.fax = parsed_row[10]
        return customer

    def caller(self):
        datas = Functions.data_reader("customers.csv")
        for i in range(1, len(datas)):
            customer = Customer.parse(datas[i])
            customer.persist()


test = Customer()
test.caller()