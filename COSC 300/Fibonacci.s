# Author: Drew Daniel
# COSC 300
# Dr. Ezekiel
# Fibonacci

# Write a MIPS program to find the number in the Fibonacci Sequence at a given place

# ##################################
# 
#	Data Section
#
# ##################################

.data

str: .asciiz "This program finds the number in the Fibonacci Sequence at a given place"
prompt: .asciiz "\n\nEnter a number: "
ans: .asciiz "\n\nThe number in the place given is:  "
bye: .asciiz "\n\nHave a nice day!"


# ##################################
#
#	Text Section
#
# ##################################

.text
.globl main
main:

# $s0 contains the number given
# $t0 contains the count
# $t1 contains the current number minus two
# $t2 contains the current number minus one
# $t3 contains the current number

la $a0, str
li $v0, 4
syscall

la $a0, prompt
li $v0, 4
syscall

li $v0, 5
syscall

move $s0, $v0
beq $s0, 1, End0
beq $s0, 2, End1
beq $s0, 3, End2

li $t0, 2
li $t1, 0
li $t2, 1

Loop:

add $t3, $t1, $t2
move $t1, $t2
move $t2, $t3
addi $t0, $t0, 1
beq  $s0, $t0, End

j Loop

End0:

li $t3, 0
j End

End1:
li $t3, 1
j End

End2:
li $t3, 1
j End

End:

la $a0, ans
li $v0, 4
syscall

move $a0, $t3
li $v0, 1
syscall

la $a0, bye
li $v0, 4
syscall

li $v0, 10
syscall






