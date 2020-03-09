print ("Input list with space as the delimiter")
inputString = input() + " "
substring = ""
x = 0
while x < len(inputString):
    while inputString[x] != " ":
        substring = substring + inputString[x]
        x=x+1
    if inputString[x] == " ":
        print(substring)
        substring = ""
    x=x+1