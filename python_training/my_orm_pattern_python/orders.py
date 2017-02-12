from helper import Functions

class Order:
    def persist(self):
        sql = "INSERT INTO `asd`.`orders` VALUES \
('" + self.OrderID + "', '" + self.CustomerID + "', '" + self.EmployeeID + "', '" + self.OrderDate + "', '" + self.RequiredDate\
              + "', '" + self.ShippedDate + "', '" + self.ShipVia + "', '" + self.Freight + "', '" + self.ShipName + "', '" \
              + self.ShipAddress + "', '" + self.ShipCity +  "', '" + self.ShipRegion +  "', '" + self.ShipPostalCode\
              + "', '" + self.ShipCountry + "');"

        print(sql)
        Functions.sql_executer(sql)


    @staticmethod
    def parse(row):
        parsed_row = row.split(";")
        order = Order()
        order.OrderID = parsed_row[0]
        order.CustomerID = parsed_row[1]
        order.EmployeeID = parsed_row[2]
        order.OrderDate = parsed_row[3]
        order.RequiredDate = parsed_row[4]
        order.ShippedDate = parsed_row[5]
        order.ShipVia = parsed_row[6]
        order.Freight = parsed_row[7]
        order.ShipName = parsed_row[8]
        order.ShipAddress = parsed_row[9]
        order.ShipCity  = parsed_row[10]
        order.ShipRegion = parsed_row[11]
        order.ShipPostalCode = parsed_row[12]
        order.ShipCountry = parsed_row[13]
        return order


    def caller(self):
        datas = Functions.data_reader("orders.csv")
        for i in range(1, len(datas)):
            order = Order.parse(datas[i])
            order.persist()


test = Order()
test.caller()