# Author: Drew Daniel
# COSC 300
# Dr. Ezekiel

# Write a MIPS Program to Sum, Sum of Square, and Average all the numbers you enter
# Exit of you enter ZERO

# #####################################
#
#	Data Section
# 
# #####################################

.data

str:    .asciiz "Testing input program. Enter ZERO to exit"
prompt: .asciiz "\n\nPlease input a number =--> "
numEl:  .asciiz "\n\nNumber of elements entered = "
sum1:   .asciiz "\n\nSum of the entered numbers = "
sum2:   .asciiz "\n\nSum of the square of the entered values = "
mean:   .asciiz "\n\nMean of the entered numbers = "

# #####################################
#
#	Text Section
#
# #####################################

.text
.globl main
main:


# $t1 contains count of elements
# $t2 contains sum of elements
# $t3 contains sum of squared elements
# $t4 contains mean of elements.

la $a0, str
li $v0, 4
syscall

Loop:

la $a0, prompt
li $v0, 4
syscall

li $v0, 5
syscall

beqz $v0, StrEnd
addi $t1, $t1, 1
add $t2, $t2, $v0
mul  $t5, $v0, $v0
add $t3, $t3, $t5

j Loop

StrEnd:

div $t4, $t2, $t1

la $a0, numEl
li $v0, 4
syscall

move $a0, $t1
li $v0, 1
syscall

la $a0, sum1
li $v0, 4
syscall

move $a0, $t2
li $v0, 1
syscall

la $a0, sum2
li $v0, 4
syscall

move $a0, $t3
li $v0, 1
syscall

la $a0, mean
li $v0, 4
syscall

move $a0, $t4
li $v0, 1
syscall

li $v0, 10
syscall






