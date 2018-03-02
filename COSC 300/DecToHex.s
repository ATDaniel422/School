# Author: Drew Daniel
# COSC 300
# Dr. Ezekiel
# Decimal to Hexadecimal

# Write a MIPS code that converts decimal to hexadecimal

# ##################################
#
#	Data Section
# 
# ##################################

.data

result: .space 8
str: .asciiz "\nEnter Decimal number you want to convert:  "
ans: .asciiz "\n\nGiven Decimal number is:  "
ans1: .asciiz "\n\nHexadecimal for given decimal number is: "
bye: .asciiz "\n\nHave a nice weekend!"


# ##################################
#
#	Text Section
#
# ##################################

.text
.globl main
main:

la $a0, str
li $v0, 4
syscall

li $v0, 5
syscall

move $t0, $v0

la $a0, ans
li $v0, 4
syscall

move $a0, $t0
li $v0, 1
syscall

li $t1, 8
la $s1, result

Loop:

beqz $t1, StrEnd
rol $t0, $t0, 4
and $t3, $t0, 0xf
ble $t3, 9, Sum
addi $t3, $t3, 55

j End

Sum:

addi $t3, $t3, 48

End:

sb $t3, 0($s1)
addi $s1, $s1, 1
addi $t1, $t1, -1

j Loop

StrEnd:

la $a0, ans1
li $v0, 4
syscall

la $a0, result
li $v0, 4
syscall

la $a0, bye
li $v0, 4
syscall

li $v0, 10
syscall









