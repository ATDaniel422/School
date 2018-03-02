# Author: Drew Daniel
# COSC 300
# Dr. Ezekiel
# Factorials 

# Write a MIPS program to find the factorial of a number input number


# ##################################
#
#	Data Section
# 
# ##################################

.data

str: .asciiz "This program computes the factorial of a given number"
prompt: .asciiz "\n\nPlease enter a number:  "
ans: .asciiz "\n\nThe factorial of the number given is:  "
bye: .asciiz "\n\nHave a nice day!"


# ##################################
#
#	Text Section
#
# ##################################

.text
.globl main
main:

# $t1 contains the initial number given
# $t2 contains the temporary number
# $t3 contains the total

la $a0, str
li $v0, 4
syscall

la $a0, prompt
li $v0, 4
syscall

li $v0, 5
syscall

beqz $v0, End

move $t1, $v0
addi $t2, $t1, -1
mul $t3, $t1, $t2

Loop:

addi $t2, $t2, -1
beqz $t2, End
mul $t3, $t3, $t2

j Loop

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










