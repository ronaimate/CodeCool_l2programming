__author__ = 'Ronai Mate'

def max_in_list(value):
    large = value[0]
    for i in value:
        if large < i:
            large = i
    return large


print(max_in_list([1,2,3,4,5,4,3,2,7,5,0]))

'''
Write a function max_in_list() that takes a list of numbers and returns the largest one.
'''