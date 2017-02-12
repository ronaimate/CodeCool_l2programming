def checker(table, i,j):
    count = 0
    if j != 0:
        if table[i][0][j-1] == "X":
            count += 1
    if j != 9:
        if table[i][0][j+1] == "X":
            count += 1
    if i != 9:
        if table[i+1][0][j] == "X":
            count += 1
    if i != 0:
        if table[i-1][0][j] == "X":
            count += 1
    if i != 0 and j != 9:
        if table[i-1][0][j+1] == "X":
            count += 1
    if i != 0 and j != 0:
        if table[i-1][0][j-1] == "X":
            count += 1
    if i != 9 and j != 0:
        if table[i+1][0][j-1] == "X":
            count += 1
    if i != 9 and j != 9:
        if table[i+1][0][j+1] == "X":
            count += 1
    return str(count)




def minesweeper(table):
    string = ""
    list = []
    for i in range(len(table)):
        string = ""
        for j in range(len(table[i][0])):
            if table[i][0][j] == "X":
                string += "X"
            if table[i][0][j] != "X":
                string += checker(table, i, j)
        list.append(string)
    return list


table = [      ['X.........'],
               ['......X...'],
               ['..........'],
               ['...X......'],
               ['..........'],
               ['XX........'],
               ['.......X..'],
               ['....X.....'],
               ['..X.......'],
               ['...X.....X']]

print(minesweeper(table))

'''
Minesweeper is a really popular game. The rule is there is a 10x10 table and there are 10 mines and we have to find out where the mines are. We have a mine detector which can detect if there are one or more mines in some of the neighbour fields. Your goal is to write a function which takes a list of lists (matrix) as a parameter which includes only dots and Xs and returns another matrix where the neighbours of Xs are numbers and each of them means how many Xs are in the neibours (except it is zero). Dots represent empty fields and Xs represent mines.
E.g.
detect([ ['X.........'],
               ['......X...'],
               ['..........'],
               ['...X......'],
               ['..........'],
               ['XX........'],
               ['.......X..'],
               ['....X.....'],
               ['..X.......'],
               ['...X.....X'] ])
return:
[ ['X1...111..'],
  ['11...1X1..'],
  ['..111111..'],
  ['..1X1.....'],
  ['22211.....'],
  ['XX1...111.'],
  ['2211111X1.'],
  ['.112X1111.'],
  ['.1X321..11'],
  ['.12X1...1X'] ])
'''