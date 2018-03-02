#Author: Drew Daniel
#COSC 300

# Start with value equal to 12.2
# decrement by 1 each iteration
# Iterate until less than 1.0

.data

start: 	.asciiz "\nEnter starting number: "
decr:   .asciiz "\nEnter number to decrememnt by: "
final:  .asciiz "\nEnter final number: "
count:  .asciiz "\nNumber of iterations: "
space:  .asciiz "\n"
bye: 	.asciiz "\nHave a nice day"

.text
.globl main
main:

# $t1 - contains counter


#user value to set start number
la $a0, start
li $v0, 4
syscall
li $v0, 6
syscall
mov.s $f13, $f0

#user input for decrememntation number
la $a0, decr
li $v0, 4
syscall
li $v0, 6
syscall
mov.s $f1, $f0

#user input final number
la $a0, final
li $v0, 4
syscall
li $v0, 6
syscall
mov.s $f3, $f0

la $a0, space

li $t1, 0

Loop:

addi $t1, $t1, 1
sub.s $f13, $f13, $f1
c.le.s $f3, $f13
bc1t Loop

la $a0, count
li $v0, 4
syscall

move $a0, $t1
li $v0, 1
syscall

la $a0, bye
li $v0, 4
syscall

li $v0, 10
syscall