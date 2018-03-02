# Author: Drew Daniel
# COSC 300-001

# Start wiht user input
# decrement by dividing by 3
# iterate until value is less than 10
 
.data

start: .asciiz "Enter starting number: "
final: .asciiz "\nThe final number is: "
count: .asciiz "\nNumber of iterations: "
bye:   .asciiz "\n\nHave a nice day!"

.text
.globl main
main:


#System prompts user to enter number
la $a0, start
li $v0, 4
syscall

#laod user's float
li $v0, 6
syscall

#move foat to $f12 for easier manipulation
mov.s $f12, $f0

# Initialize decrementer, goal, and counter
li.s $f0, 3.0
li.s $f2, 10.0
li $t0, 0

# Begin loop to decrement 
Loop: 
addi $t0, $t0, 1
div.s $f12, $f12, $f0
c.le.s $f2, $f12
bc1t Loop

la $a0, final
li $v0, 4
syscall

li $v0, 2
syscall

la $a0, count
li $v0, 4
syscall

move $a0, $t0
li $v0, 1
syscall

la $a0, bye
li $v0, 4
syscall

li $v0, 10
syscall