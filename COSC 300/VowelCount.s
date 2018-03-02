# Drew Daniel
# COSC 300
# Dr. Ezekiel
# Vowel Count

# MIPS code to count vowels


# ##################################
#
#	Data Section
# 
# ##################################

.data

given: .asciiz "The Given String is = "

str: .asciiz "Imagine glasses that could bring everything into focus, shifting prescription form near to farsignted and back again in Moments"

ans: .asciiz "\n\nNumber of vowels = "

bye: .asciiz "\n\nHave a nice weekend"

# ##################################
#
#	Text Section
#
# ##################################

.text
.globl main
main:

la $a0, given
li $v0, 4
syscall

la $a0, str
li $v0, 4
syscall

la $a0, ans
li $v0, 4
syscall

li $s0, 0
la $v0, str

jal VowelCount

move $a0, $v0
li $v0, 1
syscall

la $a0, bye
li $v0, 4
syscall

li $v0, 10
syscall

VowelCount:

addi $sp, $sp, -16
sw $s0, 0($sp)
sw $s1, 4($sp)
sw $a0, 8($sp)
sw $ra, 12($sp)

li $s0, 0
move $s1, $v0

Loop:

lb $a0, 0($s1)
beqz $a0, End
jal VowelTest
add $s0, $s0, $v0
addi $s1, $s1, 1
j Loop

End:

move $v0, $s0

lw $s0, 0($sp)
lw $s1, 4($sp)
lw $a0, 8($sp)
lw $ra, 12($sp)
addi $sp, $sp, 16                                                                                                                                                                       

jr $ra


VowelTest:

li $v0, 0

beq $a0, 'a', Yes
beq $a0, 'e', Yes
beq $a0, 'i', Yes
beq $a0, 'o', Yes
beq $a0, 'u', Yes
beq $a0, 'A', Yes
beq $a0, 'E', Yes
beq $a0, 'I', Yes
beq $a0, 'O', Yes
beq $a0, 'U', Yes
jr $ra


Yes:
li $v0, 1

jr $ra



