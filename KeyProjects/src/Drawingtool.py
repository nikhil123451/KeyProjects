import turtle
import time

list_selections = ["cookie","christmas tree","ball","spiked ball"]
selection = input("What do you want to draw? (cookie, christmas tree, ball, spiked ball): ")
if not(selection.lower() in list_selections):
    print("Invalid Item")
    time.sleep(2)
    exit()

size = float(input("What size " + str(selection.lower()) + " would you like?: "))

#setup
wn = turtle.Screen()
t = turtle.Turtle()
d = turtle.Turtle()
d.speed(0)
t.speed(0)

#cookie creation
def draw_cookie(radius):
    t.fillcolor("#e8c299")
    ml = radius
    t.pensize(ml/30)
    t.begin_fill()
    t.circle(ml)
    t.end_fill()
    t.pu()
    t.goto(ml/3,ml/1.5)
    t.fillcolor("#4d4339")
    t.begin_fill()
    t.circle(ml/7)
    t.end_fill()
    t.goto(-ml/3,ml/2.69)
    t.begin_fill()
    t.circle(ml/6)
    t.end_fill()
    t.left(90)
    t.forward(ml/1.1)
    t.right(90)
    t.begin_fill()
    t.circle(ml/6)
    t.end_fill()
    t.ht()

#chrimuh twee creation
def draw_christmas_tree(size):
    t.fillcolor("#695336")
    ml = size
    t.pensize(ml/30)
    t.pu()
    t.backward(ml)
    t.right(90)
    t.forward(ml)
    t.left(90)
    t.pd()
    t.begin_fill()
    for i in range(2):
        t.forward(ml)
        t.left(90)
        t.forward(ml*1.5)
        t.left(90)
    t.end_fill()
    d.fillcolor("#2f6136")
    d.pensize(ml/30)
    d.begin_fill()
    d.right(45)
    d.forward(ml)
    d.left(110)
    d.forward(ml)
    d.right(110)
    d.forward(ml/1.3)
    d.left(160)
    d.forward(2*ml)
    d.right(150)
    d.forward(ml*2.31)
    d.left(160)
    d.forward(ml*2)
    d.left(40)
    d.forward(ml*2.5)
    d.left(40)
    d.forward(ml*2.5)
    d.left(50)
    d.forward(ml*1.5)
    d.left(160)
    d.forward(ml)
    d.right(155)
    d.forward(ml*1.3)
    d.left(140)
    d.forward(ml*2)
    d.right(110)
    d.forward(ml)
    d.left(100)
    d.forward(ml/2)
    d.end_fill()
    d.setheading(0)
    d.fillcolor("#ebe417") #yellow
    d.pu()
    d.goto(ml/2,ml) #1
    d.begin_fill()
    d.circle(ml/5)
    d.end_fill()
    d.fillcolor("#002fff") #blue
    d.pu()
    d.goto(-ml,ml) #2
    d.begin_fill()
    d.circle(ml/5)
    d.end_fill()
    d.fillcolor("#babbbf") #grey
    d.pu()
    d.goto(ml/2,ml/10) #3
    d.begin_fill()
    d.circle(ml/5)
    d.end_fill()
    d.fillcolor("#d12828") #red
    d.pu()
    d.goto(-2.5*ml,ml/2) #4
    d.begin_fill()
    d.circle(ml/5)
    d.end_fill()
    d.ht()
    t.ht()

#ball creation
def draw_ball(radius):
    ml = radius
    shades_of_red = ["red", "darkred", "firebrick"]
    t.pensize(ml/30)
    t.fillcolor("red")
    t.begin_fill()
    t.circle(ml)
    t.end_fill()
    ml -= 10
    for i in range(len(shades_of_red)-1):
        t.pencolor(shades_of_red[i+1])
        t.fillcolor(shades_of_red[i+1])
        t.begin_fill()
        t.circle(ml)
        t.end_fill()
        ml -= 10
    t.ht()

#spiked ball creation :((
def draw_spiked_ball(radius):
    ml = radius
    draw_ball(ml)
    t.st()
    t.pencolor("black")
    t.pensize(ml/30)
    t.pu()
    t.goto(-ml/5.34,0)
    t.pd()
    for i in range(20):
        t.right(21)
        t.forward(ml/5)
        t.left(42)
        t.forward(ml/5)
    t.ht()
    
selection = selection.replace(" ", "_")
exec("draw"+"_"+str(selection.lower())+"("+str(size)+")")
d.ht()
t.ht()

wn.exitonclick()
