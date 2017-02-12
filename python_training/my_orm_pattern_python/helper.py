import mysql.connector

class Functions:
    @staticmethod
    def data_reader(filename):
        file_rows = []
        with open(filename, "r", encoding="utf8") as f:
            for line in f:
                file_rows.append(line)
        return file_rows


    @staticmethod
    def sql_executer(command):
        cnx = mysql.connector.connect(host="127.0.0.1", user="root", password="", db="asd")
        cursor = cnx.cursor()
        try:
            cursor.execute(command)
            cnx.commit()
        except:
            cnx.rollback()
            cnx.close()


