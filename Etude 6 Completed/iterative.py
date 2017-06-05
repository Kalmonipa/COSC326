from tkinter import *
import sys
import math


width = []
height = []
sizes = []

def drawSquares(window, w, h, size):
    #Middle
    window.create_rectangle(w, h, size, size, fill="grey")
    #Top Left
    window.create_rectangle(w + (size / 3), h - (2 * size / 3), size / 3, size / 3, fill="grey")
    #Top Mid
    window.create_rectangle(w - (2 * size / 3), h - (2 * size / 3), size / 3, size / 3, fill="grey")
    #Top Right		
    window.create_rectangle(w + (4 * size / 3), h - (2 * size / 3), size / 3, size / 3, fill="grey")
    #Mid Left
    window.create_rectangle(w - (2 * size / 3), h + (size / 3), size / 3, size / 3, fill="grey")
    #Mid Right
    window.create_rectangle(w + size + (size / 3), h + (size / 3), size / 3, size / 3, fill="grey")
    #Bot Left
    window.create_rectangle(w + (size / 3), h + (4 * size / 3), size / 3, size / 3, fill="grey")
    #Bot Mid
    window.create_rectangle(w - (2 * size / 3), h + (4 * size / 3), size / 3, size / 3, fill="grey")
    #Bot Right
    window.create_rectangle(w + size + (size / 3),h + (4 * size / 3), size / 3, size / 3, fill="grey")


def paint():
    i = 0
    w = (width[i] / 2) - (x / 2)
    h = (height[i] / 2) - (x / 2)
    w.create_rectangle(w, h, x, x)

    while numSquares > 0:
        if(len(width) == 0):
            drawSquares(window, w, h, x)
            update(w, h, x)
        else:
            for i in range(math.pow(9, (numSquares-1))):
                if(update(width[i], height[i], sizes[i]==0)):
                   drawSquares(window, width[i], height[i], sizes[i])

        numSquares = numSquares - 1
    del width[:]
    del height[:]
    del sizes[:]


def update(x, y, size):
    width.add(x + (size / 3))
    height.add(y - (2 * size / 3))
    sizes.add(size/3)

    width.add(x - (2 * size / 3))
    height.add(y - (2 * size / 3))
    sizes.add(size/3)
        
    width.add(x + (4 * size / 3))
    height.add(y - (2 * size / 3))
    sizes.add(size/3)
        
    width.add(x - (2 * size / 3))
    height.add(y + (size / 3))
    sizes.add(size/3)
        
    width.add(x + size + (size / 3))
    height.add(y + (size / 3))
    sizes.add(size/3)
        
    width.add(x + (size / 3))
    height.add(y + (4 * size / 3))
    sizes.add(size/3)
        
    width.add(x - (2 * size / 3))
    height.add(y + (4 * size / 3))
    sizes.add(size/3)
        
    width.add(x + size + (size / 3))
    height.add(y + (4 * size / 3))
    sizes.add(size/3)
       
    d =sizes[len(sizes)-1]
    if(d<243/pow(3,numSquares)):
        return 1



numSquares = int(sys.argv[1])

master = Tk()
window = Canvas(master)

windowWidth = 1000
windowHeight = 1000

#drawSquares(w, numSquares, width, height, 0, 0)
paint()

window.configure(background="lightgreen")
window.config(width=windowWidth, height=windowHeight)

window.pack()

mainloop()
