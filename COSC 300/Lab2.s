#Write Hello World in MIPS
#Written by Drew Daniel
#Written on February 2, 2017


.data

str: .asciiz "Hello World \n"
bye: .asciiz "Have a nice weekend"



.text
.globl main
main:



la $a0, str
li $v0, 4
syscall

la $a0, bye
li $v0, 4
syscall

li $v0, 10
syscall