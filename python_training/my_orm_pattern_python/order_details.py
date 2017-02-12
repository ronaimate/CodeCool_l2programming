from helper import Functions

class Order_Detail:
    def persist(self):
        sql = "INSERT INTO `asd`.`orderdetails` (`OrderID`, `ProductID`, `UnitPrice`, `Quantity`, `Discount`) VALUES \
('" + self.OrderID + "', '" + self.ProductID + "', '" + self.UnitPrice + "', '" + self.Quantity + "', '" + self.Discount + "');"
        Functions.sql_executer(sql)


    @staticmethod
    def parse(row):
        parsed_row = row.split(";")
        order_detail = Order_Detail()
        order_detail.OrderID = parsed_row[0]
        order_detail.ProductID = parsed_row[1]
        order_detail.UnitPrice = parsed_row[2]
        order_detail.Quantity = parsed_row[3]
        order_detail.Discount = parsed_row[4]
        return order_detail

    def caller(self):
        datas = Functions.data_reader("order_datails.csv")
        for i in range(1, len(datas)):
            order_detail = Order_Detail.parse(datas[i])
            order_detail.persist()


test = Order_Detail()
test.caller()