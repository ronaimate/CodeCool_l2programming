import mysql.connector
import config

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
        cnx = mysql.connector.connect(host=config.host, user=config.user, password="")
        cursor = cnx.cursor()
        try:
            cursor.execute(command)
            cnx.commit()
        except:
            cnx.rollback()
            cnx.close()


