# Author: Drew Daniel
# Write MIPS code to convert temperature using user input of both double and floating point.

.data

prompt: .asciiz "Enter temperature in Celcius: "
anss1:   .asciiz "\nThe original temperature in Celcius single-position is: "
anss2:   .asciiz "\nThe temperature in Fharenheit in single-position is: "
ansd1:   .asciiz "\nThe original temperature in Celcius double-position is: "
ansd2:   .asciiz "\nThe temperature in Fharenheit in double-position is: "
space:   .asciiz "\n\n"
bye:    .asciiz "\n\nHave a nice weekend"

convs:   .float 1.8	#multiplier for conversion
thirtytwos: .float 32.0

convd:  .double 1.8
thirtytwod: .double 32.0

.text
.globl main
main:

l.s $f4, convs

l.s $f6, thirtytwos

la $a0, prompt
li $v0, 4
syscall

li $v0, 6
syscall

mov.s $f12, $f0

la $a0, anss1
li $v0, 4
syscall

li $v0, 2
syscall

mul.s $f12, $f12, $f4

add.s $f12, $f12, $f6

la $a0, anss2
li $v0, 4
syscall

li $v0, 2
syscall

la $a0, space
li $v0, 4
syscall

l.d $f4, convd

l.d $f6, thirtytwod

la $a0, prompt
li $v0, 4
syscall

li $v0, 7
syscall

mov.d $f12, $f0

la $a0, ansd1
li $v0, 4
syscall

li $v0, 3
syscall

mul.d $f12, $f12, $f4

add.d $f12, $f12, $f6

la $a0, ansd2
li $v0, 4
syscall

li $v0, 3
syscall


la $a0, bye
li $v0, 4
syscall

li $v0, 10
syscall