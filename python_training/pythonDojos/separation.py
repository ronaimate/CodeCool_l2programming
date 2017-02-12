vowels = "AEIOUaeiou"
constans = "BCDFGHJKLMNPQRSTVWXYZbcdfghjklmnpqrstvwz"

def char_in_line(line):
    return set(line)

def separate(line):
    ret_vowels = [c for c in vowels if c in char_in_line(line)]
    ret_consonants = [c for c in constans if c in char_in_line(line)]
    result = []
    if len(ret_vowels) != 0:
        result.append(ret_vowels)
    if len(ret_consonants) != 0:
        result.append(ret_consonants)
    return result