# Write a MIPS code that asks the user for decimal number

# Converts it to ASCII and print the result

.data
prompt: .asciiz "\n Enter the decimal number to convert = "
ans: .asciiz "\n\n ASCII for the given decimal is = "
bye: .asciiz "\n\n\n\n You have a nice weekend" 
result: .space 12

.text
.globl main
main: 

# t0 contains entered number
# t1 contains 10 for division
# t2 contains remainder to be converted
# t3 contains result array
# t4 contains counter



la $a0, prompt
li $v0, 4
syscall

li $v0, 5
syscall

move $t0, $v0

li $t1, 10
li $t4, 0
la $t3, result

la $a0, ans
li $v0, 4
syscall


Loop1:

rem $t2, $t0, $t1
addi $t2, $t2, 48
# sb  $t2, 0($t3)

move $a0, $t2
li $v0, 1
syscall


div $t0, $t0, $t1
beqz $t0, exit1

j Loop1

exit1:

#la $t3, result


#la $a0, ans
#li $v0, 4
#syscall


#Loop2:

#beqz $t4, exit2
#lw $a0, 0($t3)
#la $v0, 4
#syscall
#addi $t3, $t3, 4
#addi $t4, $t4, -1


#j Loop2

#exit2:

la $a0, bye
li $v0, 4
syscall

li $v0, 10
syscall