from helper import Functions
import config
class OrmTest():
    dict = {}
    sql = None
    Tablename = config.Tablename
    Filename = config.Filename
    Dbname = config.DBname
    def persist(self):
        print(self.sql)
        Functions.sql_executer(self.sql)


    @staticmethod
    def parse(keys, value):
        ormtest = OrmTest()
        parsed_keys = keys.split(";")
        parsed_value = value.split(";")
        for i in range(len(parsed_keys)):
            ormtest.dict[parsed_keys[i]] = parsed_value[i]
        sql = "INSERT INTO `" + ormtest.Dbname + "`.`" + ormtest.Tablename + "` ("
        result_sql = ""
        if len(ormtest.dict) != 0:
            for key, value in ormtest.dict.items():
                sql += "`" + key + "`, "
            sql = sql[:-2] + ") VALUES ("
            for key, value in ormtest.dict.items():
                sql += "'" + value + "', "
        sql = sql[:-2] +");"
        for i in range(len(sql)):
            if sql[i] != "\n":
                result_sql += sql[i]
        ormtest.sql = result_sql
        return ormtest


    def caller(self):
        datas = Functions.data_reader((self.Filename))
        for i in range(1, len(datas)):
            order = OrmTest.parse(datas[0], datas[i])
            order.persist()


test = OrmTest()
test.caller()