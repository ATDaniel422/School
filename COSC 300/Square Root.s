# Author: Drew Daniel
# Write MIPS code to find square root

.data

prompt: 	.asciiz "Enter number to find square root: "
ans:		.asciiz "\nThe square root is: "
bye: 		.asciiz "\nHave a nice weekend."

one:   		.double 0.0001

.text
.globl main
main:

la $a0, prompt
li $v0, 4
syscall

li $v0, 7
syscall

mov.d $f10, $f0
l.d $f2, one		#Incrimentor
mov.d $f12, $zero

Loop:

add.d $f12, $f12, $f2
mul.d $f6, $f12, $f12
c.eq.d $f6, $f10
bc1t End
j Loop

End:

la $a0, ans
li $v0, 4
syscall

li $v0, 3
syscall

la $a0, bye
li $v0, 4
syscall

li $v0, 10
syscall