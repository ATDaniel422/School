 # Author: Drew Daniel
 # COSC 300-001
 # Dr. Ezekiel
 
 # MIPS to calculate the area of a circle

.data

Pi: .double 3.1415926535892924
radius: .double 12.3456789012
enter: .asciiz "Enter a radius value: "
result: .asciiz "\n\nThe area of the circle is: "
bye: .asciiz "\n\nHave a nice weekend"


.text
.globl main
main:

l.d $f4, Pi
l.d $f0, radius

la $a0, enter
li $v0, 4
syscall

li $v0, 7			#Value is read into $f0
syscall

mov.d $f2, $f0			#move from $f0 to $f2

la $a0, result
li $v0, 4
syscall

mul.d $f12, $f2, $f2		#multiply radius * radius
mul.d $f12, $f12, $f4		#multiply by Pi

li $v0, 3			#MIPS always prints from $f12, where the result already is
syscall

la $a0, bye
li $v0, 4
syscall

li $v0, 10
syscall