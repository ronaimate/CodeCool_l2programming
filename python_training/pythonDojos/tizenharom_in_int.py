import re

def tizenharom(number_int):
    elso = 0
    masodik = 0
    number = str(number_int)
    for i in range(len(number)):
        if number[i] == "1":
            elso = i
            break
    for i in range(len(number)):
        if number[i] == "3":
            masodik = i
            break
    if elso < masodik:
        return True
    return False

def tizenharom_2(number):
    return (str(number).find("3",str(number).find("1") > 0)) > 0

def tizenharom_3(number):
    return 0 <= str(number).find("1") < str(number).find("3",str(number).find("1"))

def tizenharom_4(number):
    return len(re.findall(r"1.*3",str(number))) > 0

number = 14333
print(tizenharom(number))
print(tizenharom_2(number))
print(tizenharom_3(number))
print(tizenharom_4(number))