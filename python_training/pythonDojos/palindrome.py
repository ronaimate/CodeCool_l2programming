def palindrome(value):
    return ([i.lower() for i in value if i.isalpha()] == [i.lower() for i in value if i.isalpha()][::-1])

print(palindrome("Was it a rat I saw"))
