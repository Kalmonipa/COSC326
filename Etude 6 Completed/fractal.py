from tkinter import *
import numpy as np
import sys

size = 3
numRows = size
numCols = size

def drawSquares(w, numSquares, width, height, xMod, yMod):
    if numSquares > 0:

        width = (width//3)
        height = height//3
        x1 = width//3
        y1 = height//3
        x2 = x1*2
        y2 = y1*2
        
        #Top Left
        w.create_rectangle(xMod+x1, yMod+y1, xMod+x2, yMod+y2, fill="grey")
		#Top Mid
        w.create_rectangle(xMod+width+x1, yMod+y1, xMod+width+x2, yMod+y2, fill="grey")
        #Top Right		
        w.create_rectangle(xMod+width*2+x1, yMod+y1, xMod+width*2+x2, yMod+y2, fill="grey")
		#Mid Left
        w.create_rectangle(xMod+x1, yMod+height+y1, xMod+x2, yMod+height+y2, fill="grey")
		#Mid Right
        w.create_rectangle(xMod+width*2+x1, yMod+height+y1, xMod+width*2+y2, yMod+height+y2, fill="grey")
        #Bot Left
        w.create_rectangle(xMod+x1, yMod+height*2+y1, xMod+x2, yMod+height*2+y2, fill="grey")
        #Bot Mid
        w.create_rectangle(xMod+width+x1, yMod+height*2+y1, xMod+width+x2, yMod+height*2+y2, fill="grey")
        #Bot Right
        w.create_rectangle(xMod+width*2+x1, yMod+height*2+y1, xMod+width*2+x2, yMod+height*2+y2, fill="grey")
		
        drawSquares(w, numSquares-1, width, height, 0, 0)
        drawSquares(w, numSquares-1, width, height, width, 0)
        drawSquares(w, numSquares-1, width, height, 0, height)
        drawSquares(w, numSquares-1, width, height, width*2, 0)
        drawSquares(w, numSquares-1, width, height, width*2, height)
        drawSquares(w, numSquares-1, width, height, 0, height*2)
        drawSquares(w, numSquares-1, width, height, width, height*2)
        drawSquares(w, numSquares-1, width, height, width*2, height*2)
	


numSquares = int(sys.argv[1])

master = Tk()
w = Canvas(master)

width = 600
height = width

w.create_rectangle(width/3, height/3, (width/3)*2, (height/3)*2, fill="grey")

drawSquares(w, numSquares, width, height, 0, 0)

w.configure(background="lightgreen")
w.config(width=width, height=height)

w.pack()

mainloop()
