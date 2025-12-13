import math

while True:
    op = str(input("Operation (add , sub, mul, div, exp, sqrt): "))
    if op != "sqrt":
        a = int(input(""))
        b = int(input(""))
    else:
        a = int(input(""))
    result = 0
    
    if op == "add":
        result = a + b 
    elif op == "sub":
        result = a - b
    elif op == "mul":
        result = a * b
    elif op == "div":
        result = a / b
    elif op == "exp":
        result = a ** b
    elif op == "sqrt":
        result = math.sqrt(a)
    else:
        print("Sorry, don't know what that means, try again")
        continue

    print(result)
