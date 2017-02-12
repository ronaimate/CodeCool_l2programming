#encoding: utf-8

def splitter(words):
    result = words.split(" ")
    return result

def translate(value):
    result = []
    dict = {
    "merry":"god",
    "christmas":"jul",
    "and":"och",
    "happy":"gott",
    "new":"nytt",
    "year":"ĺr"
    }
    for i in splitter(value):
        result.append((dict[i]))
    return result

print(translate("merry christmas and happy new year"))