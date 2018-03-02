# Author: Drew Daniel
# COSC 300-001
# Rabbit population growth

.data

prompt: .asciiz "Rabbits take one month to reach maturity, and one month to reproduce. At the end of one year, how many rabbits will there be?"
answer: .asciiz "\n\nNumber of Pairs of Rabbits: "
bye:    .asciiz "\n\nHave a nice day!"

.text
.globl main
main:

# $t0 will hold starting population
# $t1 will hold month-2 population
# $t2 will hold month counter
# $t3 will hold month limit


la $a0, prompt
li $v0, 4
syscall

# Load initial values
li $t0, 1
li $t1, 2
li $t2, 2
li $t3, 12

Loop:
addi $t2, $t2, 1
jal Fib
bne $t2, $t3, Loop


la $a0, answer
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

Fib:

move $s0, $t0
move $s1, $t1
add $s2, $s0, $s1
move $t0, $s1
move $t1, $s2
jr $ra