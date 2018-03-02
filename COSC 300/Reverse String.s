# Author: Drew Daniel
# COSC 300
# Dr. Ezekiel

# Write MIPS that reverses a string

# t0 -- contains the string
# t1 -- contains the count
# t2 -- contains reversed string

# #################################################
#
#           Data Segment
# 
# #################################################

.data

str: .asciiz "If GM had kept up with technology like the computer industry has, we would all be driving $25 cars that got 1,000 MPG-- Bill Gates"

ans: .asciiz "The Original String is = "
count: .asciiz "\n\nString Count is = "
ans1: .asciiz "\n\nReversed String is = "

array: .space 200

go: .asciiz "\n\nYOU HAVE A NICE WEEKEND, SEE YOU ON TUESDAY \n"

# #################################################
# 
#           Text Segment
# 
# #################################################

.text
.globl main
main:


la $a0, ans
li $v0, 4
syscall

la $a0, str
li $v0, 4
syscall

la $t1, str
li $t3, 0

Loop:
lb $t0, 0($t1)
beqz $t0, StrEnd
addi $t3, $t3, 1
addi $t1, $t1, 1
j Loop

StrEnd:

la $a0, count
li $v0, 4
syscall

move $a0, $t3
li $v0, 1
syscall

la $t4, array
addi $t1, $t1, -1

Loop1:
beqz $t3, end1
lb $t0, 0($t1)
sb $t0, 0($t4)

addi $t3, $t3, -1
addi $t1, $t1, -1
addi $t4, $t4, 1
j Loop1

end1:

la $t1, str
la $t4, array

Loop2:
lb $t0, 0($t1)
beqz $t0, StrEnd1
lb $t5, 0($t4)
sb $t5, 0($t1)
addi $t4, $t4, 1
addi $t1, $t1, 1

j Loop2

StrEnd1:

la $a0, ans1
li $v0, 4
syscall

la $a0, str
li $v0, 4
syscall

la $a0, go
li $v0, 4
syscall

li $v0, 10
syscall

