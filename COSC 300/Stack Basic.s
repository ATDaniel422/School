# Author: Drew Daniel
# COSC 300
# Dr. Ezekiel

.data

prompt: .asciiz "Testing My Code"
prompt1: .asciiz "\n\nEnter a number: "
ans: .asciiz "\nThe Number after storing in STACK: "

.text
.globl main
main:

la $a0, prompt
li $v0, 4
syscall

la $a0, prompt1
li $v0, 4
syscall


li $v0, 5   		#will ask to enter a number
syscall

addi, $sp, $sp, -4 	#creates 4 bytes of space in stack memory
sw $v0, 0($sp)		#puts value in $v0 in stack

la $a0, ans
li $v0, 4
syscall

lw, $a0, 0($sp)
li $v0, 1
syscall

addi $sp, $sp, 4	#pushes stack back up

li $v0, 10
syscall