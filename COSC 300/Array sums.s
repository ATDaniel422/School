# Author: Drew Daniel
# Array Sums


# Write MIPS that searches an array of words for the biggest and smallest element
# The directive .word is used to set up an array of 20 4-byte words
# in the data section

# t1 -- contains count of elements
# t2 -- contains min value
# t3 -- contains max value
# t4 -- each word from array in turn

# ############################################
#
#        Data Segment
#
#
# ############################################


.data

anArray: .word 3, 4, 2, 6, 223, 345, 121, 92, 99, 14, 7, 76, 67, 49, 56, 43, 7, 81, 21, 31
count:  .word 20
Thursday: .asciiz "TODAY IS THURSDAY FEB 09, 2017"
result1: .asciiz "\n\n\nThe Minimum value is = "
result2: .asciiz "\n\nThe Maximum value is = "
S1: .asciiz "\n\nThe sum of the values is = "
S2: .asciiz "\n\nThe sum of the squared values is = "
S3: .asciiz "\n\nThe sum of the cubed values is = "
bye: .asciiz "\n\nYou have a nice weekend and I will see you on Tuesday Feb 14 "

# ###########################################
#
#
#	Text Segment
#
# ###########################################

.text
.globl main
main:


la $t0, anArray

li $t1, 0

lw $t2, 0($t0)

lw $t3, 0($t0)

lw $t5, count

Loop:

lw $t4, 0($t0)
beq $t1, $t5, StrEnd
blt $t2, $t4, Move1
bgt $t3, $t4, Move2

Loop1:

addi $t1, $t1, 1
addi $t0, $t0, 4

j Loop

Move1:
addi $t2, $t4, 0
j Loop1

Move2:
addi $t3, $t4, 0
j Loop1


StrEnd:

la $t0, anArray
lw $t1, count
li $s0, 0
li $s1, 0
li, $s2, 0

Loop2:

lw $t4, 0($t0)
add $s0, $s0, $t4
mul $t5, $t4, $t4
add $s1, $s1, $t5
mul $t6, $t5, $t4
add $s2, $s2, $t6

addi $t1, $t1, -1
addi $t0, $t0, 4

bnez $t1, Loop2

# ############################
# Print Statements
# ############################

la $a0, Thursday
li $v0, 4
syscall

la $a0, result1
li $v0, 4
syscall

move $a0, $t2
li $v0, 1
syscall

la $a0, result2
li $v0, 4
syscall

move $a0, $t3
li $v0, 1
syscall

la $a0, S1
li $v0, 4
syscall

move $a0, $s0
li $v0, 1
syscall

la $a0, S2
li $v0, 4
syscall

move $a0, $s1
li $v0, 1
syscall

la $a0, S3
li $v0, 4
syscall

move $a0, $s2
li $v0, 1
syscall

la $a0, bye
li $v0, 4
syscall

# ###############################

# Syscall to exit

li $v0, 10
syscall