from random import randint
import time
print ("Welcome to the dice roller! ")
selection = 0
while selection !='q':
    selection = input("Please select a number of dice to roll, or type 'q' to quit: ")
    if selection == "q":
        print ("die")
        break
    sides = input("Please enter a number of sides for the die or dice to have: ")
    if sides == "q":
        print ("die")
        break
    i = 0
    total = 0
    time1 = time.monotonic()
    while i < int(selection):
        roll = randint(1,int(sides))
        print(roll)
        total = total + roll
        i = i+1
    if int(selection) > 1:
        print ("Total: ", total)
        print("Time taken: ", time.monotonic()-time1)
print ("Thanks, bye")
